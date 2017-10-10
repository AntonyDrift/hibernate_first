package by.it.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Created by yslabko on 10/08/2017.
 */
@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Cat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Audited
    private Long id;
    @Audited
    private String name;
    @Audited
    private String owner;
    private String color;
    @Audited
    private int age;
    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    private Date updatedDate;
}
