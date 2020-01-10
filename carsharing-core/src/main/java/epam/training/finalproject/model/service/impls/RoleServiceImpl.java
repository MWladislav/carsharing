package epam.training.finalproject.model.service.impls;

import epam.training.finalproject.exception.EntityNotFoundException;
import epam.training.finalproject.model.dao.interfaces.RoleDao;
import epam.training.finalproject.model.domain.entity.Role;
import epam.training.finalproject.model.domain.entity.enums.RoleName;
import epam.training.finalproject.model.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Deprecated
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findByRoleName(RoleName roleName) {
        try {
            return roleDao.findByRoleName(roleName).get();
        }
        catch (DataAccessException ex){
            throw new EntityNotFoundException("Role with role name "+roleName+" is not found",ex.getCause());
        }
    }

    @Override
    public List<Role> findRolesByUserId(Long id) {
        try {
            return roleDao.findRolesByUserId(id);
        }
        catch (DataAccessException ex){
            throw new EntityNotFoundException("Role for user with id "+id+" is not found",ex.getCause());
        }
    }

    @Override
    public Long save(Long userId, Long roleId) {
        return roleDao.save(userId,roleId);
    }
}
