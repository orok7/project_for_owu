package eins.dao;

import eins.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product,Integer> {

    @Query("select p from Product p left outer join fetch p.productGroup")
    List<Product> findAllWithGroups();

    @Query("select p from Product p left outer join fetch p.productGroup where p.id=:id")
    Product findOneWithGroup(@Param("id") int id);

}
