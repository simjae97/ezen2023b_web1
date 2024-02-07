package examaple.day02.consoleMvc;

import java.util.ArrayList;
import java.util.Scanner;

public class Mainview {
    TodoController todoController = new TodoController();
    Scanner scanner= new Scanner(System.in);
    //1.메인 페이지
    public void home(){
        while (true){
            doGet();
            System.out.println("1.할일 등록 2.할일상태 변경 3.할일 삭제");
            int ch = scanner.nextInt();
            if (ch == 1) {doPost();} // 할일목록 출력
            else if(ch == 2){doPut();}
            else if (ch== 3){doDelete();}
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
        for(int i=0; i<result.size(); i++){
            TodoDto todoDto = result.get(i);
            System.out.printf("%2s %10s %10s %10s \n",
                    todoDto.getId(),
                    todoDto.getDeadline(),
                    todoDto.isState(),
                    todoDto.getContent());
        }
    }

    public void doPut(){
        System.out.println("수정할 todo id: ");
        int id = scanner.nextInt();
        System.out.println("수정할 상태:");
        boolean state = scanner.nextBoolean();
        TodoDto todoDto = new TodoDto();
        todoDto.setId(id);
        todoDto.setState(state);
        if(todoController.doPut(todoDto)){
            System.out.println("실행성공");
        }
    }
    public void  doDelete(){
        System.out.println("삭제할 todo id:");
        int id = scanner.nextInt();
        if(todoController.doDelete(id)){
            System.out.println("실행성공");
        }
    }
}
