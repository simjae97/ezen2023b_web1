package examaple.day10._2example;

public class Example {
    public static void main(String[] args) {
        WorkObject workObject=new WorkObject();

        ThreadA threadA = new ThreadA(workObject);
        ThreadB threadB = new ThreadB(workObject);

        threadA.start();
        threadB.start();
    }
}
