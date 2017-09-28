package by.academy.it.pojos;
import lombok.*;
import javax.persistence.*;
@Data
@NoArgsConstructor
@Entity @Table(name = "PERSON")
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Person {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String surname;
}


