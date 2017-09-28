package by.academy.it.pojos;
import lombok.*;

import javax.persistence.*;
@Data
@NoArgsConstructor
@Entity @Table(name = "PERSON")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PERSON_TYPE",
        discriminatorType = DiscriminatorType.CHAR)
//@DiscriminatorFormula("case when COMPANY is not null then 'E' else 'S' end")
//@DiscriminatorOptions(force = false, insert = true)
@DiscriminatorValue("P")
public class Person {
    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    private String surname;
}


