package ezenweb.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class MemberDTO {
    private int no;
    private String id;
    private String pw;
    private String name;
    private String email;
    private String phone;
    private String img;

}
