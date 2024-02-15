package examaple.day03.webMVC;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TodoDto {
    private int id;
    private String content;
    private String deadline;

    private boolean state;

}
