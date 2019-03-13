package epam.finalProject.carSharing.model.service.impls;

import epam.finalProject.carSharing.model.dao.interfaces.UserDao;
import epam.finalProject.carSharing.model.domain.entity.UserDetailsImpl;
import epam.finalProject.carSharing.model.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDAO;

    @Autowired
    public UserDetailsServiceImpl(UserDao userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final User user = userDAO.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return new UserDetailsImpl(user);
    }
}
