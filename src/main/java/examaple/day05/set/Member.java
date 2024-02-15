package examaple.day05.set;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@ToString
public class Member {
    public String name;
    public int age;

    //hashCode , equals -> 메소드의 주인은 object
    @Override
    public int hashCode() {
        return name.hashCode()+age;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Member target){
            return target.name.equals(name) && (target.age == age);
        }
        else {
            return false;
        }
    }

}
