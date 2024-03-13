package ezenweb.service;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
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
        uploadPath ="C:\\Users\\504\\Desktop\\ezen2023b_web1\\build\\resources\\main\\static\\img\\";

        String filename = uuid+"_"+multipartFile.getOriginalFilename().replaceAll("_", "-");
        File file = new File(uploadPath+filename);
        System.out.println("file = " + file);
        try {
            multipartFile.transferTo(file);

        }
        catch (Exception e){
            System.out.println("안녕안녕 = " + e);
            return null;
        }
        return filename;
    }
    @Autowired
    private HttpServletRequest request; // 요청을 보낸 정보가 담겨있는 객체

    @Autowired
    private HttpServletResponse response; //http로 응답을 보낼 정보와 기능을 가지고 있는 객체
    //2.다운로드 메소드
    public void getBoardFileDownload(String bfile){
        System.out.println("dd");
        try {


            //1. 다운로드할 파일의 경로와 파일명 연결해서 해당 파일 찾기
            String downloadPath = uploadPath + bfile;
            System.out.println(downloadPath);

            //2.해당 파일을 객체로 가져오기[file클래스] => 메소드 이용하기 위함
            File file = new File(downloadPath);
            //3. 파일의 유효성 검사: 존재하는지 여부 체크
            if (file.exists()) {
                System.out.println("첨부파일 있다.");
                // http로 응답시 응답방법에 대한 정보를 추가
                    // 기본값 url은 한글 지원이 안된다
                    // url에 한글지원 하기 위해서는 urlencoder.encode(url정보,"utf-8)
                response.setHeader("Content-Disposition","attachement;filename="+ URLEncoder.encode(bfile.split("_")[1],"utf-8"));
                // 1. 해당 파일을 바이트로 불러온다.
                    //1-1 파일 입력스트림 객체 생성
                BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
                    //1-2 바이트 배열(고정길이) vs 리스트(가변길이)
                        //1.파일의 크기/사이즈/용량 : 파일의 크기만큼 바이트 배열 선언하기 위해
                    long filesize = file.length();
                    byte[] bytes = new byte[(int)filesize]; // 배열의 길이는 int형
                    //1-2 읽어온 파일의 바이트 넣어줌
                    fin.read(bytes);
                // 2. 불러온 바이트를 http response 이용한 바이트로 응답한다.
                    //2-1 http 응답 스트림 객체 생성
                BufferedOutputStream fout = new BufferedOutputStream(response.getOutputStream());
                    // 2-2 응답스트림.write(내보낼 바이트 배열) 내보내기 할 바이트 배열 준비 상태이면 내보내기
                fout.write(bytes);

                //---버퍼 초기화
                fin.close();
                fout.close();

            } else {
                System.out.println("첨부파일 없다.");
            }
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }
    }

    //3.파일 삭제[게시물 삭제시 만약에 첨부파일이 있으면 첨부파일도 같이 삭제, 게시물 수정시 첨부파일 변경하면 기존파일 삭제
    public boolean fileDelete(String bfile){

        String filePath = uploadPath+bfile;
        File file = new File(filePath);
        if(file.exists()){ //만약에 해당 경로에 파일이 존재하면 삭제
            return file.delete(); //해당 경로의 파일을 삭제
        }
         return false;
    }
}
