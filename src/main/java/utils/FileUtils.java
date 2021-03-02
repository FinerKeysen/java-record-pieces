package utils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author zhujun
 */
public class FileUtils {

    public static boolean isFileNotExist(String filePath) {
        File file = new File(filePath);
        return !file.exists();
    }

    public static boolean isFileExist(String filePath) {
        return !isFileNotExist(filePath);
    }

    public static boolean createDirectory(String dirPath) {
        File file = new File(dirPath);
        return file.mkdirs();
    }

    public static void move(String fromFile,String toFile) throws IOException {
        Path source = Paths.get(fromFile);
        Path target = Paths.get(toFile);
        Files.move(source, target);
    }


    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
           return file.isFile() ? deleteFile(fileName): deleteDirectory(fileName);
        }
        return true;
    }
    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            return file.delete();
        }
        return true;
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir
     *            要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)){
            dir +=  File.separator;
        }
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) ) {
            return true;
        }
        if (!dirFile.isDirectory()){
            return deleteFile(dirFile.getAbsolutePath());
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath())&&flag;
            } else if (files[i].isDirectory()) {
                // 删除子目录
                flag =deleteDirectory(files[i].getAbsolutePath())&&flag;
            }
        }
        // 删除当前目录
        return flag&&dirFile.delete();
    }

    /**
     * 获取下载头部，解决不同浏览器下载乱码问题
     * @param fileName
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getDowloadHeader(String fileName, HttpServletRequest request) throws UnsupportedEncodingException {
        String new_filename = URLEncoder.encode(fileName, "UTF8");
        String rtn = "filename=\"" + new_filename + "\"";
        String userAgent = request.getHeader("User-Agent");
        if (userAgent != null) {
            userAgent = userAgent.toLowerCase();
            if (userAgent.indexOf("msie") != -1) {// IE浏览器，只能采用URLEncoder编码
                rtn = "filename=\"" + new_filename + "\"";
            } else if (userAgent.indexOf("opera") != -1) {// Opera浏览器只能采用filename*
                rtn = "filename*=UTF-8''" + new_filename;
            } else if (userAgent.indexOf("safari") != -1) {// Safari浏览器，只能采用ISO编码的中文输出
                rtn = "filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\"";
            } else if (userAgent.indexOf("mozilla") != -1) {// FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
                rtn = "filename*=UTF-8''" + new_filename;
            }
        }
        return rtn;
    }

    public static boolean isAbsolutePath(String path){

        File file = new File(path);
        return file.isAbsolute();
    }
}
