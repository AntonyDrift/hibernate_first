package by.it.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(exclude = "department")
@ToString(exclude = "department")
@Entity
public class Employee implements Serializable {
    @Id @GeneratedValue
    private Long employeeId;
    private String firstName;
    private String lastName;
    @CreationTimestamp
    private LocalDateTime date;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private EmployeeDetail employeeDetail;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;
}
