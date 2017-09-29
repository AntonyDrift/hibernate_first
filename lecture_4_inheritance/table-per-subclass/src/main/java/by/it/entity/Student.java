package by.it.entity;

import lombok.*;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import javax.persistence.*;
@Data
@NoArgsConstructor
@Entity
@Table(name = "STUDENT")
@PrimaryKeyJoinColumn(name="PERSON_ID")
public class Student extends Person {
    private String faculty;
    private Double mark;

    public Student(Integer id, int age, String name, String surname, String faculty, Double mark) {
        super(id, age, name, surname);
        this.faculty = faculty;
        this.mark = mark;
    }
}

