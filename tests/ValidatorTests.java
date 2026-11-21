import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ValidatorTests {
    private record Case(String id, String input, String expected, String boundary) {}

    private static final List<Case> CASES = List.of(
            new Case("T01", "Abcdefg1\n", "OK", "8 characters (min)"),
            new Case("T02", "Abcdefghijklmno1\n", "OK", "16 characters (max)"),
            new Case("T03", "Abcdef1\n", "LEN", "7 characters (below min)"),
            new Case("T04", "Abcdefghijklmnop1\n", "LEN", "17 characters (above max)"),
            new Case("T05", "abcdefg1\n", "UPPER", "regular"),
            new Case("T06", "Abcdefgh\n", "DIGIT", "regular"),
            new Case("T07", "Abcdef 1\n", "CHAR", "regular"),
            new Case("T08", "\n", "LEN\nUPPER\nDIGIT", "empty input"),
            new Case("T09", "abc\n", "LEN\nUPPER\nDIGIT", "regular"),
            new Case("T10", "Abcdef#1\n", "CHAR", "regular"),
            new Case("T11", "Abcdefg1\nabc\n", "OK", "regular"),
            new Case("T12", " Abcdefg1 \n", "CHAR", "regular"),
            new Case("T13", "", "LEN\nUPPER\nDIGIT", "EOF"),
            new Case("T14", "#\n", "LEN\nUPPER\nDIGIT\nCHAR", "all four codes in order")
    );

    public static void main(String[] args) throws Exception {
        Path out = Path.of("evidence", "final");
        Files.createDirectories(out);
        StringBuilder table = new StringBuilder("ID\tType\tExpected\tActual\tStatus\n");
        int passed = 0;
        for (Case test : CASES) {
            Process process = new ProcessBuilder(
                    Path.of(System.getProperty("java.home"), "bin", "java").toString(),
                    "-cp", "build", "PasswordValidator").start();
            try (var stdin = process.getOutputStream()) {
                stdin.write(test.input().getBytes(StandardCharsets.UTF_8));
            }
            String stdout = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8)
                    .replace("\r\n", "\n");
            String stderr = new String(process.getErrorStream().readAllBytes(), StandardCharsets.UTF_8);
            int exit = process.waitFor();
            boolean ok = exit == 0 && stderr.isEmpty() && stdout.equals(test.expected() + "\n");
            String actual = stdout.endsWith("\n") ? stdout.substring(0, stdout.length() - 1) : stdout;
            if (ok) {
                passed++;
            }
            String status = ok ? "PASS" : "FAIL";
            System.out.printf("%s  %-4s  %s%n", test.id(), status, test.boundary());
            Files.writeString(out.resolve(test.id() + ".txt"),
                    "Input: " + test.input().replace("\n", "\\n") + "\n"
                            + "Expected:\n" + test.expected() + "\n"
                            + "Actual:\n" + actual + "\n"
                            + "Stdout (escaped): " + stdout.replace("\n", "\\n") + "\n"
                            + "Exit code: " + exit + "\nStderr: " + stderr
                            + "Status: " + status + "\n", StandardCharsets.UTF_8);
            table.append(test.id()).append('\t').append(test.boundary()).append('\t')
                    .append(test.expected().replace("\n", "/")).append('\t')
                    .append(actual.replace("\n", "/")).append('\t').append(status).append('\n');
        }
        Files.writeString(out.resolve("results.tsv"), table, StandardCharsets.UTF_8);
        System.out.printf("Result: %d/%d passed%n", passed, CASES.size());
        if (passed != CASES.size()) {
            System.exit(1);
        }
    }
}
