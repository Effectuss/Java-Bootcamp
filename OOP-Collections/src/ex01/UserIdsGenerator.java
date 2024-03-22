package ex01;

public class UserIdsGenerator {
    private static final UserIdsGenerator instance = new UserIdsGenerator();
    private static Integer id = 0;

    private UserIdsGenerator() {
    }

    public static UserIdsGenerator getInstance() {
        return instance;
    }

    public Integer getId() {
        ++id;
        return id;
    }
}
