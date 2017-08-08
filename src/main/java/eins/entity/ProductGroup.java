package eins.entity;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
/*
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductGroup parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<ProductGroup> subGroups = new ArrayList<>();*/
}
