package basic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HuK
 * @version 1.0.0
 * @date 2021/3/2 19:15
 * @description
 */
public class SwitchGrammarTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        for (int i:list) {
            System.out.println("select " + i + ", begin to choose...");
            switch (i) {
                case 0: {
                    System.out.println("hello");
                    break;
                }
                case 1: {
                    System.out.println("huk");
                }
                case 2: {
                    System.out.println("yuan");
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }
}

    