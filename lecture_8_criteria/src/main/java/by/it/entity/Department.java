package by.it.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Department
 *
 * Created by yslabko on 09/08/2017.
 */
@Entity
@Data
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList();
    public Department(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
