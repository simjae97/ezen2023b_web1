package examaple.day10._1example;

public class User1Thread extends Thread{

    // 1. 필드: 유저1 클래스객체가 가지고 있는 계산기
    private Calculator calculator;

    public User1Thread(){
        setName("User1Thread");
    }

    public void setCalculator(Calculator calculator){
        this.calculator=calculator;
    }
    @Override
    public void run() {
        calculator.setMemory(100);
    }
}
