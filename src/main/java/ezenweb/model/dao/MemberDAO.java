package ezenweb.model.dao;

import ezenweb.model.dto.LoginDTO;
import ezenweb.model.dto.MemberDTO;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class MemberDAO extends DAO{

    // 1. 회원가입 메소드
    public boolean doPostsignup(MemberDTO memberDTO){
        try {
            String sql = "Insert into member(id,pw,name,email,phone,img) values(?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, memberDTO.getId());
            ps.setString(2, memberDTO.getPw());
            ps.setString(3, memberDTO.getName());
            ps.setString(4, memberDTO.getEmail());
            ps.setString(5, memberDTO.getPhone());
            ps.setString(6, memberDTO.getImg());
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
    //2.로그인
    public int dologin(LoginDTO memberDTO){
        try {
            String sql = "select no from member where id = ? and pw = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, memberDTO.getId());
            ps.setString(2, memberDTO.getPw());
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }
}
