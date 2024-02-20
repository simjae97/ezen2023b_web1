package examaple.day08;

import java.awt.*;

public class Step1 {
    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() { //작업스레드가 실행하는 메소드
            @Override
            public void run() {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                for(int i= 1; i<= 5; i++){
                    try {
                        toolkit.beep();
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e ){
                        System.out.println(e);
                    }
                }
            }
        });
        thread.start(); // 2.작업스레드 실행
        for (int i = 1; i <=  5; i++) { //메인스레드 처리
            System.out.print("띵");
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e){
                System.out.println(e);
            }
        }

        // =================멀티스레ㅡㄷ2일때===================
        // 1. Runnable 객체
        Runnable runnable =new 작업클래스();
        Thread thread1 = new Thread(runnable);
        thread1.start();

        작업스레드2 작업스레드2 = new 작업스레드2();
        작업스레드2.start();

        //vs
        Thread thread2 = new Thread(){
            public void run(){
                super.run();
            }
        };
    }
}
