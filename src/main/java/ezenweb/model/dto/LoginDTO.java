package ezenweb.model.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter@Setter
public class LoginDTO {
    private int no;
    private String id;
    private String pw;
}
