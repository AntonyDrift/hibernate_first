package by.it.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by yslabko on 09/30/2017.
 */
@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Author {
    @Id
    private Long id;
    private String name;
//    @OrderBy("title desc")
//    @OrderColumn(name="order_id")
//@org.hibernate.annotations.OrderBy(clause = "CHAR_LENGTH(title) desc")
    @OneToMany(/*mappedBy = "author",*/ cascade = CascadeType.ALL)
    private List<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Author author = (Author) o;

        if (id != null ? !id.equals(author.id) : author.id != null) return false;
        return name != null ? name.equals(author.name) : author.name == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
