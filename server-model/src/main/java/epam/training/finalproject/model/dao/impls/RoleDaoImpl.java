package epam.training.finalproject.model.dao.impls;

import epam.training.finalproject.model.dao.interfaces.RoleDao;
import epam.training.finalproject.model.domain.entity.Role;
import epam.training.finalproject.model.domain.entity.enums.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    private final String SQL_FIND_BY_ROLE_NAME="select * from role where role_name=?";
    private final String SQL_FIND_ROLES_BY_USER_ID="select * from role r right join user_role ur on r.idRole=ur.role_id where ur.user_id = ?";
    private final String SQL_SAVE_USER_ROLE="insert into user_role(user_id,role_id) values(?,?)";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Role findByRoleName(RoleName roleName) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ROLE_NAME, new RowMapper<Role>() {
            @Override
            public Role mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Role(
                        (long) resultSet.getInt("idRole"),
                        RoleName.valueOf(resultSet.getString("role_name")));
            }
        },roleName);
    }

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        return jdbcTemplate.query(SQL_FIND_ROLES_BY_USER_ID, new RowMapper<Role>() {
            @Override
            public Role mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Role((long)resultSet.getInt("idRole"),RoleName.valueOf(resultSet.getString("role_name")));
            }
        },userId);
    }

    @Override
    public Long save(Long userId, Long roleId) {
        return (long)jdbcTemplate.update(SQL_SAVE_USER_ROLE,userId,roleId);
    }
}
