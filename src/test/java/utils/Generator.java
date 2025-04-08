package utils;
import java.util.UUID;

public class Generator {

    private static final String[] EMAIL_DOMAINS = {
        "example.com", "mail.com", "test.com", "dummy.com", "random.com"
    };

    private static final String[] GENDER = { "male", "female" };

    public String generateRandomName() {
        return UUID.randomUUID().toString().replaceAll("[0-9-]", "").substring(0, 8);
    }

    public String generateRandomPassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public String generateRandomEmail() {
        String name = generateRandomName();
        String domain = EMAIL_DOMAINS[(int) (Math.random() * EMAIL_DOMAINS.length)];
        return name + "@" + domain;
    }

    public String generateGender() {
        return GENDER[(int) (Math.random() * GENDER.length)];
    }


}
