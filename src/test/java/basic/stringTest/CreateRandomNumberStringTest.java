package basic.stringTest;

import java.util.Random;

/**
 * @Author huk
 * @Date 2020/11/14 14:55
 * @Version 1.0.0
 */
public class CreateRandomNumberStringTest {
    /**
     * 将num转换为指定长度的字符串，前置空位补0
     *      如length=4,num=1,return 0001
     * @param length 指定长度
     * @param num 指定数字
     * @return 字符串
     */
    public static String getSpecifiedNumString(int length, int num){
        String numStr = String.valueOf(num);
        StringBuilder res = new StringBuilder().append(numStr);
        int numStrLength = numStr.length();
        if (length > numStrLength) {
            for (int i=length-numStrLength; i>0; i--){
                res = new StringBuilder("0").append(res);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        int start = 1600000, end = 3200000;
        Random random = new Random();
        int num = random.nextInt(end - start + 1) + start;
        System.out.println(getSpecifiedNumString(8, num));
    }
}
