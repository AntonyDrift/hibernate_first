package by.it.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Item
 *
 * Created by yslabko on 03/29/2017.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "ITEM")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

//    @OneToMany(mappedBy = "item",
//            fetch = FetchType.LAZY)
//    private Set<Bid> bids = new HashSet<>();

    private String name;
}