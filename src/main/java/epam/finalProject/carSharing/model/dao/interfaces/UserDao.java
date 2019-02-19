package epam.finalProject.carSharing.model.dao.interfaces;

import epam.finalProject.carSharing.model.domain.entity.User;

public interface UserDao extends PersistentDao<Long,User> {

    User findByUsername(String username);

    User getByIdWithPassportData(Long id);

}
