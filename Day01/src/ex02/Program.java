package ex02;

public class Program {
    public static void main(String[] args) {
        User[] arrJava = new User[15];

        for (int i = 0; i < arrJava.length; ++i) {
            arrJava[i] = new User("User", i);
        }

        UsersArrayList arrMy = new UsersArrayList();

        for (int i = 0; i < arrJava.length; ++i) {
            arrMy.addUser(new User("User", i));
        }

        for (int i = 0; i < arrJava.length; ++i) {
            System.out.print("java arr " + arrJava[i].toString() + "   My arr " + arrMy.getUserByIndex(i).toString() + "\n");
        }

        System.out.println(arrMy.getUserByID(28));

        try {
            System.out.println(arrMy.getUserByIndex(28));
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(arrMy.getUserByID(123123));
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}
