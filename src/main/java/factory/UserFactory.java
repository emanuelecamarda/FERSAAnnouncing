/**
 * Edit by EC.
 */

package factory;

import entity.User;

public class UserFactory {

    private UserFactory() {}

    public static User getUser(String nickname, String name, String surname, String email, String password,
                                  Character gender) {
        User u = new User(nickname, name, surname, email, password);
        u.setGender(gender);
        return u;
    }
}
