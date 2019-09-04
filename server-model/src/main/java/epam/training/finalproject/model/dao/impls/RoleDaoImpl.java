package epam.training.finalproject.model.dao.impls;

import epam.training.finalproject.model.dao.interfaces.RoleDao;
import epam.training.finalproject.model.domain.entity.Role;
import epam.training.finalproject.model.domain.entity.enums.RoleName;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class RoleDaoImpl implements RoleDao {

    private static Logger LOGGER= Logger.getLogger(RoleDaoImpl.class);

    private final String SQL_FIND_BY_ROLE_NAME="select * from role where role_name=?";
    private final String SQL_FIND_ROLES_BY_USER_ID="select * from role r right join user_role ur on r.idRole=ur.role_id where ur.user_id = ?";
    private final String SQL_SAVE_USER_ROLE="insert into user_role(user_id,role_id) values(?,?)";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Role> findByRoleName(RoleName roleName) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_FIND_BY_ROLE_NAME, new RowMapper<Role>() {
                @Override
                public Role mapRow(ResultSet resultSet, int i) throws SQLException {
                    return new Role(
                            (long) resultSet.getInt("idRole"),
                            RoleName.valueOf(resultSet.getString("role_name")));
                }
            }, roleName)));
        }
        catch (DataAccessException ex){
            LOGGER.error("Role with role name "+roleName+" is not found",ex.getCause());
            return Optional.empty();
        }
    }

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        try {
            return jdbcTemplate.query(SQL_FIND_ROLES_BY_USER_ID, new RowMapper<Role>() {
                @Override
                public Role mapRow(ResultSet resultSet, int i) throws SQLException {
                    return new Role((long)resultSet.getInt("idRole"),RoleName.valueOf(resultSet.getString("role_name")));
                }
            },userId);
        }
        catch (DataAccessException ex){
            LOGGER.error("Any roles with user id "+userId+" are not found",ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public Long save(Long userId, Role role) {
        try {
            return (long)jdbcTemplate.update(SQL_SAVE_USER_ROLE,userId,role.getId());
        }
        catch (DataAccessException ex){
            LOGGER.error("Role has invalid credentials",ex.getCause());
            return -1L;
        }
    }
}
