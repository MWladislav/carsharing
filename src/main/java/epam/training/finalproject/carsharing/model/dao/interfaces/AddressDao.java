package epam.training.finalproject.carsharing.model.dao.interfaces;

import epam.training.finalproject.carsharing.model.domain.entity.Address;

public interface AddressDao extends PersistentDao<Long,Address> {

    Address findByCountry(String country);

    Address findByCity(String city);

    Address findByStreet(String street);
}
