package service;

import org.dom4j.Document;
import org.dom4j.Element;

import java.io.File;

public interface IXmlService {

    Boolean writeXmlFile(Document doc, File file);

    Boolean writeXmlFile(Document doc, File file, boolean trimText);

    Boolean writeXmlFile(Document doc, String filePath);

    Document getDocument(File file);

    Document getDocument(String filePath);

    /**
     * 外部引入的xml文件的读取接口
     * 按指定的根节点名 rootElemName 读取内容，返回实体类对象
     * @param filePath 文件路径
     * @param <T> 泛型
     * @param clazz 读取对象
     * @param hasRootElem 是否有根节点
     */
    <T> T readObjectFromXmlFile(String filePath, Class<T> clazz, boolean hasRootElem);

    <T> T readObjectFromXmlFile(String filePath, Class<T> clazz);

    /**
     * 外部引入的xml文件的写入接口
     * 当 remainRootElem 为 true 时，表示保留根节点标签
     * 当 remainRootElem 为 false 时，不保留根节点标签.
     *      且当 rootElemName 非空时，会在保存时去掉指定的根节点标签名 rootElemName
     *      当 rootElemName 空时，会删除默认根节点名 MyRoot
     * @param filePath 文件路径
     * @param <T> 泛型
     * @param t 写入对象
     * @param remainRootElem 是否保留根节点标签
     * @param rootElemName 根节点标签名
     */
    <T> void writeObject2XmlFile(String filePath, T t, boolean remainRootElem, String rootElemName);

    <T> void writeObjectToXmlFile(String filePath, T t);
}
