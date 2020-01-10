package epam.training.finalproject.model.dao.interfaces;

import epam.training.finalproject.model.domain.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressDao extends AbstractEntityDao<Long, Address> {

    Optional<Address> findByAllParameters(String country,String city,String street, int houseNumber, int flatNumber);

    List<Address> findByCountry(String country);

    List<Address> findByCity(String city);

    List<Address> findByStreet(String street);
}
