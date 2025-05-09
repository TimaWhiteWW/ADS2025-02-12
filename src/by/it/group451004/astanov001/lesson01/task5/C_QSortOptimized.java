package by.it.group451004.astanov001.lesson01.task5;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии
            - за сам массив отрезков - сортировка на месте
            - рекурсивные вызовы должны проводиться на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска
        для первого отрезка решения, а затем найдите оставшуюся часть решения
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/


public class C_QSortOptimized {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_QSortOptimized.class.getResourceAsStream("dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }
    private void quickSort3(Segment[] arr, int left, int right) {
        while (left < right) {
            int lt = left, gt = right;
            Segment pivot = arr[left];
            int i = left + 1;
            while (i <= gt) {
                if (arr[i].compareTo(pivot) < 0) {
                    swap(arr, lt++, i++);
                } else if (arr[i].compareTo(pivot) > 0) {
                    swap(arr, i, gt--);
                } else {
                    i++;
                }
            }
            // рекурсивно сортируем меньшую часть (tail recursion optimization)
            if (lt - left < right - gt) {
                quickSort3(arr, left, lt - 1);
                left = gt + 1;
            } else {
                quickSort3(arr, gt + 1, right);
                right = lt - 1;
            }
        }
    }

    private void swap(Segment[] arr, int i, int j) {
        Segment temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private int countCameras(Segment[] segments, int point) {
        int low = 0, high = segments.length - 1;
        int count = 0;

        // бинарный поиск первого подходящего отрезка
        while (low <= high) {
            int mid = (low + high) / 2;
            if (segments[mid].start <= point) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // проверка всех отрезков слева от low
        for (int i = 0; i < low; i++) {
            if (segments[i].stop >= point) {
                count++;
            }
        }
        return count;
    }
    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        for (int i = 0; i < n; i++) {
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        // Сортировка сегментов по началу
        quickSort3(segments, 0, n - 1);

        // Подсчет перекрытий
        for (int i = 0; i < m; i++) {
            result[i] = countCameras(segments, points[i]);
        }

        return result;
    }

    //отрезок
    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            if (start <= stop) {
                this.start = start;
                this.stop = stop;
            } else {
                this.start = stop;
                this.stop = start;
            }
        }

        @Override
        public int compareTo(Segment other) {
            return Integer.compare(this.start, other.start);
        }
    }
}
