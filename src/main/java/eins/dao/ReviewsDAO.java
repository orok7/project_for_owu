package eins.dao;

import eins.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewsDAO extends JpaRepository<Reviews, Integer> {

    List<Reviews> findAllByProductId(int id);

}