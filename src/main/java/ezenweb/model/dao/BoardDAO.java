package ezenweb.model.dao;

import ezenweb.model.dto.BoardDTO;
import org.springframework.stereotype.Component;

import java.sql.Statement;


@Component
public class BoardDAO extends DAO{
    //1. 글쓰기 처리
    public long doPostBoardWrite( BoardDTO boardDto){
        System.out.println("BoardDao.doPostBoardWrite");
        try {
            String sql = "insert into board(btitle,bcontent,bfile,mno,bcno) values(?,?,?,?,?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,boardDto.getBtitle());
            ps.setString(2,boardDto.getBcontent());
            ps.setString(3, boardDto.getBfile());
            ps.setLong(4,boardDto.getMno());
            ps.setLong(5,boardDto.getBcno());
            int count = ps.executeUpdate();
            if(count == 1){
                rs = ps.getGeneratedKeys();
                if(rs.next()){
                    return rs.getLong(1);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("e = " + e);
        }
        System.out.println("boardDto = " + boardDto);
        return 0;
    }
    //2. 전체 글 출력 호출

    //3. 개별 글 출력 호출
    public BoardDTO doGetBoardView(int bno){
        System.out.println("BoardDAO.doGetBoardView");
        BoardDTO boardDTO = null;
        try{
            String sql = "select * from board where bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,bno); rs= ps.executeQuery();
            if(rs.next()){
                boardDTO = new BoardDTO(rs.getLong("bno"),rs.getString("btitle"),rs.getString("bcontent"),rs.getString("bfile"),rs.getLong("bview"),rs.getString("bdate"),rs.getLong("mno"),rs.getLong("bcno"),null);

            }
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }
        return boardDTO;
    }
    //4. 글 수정 처리

    //5. 글 삭제 처리

}
