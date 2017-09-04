package by.it.component;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Address
 *
 * Created by yslabko on 09/01/2017.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private String street;
    private String city;
    private String postalCode;
}
