package print_zero_even_odd;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public class ZeroEvenOdd {
    private final int n;
    private final AtomicInteger counter = new AtomicInteger(0);
    private volatile boolean isZeroTime = true;
    private final Semaphore semaphore1 = new Semaphore(1);
    private final Semaphore semaphore2 = new Semaphore(1);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (counter.get() < n) {
            if (isZeroTime) {
                semaphore1.acquire();
                semaphore2.acquire();
                printNumber.accept(0);
                counter.incrementAndGet();
                isZeroTime = false;
                semaphore1.release();
                semaphore2.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (counter.get() <= n) {
            if (!isZeroTime) {
                if (counter.get() % 2 == 0) {
                    semaphore2.acquire();
                    printNumber.accept(counter.get());
                    isZeroTime = true;
                    semaphore2.release();
                }
                if (counter.get() == n) {
                    break;
                }
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (counter.get() <= n) {
            if (!isZeroTime) {
                if (counter.get() % 2 == 1) {
                    semaphore1.acquire();
//                    System.out.println(counter.get());
                    printNumber.accept(counter.get());
                    isZeroTime = true;
                    semaphore1.release();
                }
                if (counter.get() == n) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 1; j++) {
            ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(6);
            Thread thread1 = new Thread(() -> {
                try {
                    zeroEvenOdd.zero(i -> System.out.println(Thread.currentThread().getName() + ": " + 0 + ", "));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "thread-0");
            Thread thread2 = new Thread(() -> {
                try {
                    zeroEvenOdd.even(i -> System.out.println(Thread.currentThread().getName() + ": " + i  + ", "));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "thread-even");
            Thread thread3 = new Thread(() -> {
                try {
                    zeroEvenOdd.odd(i -> System.out.println(Thread.currentThread().getName() + ": " + i + ", "));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "thread-odd");
            thread1.start();
            thread2.start();
            thread3.start();
            thread1.join();
            thread2.join();
            thread3.join();
        }
    }
}
