package user;

import java.security.MessageDigest;
import java.util.Base64;

public class SecurityUtils{

    public static String hashMe(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);

        } catch (Exception e){
            return password;
        }
    }
}