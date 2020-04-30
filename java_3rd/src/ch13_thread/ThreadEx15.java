package ch13_thread;

/*
* suspend(), stop()이 교착상태를 일으키기 쉽게 작성되어 있다.
* 그래서 suspend(), resume(), stop()는 모두 deprecated 되었다.
* 예제는 작성하지만, 사용하지 말자.
* ThreadEx16 예제를 참고해서 학습하도록 하자.
* */
public class ThreadEx15 {
    public static void main(String[] args) {
        RunImplEx15 r = new RunImplEx15();
        Thread th1 = new Thread(r, "*");
        Thread th2 = new Thread(r, "**");
        Thread th3 = new Thread(r, "***");

        th1.start();
        th2.start();
        th3.start();

        try {
            Thread.sleep(2000);
            th1.suspend(); // 쓰레드 th1을 잠시 중단시킨다.
            Thread.sleep(2000);
            th2.suspend();
            Thread.sleep(3000);
            th1.resume(); // 쓰레드 th1이 다시 동작하도록 한다.
            Thread.sleep(3000);
            th1.stop(); // 쓰레드 th1을 강제종료시킨다.
            th2.stop();
            Thread.sleep(2000);
            th3.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class RunImplEx15 implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }
}
