package examaple.day08._2인과제수업.member.dto;

import lombok.*;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString
public class BoardDTO {
    private int bno;
    private String bcontent;
    private String bwriter;
    private String bpassword;

    public BoardDTO(String bcontent, String bwriter, String bpassword) {
        this.bcontent = bcontent;
        this.bwriter = bwriter;
        this.bpassword = bpassword;
    }
}
