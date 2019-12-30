package epam.training.finalproject.model.dao.impls;

import epam.training.finalproject.model.dao.interfaces.AddressDao;
import epam.training.finalproject.model.domain.entity.Address;
import epam.training.finalproject.model.domain.mapper.AddressMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AddressDaoImpl implements AddressDao {

    private static Logger LOGGER = Logger.getLogger(AddressDaoImpl.class);

    private final String SQL_GET_ALL = "select * from addresses";
    private final String SQL_FIND_BY_COUNTRY = "select * from addresses where country=?";
    private final String SQL_FIND_BY_CITY = "select * from addresses where city=?";
    private final String SQL_FIND_BY_STREET = "select * from addresses where street=?";
    private final String SQL_FIND_BY_ALL_ARGUMENTS = "select * from addresses where country=? and city=? and street=? " +
            "and house_number=? and flat_number=?";
    private final String SQL_GET_BY_ID = "select * from addresses where idAddress=?";
    private final String SQL_DELETE_ADDRESS = "update addresses set deleted=? where idAddress = ?";
    private final String SQL_UPDATE_ADDRESS = "update addresses set country=?, city=?, street=?, house_number=?, " +
            "flat_number=? where idAddress = ?";
    private final String SQL_SAVE_ADDRESS = "insert into addresses(country,city,street,house_number,flat_number) " +
            "values(?,?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AddressDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Address> findByAllParameters(String country, String city, String street, int houseNumber,
                                                 int flatNumber) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_FIND_BY_ALL_ARGUMENTS,
                    new AddressMapper(), country, city, street, houseNumber, flatNumber)));
        } catch (DataAccessException ex) {
            LOGGER.error("Address with country " + country + ", city " + city + ", street " +
                    street + ", house number " + houseNumber + "and flat number " + flatNumber + " is not found",
                    ex.getCause());
            return Optional.empty();
        }
    }

    @Override
    public List<Address> findByCountry(String country) {
        try {
            return jdbcTemplate.query(SQL_FIND_BY_COUNTRY, new AddressMapper(), country);
        } catch (DataAccessException ex) {
            LOGGER.error("Any addresses with country " + country + " are not found", ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Address> findByCity(String city) {
        try {
            return jdbcTemplate.query(SQL_FIND_BY_CITY, new AddressMapper(), city);
        } catch (DataAccessException ex) {
            LOGGER.error("Any addresses with city " + city + " are not found", ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Address> findByStreet(String street) {
        try {
            return jdbcTemplate.query(SQL_FIND_BY_STREET, new AddressMapper(), street);
        } catch (DataAccessException ex) {
            LOGGER.error("Any addresses with street " + street + " are not found", ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Address> getById(Long id) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_GET_BY_ID,
                    new AddressMapper(), id)));
        } catch (DataAccessException ex) {
            LOGGER.error("Address with id " + id + " is not found", ex.getCause());
            return Optional.empty();
        }
    }

    @Override
    public List<Address> getAll() {
        try {
            return jdbcTemplate.query(SQL_GET_ALL, new AddressMapper());
        } catch (DataAccessException ex) {
            LOGGER.error("Any addresses are not found", ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public Long delete(Address address) {
        try {
            return (long) jdbcTemplate.update(SQL_DELETE_ADDRESS, address.isDeleted(), address.getId());
        } catch (DataAccessException ex) {
            LOGGER.error("Address with id " + address.getId() + " has invalid credentials", ex.getCause());
            return -1L;
        }
    }

    @Override
    public Long update(Address address) {
        try {
            return (long) jdbcTemplate.update(SQL_UPDATE_ADDRESS, address.getCountry(),
                    address.getCity(), address.getStreet(), address.getHouseNumber(), address.getHouseNumber(),
                    address.getId());
        } catch (DataAccessException ex) {
            LOGGER.error("Address with id " + address.getId() + " has invalid credentials", ex.getCause());
            return -1L;
        }
    }

    @Override
    public Long save(Address address) {
        try {
            return (long) jdbcTemplate.update(SQL_SAVE_ADDRESS, address.getCountry(),
                    address.getCity(), address.getStreet(), address.getHouseNumber(), address.getHouseNumber());
        } catch (DataAccessException ex) {
            LOGGER.error("Address has invalid credentials", ex.getCause());
            return -1L;
        }
    }
}
