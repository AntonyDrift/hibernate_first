package by.it.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by yslabko on 09/30/2017.
 */
@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Book {
    @Id @GeneratedValue
    private Long id;
    private String title;
}
