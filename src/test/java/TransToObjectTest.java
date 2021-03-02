import com.alibaba.fastjson.JSON;
import dto.StudentDTO;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DocumentUtils;
import utils.TransUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author HuK
 * @version 1.0.0
 * @date 2021/3/2 14:54
 * @description
 */
public class TransToObjectTest {

    private static final Logger logger = LoggerFactory.getLogger(TransToObjectTest.class);

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Document document = DocumentUtils.getDocument("src/main/resources/conf/context.xml");
        List allResources = document.getRootElement().elements("Resource");
        for (Object obj:allResources) {
            Element element = (Element)obj;
            StudentDTO studentDTO = new StudentDTO();
            TransUtils.transToDtoObject(studentDTO, element);
            logger.info(JSON.toJSONString(studentDTO));
        }
    }
}

    