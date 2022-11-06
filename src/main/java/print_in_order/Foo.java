package print_in_order;

import java.util.concurrent.CountDownLatch;

// Design a mechanism and modify the program to ensure that second() is executed after first(),
// and third() is executed after second().
public class Foo {
    public Foo() {}
    private final CountDownLatch latch1 = new CountDownLatch(1);
    private final CountDownLatch latch2 = new CountDownLatch(1);

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        latch1.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // printSecond.run() outputs "second". Do not change or remove this line.
        latch1.await();
        printSecond.run();
        latch2.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        // printThird.run() outputs "third". Do not change or remove this line.
        latch2.await();
        printThird.run();
    }

    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();
        foo.first(() -> System.out.println("first"));
        foo.second(() -> System.out.println("second"));
        foo.third(() -> System.out.println("third"));
    }
}
