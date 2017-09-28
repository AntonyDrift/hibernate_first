package by.academy.it.pojos;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
@DiscriminatorValue("E")
@DynamicInsert
@DynamicUpdate
public class Employee extends Person {
    private static final long serialVersionUID = 4L;

    private String company;
    private Double salary;
}

