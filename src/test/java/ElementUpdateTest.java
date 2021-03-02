import com.alibaba.fastjson.JSON;
import dto.StudentDTO;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.Impl.XmlServiceImpl;
import utils.DocumentUtils;
import utils.TransUtils;

import java.util.List;

/**
 * @author HuK
 * @version 1.0.0
 * @date 2021/3/2 16:57
 * @description
 */
public class ElementUpdateTest {
    private static final Logger logger = LoggerFactory.getLogger(ElementUpdateTest.class);
    private static final String filePath = "src/main/resources/conf/context.xml";
    private static final XmlServiceImpl xmlService = new XmlServiceImpl();

    public static void main(String[] args) {
        Document document = DocumentUtils.getDocument(filePath);
        List allResources = document.getRootElement().elements("Resource");
        for (Object obj:allResources) {
            Element element = (Element)obj;
            String name = element.attributeValue("name");
            if ("huk".equals(name)) {
                Attribute ageAttr = element.attribute("age");
                ageAttr.setValue("26");
            }
        }
        xmlService.writeXmlFile(document, filePath);
    }
}

    