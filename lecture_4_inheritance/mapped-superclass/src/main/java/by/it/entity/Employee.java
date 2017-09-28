package by.it.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Employee extends DateEntity {
    private String name;
    private String company;
    private Double salary;

    @Override
    public String toString() {
        return "Employee{id=" + getId() + ", name='" + name +
                ", company='" + company +
                ", salary=" + salary + ", createdDate=" +
                getCreatedDate() + ", updatedDate=" +
                getUpdatedDate() +'}';
    }
}

