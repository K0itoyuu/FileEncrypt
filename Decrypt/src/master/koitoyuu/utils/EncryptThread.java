package master.koitoyuu.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;

/**
 * @author Koitoyuu233~
 */
public class EncryptThread implements Runnable {
    private final File file;
    public EncryptThread(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(file.getPath()));
            bytes = encrypt(bytes);
            //防止隐藏的文件抛出FileNotFoundException
            file.delete();
            file.createNewFile();
            writeBytes(file,bytes);
            System.out.println("[解密成功] " + file.getPath());
        } catch (Exception e) {
            System.out.println("[解密失败] " + file.getPath());
        }
    }

    public void writeBytes(File file,byte[] bytes) throws IOException {
        Files.write(Paths.get(file.getPath()),bytes);
    }

    private SecretKey generateKey(byte[] key) throws Exception {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key);
        KeyGenerator gen = KeyGenerator.getInstance("AES");
        gen.init(128, random);
        return gen.generateKey();
    }

    private byte[] encrypt(byte[] bytes) {
        try {
            String key = "FileEncryptByKoitoyuu233~";
            SecretKey secKey = generateKey(key.getBytes());
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secKey);
            return cipher.doFinal(bytes);
        } catch (Exception e) {
            return bytes;
        }
    }

}
