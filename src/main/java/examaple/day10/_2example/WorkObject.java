package examaple.day10._2example;

public class WorkObject {
    public synchronized void methodA(){
        // 1. 현재 스레드 객체 호출 .currentThread
        // 2. 스레드 이름 호출 : .getName
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName());
        notify(); //실행대기
        try {
            wait(); //일시정지: 아예 멈춤
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public synchronized void methodB(){
        // 1. 현재 스레드 객체 호출 .currentThread
        // 2. 스레드 이름 호출 : .getName
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName());
        notify(); //실행대기
        try {
            wait(); //일시정지: 아예 멈춤
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}