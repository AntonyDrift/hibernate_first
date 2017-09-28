package by.academy.it.pojos;

import lombok.*;
import javax.persistence.*;
@Data
@NoArgsConstructor
@Entity
@Table(name = "STUDENT")
@DiscriminatorValue("S")
public class Student extends Person {
    private static final Long serialVersionUID = 3L;

    private String faculty;
    private Double mark;
}

