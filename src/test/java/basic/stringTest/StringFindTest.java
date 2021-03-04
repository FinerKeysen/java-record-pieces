package basic.stringTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huk
 * @version 1.0.0
 * @date 2020/12/25 15:22
 */
public class StringFindTest {
    private static final Logger logger = LoggerFactory.getLogger(StringFindTest.class);

    public static int testLastIndexOf(String str, String sub) {
        return str.lastIndexOf(sub);
    }

    public static void main(String[] args) {
        String path1 = "/c/HK_DOCUS/Docus/IdeaSpace/ctgtomcat/master-new/webconsole/src/main/fe";
        int f = testLastIndexOf(path1, "/");
        logger.info(String.format("%s", f));
        f = testLastIndexOf(path1, "fe");
        logger.info(String.format("%s", f));
    }
}
