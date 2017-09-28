package by.academy.it.pojos;

import lombok.*;
import javax.persistence.*;
@Data
@NoArgsConstructor
@Entity
@Table(name = "STUDENT")
public class Student extends Person {
    private static final Long serialVersionUID = 3L;

    private String faculty;
    private Double mark;
}

