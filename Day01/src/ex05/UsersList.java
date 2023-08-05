package ex05;

public interface UsersList {
    public void addUser(User user);

    public User getUserByID(Integer id) throws UserNotFoundException;

    public User getUserByIndex(Integer index) throws UserNotFoundException;

    public Integer getNumberOfUsers();
}
