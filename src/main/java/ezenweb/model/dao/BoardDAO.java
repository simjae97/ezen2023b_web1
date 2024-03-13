package ezenweb.model.dao;

import ezenweb.model.dto.BoardDTO;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


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
//    //2-2 전체 게시물 수 호출
//    public int getBoardSize(){
//        try {
//            String sql = "select count(*) from board";
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            if(rs.next()){
//                return rs.getInt(1);
//            }
//        }
//        catch (Exception e){
//            System.out.println("e = " + e);
//        }
//        return -1;
//    }
//    public int getBoardSize(int bcno){
//        try {
//            String sql = "select count(*) from board where bcno = ?";
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1,bcno);
//            rs = ps.executeQuery();
//            if(rs.next()){
//                return rs.getInt(1);
//            }
//        }
//        catch (Exception e){
//            System.out.println("e = " + e);
//        }
//        return -1;
//    }
//    //2. 전체 글 출력 호출
//    public List<BoardDTO> doGetBoardViewList(int pageBoardSize,int startRow){
//        System.out.println("BoardDAO.doGetBoardViewList");
//        BoardDTO boardDTO = null;
//        List<BoardDTO> result = new ArrayList<>();
//        try{
//            String sql = "select * from board b inner join member m on b.mno = m.mno order by b.bdate desc limit ?,?";
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1,startRow);
//            ps.setInt(2,pageBoardSize);
//            rs= ps.executeQuery();
//            while (rs.next()){
//                boardDTO = new BoardDTO(rs.getLong("bno"),
//                        rs.getString("btitle"),
//                        rs.getString("bcontent"),
//                        rs.getString("bfile"),
//                        rs.getLong("bview"),
//                        rs.getString("bdate"),
//                        rs.getLong("mno"),
//                        rs.getLong("bcno"),
//                        null, rs.getString("id"), rs.getString("img") );
//                result.add(boardDTO);
//            }
//        }
//        catch (Exception e){
//            System.out.println("e = " + e);
//        }
//        return result;
//    }
//    public List<BoardDTO> doGetBoardViewList(int pageBoardSize,int startRow , int bcno){
//        System.out.println("BoardDAO.doGetBoardViewList");
//        BoardDTO boardDTO = null;
//        List<BoardDTO> result = new ArrayList<>();
//        try{
//            String sql = "select * from board b inner join member m on b.mno = m.mno where bcno = ? order by b.bdate desc limit ?,?";
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1,bcno);
//            ps.setInt(2,startRow);
//            ps.setInt(3,pageBoardSize);
//            rs= ps.executeQuery();
//            while (rs.next()){
//                boardDTO = new BoardDTO(rs.getLong("bno"),
//                        rs.getString("btitle"),
//                        rs.getString("bcontent"),
//                        rs.getString("bfile"),
//                        rs.getLong("bview"),
//                        rs.getString("bdate"),
//                        rs.getLong("mno"),
//                        rs.getLong("bcno"),
//                        null, rs.getString("id"), rs.getString("img") );
//                result.add(boardDTO);
//            }
//        }
//        catch (Exception e){
//            System.out.println("e = " + e);
//        }
//        return result;
//    }
public int getBoardSize( int bcno ,String key , String keyWord){
    try{
        String sql = "select count(*) from board b inner join member m on b.mno = m.mno";
        // ==================== 1.만약에 카테고리 조건이 있으면 where 추가.
        if( bcno > 0 ){ sql += " where bcno = "+bcno ; }
        if( !keyWord.isEmpty()){System.out.println("검색 키워드가 존재");
            if(bcno > 0){ sql += " and ";}
            else{sql += " where ";}
            sql +=  key+" like '%"+keyWord+"%' ";
        }
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        if( rs.next() ){ return rs.getInt(1); }
    }catch (Exception e ){  System.out.println("e = " + e);}
    return 0;
}
    public List<BoardDTO> doGetBoardViewList( int startRow , int pageBoardSize , int bcno  ,String key ,String keyWord ){ System.out.println("BoardDao.doGetBoardViewList");
        BoardDTO boardDto = null;   List<BoardDTO> list = new ArrayList<>();
        System.out.println("startRow = " + startRow + ", pageBoardSize = " + pageBoardSize + ", bcno = " + bcno + ", key = " + key + ", keyword = " + keyWord);
        try{  // String sql = "select * from board";
            // SQL 앞부분
            String sql = "select * from board b inner join member m " +
                    " on b.mno = m.mno ";
            // SQL 가운데부분 [ 조건에 따라 where 절 추가 ]
            if( bcno > 0 ){ sql += " where bcno = "+bcno ; }
            if( !keyWord.isEmpty()){System.out.println("검색 키워드가 존재");
                if(bcno > 0){ sql += " and ";}
                else{sql += " where ";}
                sql +=  key+" like '%"+keyWord+"%' ";
            }
            // SQL 뒷부분
            sql += " order by b.bdate desc " +
                    " limit ? , ?";

            ps =conn.prepareStatement(sql);
            ps.setInt( 1 , startRow );
            ps.setInt( 2 , pageBoardSize );

            rs = ps.executeQuery();
            while ( rs.next() ){
                boardDto = new BoardDTO( rs.getLong( "bno" ) , rs.getString( "btitle" ) ,
                        rs.getString( "bcontent" ) , rs.getString( "bfile" ) ,
                        rs.getLong("bview") , rs.getString("bdate") ,
                        rs.getLong("mno") , rs.getLong("bcno") , null ,
                        rs.getString("id") , rs.getString("img") );
                list.add( boardDto );
            }
        }catch (Exception e ){ System.out.println("e = " + e);  }
        return list;
    }

    //3-1 개별글 출력시 조회수 증가
    public boolean boardViewIncrease(int bno){
        try {
            String sql = "update board set bview=bview+1 where bno= ? ";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,bno);
            int count =ps.executeUpdate();
            if(count == 1 ){
                return true;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    //3. 개별 글 출력 호출
    public BoardDTO doGetBoardView(int bno ){
        System.out.println("BoardDAO.doGetBoardView");
        BoardDTO boardDTO = null;
        try{
            String sql = "select * from board b inner join member m on b.mno = m.mno where b.bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,bno); rs= ps.executeQuery();
            if(rs.next()){
                boardDTO = new BoardDTO(rs.getLong("bno")
                        ,rs.getString("btitle")
                        ,rs.getString("bcontent")
                        ,rs.getString("bfile")
                        ,rs.getLong("bview")
                        ,rs.getString("bdate")
                        ,rs.getLong("mno")
                        ,rs.getLong("bcno")
                        ,null, rs.getString("id"), rs.getString("img"));

            }
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }
        return boardDTO;
    }
    //4. 글 수정 처리
    public boolean doUpateBoard(BoardDTO boardDTO){
        System.out.println("BoardDAO.doUpateBoard");
        try {
            String sql = "update board set btitle = ? , bcontent = ? , bcno = ?, bfile =? where bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,boardDTO.getBtitle());
            ps.setString(2,boardDTO.getBcontent());
            ps.setLong(3,boardDTO.getBcno());
            ps.setString(4,boardDTO.getBfile());
            ps.setLong(5,boardDTO.getBno());
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }
    //5. 글 삭제 처리
    public boolean doDeleteBoard(int bno){
        try {
            String sql = "delete from board where bno= "+bno;
            ps = conn.prepareStatement(sql);
            int count = ps.executeUpdate(); if(count == 1) return true;
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }
    //6.유효성검사
    public boolean boardwriterAuth(int bno, String mid){
        try {
            String sql = "Select * from board b inner join member m on b.mno = m.mno where bno= ? and id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,bno);
            ps.setString(2,mid);
            rs= ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }

        return false;
    }

    //7. 댓글 등록
    public boolean getReplyWrite( Map<String ,String > map){
        try {
            String sql = "insert into breply(brcontent,brindex,mno,bno) values(?,?,?,?)";
            ps= conn.prepareStatement(sql);
            ps.setString(1,map.get("brcontent"));
            ps.setString(2,map.get("brindex"));
            ps.setString(3,map.get("mno"));
            ps.setString(4,map.get("bno"));
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }
    //8.댓글 출력
    public List<Map<String,Object >> getReplyDo(int bno){
        List <Map<String ,Object >> result = new LinkedList<>();
        try {
            String sql = "select * from breply where brindex = 0 and bno="+bno;
            ps = conn.prepareStatement(sql);
            rs= ps.executeQuery();
            while (rs.next()){
                // 상위댓글 하나씩 객체화 하는곳
                Map<String ,Object > map = new HashMap<>();
                map.put("brno",rs.getString("brno"));
                map.put("brcontent",rs.getString("brcontent"));
                map.put("brdate",rs.getString("brdate"));
                map.put("mno",rs.getString("mno"));
                // -------해당 상위 댓글의 하위 댓글도 호출하기 ---------------//
                String sql2 = "select * from breply where brindex = ?  and bno = "+bno;
                ps = conn.prepareStatement(sql2);
                ps.setInt(1,Integer.parseInt(rs.getString("brno" ) ) );
                    //(int) vs Integer.parsInt()
                // rs 사용하면 안되는 이유 : 이미 위의 while문에서 사용중 이므로
                ResultSet rs2 = ps.executeQuery();
                List<Map<String ,Object>> subList = new ArrayList<>();
                while (rs2.next()) {
                    Map<String, Object> subMap = new HashMap<>(); // 댓글 답변
                    subMap.put("brno", rs2.getString("brno"));
                    subMap.put("brcontent", rs2.getString("brcontent"));
                    subMap.put("brdate", rs2.getString("brdate"));
                    subMap.put("mno", rs2.getString("mno"));
                    subList.add(subMap);
                }
                map.put("supReply",subList);
                //종료
                result.add(map);
            }
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }

        return result;
    }

}
