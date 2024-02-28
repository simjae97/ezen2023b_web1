package ezenweb.service;

import ezenweb.model.dao.MemberDAO;
import ezenweb.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    // 1.회원가입 서비스
    @Autowired
    FileService fileService;
    @Autowired
    MemberDAO memberDAO;
    public boolean doPostSignup(MemberDTO memberDTO) {
        boolean result = false;
        String fileName = "default.png";
        if(!memberDTO.getImg().isEmpty()){
            fileName = fileService.fileUpload(memberDTO.getImg());
            if (fileName == null) {
                return false;
            }
        }
        //1.파일 처리
        memberDTO.setUuidFile(fileName);
            //2.DB 처리 // DTO에 업로드 성공한 파일명을 대입
        result = memberDAO.doPostsignup(memberDTO);
        return result;
    }

    //2.로그인 서비스

    //3.회원정보 요청 서비스
    public MemberDTO doGetLoginInfo(String id){
        // 1. DAO 호출
        return memberDAO.doGetLoginInfo(id);
    }
}