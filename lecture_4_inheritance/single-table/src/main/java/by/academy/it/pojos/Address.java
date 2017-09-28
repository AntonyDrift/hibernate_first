package by.academy.it.pojos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Class Address
 * <p>
 * Created by yslabko on 01/23/2017.
 */
@Data
@NoArgsConstructor
@Embeddable
public class Address {
    private String street;
    private String city;
}
