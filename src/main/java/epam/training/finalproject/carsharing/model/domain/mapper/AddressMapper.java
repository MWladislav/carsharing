package epam.training.finalproject.carsharing.model.domain.mapper;

import epam.training.finalproject.carsharing.model.domain.entity.Address;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressMapper implements RowMapper<Address> {

    public Address mapRow(ResultSet resultSet, int i) throws SQLException {
        Address address = new Address();
        address.setId(resultSet.getInt("idAddress"));
        address.setCountry(resultSet.getString("country"));
        address.setCity(resultSet.getString("city"));
        address.setStreet(resultSet.getString("street"));
        address.setHouseNumber(resultSet.getInt("house_number"));
        address.setFlatNumber(resultSet.getInt("flat_number"));
        return address;
    }
}
