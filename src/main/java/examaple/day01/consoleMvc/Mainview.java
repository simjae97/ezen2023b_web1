package examaple.day01.consoleMvc;

import java.util.ArrayList;
import java.util.Scanner;

public class Mainview {
    TodoController todoController = new TodoController();
    Scanner scanner= new Scanner(System.in);
    //1.메인 페이지
    public void home(){
        while (true){
            doGet();
            System.out.println("1.할일 등록: ");
            int ch = scanner.nextInt();
            if (ch == 1) {doPost();} // 할일목록 출력
        }
    }

    public void doPost(){
        System.out.println("할일 내용:"); String content = scanner.next();
        System.out.println("마감일[yyyy-mm-dd] : "); String deadline = scanner.next();
        TodoDto todoDto =new TodoDto();
        todoDto.setContent(content);
        todoDto.setDeadline(deadline);
        boolean result = todoController.doPost(todoDto);
        System.out.println(result);
    }

    public void doGet(){
        ArrayList<TodoDto> result = todoController.doGet();
        System.out.println("hello");
        for(int i=0; i<result.size(); i++){
            TodoDto todoDto = result.get(i);
            System.out.printf("%2s %10s %10s %10s \n",
                    todoDto.getId(),
                    todoDto.getDeadline(),
                    todoDto.isState(),
                    todoDto.getContent());
        }
    }
}
