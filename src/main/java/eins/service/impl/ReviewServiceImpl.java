package eins.service.impl;

import eins.dao.ReviewDAO;
import eins.entity.Review;
import eins.service.interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewDAO dbDAO;

    @Override
    public void save(Review o) {
        dbDAO.save(o);
    }

    @Override
    public Review findOne(int id) {
        return dbDAO.findOne(id);
    }

    @Override
    public List<Review> findAll() {
        return dbDAO.findAll();
    }

    @Override
    public List<Review> findAllByProductId(int id) {
        return dbDAO.findAllByProductId(id);
    }

    @Override
    public List<Review> findAllByProductIdWithUsers(int id) {
        return dbDAO.findAllByProductIdWithUsers(id);
    }

    @Override
    public List<Review> findAllByUserUsername(String username) {
        return dbDAO.findAllByUserUsername(username);
    }
}