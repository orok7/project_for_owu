package eins.dao;

import eins.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageDAO extends JpaRepository<Image,Integer> {

    List<Image> findAllByProductId(int id);

}
