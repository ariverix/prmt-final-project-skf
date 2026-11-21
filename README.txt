PASSWORD VALIDATOR — итоговый учебный проект

Java 17: javac --release 17 -encoding UTF-8 -d build src/PasswordValidator.java tests/ValidatorTests.java
Запуск: java -cp build PasswordValidator
Тесты: java -cp build ValidatorTests

Ввод: одна первая строка stdin. Длина 8–16, A-Z/a-z/0-9, нужна заглавная латинская буква и цифра. Выход: OK либо LEN, UPPER, DIGIT, CHAR (по одному коду в строке).

Основной код: src/PasswordValidator.java — 56 непустых некомментированных строк.
Тестовый код отдельно в tests/ValidatorTests.java.

Доказательства: evidence/compile.txt, evidence/final/, evidence/study_failure/.
Требования: docs/requirements.md; карта: docs/process.md; матрица: docs/matrix.md; журнал: docs/journal.md; приёмка: docs/acceptance.md.

Фактические запуски в этой среде: OpenJDK 21; совместимость компиляции с Java 17 подтверждена --release 17. Неотправленные промпты помечены NOT_SENT.
