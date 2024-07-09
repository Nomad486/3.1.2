package Spring.SpringBoot.dao;

import Spring.SpringBoot.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User readUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> readAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    @Override
    public void updateUser(int id, User user) {
        try {
            User existingUser = readUserById(id);
            existingUser.setName(user.getName());
            existingUser.setAge(user.getAge());
            existingUser.setSurname(user.getSurname());
            entityManager.merge(existingUser);
        } catch (NullPointerException e) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    @Override
    public void deleteUser(int id) {
        User user = readUserById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}
