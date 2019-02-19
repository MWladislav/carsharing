package epam.finalProject.carSharing.model.dao.interfaces;

import epam.finalProject.carSharing.model.domain.entity.Address;

public interface AddressDao extends PersistentDao<Long,Address> {

    Address findByCountry(String country);

    Address findByCity(String city);

    Address findByStreet(String street);
}
