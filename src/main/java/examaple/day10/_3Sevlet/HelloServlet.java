package examaple.day10._3Sevlet;


// 자바 회사에서 웹 개발을 위한 HTTP 통신 클래스:Httpservlet 주로 컨트롤러 역할
    // 1.해당 클래스에 HttpServlet 상속받는다
    // 2. 해당 클래스에 @W ebServlet 어노테이션 주입해서 web.xml에 등록
    // 3. 메도스 오버라이딩
/*
    서블릿 실행 구동 순서
    1.클라이언트(브라우저) http 요청이 (AWS)서버로 들어온다
    2.서블릿 컨테이너에 요청받은 서블릿이 있는지 체크
    3.없으면 init() 메소드 실행한 서블릿 생성
    4.생성하거나 이미 조노재하면 Thread 할당
    5.service() 실행하고 http 요청 메소드에 따른 메소드로 이동
    6.doXXX 메소드 실행시 요청객체 생성
    7.doxxx 메소드 실행후 응답객체 생성
    -----다음 요청이 올 때까지 살아있음
    다음요청
    1->2->4->5->6
    ----서버가 종료되면 destroy 실행되며 안전하게 서블렛 제거

 */
import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/team4")
public class HelloServlet extends HttpServlet {
    // httpsevlet 클래스로 부터 상속받은 다양한 http 관련 메소드 사용가능


    @Override //1.[최초 요청 한번]
    public void init() throws ServletException {
        System.out.println("HelloServlet.init");
        super.init();
    }

    @Override //2.[무조건 실행]
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        super.service(req, resp);
    }

    @Override // 4. 서버 종료시 실행
    public void destroy() {
        System.out.println("HelloServlet.destroy");
    }

    @Override //3.method에 따라 실행
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.doGet");// 상태 코드를 변경하여 성공적인 응답을 나타냄
        resp.setContentType("text/html"); //html로 보내주겠다
        resp.getWriter().println("get메소드가호출되는중입니다");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.doPut");
        String result = "{\"이것은\": \"풋\"}";

        // Content-Type 설정
        resp.setContentType("application/json"); //json으로 보내주겠따
        resp.getWriter().println(result.toString()); 
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //실제 입력받은값으로 바꾸기
        System.out.println("HelloServlet.doDelete");

        // JSON 객체 생성
        String result = "{\"이것은\": \"딜리트\"}";

        // Content-Type 설정
        resp.setContentType("application/json");

        // 응답으로 JSON 데이터 보내기
        resp.getWriter().println(result.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.doPost");
        String result = "{\"이것은\": \"포스트\"}";

        // Content-Type 설정
        resp.setContentType("application/json");
        resp.getWriter().println(result.toString());
    }
}
