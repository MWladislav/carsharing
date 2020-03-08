package epam.training.finalproject.web.security;

import epam.training.finalproject.exception.EntityNotFoundException;
import epam.training.finalproject.model.domain.entity.User;
import epam.training.finalproject.model.service.interfaces.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static Logger LOGGER = Logger.getLogger(UserDetailsServiceImpl.class);

//    @Autowired
//    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String s) {
        try {
            User user = null;//userService.findByUsername(s);
            user.setRoles(roleService.findRolesByUserId(user.getId()));
            return new UserPrincipal(user);
        }
        catch (EntityNotFoundException ex){
            LOGGER.debug(ex.getMessage());
            throw new BadCredentialsException(ex.getMessage());
        }
    }
}
