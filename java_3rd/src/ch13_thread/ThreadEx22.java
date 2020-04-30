package ch13_thread;

public class ThreadEx22 {
    public static void main(String[] args) {
        Runnable r = new RunnableEx22();
        new Thread(r).start();
        new Thread(r).start();
    }
}

class Account22 {

    private int balance = 1000; // private으로 해야 동기화가 의미가 있다.

    public int getBalance() {
        return balance;
    }

    public synchronized void withdraw(int money) { // synchronized로 메서드를 동기화
        if(balance >= money) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            balance -= money;
        }
    }
}

class RunnableEx22 implements Runnable {
    Account22 acc = new Account22();

    @Override
    public void run() {
        while(acc.getBalance() > 0) {
            // 100, 200, 300중의 한 값을 임의로 선택해서 출금(withdraw)
            int money = (int) (Math.random() * 3 + 1) * 100;
            acc.withdraw(money);
            System.out.println("balance : " + acc.getBalance());
        }
    }
}