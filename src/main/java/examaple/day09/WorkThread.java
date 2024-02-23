package examaple.day09;

public class WorkThread extends Thread{

    public boolean work = true;

    public WorkThread(String name){
        setName(name);
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            }
            catch (Exception e){
                System.out.println(e);
            }
            if(work){
                System.out.println(getName());
            }
            else {
                System.out.println("1");
                Thread.yield();
                System.out.println("2");
            }
        }
    }
}
