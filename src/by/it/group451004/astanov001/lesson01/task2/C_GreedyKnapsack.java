package by.it.group451004.astanov001.lesson01.task2;
/*
Даны
1) объем рюкзака 4
2) число возможных предметов 60
3) сам набор предметов
    100 50
    120 30
    100 50
Все это указано в файле (by/it/a_khmelev/lesson02/greedyKnapsack.txt)

Необходимо собрать наиболее дорогой вариант рюкзака для этого объема
Предметы можно резать на кусочки (т.е. алгоритм будет жадным)
 */

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Arrays;

public class C_GreedyKnapsack {
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        InputStream inputStream = C_GreedyKnapsack.class.getResourceAsStream("greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(inputStream);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)", costFinal, finishTime - startTime);
    }

    double calc(InputStream inputStream) throws FileNotFoundException {
        Scanner input = new Scanner(inputStream);
        int n = input.nextInt();      // количество предметов
        int W = input.nextInt();      // вместимость рюкзака
        Item[] items = new Item[n];   // массив предметов
        for (int i = 0; i < n; i++) {
            items[i] = new Item(input.nextInt(), input.nextInt());
        }

        // Сортировка предметов по убыванию value-to-weight ratio
        Arrays.sort(items);

        double result = 0;
        int remainingCapacity = W;

        for (Item item : items) {
            if (remainingCapacity == 0) break;
            if (item.weight <= remainingCapacity) {
                result += item.cost;
                remainingCapacity -= item.weight;
            } else {
                result += item.cost * ((double) remainingCapacity / item.weight);
                remainingCapacity = 0;
            }
        }

        System.out.printf("Удалось собрать рюкзак на сумму %f\n", result);
        return result;
    }

    private static class Item implements Comparable<Item> {
        int cost;
        int weight;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "cost=" + cost +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Item o) {
            double thisRatio = (double) this.cost / this.weight;
            double otherRatio = (double) o.cost / o.weight;
            return Double.compare(otherRatio, thisRatio); // сортировка по убыванию
        }
    }
}