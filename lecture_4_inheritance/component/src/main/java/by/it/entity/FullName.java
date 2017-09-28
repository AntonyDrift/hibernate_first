package by.it.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Class FullName
 *
 * Created by yslabko on 01/23/2017.
 */
@Data
@NoArgsConstructor @AllArgsConstructor
@Embeddable
public class FullName {
    private String firstName;
    private String lastName;
}
