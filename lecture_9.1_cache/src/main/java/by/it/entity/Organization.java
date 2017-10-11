package by.it.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import lombok.Data;

/**
 * Class Organization
 * <p>
 * Created by yslabko on 04/03/2017.
 */
@Data
@Entity
@Table(name = "T_ORGANIZATION")
@OptimisticLocking(type = OptimisticLockType.VERSION )
//OptimisticLockType DIRTY | ALL | VERSION | NONE
@DynamicUpdate
public class Organization {
    @Column(name = "ORGANIZATION_ID")
    Long id;

}
