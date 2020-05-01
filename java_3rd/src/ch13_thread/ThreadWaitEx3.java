package ch13_thread;

import java.util.ArrayList;

/*
* wait()와 notify()를 추가한 예제
* notify()를 사용하면 하나의 쓰레드한테만 통지가 갈 수 있어서 기아현상(starvation)이 발생 할수 있다.
* 그래서 notifyAll()을 사용해야 한다.
* 하지만 쓰레드를 모두 깨우기 때문에 서로 경쟁해서 먼저 작동하려하는데, 그걸 경쟁 상태(race condition)라고 한다.
* Lock, Condition을 이용하면 선별 통지가 가능해진다. (다음 예제에서..)
* */
public class ThreadWaitEx3 {
    public static void main(String[] args) throws InterruptedException {
        Table3 table = new Table3(); // 여러 쓰레드가 공유하는 객체

        new Thread(new Cook3(table), "COOK1").start();
        new Thread(new Customer3(table, "donut"), "CUST1").start();
        new Thread(new Customer3(table, "burger"), "CUST2").start();

        Thread.sleep(2000);
        System.exit(0);
    }
}

class Customer3 implements Runnable {
    private final Table3 table;
    private final String food;

    public Customer3(Table3 table, String food) {
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

class Cook3 implements Runnable {
    private final Table3 table;

    public Cook3(Table3 table) {
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

class Table3 {

    private static final int MAX_FOOD = 6; // 테이블에 놓을 수 있는 최대 음식의 개수
    public String[] dishNames = {"donut", "donut", "burger"}; // donut의 확률을 높인다.

    private ArrayList<String> dishes = new ArrayList<>();

    public void remove(String dishName) {
        synchronized (this) {
            String name = Thread.currentThread().getName();

            while (dishes.size() == 0) {
                System.out.println(name + " is waiting.");
                try {
                    wait(); // CUST 쓰레드를 기다리게 한다.
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }

            while (true) {
                for (int i = 0; i < dishes.size(); i++) {
                    if (dishName.equals(dishes.get(i))) {
                        dishes.remove(i);
                        notify(); // 잠자고 있는 COOK을 깨우기 위함
                        return;
                    }
                }

                try {
                    System.out.println(name + " is waiting.");
                    wait(); // 원하는 음식이 없는 CUST 쓰레드를 기다리게 한다.
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public int dishNum() {
        return dishNames.length;
    }

    public synchronized void add(String dish) {
        // 테이블에 음식이 가득찼으면, 테이블에 음식을 추가하지 않는다.
        while (dishes.size() >= MAX_FOOD) {
            String name = Thread.currentThread().getName();
            System.out.println(name + " is waiting.");
            try {
                wait(); // COOK 쓰레드를 기다리게 한다.
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
        dishes.add(dish);
        notify(); // 기다리고 있는 CUST를 깨우기 위함.
        System.out.println("Dishes : " + dishes.toString());
    }
}