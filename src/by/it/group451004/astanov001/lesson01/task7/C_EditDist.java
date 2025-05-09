package by.it.group451004.astanov001.lesson01.task7;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,

    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,

    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,


    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {
    String getDistanceEdinting(String one, String two) {
        int n = one.length();
        int m = two.length();

        int[][] dp = new int[n + 1][m + 1];
        String[][] path = new String[n + 1][m + 1];  // Для хранения операций

        // Инициализация
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
            path[i][0] = i == 0 ? "" : path[i - 1][0] + "-" + one.charAt(i - 1) + ",";
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
            path[0][j] = j == 0 ? "" : path[0][j - 1] + "+" + two.charAt(j - 1) + ",";
        }

        // Основной цикл
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int cost = (one.charAt(i - 1) == two.charAt(j - 1)) ? 0 : 1;

                int del = dp[i - 1][j] + 1;
                int ins = dp[i][j - 1] + 1;
                int rep = dp[i - 1][j - 1] + cost;

                dp[i][j] = Math.min(Math.min(del, ins), rep);

                if (dp[i][j] == rep) {
                    path[i][j] = path[i - 1][j - 1] +
                            (cost == 0 ? "#" : "~" + two.charAt(j - 1)) + ",";
                } else if (dp[i][j] == del) {
                    path[i][j] = path[i - 1][j] + "-" + one.charAt(i - 1) + ",";
                } else {
                    path[i][j] = path[i][j - 1] + "+" + two.charAt(j - 1) + ",";
                }
            }
        }

        return path[n][m];
    }
    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_EditDist.class.getResourceAsStream("dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}