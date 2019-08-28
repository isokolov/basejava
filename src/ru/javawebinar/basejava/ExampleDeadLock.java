package ru.javawebinar.basejava;

public class ExampleDeadLock {
    public static void main(String[] args) {
        final String resource1 = "resource1";
        final String resource2 = "resource2";
        deadLock(resource1, resource2);
        deadLock(resource2, resource1);
    }

    private static void deadLock(Object object1, Object object2) {
        new Thread() {
            @Override
            public void run() {
                System.out.println("Starting object " + object1);
                synchronized (object1) {
                    try {
                        System.out.println("Pause object " + object1 + " for 10 ms");
                        Thread.sleep(10);
                        System.out.println("Calling object " + object2 + " from object " + object1);
                        synchronized (object2) {
                            System.out.println("Object " + object2 + " called");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
