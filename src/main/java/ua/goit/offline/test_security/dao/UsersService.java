package ua.goit.offline.test_security.dao;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by andreymi on 1/31/2017.
 */
public class UsersService {
    private UsersDao usersDao;

    public UsersService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Transactional(readOnly = true)
    public User find(String username) {
        return usersDao.find(username);
    }
}
