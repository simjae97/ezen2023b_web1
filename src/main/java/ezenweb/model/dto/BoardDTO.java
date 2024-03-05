package ezenweb.model.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardDTO {
    long bno ;          // 번호
    String btitle;      // 제목
    String bcontent;        // 내용
    String bfile;       // 첨부파일 [ 첨부파일 이름 출력용 - DB처리 ]
    long bview ;        // 조회수
    String bdate;       // 작성일
    long mno ;          // 작성자 번호
    long bcno ;         // 카테고리
    MultipartFile uploadfile; // 실제 첨부파일.[ DB처리X , 서버에 저장
}


/*
    글쓰기용
        -입력받기 : btitle , bcontent , bfile , bcno
        -서버처리: bno 자동 bview bdate 기본값 mno 로그인세션
 */