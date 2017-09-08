package by.it.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Power
 *
 * Created by yslabko on 09/06/2017.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Power {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String name;
    @Column
    private int power;
    public Power(String name, int power) {
        this.name = name; this.power = power;
    }

}
