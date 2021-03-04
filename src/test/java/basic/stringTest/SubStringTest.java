package basic.stringTest;

/**
 * @author HuK
 * @version 1.0.0
 * @date 2021/2/2 9:30
 */
public class SubStringTest {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:derby://localhost:1527/testdb;create=true";
        String infos = jdbcUrl.substring("jdbc:derby://".length());
        String host = infos.substring(0, infos.indexOf(":"));
        String port = infos.substring(infos.indexOf(":")+1, infos.indexOf("/"));
        String dbName = infos.substring(infos.indexOf("/")+1, infos.indexOf(";"));
        String attribute = infos.substring(infos.indexOf(";")+1, infos.indexOf("="));
        System.out.println(infos);
        System.out.println(host);
        System.out.println(port);
        System.out.println(dbName);
        System.out.println(attribute);
    }
}

    