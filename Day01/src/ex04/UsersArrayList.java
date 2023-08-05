package ex04;

import java.util.Objects;

public class UsersArrayList implements UsersList {
    private Integer size = 0;
    private User[] arrayUsers = new User[10];

    @Override
    public void addUser(User user) {
        if (size == arrayUsers.length) {
            createNewArray();
        }
        if (!isExistUser(user.getIdentifier())) {
            arrayUsers[size++] = user;
        } else {
            throw new UserAlreadyExistsException("User with id " + user.getIdentifier() + " already exists!");
        }
    }

    public boolean isExistUser(Integer id) {
        for (int i = 0; i < size; ++i) {
            if (Objects.equals(arrayUsers[i].getIdentifier(), id)) {
                return true;
            }
        }
        return false;
    }

    private void createNewArray() {
        User[] tmpArray = arrayUsers;
        arrayUsers = new User[size * 2];
        System.arraycopy(tmpArray, 0, arrayUsers, 0, tmpArray.length);
    }

    @Override
    public User getUserByID(Integer id) {
        for (int i = 0; i < size; ++i) {
            if (Objects.equals(arrayUsers[i].getIdentifier(), id)) {
                return arrayUsers[i];
            }
        }
        throw new UserNotFoundException("User with id " + id + " does not exist!");
    }

    @Override
    public User getUserByIndex(Integer index) {
        if (index < 0 || index >= size) {
            throw new UserNotFoundException("The user with index " + index + " does not exist!");
        }
        return arrayUsers[index];
    }

    @Override
    public Integer getNumberOfUsers() {
        return size;
    }
}
