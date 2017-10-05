package by.it.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yslabko on 09/30/2017.
 */
@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Book {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private int year;
    @ManyToOne
    private Author author;

    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + '\'' +
                ", year=" + year + ", author=" + author.getName() +'}';
    }
}
