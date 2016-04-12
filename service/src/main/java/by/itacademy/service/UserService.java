package by.itacademy.service;

import by.itacademy.dao.impl.UserDao;
import by.itacademy.pojos.User;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**+
 * service level for user entities
 * singleton
 * perform business logic of app
 */
public class UserService {
    public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PASSWORD_REGEX = "^.*(?=.{10,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";
    public static final String NAME_REGEX = "[A-Z]{1}[a-z]+";
    private UserDao userDAO = UserDao.getInstance();
    private static UserService instance;

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private UserService() {
    }

    /**+
     * add user to DB
     * @param email string parameter
     * @param password string parameter
     * @param name string parameter
     * @param surname string parameter
     */
    public void addUser(String email, String password, String name, String surname) {
        boolean isEmailCorrect = isCorrectEmail(email);
        boolean isPasswordCorrect = isCorrectPassword(password);
        boolean isNameCorrect = isCorrectName(name);
        boolean isSurnameCorrect = isCorrectName(surname);
        if (isEmailCorrect && isPasswordCorrect && isNameCorrect && isSurnameCorrect) {
            User user = new User(email, password, name, surname);
            userDAO.addUser(user);
        }
    }

    /**+
     * check if name is appropriate
     * @param name string
     * @return true if correct
     */
    private boolean isCorrectName(String name) {
        Pattern pattern = Pattern.compile(NAME_REGEX);
        Matcher matcher = pattern.matcher(name);
        return !name.isEmpty() && matcher.matches();
    }

    /**+
     * check if password is appropriate
     * @param password string
     * @return true if correct
     */

    private boolean isCorrectPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return !password.isEmpty() && matcher.matches();
    }

    /**+
     * check if name or surname is appropriate
     * @param email string
     * @return true if correct
     */
    private boolean isCorrectEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return !email.isEmpty() && matcher.matches();
    }

    /**+
     *
     * get user entity by email
     * @param email string
     * @return user entity
     */

    public User obtainUser(String email) {
        return userDAO.obtainUser(email);
    }

    /**+
     *
     * @return list of users
     */
    public List<User> obtainUserList() {
        return userDAO.obtainUserList();
    }
}
