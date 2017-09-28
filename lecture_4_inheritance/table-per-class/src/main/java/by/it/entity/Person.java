package by.it.entity;
import lombok.*;

import javax.persistence.*;
@Data
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "PERSON")
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Person {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private int age;
    @Column
    private String name;
    @Column
    private String surname;
}


