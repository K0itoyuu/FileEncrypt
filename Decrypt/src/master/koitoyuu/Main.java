package master.koitoyuu;

import master.koitoyuu.utils.DecryptThread;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kotioyuu233~
 */
public class Main {
    public static void main(String[] args) {
        File file = new File(args[0]);
        if (file.exists()) {
            if (file.isDirectory()) {
                List<File> files = new ArrayList<>();
                getAllFile(file,files);
                for (File file1 : files) {
                    new DecryptThread(file1).run();
                }
                System.out.println("[信息] 嗯哼 解密完成呐~");
            } else if (file.isFile()) {
                new DecryptThread(file).run();
                System.out.println("[信息] 嗯哼 解密完成呐~");
            } else {
                System.out.println("[信息] 卧槽 这是歌姬吧!");
            }
        } else {
            System.out.println("[信息] 文件或文件夹不存在呜...");
        }
    }

    private static void getAllFile(File file, List<File> allFileList) {
        File[] fileList = file.listFiles();
        if (fileList != null) {
            for (File file1 : fileList) {
                if (file1.isDirectory()) {
                    getAllFile(file1, allFileList);
                } else {
                    allFileList.add(file1);
                }
            }
        }
    }
}
