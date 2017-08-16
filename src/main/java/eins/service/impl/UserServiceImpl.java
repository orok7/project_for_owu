package eins.service.impl;

import eins.dao.CompanyUserDAO;
import eins.dao.UserDAO;
import eins.entity.CompanyUser;
import eins.entity.User;
import eins.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO uDAO;
    @Autowired
    private CompanyUserDAO cuDAO;

    @Override
    public void save(User user) {
        uDAO.save(user);
    }

    @Override
    public void remove(int id) {
        uDAO.delete(id);
    }

    @Override
    public void save(User user, CompanyUser companyUser) {
        cuDAO.save(companyUser);
        user.setCompany(true);
        user.setCompanyDate(companyUser);
        uDAO.save(user);
    }

    @Override
    public User findOne(int id) {
        return uDAO.findOne(id);
    }

    @Override
    public User findOneWithCompanyData(int id) {
        return uDAO.findOneWithCompanyData(id);
    }

    @Override
    public User findByUsername(String username) {
        return uDAO.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return uDAO.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username);
    }
}
