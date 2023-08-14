package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<User> getUserList() {
        Query query = entityManager.createQuery(" FROM User ");
        return query.getResultList();
    }

    @Override
    public User getUserById(Long id) {
        Query query = entityManager.createQuery(" FROM User WHERE id = :id");
        query.setParameter("id", id);

        return (User) query.getResultList().get(0);
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);

    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}
