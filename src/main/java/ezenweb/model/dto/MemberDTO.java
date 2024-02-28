package ezenweb.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
//    private String img;
    private MultipartFile img;
    private String uuidFile;
}
