package utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * @author HuK
 * @version 1.0.0
 * @date 2021/3/2 15:23
 */
public class DocumentUtils {
    public static Document getDocument(File file){
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static Document getDocument(String filePath){
        return  getDocument(new File(filePath));
    }
}

    