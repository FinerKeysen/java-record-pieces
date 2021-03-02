package service.Impl;

import exception.CodedException;
import exception.Errors;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;
import service.IXmlService;
import utils.FileUtils;
import utils.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class XmlServiceImpl implements IXmlService {


    protected static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    protected static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

    protected static ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    @Override
    public Boolean writeXmlFile(Document doc, File file) {
        this.writeXmlFile(doc,file,true);
        return true;
    }


    @Override
    public Boolean writeXmlFile(Document doc, File file, boolean trimText) {
        writeLock.lock();
        XMLWriter writer = null;
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        format.setTrimText(trimText);

        try {
            writer = new XMLWriter(new FileOutputStream(file), format);
            writer.write(doc);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if(writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            writeLock.unlock();
        }
        return true;
    }

    @Override
    public Boolean writeXmlFile(Document doc, String filePath) {
        return this.writeXmlFile(doc,new File(filePath));
    }

    @Override
    public Document getDocument(File file){
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    @Override
    public Document getDocument(String filePath){
        return  this.getDocument(new File(filePath));
    }


    @Override
    public <T> T readObjectFromXmlFile(String filePath, Class<T> clazz) {
        if (!new File(filePath).exists()) {
            throw new CodedException(Errors.FILE_NOT_EXIST);
        }
        readLock.lock();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            // 从xml文件中读取，并转化为java对象
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            InputStream inputStream = new FileInputStream(filePath);

            Object unmarshal = unmarshaller.unmarshal(inputStream);
            return clazz.cast(unmarshal);
        } catch (Exception e) {
            throw new CodedException(Errors.CLUSTER_CONFIG_FILE_READ_ERROR, e);
        } finally {
            readLock.unlock();
        }
    }


    @Override
    public <T> void writeObjectToXmlFile(String filePath, T t) {
        writeLock.lock();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            OutputStream outputStream = new FileOutputStream(filePath);
            marshaller.marshal(t, outputStream);
        } catch (Exception e) {
            throw new CodedException(Errors.CLUSTER_CONFIG_FILE_WRITE_ERROR, e);
        } finally {
            writeLock.unlock();
        }
    }


    @Override
    public <T> T readObjectFromXmlFile(String filePath, Class<T> clazz, boolean hasRootElem) {
        if (hasRootElem) {
            return readObjectFromXmlFile(filePath, clazz, null);
        } else {
            return readObjectFromXmlFile(filePath, clazz, "MyRoot");
        }
    }


    /**
     * 外部引入的xml文件的读取接口
     * 按指定的根节点名 rootElemName 读取内容，返回实体类对象
     * @param filePath 文件路径
     * @param <T> 泛型
     * @param clazz 读取对象
     * @param rootElemName 根节点标签名
     */
    public <T> T readObjectFromXmlFile(String filePath, Class<T> clazz, String rootElemName) {
        if (FileUtils.isFileNotExist(filePath)) {
            throw new CodedException(Errors.DBCP_CONFIG_FILE_NOT_FOUND.getCode(), Errors.DBCP_CONFIG_FILE_NOT_FOUND.getMsg() + filePath);
        }
        readLock.lock();
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            // 从xml文件中读取，并转化为java对象
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            FileInputStream in = new FileInputStream(filePath);
            InputStream inputStream = in;
//        in.skip(xmlHeader.length());
            if (rootElemName != null) {
                String tagHead = "<" + rootElemName + ">";
                String tagTail = "</" + rootElemName + ">";
                inputStream = new SequenceInputStream(
                        Collections.enumeration(Arrays.asList(
                                new ByteArrayInputStream(tagHead.getBytes()),
                                in,
                                new ByteArrayInputStream(tagTail.getBytes())))
                );
            }
            Object unmarshal = unmarshaller.unmarshal(inputStream);
            return clazz.cast(unmarshal);
        } catch (Exception e) {
            throw new CodedException(Errors.DBCP_CONFIG_FILE_READ_ERROR, e);
        } finally {
            readLock.unlock();
        }
    }


    @Override
    public <T> void writeObject2XmlFile(String filePath, T t, boolean remainRootElem, String rootElemName) {
        if(remainRootElem) {
            writeObject2XmlFile(filePath, t, null);
        } else {
            if (StringUtils.isNotBlank(rootElemName)) {
                writeObject2XmlFile(filePath, t, rootElemName);
            } else {
                writeObject2XmlFile(filePath, t, "MyRoot");
            }
        }
    }

    /**
     * 将实体类对象转换为xml格式，并写入xml文件，并在保存时去掉指定的根节点标签名 rootElemName
     * @param filePath 文件路径
     * @param t 内容对象
     * @param <T> 泛型
     * @param rootElemName 根节点标签名,非空时去掉;null时保留
     */
    public <T> void writeObject2XmlFile(String filePath, T t, String rootElemName) {
        if (FileUtils.isFileNotExist(filePath)) {
            throw new CodedException(Errors.DBCP_CONFIG_FILE_NOT_FOUND.getCode(), Errors.DBCP_CONFIG_FILE_NOT_FOUND.getMsg() + filePath);
        }
        writeLock.lock();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            File file = new File(filePath);
            marshaller.marshal(t, file);

            if (rootElemName != null){
                removeHeaderAttrAndRootElemName(filePath, true, null, rootElemName);
            }
        } catch (Exception e) {
            throw new CodedException(Errors.DBCP_CONFIG_FILE_WRITE_ERROR, e);
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * 移除文件头或者指定的文件头属性或指定的根节点标签
     * @param xmlFilePath 文件路径
     * @param deleteHeader 是否移除文件头，检测默认标记"<?xml"实现
     * @param removeHeaderAttr 指定的文件头属性字串
     * @param rootElemName 指定的根节点标签名
     */
    public void removeHeaderAttrAndRootElemName(String xmlFilePath, boolean deleteHeader, String removeHeaderAttr, String rootElemName) {
        if (FileUtils.isFileNotExist(xmlFilePath)) {
            throw new CodedException(Errors.DBCP_CONFIG_FILE_NOT_FOUND.getCode(), Errors.DBCP_CONFIG_FILE_NOT_FOUND.getMsg() + xmlFilePath);
        }
        writeLock.lock();
        try {
            // 去掉文件头指定属性、根节点标签
            BufferedReader br = new BufferedReader(new FileReader(xmlFilePath));
            String s = br.readLine();
            StringBuilder swt = new StringBuilder();
            while (s != null) {
                if (deleteHeader && s.contains("<?xml")) {
                    s = br.readLine();
                }
                if (StringUtils.isNotBlank(removeHeaderAttr) && s.contains(removeHeaderAttr)) {
                    s = s.replace(removeHeaderAttr, "");
                }
                if (StringUtils.isNotBlank(rootElemName) && !s.equals("<" + rootElemName + ">") && !s.equals("</" + rootElemName + ">")) {
                    swt.append(s).append("\n");
                }
                s = br.readLine();
            }
            br.close();
            FileWriter fw = new FileWriter(xmlFilePath);
            fw.write(swt.toString());
            fw.flush();
            fw.close();
        } catch (Exception e) {
            throw new CodedException(Errors.DBCP_EXTRA_CONFIG_FILE_WRITE_ERROR, e);
        } finally {
            writeLock.unlock();
        }
    }
}
