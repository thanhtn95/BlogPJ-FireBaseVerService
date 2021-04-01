package zone.god.blogpjfirebase.repository;

import zone.god.blogpjfirebase.model.User;

import java.util.concurrent.ExecutionException;

public interface UserRepository {
    boolean saveUser(User user) throws ExecutionException, InterruptedException;
    boolean deleteUser(String username, String password);
    User getUser(String username, String password);
    boolean checkIfUserExist(String username) throws ExecutionException, InterruptedException;
}
