package by.it.pojos;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
@PrimaryKeyJoinColumn(name="PERSON_ID")
public class Employee extends Person {
    private static final long serialVersionUID = 4L;

    private String company;
    private Double salary;
}
