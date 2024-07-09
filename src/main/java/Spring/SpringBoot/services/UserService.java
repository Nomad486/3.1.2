package Spring.SpringBoot.services;


import Spring.SpringBoot.model.User;

import java.util.List;

public interface UserService {

    void createUser(User user);
    User readUserById(int id);
    List<User> readAllUsers();
    void updateUser(int id, User user);
    void deleteUser(int id);

}