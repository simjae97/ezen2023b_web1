package ezenweb.controller;




import ezenweb.model.dto.BoardDTO;
import ezenweb.model.dto.BoardPageDTO;
import ezenweb.model.dto.MemberDTO;
import ezenweb.service.BoardService;
import ezenweb.service.FileService;
import ezenweb.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private FileService fileService;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private MemberService memberService;

    //1. 글쓰기 처리
    @PostMapping("/write.do")
    @ResponseBody
    public long doPostBoardWrite(BoardDTO boardDTo){
        System.out.println("BoardController.doPostBoardWrite");

        // 1. 현재 로그인된 세션 호출
        Object object = request.getSession().getAttribute("loginDto");
        if(object == null){
            return 0;
        }

        String mid = (String) object;

        int mno = memberService.doGetLoginInfo(mid).getNo();

        boardDTo.setMno(mno);

        return boardService.doPostBoardWrite(boardDTo);
    }
    //2. 전체 글 출력 호출
    @GetMapping("/do") // 쿼리스트링 매개변수 : 현재페이지
    @ResponseBody
    public BoardPageDTO doGetBoardViewList(Integer page , int pageBoardSize , int bcno,String key , String keyword){
        System.out.println("BoardController.doGetBoardViewList");
        return boardService.doGetBoardViewList(page , pageBoardSize , bcno , key , keyword);
    }
    //3. 개별 글 출력 호출
    @GetMapping("/view.do")
    @ResponseBody
    public BoardDTO doGetBoardView(@RequestParam int bno){
        System.out.println("BoardController.doGetBoardView");
        return boardService.doGetBoardView(bno);
    }
    //4. 글 수정 처리
    @PutMapping("/update.do")
    @ResponseBody
    public boolean doUpateBoard(BoardDTO boardDTO){
        Object object = request.getSession().getAttribute("loginDto");
        if(object != null) {
            String mid = (String) object;
            boolean result =boardService.boardwriterAuth((int)boardDTO.getBno(),mid);
            if(result) {
                return boardService.doUpateBoard(boardDTO);
            }
        }
        return false;
    }
    //5. 글 삭제 처리
    @DeleteMapping("/delete.do")
    @ResponseBody
    public boolean doDeleteBoard(int bno){
        Object object = request.getSession().getAttribute("loginDto");
        if(object != null) {
            String mid = (String) object;
            boolean result =boardService.boardwriterAuth(bno,mid);
            if(result){
            return boardService.doDeleteBoard(bno);
            }
        }
        return false;
    }

    //6. 다운로드 처리 (1.매개변수:무엇을?:파일명  2.반환 3.사용처: get http 요청 )
    @GetMapping("/file/download")
    @ResponseBody
    public void getBoardFileDownload( @RequestParam String bfile){
        System.out.println("boardService = " + bfile);
//        //1.
//        FileService fileService1 = new FileService();
//        fileService1.getBoardFileDownload(bfile);
//        //2.
//        new FileService().getBoardFileDownload(bfile);
//        //3.싱글톤
//        FileService.getInstance().fileDownload(bfile);
//        //4.스태틱
//        FileService.fileDownload(bfile);
//        //5.AutoWired
        fileService.getBoardFileDownload(bfile);
    }

    //7.댓글 작성 (content, brindex,mno,bno)
    @PostMapping("/reply/write.do")
    @ResponseBody
    public boolean getReplyWrite( @RequestParam Map <String ,String > map){
        System.out.println("BoardController.getReplyWrite");
        System.out.println("map = " + map);
        //작성자(회원번호)
        Object object = request.getSession().getAttribute("loginDto");
        if(object == null){
            return false;
        }

        String mid = (String) object;

        int mno = memberService.doGetLoginInfo(mid).getNo();

        map.put("mno", mno+"");
        return boardService.getReplyWrite(map);
    }
    //8.댓글 출력 (brno, brcontent, brdate , brindex , mno)
    @GetMapping("/reply/do")
    @ResponseBody
    public List<Map<String,String >> getReplyDo(int bno){
        System.out.println("BoardController.getReplyDo");
        System.out.println("bno = " + bno);
        return boardService.getReplyDo(bno);
    }

    //============================================================================//

    //1. 글 작성 페이지 이동
    @GetMapping("/write")
    public String getBoardWrite(){
        return "ezenweb/board/write";
    }
    //2. 게시판 페이지 이동
    @GetMapping("/")
    public String getBoard(){
        return "ezenweb/board/board";
    }
    //3. 게시판 상세 페이지 이동
    @GetMapping("/view")
    public String getBoardView( int bno ){
        return "ezenweb/board/view";
    }
    //4. 글 수정 페이지 이동
    @GetMapping("/update")
    public String getBoardUpdate(){
        return "ezenweb/board/update";
    }
}
