package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserDao dao;

    @Transactional(readOnly = true)
    @Override
    public List<User> getUserList() {
        return dao.getUserList();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        return dao.getUserById(id);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        dao.deleteUser(id);
    }

    @Autowired
    public void setDao(UserDao dao) {
        this.dao = dao;
    }
}
