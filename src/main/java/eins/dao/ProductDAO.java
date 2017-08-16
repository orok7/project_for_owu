package eins.dao;

import eins.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product,Integer> {

//    SELECT * FROM product WHERE product.name LIKE '%австрія%' OR description LIKE '%австрія%';
    @Query("select p from Product p where p.name like :searchThis or p.description like :searchThis")
    List<Product> findAllBySearch(@Param("searchThis") String searchThis);

    @Query("select p from Product p left outer join fetch p.productGroup")
    List<Product> findAllWithGroups();

    @Query("select p from Product p left outer join fetch p.productGroup where p.id=:id")
    Product findOneWithGroup(@Param("id") int id);

    List<Product> findAllByProductGroupName(String name);

}
