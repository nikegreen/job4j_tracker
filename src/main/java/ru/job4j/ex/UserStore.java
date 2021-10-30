package ru.job4j.ex;

public class UserStore {

    public static User findUser(User[] users, String login) throws UserNotFoundException {
        for (User user : users) {
            if (user != null && user.getUsername() != null && user.getUsername().equals(login)) {
                return user;
            }
        }
        throw new UserNotFoundException("User Not Found Exception");
    }

    public static boolean validate(User user) throws UserInvalidException {
        if (!user.isValid() || user.getUsername() != null || user.getUsername().length() < 3) {
            throw new UserInvalidException("User Invalid Exception");
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            User[] users = {
                    new User("Petr Arsentev", true)
            };
            User user = findUser(users, "Petr Arsentev");
            if (validate(user)) {
                System.out.println("This user has an access");
            }
        } catch (UserInvalidException exception) {
            System.out.println("user not valid!");
        } catch (UserNotFoundException exception) {
            System.out.println("user not found!");
        }
    }
}
