package examaple.day09;

import java.awt.*;

public class Example1 {
    public static void main(String[] args) {
        // 1. 현재 코드를 실행하는 스레드 객체 호출
            //Thread.currentThread();
        Thread thread = Thread.currentThread();

        System.out.println("thread.ㅎ = " + thread.getName());

        //3작업스레드 생성
            //자식 익명객체
        Thread threadA = new Thread(){
            @Override
            public void run() {
                System.out.println("작업스레드A :"+getName());
                System.out.println("새로운 쓰레드"+Thread.currentThread().getName());
            }
        };
        threadA.start();

        for(int i= 0; i<3; i++){
            Thread threadB = new Thread(){
                @Override
                public void run() {
                    Thread thread1 = Thread.currentThread();
                    thread1.setName("내가만든 작업쓰레드");
                    System.out.println("새로운 쓰레드"+Thread.currentThread().getName());
                }
            };
            threadB.start();
        }


        // p603 주어진 시간동안 스레드 일시정지 .sleep(): 정적메소드(static) : 정적메소드 호출하는 방법 : 클래스명.정적메소드();
        Toolkit toolkit =Toolkit.getDefaultToolkit();
        for (int i = 0; i <10 ; i++) {
            toolkit.beep();
            try {
                Thread.sleep(3000);
            }
            catch (InterruptedException e){
                System.out.println(e);
            }
        }
    }
}
