package by.it.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private FullName fullName;
    private String company;
    private Double salary;
}

