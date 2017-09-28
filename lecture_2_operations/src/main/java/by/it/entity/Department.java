package by.it.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by yslabko on 09/24/2017.
 */
@Entity
@Data
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private  String name;
}
