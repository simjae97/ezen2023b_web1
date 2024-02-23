package examaple.day09;

public class Example2 {

    public static void main(String[] args) {
        Sum sum = new Sum();
        //p604.다른 쓰레드의 종료를 기다림
        sum.start();
        try {
            sum.join();
        }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println(sum.getSum());

        //p606.다른 쓰레드에게 양보
        WorkThread workThreadA = new WorkThread("workThreadA");
        WorkThread workThreadB = new WorkThread("workThreadB");

        workThreadA.start();
        workThreadB.start();
        try {
            Thread.sleep(5000);
        }
        catch (Exception e){
            System.out.println(e);
        }
        workThreadA.work = false;
        try {
            Thread.sleep(5000);
        }
        catch (Exception e){
            System.out.println(e);
        }
        workThreadA.work=true;
    }
}
