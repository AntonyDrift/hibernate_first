package by.it.pojos;

import lombok.*;
import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STUDENT")
@PrimaryKeyJoinColumn(name="PERSON_ID")
public class Student extends Person {
    private static final Long serialVersionUID = 3L;

    private String faculty;
    private Double mark;
}

