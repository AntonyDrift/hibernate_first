package by.it.pojos;
import lombok.*;
import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity @Table(name = "PERSON")
@Inheritance(strategy= InheritanceType.JOINED)
public class Person {
    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    private String surname;
}