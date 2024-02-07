package examaple.day02.consoleMvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TodoDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    //2.생성자 : db연동콬드
    public TodoDao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springweb", "root", "1234");


        }
        catch (Exception e){
            System.out.println("db연동"+e);
        }


    }

    public boolean doPost(TodoDto todoDto){
        try {
            String sql = "insert into todo(content,deadline) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,todoDto.getContent());
            ps.setString(2,todoDto.getDeadline());
            int count =ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        return false;
    }

    public ArrayList<TodoDto> doGet(){
        ArrayList<TodoDto> list = new ArrayList<>();
        try {
            String sql = "select * from todo";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                TodoDto todoDto = new TodoDto();
                todoDto.setDeadline(rs.getString("deadline"));
                todoDto.setContent(rs.getString("content"));
                todoDto.setId(rs.getInt("id"));
                todoDto.setState(rs.getBoolean("state"));
                list.add(todoDto);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return list;
    }
    public boolean doPut(TodoDto todoDto){
        try {
            String sql = "update todo set state =? where id = ? ";
            ps= conn.prepareStatement(sql);
            ps.setBoolean(1, todoDto.isState());
            ps.setInt(2,todoDto.getId());
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean doDelete(int id){
        try {
            String sql = "delete from todo where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            int count = ps.executeUpdate();
            if(count == 1){return true;}
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
}
