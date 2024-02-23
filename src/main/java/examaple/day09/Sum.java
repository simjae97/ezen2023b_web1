package examaple.day09;

public class Sum extends Thread{ //자식객체 만들기
    private long sum;

    @Override
    public void run() {
        for(int i=0; i<=100; i++){
            sum += i;
        }
    }

    public long getSum(){
        return sum;
    }
    public void setSum(long sum){
        this.sum=sum;
    }
}
