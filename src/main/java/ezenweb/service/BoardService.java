package ezenweb.service;

import ezenweb.model.dao.BoardDAO;
import ezenweb.model.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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

    //3. 개별 글 출력 호출
    public BoardDTO doGetBoardView(int bno){
        System.out.println("BoardService.doGetBoardView");
        return boardDAO.doGetBoardView(bno);
    }
    //4. 글 수정 처리

    //5. 글 삭제 처리

}
