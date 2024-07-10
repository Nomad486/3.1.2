package Spring.SpringBoot.services;

import Spring.SpringBoot.dao.UserDao;
import Spring.SpringBoot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional (readOnly = true)
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional (readOnly = false)
    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Override
    public User readUserById(int id) {
        return userDao.readUserById(id);
    }

    @Override
    public List<User> readAllUsers() {
        return userDao.readAllUsers();
    }

    @Transactional (readOnly = false)
    @Override
    public void updateUser(int id, User user) {
        userDao.updateUser(id, user);
    }

    @Transactional (readOnly = false)
    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }
}