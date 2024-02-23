package examaple.day10._1example;

public class Calculator {
    private int memory;

    public int getMemory() {
        return memory;
    }

    // 매개변수를 저장후 20초뒤에 값을 출력하게
    //synchronized 여러 스레드가 동시에 접근하기 못하게 로킹을 걸어줌
    public synchronized void setMemory(int memory) {
        this.memory = memory;
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName()+"memory = " + this.memory);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
