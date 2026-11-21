# Матрица требований и проверок

| ID | Проверка | Ожидание и факт финальной версии | Статус | Подтверждение |
| --- | --- | --- | --- | --- |
| R01 | V01, V02, T01 | Консоль, Java 17, стандартная библиотека, без package. Сборка exit 0, вывод OK | Пройдено | [JDK 17](../evidence/ci/java17_final_run.txt) · [осмотр](../evidence/compile.txt) |
| R02 | T07, T11, T12 | CHAR с внутренним и краевыми пробелами, OK для первой строки с последующей строкой | Пройдено | [T07](../evidence/final/T07.txt) · [T11](../evidence/final/T11.txt) · [T12](../evidence/final/T12.txt) |
| R03 | T01–T04 | 8/16 → OK, 7/17 → LEN | Пройдено | [T01–T04](../evidence/final/results.tsv) |
| R04 | T01, T05, T09, T14 | Заглавная ASCII учитывается, при её отсутствии UPPER | Пройдено | [T05](../evidence/final/T05.txt) · [T14](../evidence/final/T14.txt) |
| R05 | T01, T06, T09, T14 | ASCII-цифра учитывается, при её отсутствии DIGIT | Пройдено | [T06](../evidence/final/T06.txt) · [T14](../evidence/final/T14.txt) |
| R06 | T01, T07, T08, T10, T12–T14 | CHAR для пробелов и #, пустой ввод не даёт CHAR | Пройдено | [T07–T14](../evidence/final/results.tsv) |
| R07 | T01, T02, T11 | Ровно одна строка OK | Пройдено | [T01](../evidence/final/T01.txt) · [T02](../evidence/final/T02.txt) · [T11](../evidence/final/T11.txt) |
| R08 | T08, T09, T13, T14 | Коды в порядке R1, T14 подтверждает одновременно все четыре | Пройдено | [T08](../evidence/final/T08.txt) · [T14](../evidence/final/T14.txt) |
| R09 | T08, T13 | LEN / UPPER / DIGIT, выход 0, stderr пуст | Пройдено | [T08](../evidence/final/T08.txt) · [T13](../evidence/final/T13.txt) |
| R10 | T01–T14, особо T11 | Нет приглашения, эха и пояснений. Вторая строка игнорируется | Пройдено | [T11](../evidence/final/T11.txt) · [набор](../evidence/final/results.tsv) |

Результаты соответствуют указанным ожиданиям на проверенных входах. Расхождения дефектной учебной версии сохранены в [D01](../evidence/study_failure/card.md).
