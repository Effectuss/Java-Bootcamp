package ex01;

public class Program {
    public static void main(String[] args) {
        UserIdsGenerator instance = UserIdsGenerator.getInstance();
        UserIdsGenerator instance2 = UserIdsGenerator.getInstance();
        System.out.println(instance2.getId());
        System.out.println(instance.getId());

        User user = new User("John", 600);
        User user1 = new User("Mike", 100);
        System.out.println(user.getIdentifier());
        System.out.println(user1.getIdentifier());
    }
}
