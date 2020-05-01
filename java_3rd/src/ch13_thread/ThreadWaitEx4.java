package ch13_thread;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
* Condition : await(), signal()을 이용
* 기아현상, 경쟁상태가 발생할 확률이 줄었을 뿐, 언제든지 발생은 할 수 있다.
* */
public class ThreadWaitEx4 {
    public static void main(String[] args) throws InterruptedException {
        Table4 table = new Table4(); // 여러 쓰레드가 공유하는 객체

        new Thread(new Cook4(table), "COOK1").start();
        new Thread(new Customer4(table, "donut"), "CUST1").start();
        new Thread(new Customer4(table, "burger"), "CUST2").start();

        Thread.sleep(2000);
        System.exit(0);
    }
}

class Customer4 implements Runnable {
    private final Table4 table;
    private final String food;

    public Customer4(Table4 table, String food) {
        this.table = table;
        this.food = food;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }

            String name = Thread.currentThread().getName();

            table.remove(food);
            System.out.println(name + " ate a " + food);
        }
    }
}

class Cook4 implements Runnable {
    private final Table4 table;

    public Cook4(Table4 table) {
        this.table = table;
    }

    @Override
    public void run() {
        while (true) {
            int idx = (int) (Math.random() * table.dishNum());
            table.add(table.dishNames[idx]);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }
}

class Table4 {

    private static final int MAX_FOOD = 6; // 테이블에 놓을 수 있는 최대 음식의 개수
    public String[] dishNames = {"donut", "donut", "burger"}; // donut의 확률을 높인다.
    private ArrayList<String> dishes = new ArrayList<>();

    private ReentrantLock lock = new ReentrantLock();
    private Condition forCook = lock.newCondition();
    private Condition forCust = lock.newCondition();

    public void remove(String dishName) {
        lock.lock(); // synchronized(this)
        String name = Thread.currentThread().getName();

        try {
            while (dishes.size() == 0) {
                System.out.println(name + " is waiting.");
                try {
                    forCust.await(); // wait(); CUST 쓰레드를 기다리게 한다.
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }

            while (true) {
                for (int i = 0; i < dishes.size(); i++) {
                    if (dishName.equals(dishes.get(i))) {
                        dishes.remove(i);
                        forCook.signal(); // notify(); 잠자고 있는 COOK을 깨우기 위함
                        return;
                    }
                }

                try {
                    System.out.println(name + " is waiting.");
                    forCust.await(); // wait(); 원하는 음식이 없는 CUST 쓰레드를 기다리게 한다.
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public int dishNum() {
        return dishNames.length;
    }

    public synchronized void add(String dish) {
        lock.lock();

        try {
            while (dishes.size() >= MAX_FOOD) {
                String name = Thread.currentThread().getName();
                System.out.println(name + " is waiting.");
                try {
                    forCook.await(); // wait(); COOK 쓰레드를 기다리게 한다.
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }

            dishes.add(dish);
            forCust.signal(); // notify(); 기다리고 있는 CUST를 깨우기 위함.
            System.out.println("Dishes : " + dishes.toString());
        } finally {
            lock.unlock();
        }
    }
}