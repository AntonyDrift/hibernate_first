package by.it.entity;
import lombok.*;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import javax.persistence.*;
@Data
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "PERSON")
@Inheritance(strategy= InheritanceType.JOINED)
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class Person implements Personable {
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


