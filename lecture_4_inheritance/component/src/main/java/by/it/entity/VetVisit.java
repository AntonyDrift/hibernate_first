package by.it.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Yauheni Krasko on 14.10.2017.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class VetVisit {
    @CreationTimestamp
    private Date firstVisit;
    @Column (insertable = false)
    private Date lastVisit;
}
