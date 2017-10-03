package by.it.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data @NoArgsConstructor
@Entity
@Table(name = "DEPARTMENT")
public class Department implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "DEPARTMENT_ID", unique = true)
    private Long departmentId;
    @Column(name = "NAME")
    private String departmentName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Employee> employees = new HashSet<>(0);
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
}