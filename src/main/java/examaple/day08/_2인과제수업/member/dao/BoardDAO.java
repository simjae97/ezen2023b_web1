package examaple.day08._2인과제수업.member.dao;

import examaple.day08._2인과제수업.member.dto.BoardDTO;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class BoardDAO {
    private Connection conn; // DB연동 결과 객체를 연결 , 기재된 SQL Statement객체 반환.
    private PreparedStatement ps;  // 기재된 SQL에 매개변수 할당 , SQL 실행
    private ResultSet rs;          // select 결과 여러개 레코드를 호출
    public BoardDAO(){         // db연동를 생성자에서 처리
        try {
            // 1. mysql JDBC 호출 ( 각 회사별  상이 , 라이브러리 다운로드 )
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 해당 db서버의 주소와 db연동
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day08", "root", "1234");
        }catch (Exception e ){   System.out.println(e); }
    }

    public boolean create(BoardDTO boardDTO){
        try {
            String sql = "insert into board(bcontent,bwriter,bpassword) values(?,?,?)";
            ps =conn.prepareStatement(sql);
            ps.setString(1, boardDTO.getBcontent());
            ps.setString(2,boardDTO.getBwriter());
            ps.setString(3, boardDTO.getBpassword());
            ps.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    public List<BoardDTO> read(){
        List<BoardDTO> result = new ArrayList<>();
        try {
            String sql = "select * from board";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                result.add(new BoardDTO(rs.getInt("bno"),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return result;
    }

    public boolean delete(int bno){
        try {
            String sql = "delete from board where bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,bno);
            ps.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean update(BoardDTO boardDTO){
        try {
            String sql = "update board set bcontent = ? where bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, boardDTO.getBcontent());
            ps.setInt(2,boardDTO.getBno());
            ps.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
}
