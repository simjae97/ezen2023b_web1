package examaple.day08._2인과제수업.controller;

import examaple.day08._2인과제수업.member.dao.BoardDAO;
import examaple.day08._2인과제수업.member.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    BoardDAO boardDAO;
    @PostMapping("/board/create")
    @ResponseBody
    public boolean create(BoardDTO boardDTO){
        boolean result = boardDAO.create(boardDTO);
        System.out.println(boardDTO);
        return result;
    }
    @GetMapping("/board/read")
    public List<BoardDTO> read(){
        List<BoardDTO> result = boardDAO.read();
        return result;
    }
    @PostMapping("/board/delete/{bno}")
    @ResponseBody
    public boolean delete(@PathVariable int bno){
        System.out.println(bno);
        boolean result = boardDAO.delete(bno);
        return result;
    }
    @PostMapping("/board/update")
    @ResponseBody
    public boolean update(BoardDTO boardDTO){
        boolean result = boardDAO.update(boardDTO);
        return result;
    }

    // =================view rest=======================//
    // 1. html (STATIC) : http://localhost:/day08board.html로 직접
    // 2. 머스터치(template) 컨트롤러 반환(model)

}
