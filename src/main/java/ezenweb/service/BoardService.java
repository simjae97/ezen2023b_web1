package ezenweb.service;

import ezenweb.model.dao.BoardDAO;
import ezenweb.model.dto.BoardDTO;
import ezenweb.model.dto.BoardPageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Service
public class BoardService{

    @Autowired
    private BoardDAO boardDAO;

    @Autowired
    private FileService fileService;


    //1. 글쓰기 처리
    public long doPostBoardWrite(BoardDTO boardDTo){
        System.out.println("BoardService.doPostBoardWrite");
        if(!boardDTo.getUploadfile().isEmpty()){
            String filename = fileService.fileUpload(boardDTo.getUploadfile());
            if(filename != null){
                boardDTo.setBfile(filename);
            }
            else {
                return -1;
            }
        }
        return boardDAO.doPostBoardWrite(boardDTo);
    }
    //2. 전체 글 출력 호출
    public BoardPageDTO doGetBoardViewList(int page , int pageBoardSize , int bcno, String key, String keyword){
        System.out.println("BoardService.doGetBoardViewList");
        //1.페이지 당 게시물을 출력할 개수
//        int pageBoardSize = 1;

        //2. 페이지당 게시물을 출력할 시작 레코드 번호
        int startRow = (page-1)*pageBoardSize;

        //3.총 페이지수
//            //1.전체 게시물 수
//        int totalpage = boardDAO.getBoardSize(pageBoardSize);
        //ceil함수 안쓸때
//        int totalBoardSize = bcno==0? boardDAO.getBoardSize() : boardDAO.getBoardSize(bcno);
        int totalBoardSize = boardDAO.getBoardSize(bcno, key , keyword);
        int totalpage = (totalBoardSize+pageBoardSize-1)/pageBoardSize;
            //2.총 페이지수 계산
//        List<BoardDTO> list = bcno==0? boardDAO.doGetBoardViewList(pageBoardSize,startRow): boardDAO.doGetBoardViewList(pageBoardSize,startRow,bcno);
        List<BoardDTO> list = boardDAO.doGetBoardViewList( startRow , pageBoardSize , bcno ,key , keyword );
        //페이징 버튼 개수
            //1.페이지 버튼 최대 개수
        System.out.println("totalpage = " + totalpage);

        int btnsize = 5;
            //2.페이지 버튼 시작 번호
        int startbtn = (page-1)/btnsize*btnsize+1;
            //3 페이지 버튼 끝번호
        int endbtn = startbtn+btnsize-1;
            //만약 페이지 버튼의 끝 번호가 총 페이지 수보다는 커질수 없다
        if(endbtn >= totalpage){ endbtn = totalpage; }
        BoardPageDTO boardPageDTO = BoardPageDTO.builder()
                .page(page)
                .totalBoardSize(totalBoardSize)
                .totalpage(totalpage)
                .list(list)
                .startbtn(startbtn)
                .endbtn(endbtn)
                .build();
        return boardPageDTO;
    }
    //3. 개별 글 출력 호출
    public BoardDTO doGetBoardView(int bno){
        System.out.println("BoardService.doGetBoardView");

        if(boardDAO.boardViewIncrease(bno)) {
            return boardDAO.doGetBoardView(bno);
        }
        return null;
    }
    //4. 글 수정 처리
    public boolean doUpateBoard(BoardDTO boardDTO){
        //새로운 첨부파일이 있는지없는지
        String bfile = boardDAO.doGetBoardView((int)boardDTO.getBno()).getBfile();
        if(!boardDTO.getUploadfile().isEmpty()){
            // 새로운 첨부파일을 업로드하고 기존 첨부파일을 삭제
            String filename = fileService.fileUpload((boardDTO.getUploadfile()));
            if(filename != null){ //업로드 성공
                boardDTO.setBfile(filename); //새로운 첨부파일의 이름 DTO 삭제
                //기존 첨부파일 삭제
                bfile = boardDAO.doGetBoardView((int)boardDTO.getBno()).getBfile();
                fileService.fileDelete(bfile);
            }
            else {
                return false;
            }
        }
        else {
            boardDTO.setBfile(bfile);
        }
        return boardDAO.doUpateBoard(boardDTO);
    }
    //5. 글 삭제 처리
    public boolean doDeleteBoard(int bno){
        // 레코드 삭제하기 전에 삭제할 첨부파일명을 임시로 꺼내온다
        String bfile = boardDAO.doGetBoardView(bno).getBfile();
        boolean result = boardDAO.doDeleteBoard(bno);
        if(result){
            //기존에 첨부파일이 있었으면
            if(bfile != null) {
                return fileService.fileDelete(bfile);
            }
        }
        return result;
    }
    //6. 게시물 작성자 인증

    public boolean boardwriterAuth(int bno, String mid){
        return boardDAO.boardwriterAuth(bno,mid);
    }
    //7. 댓글 등록
    public boolean getReplyWrite( Map <String ,String > map){
        return boardDAO.getReplyWrite(map);
    }
    //8.댓글 출력
    public List<Map<String,Object >> getReplyDo(int bno){
        return boardDAO.getReplyDo(bno);
    }
}
