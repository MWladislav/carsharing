package epam.training.finalproject.carsharing.model.service.interfaces;

import epam.training.finalproject.carsharing.model.domain.entity.User;

public interface UserService extends PersistentService<Long,User>{

    User findByUsername(String username);

    User getByIdWithOrders(long id);

    User getByIdWithOrdersAndAdInfo(long id);

    User getByIdWithPassportDataAndAddress(long id);

}
