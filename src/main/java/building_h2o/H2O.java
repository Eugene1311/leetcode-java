package building_h2o;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class H2O {
    private final Semaphore hydrogenSemaphore = new Semaphore(HYDROGEN_PERMITS);
    private final Semaphore oxygenSemaphore = new Semaphore(OXYGEN_PERMITS);

    private final static int HYDROGEN_PERMITS = 2;
    private final static int OXYGEN_PERMITS = 1;

    public H2O() {
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydrogenSemaphore.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        if (hydrogenSemaphore.availablePermits() == 0 && oxygenSemaphore.availablePermits() < OXYGEN_PERMITS) {
            oxygenSemaphore.release();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxygenSemaphore.acquire();
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        hydrogenSemaphore.release(HYDROGEN_PERMITS);
    }

    public static void main(String[] args) throws InterruptedException {
        H2O h2O = new H2O();
        String water = "HHOHHOOOOHHHHHH"; // .repeat(20);

        for (int i = 0; i < 100; i++) {
            ExecutorService executorService = Executors.newFixedThreadPool(water.length());
            for (char letter : water.toCharArray()) {
                if (letter == 'H') {
                    executorService.submit(() -> {
                        try {
                            h2O.hydrogen(() -> System.out.println(Thread.currentThread().getName() + ": " + letter));
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
                if (letter == 'O') {
                    executorService.submit(() -> {
                        try {
                            h2O.oxygen(() -> System.out.println(Thread.currentThread().getName() + ": " + letter));
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
            executorService.shutdown();
            executorService.awaitTermination(100, TimeUnit.MILLISECONDS);
            System.out.println("\n------");
        }

    }
}
