package ezenweb.model.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BoardDTO {
    private long bno ;          // 번호
    private String btitle;      // 제목
    private String bcontent;        // 내용
    private String bfile;       // 첨부파일 [ 첨부파일 이름 출력용 - DB처리 ]
    private long bview ;        // 조회수
    private String bdate;       // 작성일
    private long mno ;          // 작성자 번호
    private long bcno ;         // 카테고리
    private MultipartFile uploadfile; // 실제 첨부파일.[ DB처리X , 서버에 저장

    private String mid;
    private String mimg;
}


/*
    글쓰기용
        -입력받기 : btitle , bcontent , bfile , bcno
        -서버처리: bno 자동 bview bdate 기본값 mno 로그인세션
    개별 출력용
        - 출력용 :bno 어쩌구 저쩌구
     -빌더 패턴
 */