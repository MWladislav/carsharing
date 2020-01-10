package epam.training.finalproject.model.service.interfaces;

import epam.training.finalproject.model.domain.entity.Role;
import epam.training.finalproject.model.domain.entity.enums.RoleName;

import java.util.List;

public interface RoleService {

    Role findByRoleName(RoleName roleName);

    List<Role> findRolesByUserId(Long id);

    Long save(Long userId,Long roleId);
}
