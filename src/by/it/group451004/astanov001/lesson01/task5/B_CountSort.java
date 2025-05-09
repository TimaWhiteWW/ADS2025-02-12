package by.it.group451004.astanov001.lesson01.task5;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Первая строка содержит число 1<=n<=10000, вторая - n натуральных чисел, не превышающих 10.
Выведите упорядоченную по неубыванию последовательность этих чисел.

При сортировке реализуйте метод со сложностью O(n)

Пример: https://karussell.wordpress.com/2010/03/01/fast-integer-sorting-algorithm-on/
Вольный перевод: http://programador.ru/sorting-positive-int-linear-time/
*/

public class B_CountSort {


    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_CountSort.class.getResourceAsStream("dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result = instance.countSort(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] countSort(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        // Считываем размер массива
        int n = scanner.nextInt();
        int[] input = new int[n];

        // Считываем массив
        for (int i = 0; i < n; i++) {
            input[i] = scanner.nextInt();
        }

        // Максимальное значение из условия задачи — 10
        int maxValue = 10;

        // Массив для подсчета количества каждого числа
        int[] count = new int[maxValue + 1]; // индексы 0..10, но 0 будет неиспользован

        // Подсчет количества каждого числа
        for (int value : input) {
            count[value]++;
        }

        // Восстановим отсортированный массив
        int index = 0;
        for (int i = 1; i <= maxValue; i++) {
            for (int j = 0; j < count[i]; j++) {
                input[index++] = i;
            }
        }

        return input;
    }

}
