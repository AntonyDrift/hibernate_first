package by.it.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer age;
    @Column
    private double salary;
    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID", nullable = false)
    private Department department;

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name +
                ", age=" + age + ", salary=" + salary + "\n\t" + department + '}';
    }
}

