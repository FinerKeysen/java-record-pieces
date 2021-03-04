package basic.stringTest;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author huk
 * @Date 2020/10/29 16:21
 * @Version 1.0.0
 */

public class CreateNumberStringTest {
    //字母+数字的基序列和纯数字基序列
    private final static String BASESTRING       = "QWERTYUIOPLKJHGGFDSAMNBBVCXZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final AtomicLong keyIndex          = new AtomicLong(0);
    private static final int MAX_SIZE_NUM = 8;        // 索引的最大位数，如4500w的最大位数为8
    private static final int VALUE_BIT_SIZE = 128;    // 值的最大位数

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

    /**
     * 根据索引起止生成纯写命令文本
     * @param start 起始索引
     * @param end 结束索引
     */
    public static void createRangeText(String mode, int start, int end) throws IOException {
        BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./" + mode + "_command_" + start / 10000 + "w-" + end / 10000 + "w.txt")));
        br.write("select 1\n");
        for (int i = start; i <= end; i++) {
            String key = getSpecifiedNumString(MAX_SIZE_NUM, i);
            switch (mode) {
                case "set": {
                    String value = getRandomValueString(128);
                    br.write("set " + key + " " + value + "\n");
                    break;
                }
                case "get": {
                    for (int j=0; j<20; j++){
                        br.write("get " + key + "\n");
                    }
                    String value = getRandomValueString(128);
                    br.write("set " + key + " " + value + "\n");
                    br.write("del " + key + "\n");
                    break;
                }
                default: {
                    System.out.println("Mode " + mode + " is illegal!!!");
                }
            }
        }
        br.flush();
        br.close();
    }

    /**
     * 生成指定长度的随机key序列
     * @param length 随机key序列的长度
     * @return 随机key序列，key_****
     */
    public static String getRandomKeyString(int length) {
        String StrIndex           = String.valueOf(keyIndex.incrementAndGet());
        StringBuilder randKeyStr   = new StringBuilder(length);
        Random random                 = new Random();
        int number;
        for (int i = 0; i < length-StrIndex.length(); i++) {
            number = random.nextInt(BASESTRING.length());
            randKeyStr.append(BASESTRING.charAt(number));
        }
        randKeyStr.append(StrIndex);
        return randKeyStr.toString();
    }

    public static String getRandomValueString(int length) {
        //StringBuffer randValueStr = new StringBuffer(length);
        return getRandomKeyString(length);
//	    for (int i = 0; i < 32; i++) {
//	    	randValueStr.append(TempStr);
//	    }
//	    return randValueStr.toString();
    }

    public static void setTest(long recordCnt, int length) throws IOException {
        Map<String, String> map = new HashMap<>();
        for (int i=0; i<recordCnt; i++){
            map.put(getRandomKeyString(length), getRandomValueString(length));

        }
        BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./command_"+recordCnt/10000+"w.txt")));
        br.write("select 1\n");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            br.write("set " + key + " " + value + "\n");
        }
        br.flush();
        br.close();
    }

    public static void getTest(long recordCnt, int length) throws IOException {
        Map<String, String> map = new HashMap<>();
        for (int i=0; i<recordCnt; i++){
            map.put(getRandomKeyString(length), getRandomValueString(length));

        }
        BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./command_"+recordCnt/10000+"w.txt")));
        br.write("select 1\n");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            br.write("set " + key + " " + value + "\n");
            for (int i=0; i<10; i++){
                br.write("get " + key + "\n");
            }
        }
        br.flush();
        br.close();
    }

    public static void main(String[] args) throws IOException {
        long currentTime = System.currentTimeMillis();
//        getTest(4000000, 16);
//        setTest(15000000, 16);
        createRangeText("set", 1, 9);
        createRangeText("set", 9, 18);
        createRangeText("set", 18, 27);
        long usedTime = System.currentTimeMillis()-currentTime;
        System.out.println("Costs: " + usedTime/1000.0 + " s.");
        System.out.println(getSpecifiedNumString(5, 12));
    }
}
