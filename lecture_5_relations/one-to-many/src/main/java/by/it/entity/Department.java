package by.it.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "DEPARTMENT")
public class Department {
    @Id @GeneratedValue
    @Column(name = "DEPARTMENT_ID")
    private Long departmentId;
    @Column(name = "NAME")
    private String departmentName;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
//    @BatchSize(size = 3)
    private List<Employee> employees = new ArrayList<>();

    public Department(String name) {
        this.departmentName = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        if (departmentId != null ? !departmentId.equals(that.departmentId) : that.departmentId != null) return false;
        return departmentName != null ? departmentName.equals(that.departmentName) : that.departmentName == null;
    }

    @Override
    public int hashCode() {
        int result = departmentId != null ? departmentId.hashCode() : 0;
        result = 31 * result + (departmentName != null ? departmentName.hashCode() : 0);
        return result;
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.setDepartment(null);
    }
}
