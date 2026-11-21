import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PasswordValidator {
    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 16;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String password = reader.readLine();

        if (password == null) {
            password = "";
        }

        String result = validate(password);
        System.out.println(result);
    }

    static String validate(String password) {
        boolean validLength = password.length() >= MIN_LENGTH
                && password.length() <= MAX_LENGTH;
        boolean hasUppercase = false;
        boolean hasDigit = false;
        boolean validCharacters = true;

        for (int i = 0; i < password.length(); i++) {
            char symbol = password.charAt(i);

            if (symbol >= 'A' && symbol <= 'Z') {
                hasUppercase = true;
            } else if (symbol >= '0' && symbol <= '9') {
                hasDigit = true;
            } else if (symbol < 'a' || symbol > 'z') {
                validCharacters = false;
            }
        }

        if (validLength && hasUppercase && hasDigit && validCharacters) {
            return "OK";
        }

        StringBuilder errors = new StringBuilder();
        if (!validLength) {
            addError(errors, "LEN");
        }
        if (!hasUppercase) {
            addError(errors, "UPPER");
        }
        if (!hasDigit) {
            addError(errors, "DIGIT");
        }
        if (!validCharacters) {
            addError(errors, "CHAR");
        }
        return errors.toString();
    }

    private static void addError(StringBuilder errors, String code) {
        if (errors.length() > 0) {
            errors.append(System.lineSeparator());
        }
        errors.append(code);
    }
}
