package ch13_thread;

public class ThreadEx12 {
    public static void main(String[] args) {
        ThreadEx12_1 th1 = new ThreadEx12_1();
        ThreadEx12_2 th2 = new ThreadEx12_2();

        th1.start();
        th2.start();

        try {
            // th1이 아니라 main에 영향을 준다.
            // 그래서 Thread.sleep(2000); 으로 작성해주는게 맞다.
            th1.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("<<main 종료>>");
    }
}

class ThreadEx12_1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("-");
        }
        System.out.print("<<th1 종료>>");
    }
}

class ThreadEx12_2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("|");
        }
        System.out.print("<<th2 종료>>");
    }
}
