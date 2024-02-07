package examaple.day02.webMVC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

//스프링이 해당 클래스가 컨트롤이라는 것을 알려야한다
@RestController //스프링 컨테이너에 빈(객체) 등록 IOC
                //IOC 제어역전 기법: 개발자가 객체 관리 x , 스프링이 대신 사용
public class TodoController {

    private TodoDao todoDao =new TodoDao();
    @PostMapping("/todo/post.do")
    public boolean doPost(TodoDto todoDto){
        return todoDao.doPost(todoDto);
    }
    @GetMapping("/todo/get.do")
    public ArrayList<TodoDto> doGet(){
        return todoDao.doGet();
    }

    //localhost:80/todo/get.do
    @PutMapping("todo/put.do")
    public boolean doPut(TodoDto todoDto){
        return todoDao.doPut(todoDto);
    }
    @DeleteMapping("todo/delete.to")
    public boolean doDelete(int id){
        return todoDao.doDelete(id);
    }
}
