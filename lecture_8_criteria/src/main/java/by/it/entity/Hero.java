package by.it.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class Hero
 *
 * Created by yslabko on 09/06/2017.
 */
@NoArgsConstructor
@Data
@Entity
public class Hero {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "HEROES_POWERS")
    private List<Power> powers = new ArrayList<>();

    public Hero(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hero{id=" + id + ", name='" + name + ", powers=" + powers.stream().map(p->p.getName())
                .collect(Collectors.joining(",")) + "}";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        if (id != hero.id) return false;
        return name != null ? name.equals(hero.name) : hero.name == null;
    }
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
