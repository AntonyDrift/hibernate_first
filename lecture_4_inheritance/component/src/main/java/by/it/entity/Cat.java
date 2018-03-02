package by.it.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Yauheni Krasko on 08.10.2017.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Cat {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column
    @Access(AccessType.FIELD)
    private Integer age;

    @Column
    @Access(AccessType.PROPERTY)
    private String name;

    @Embedded
    @AttributeOverrides( value = {@AttributeOverride(name = "firstVisit", column = @Column(name = "FIRST_VISIT"))})
    private VetVisit vetVisit;
}
