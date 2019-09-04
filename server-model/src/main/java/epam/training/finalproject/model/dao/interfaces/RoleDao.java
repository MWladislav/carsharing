package epam.training.finalproject.model.dao.interfaces;

import epam.training.finalproject.model.domain.entity.Role;
import epam.training.finalproject.model.domain.entity.enums.RoleName;

import java.util.List;
import java.util.Optional;

public interface RoleDao {

    Optional<Role> findByRoleName(RoleName roleName);

    List<Role> findRolesByUserId(Long id);

    Long save(Long userId, Role role);
}
