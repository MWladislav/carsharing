package epam.training.finalproject.model.service.impls;

import epam.training.finalproject.exception.EntityNotFoundException;
import epam.training.finalproject.exception.OperationException;
import epam.training.finalproject.model.dao.interfaces.RoleDao;
import epam.training.finalproject.model.domain.entity.Role;
import epam.training.finalproject.model.domain.entity.enums.RoleName;
import epam.training.finalproject.model.service.interfaces.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private static Logger LOGGER = Logger.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findByRoleName(RoleName roleName) {
        if (roleName != null) {
            return roleDao.findByRoleName(roleName)
                    .orElseThrow(() -> new EntityNotFoundException("Role with role name " + roleName + " is not found"));
        }
        LOGGER.debug("Role name is null!");
        throw new IllegalArgumentException("Role name is invalid");
    }

    @Override
    public List<Role> findRolesByUserId(Long id) {
        if (id > 0) {
            List<Role> roles = roleDao.findRolesByUserId(id);
            if (!roles.isEmpty()) {
                LOGGER.debug("User roles list is empty");
                throw new EntityNotFoundException("Any role for user with id " + id + " is not found");
            }
            return roles;
        }
        throw new IllegalArgumentException("Id is invalid");
    }

    @Override
    public Long save(Long userId, Long roleId) {
        if (userId > 0 && roleId > 0) {
            Long result = roleDao.save(userId, roleId);
            if (result == -1L) {
                LOGGER.debug("Invalid user id!");
                throw new OperationException("User credentials are invalid");
            }
        }
        throw new IllegalArgumentException("Invalid credentials");
    }
}
