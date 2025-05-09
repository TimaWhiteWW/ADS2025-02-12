package by.it.group451004.astanov001.lesson01.task1;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m
 * время расчета должно быть не более 2 секунд
 */

public class FiboC {

    private long startTime = System.currentTimeMillis();

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 55555;
        int m = 1000;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }
    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    long fasterC(long n, int m) {
        int pisanoPeriod = getPisanoPeriod(m);
        int remainder = (int)(n % pisanoPeriod);
        return getFibonacciMod(remainder, m);
    }
    private int getPisanoPeriod(int m) {
        int prev = 0;
        int curr = 1;
        for (int i = 0; i < m * m; i++) {
            int temp = (prev + curr) % m;
            prev = curr;
            curr = temp;
            if (prev == 0 && curr == 1) {
                return i + 1;
            }
        }
        return 0;
    }
    private int getFibonacciMod(int n, int m) {
        if (n <= 1)
            return n;
        int prev = 0;
        int curr = 1;
        for (int i = 2; i <= n; i++) {
            int temp = (prev + curr) % m;
            prev = curr;
            curr = temp;
        }

        return curr;
    }
}

