package ezenweb.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service //해당 클래스를 스프링 컨테이너에 등록
public class FileService {

    //어디에 누구를
    String uploadPath ="C:\\Users\\504\\Desktop\\ezen2023b_web1\\build\\resources\\main\\static\\img\\";
    //1.업로드 메소드
    public String fileUpload(MultipartFile multipartFile){
        System.out.println("MemberController.signup");
        // 서버에 업로드 했을때 설계
        // 1. 여러클라이언트가 동일한 파일명으로 서버에게 업로드 했을때 식별깨짐
        //식별 이름: 1.(아이디어)날짜조합+데이터 2.uuid
        //2. 클라이언트 화면 표시
        // 업로드 경로 : 아파치 톰캣에 업로드

        //1.첨부 파일 업로드하기{업로드란: PC에서 바이트(대용량/파일)을 복사해서 서버에 전달
        // 1. 첨부파일을 저장할 경롤
        // File클래스: 파일관련된 메소드 제공
        String uuid = UUID.randomUUID().toString();
        System.out.println("uuid = " + uuid);
        // 파일 이름 조합하기 새로운 식별이름과 실제 파일 이름
        // 식별키와 실제 이름 구분: 왜?? 나중에 쪼개서 구분하려고 (다운로드시 식별키 빼고 제공
        // 혹시나 파일 이름이 구분문자가 있을경우 기준이 깨짐
        //업로드할 경로 설정
        String uploadPath ="C:\\Users\\504\\Desktop\\ezen2023b_web1\\build\\resources\\main\\static\\img\\";

        String filename = uuid+"_"+multipartFile.getOriginalFilename().replaceAll("_", "*");
        File file = new File(uploadPath+filename);
        System.out.println("file = " + file);
        try {
            multipartFile.transferTo(file);

        }
        catch (Exception e){
            System.out.println("e = " + e);
            return null;
        }
        return filename;
    }
    //2.다운로드 메소드

}
