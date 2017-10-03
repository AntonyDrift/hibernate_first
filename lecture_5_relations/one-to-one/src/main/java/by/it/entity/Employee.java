package by.it.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="EMPLOYEE")
public class Employee implements Serializable {
    @Id @GeneratedValue
    @Column(name = "EMPLOYEE_ID", unique = true)
    private Long employeeId;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @CreationTimestamp
    private LocalDateTime date;

    @OneToOne(mappedBy = "employee",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private EmployeeDetail employeeDetail;
}
