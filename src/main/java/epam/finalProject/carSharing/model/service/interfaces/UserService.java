package epam.finalProject.carSharing.model.service.interfaces;

import epam.finalProject.carSharing.model.domain.entity.User;

public interface UserService extends PersistentService<Long,User>{

    User findByUsername(String username);

    User getByIdWithOrders(long id);

    User getByIdWithPassportDataAndAddress(long id);

}
