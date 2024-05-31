package Advanced;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class CryptoMD5 {

    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException {

        String file = "src/main/resources/cryptoMD5Test";
        Scanner scan = new Scanner(new File(file));
        String password = scan.next();
        String myHash = generateMD5(password);
        String myHash2 = generateSHA256(password);
        System.out.println(myHash);
        System.out.println(myHash2);
    }

    public static String generateMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toLowerCase();
        return myHash;
    }

    public static String generateSHA256(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toLowerCase();
        return myHash;
    }

}
