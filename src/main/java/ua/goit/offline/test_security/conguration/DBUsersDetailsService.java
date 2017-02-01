package ua.goit.offline.test_security.conguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.goit.offline.test_security.dao.User;
import ua.goit.offline.test_security.dao.UsersService;

import java.util.Collections;

import static org.springframework.security.core.userdetails.User.withUsername;

/**
 * Created by andreymi on 1/31/2017.
 */
public class DBUsersDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBUsersDetailsService.class);

    private UsersService usersService;

    public DBUsersDetailsService(UsersService usersService) {
        this.usersService = usersService;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = usersService.find(username);
        } catch (Exception e) {
            LOGGER.error(String.format("Unable to load information about the user: %1$s: %2$s", username, e.getMessage()), e);
        }
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        String[] roles = user.getRoles() != null ? user.getRoles().split(";") : new String[]{};
        return withUsername(user.getUsername()).password(user.getPassword()).roles(roles).build();

    }
}
