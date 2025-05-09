package by.it.group451004.astanov001.lesson01.task6;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: наибольшая невозростающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/


public class C_LongNotUpSubSeq {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_LongDivComSubSeq.class.getResourceAsStream("dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }
    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        // Индексы предыдущих элементов в подпоследовательности
        int[] prev = new int[n];
        // Индексы последних элементов подпоследовательностей длины len
        int[] pos = new int[n];
        int length = 1;

        // начальная точка
        pos[0] = 0;
        prev[0] = -1;

        for (int i = 1; i < n; i++) {
            // Бинарный поиск на максимальный индекс j, такой что A[pos[j]] >= A[i]
            int left = 0, right = length;
            while (left < right) {
                int mid = (left + right) / 2;
                if (a[pos[mid]] >= a[i]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            // Обновляем цепочку
            if (left > 0) {
                prev[i] = pos[left - 1];
            } else {
                prev[i] = -1;
            }

            pos[left] = i;

            if (left == length) {
                length++;
            }
        }

        // Восстановим последовательность индексов
        int[] seq = new int[length];
        int k = pos[length - 1];
        for (int i = length - 1; i >= 0; i--) {
            seq[i] = k + 1; // с 1 начинается индекс
            k = prev[k];
        }

        // Вывод
        System.out.println(length);
        for (int i = 0; i < length; i++) {
            System.out.print(seq[i] + " ");
        }
        System.out.println();

        return length;
    }
}