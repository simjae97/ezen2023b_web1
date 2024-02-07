package examaple.day01.consoleMvc;

public class Appstart {
    public static void main(String[] args) {
        Mainview mainview = new Mainview();
        mainview.home();

        //2.
        new Mainview().home();

        //3.정적 메소드일때는 MainView.home

        //4.싱글톤일 경우 MainView.getInstance().home()

        //5.IDC 제어 역전. DI 의존성 주입

    }
}