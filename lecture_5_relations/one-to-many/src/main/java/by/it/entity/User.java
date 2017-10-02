package by.it.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class User
 *
 * Created by yslabko on 09/29/2017.
 */
@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private String name;
    @ElementCollection
    @OrderColumn(name = "`order`")
    private List<String> pets = new ArrayList<>();
}
