package common;

import org.testng.asserts.SoftAssert;
import java.nio.charset.Charset;
import java.util.Random;

public class Support {

    public static void Verify(boolean statementToVerify){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(statementToVerify, "Statement could not be verified.");
    }

    public static String generateRandomString(int length){
        byte[] array = new byte[length];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }

    public static int generateRandomNumber(int ceiling){
        Random random = new Random();
        int numberToReturn = random.nextInt(ceiling);
        if (numberToReturn == 0)
            numberToReturn = numberToReturn + 1;
        return numberToReturn;
    }
}
