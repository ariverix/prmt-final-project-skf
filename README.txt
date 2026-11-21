КОНСОЛЬНЫЙ ВАЛИДАТОР ПАРОЛЯ
Итоговый проект Skillfactory. Java 17, только стандартная библиотека.

Из корня архива с JDK 17 в PATH:
  javac --release 17 -encoding UTF-8 -d build src/PasswordValidator.java tests/ValidatorTests.java
  java -cp build PasswordValidator
  java -cp build ValidatorTests

Введите одну синтетическую строку и нажмите Enter. Для Abcdefg1 ответ OK.
Для # — LEN, UPPER, DIGIT, CHAR на отдельных строках в указанном порядке.
Пустая строка и EOF дают LEN, UPPER, DIGIT. Реальные пароли не используйте.

src/PasswordValidator.java — финальная программа, 56 строк.
tests/ValidatorTests.java — отдельный раннер, tests/scenarios.md — условия T01–T14.
docs/ — требования, схема, журнал, матрицы, контрольные точки и акт.
prompts/ — версии запросов и шаблоны с отметками.
evidence/ — аудит, логи, учебный сбой D01 с версиями до/после.
assets/ — изображения и подписи. Отчёт передаётся отдельным файлом.

Подтверждение JDK 17: https://github.com/ariverix/prmt-final-project-skf/actions/runs/44907573229
