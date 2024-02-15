package book.dto;


import lombok.*;


@AllArgsConstructor // 컴파일시 모든 필드 생성자를 자동으로 만들어준다
@NoArgsConstructor //컴파일시 기본 생성자를 자동으로 만들어준다
@ToString   // 컴파일시 toString을 자동으로 만들어준다
@Getter // 컴파일시 getter/setter를 만들어준다
@Setter
public class ArticleForm {
    //1.필드
        //관례적으로 모든 필드는 직접접근을 허용하지않는다 private , 안정성 보장 , 캡슐화 , getter ,setter 생성자
    private long id;
    private String title;
    private String content;
    //2.생성자
//    public ArticleForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }
    //3.메소드


}
