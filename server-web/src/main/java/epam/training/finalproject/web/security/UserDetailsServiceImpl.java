package epam.training.finalproject.web.security;

import epam.training.finalproject.exceptions.EntityNotFoundException;
import epam.training.finalproject.model.domain.entity.User;
import epam.training.finalproject.model.service.interfaces.RoleService;
import epam.training.finalproject.model.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) {
        try {
            User user = userService.findByUsername(s);
            user.setRoles(roleService.findRolesByUserId(user.getId()));
            return new UserPrincipal(user);
        }
        catch (EntityNotFoundException ex){
            throw new UsernameNotFoundException(s,ex.getCause());
        }
    }

    public UserDetails loadUserById(Long id){
        final User user = userService.getById(id);
        if (user == null) {
            throw new EntityNotFoundException("There is no user with such id");
        }
        user.setRoles(roleService.findRolesByUserId(id));
        return new UserPrincipal(user);
    }
}
