package by.it.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Class DateEntity
 *
 * Created by yslabko on 01/23/2017.
 */
@Data @ToString
@NoArgsConstructor
@MappedSuperclass
public class DateEntity {
    @Id
    @GeneratedValue
    private Long id;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updatedDate;
}
