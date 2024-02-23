package examaple.day11.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.json.GsonJsonParser;

import java.io.IOException;

@WebServlet("/servlet")
public class TestServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.doGet");
        // 요청HttpServletRequest
        String id = req.getParameter("id");
        System.out.println("id = " + id);

        int type = Integer.parseInt(req.getParameter("age"));
        System.out.println("type = " + type);
        //응답 HttpServletResponse
//        resp.setContentType("text/html"); //받는 데이터의 타입(데이터를 사용하는 방법)
        resp.setContentType("application/json"); // responsebody

        resp.getWriter().println("{ \"msg\" : \"클라이언트에게 보내는 메세지\" ,\"type\" : \"1\"}");
        // 자바 데이터를 JSON 형태로 반환
            // 1. 직접한다 (실무 x)
            // 2. 외부 라이브러리 사용
            // 3. springMVC @responsebody
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.doPost");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.doPut");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.doDelete");
    }
}
