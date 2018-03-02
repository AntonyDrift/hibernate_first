package by.it.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by yslabko on 09/30/2017.
 */
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(exclude = "books")
@Entity
public class Author {
    @Id
    private Long id;
    private String name;
//    @OrderBy("title desc")
//    @OrderColumn(name="order_id")
@org.hibernate.annotations.OrderBy(clause = "CHAR_LENGTH(title) desc")
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;
}
