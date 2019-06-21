package epam.training.finalproject.model.dao.impls;

import epam.training.finalproject.model.dao.interfaces.AddressDao;
import epam.training.finalproject.model.domain.entity.Address;
import epam.training.finalproject.model.domain.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AddressDaoImpl implements AddressDao {


    private final String SQL_GET_ALL = "select * from address";
    private final String SQL_FIND_BY_COUNTRY = "select * from address where country=?";
    private final String SQL_FIND_BY_CITY = "select * from address where city=?";
    private final String SQL_FIND_BY_STREET = "select * from address where street=?";
    private final String SQL_GET_ADDRESS_BY_ID = "select * from address where idAddress=?";
    private final String SQL_DELETE_ADDRESS = "delete from address where idAddress = ?";
    private final String SQL_UPDATE_ADDRESS = "update address set country=?, city=?, street=?, house_number=?, flat_number=? where idAddress = ?";
    private final String SQL_SAVE_ADDRESS = "insert into address(country,city,street,house_number,flat_number) values(?,?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AddressDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Address getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_GET_ADDRESS_BY_ID,new AddressMapper(),id);
    }

    @Override
    public List<Address> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL,new AddressMapper());
    }

    @Override
    public Long delete(Address address) {
        return (long) jdbcTemplate.update(SQL_DELETE_ADDRESS,address.getId());
    }

    @Override
    public Long update(Address address) {
        return (long) jdbcTemplate.update(SQL_UPDATE_ADDRESS,address.getCountry(),address.getCity(),address.getStreet(),address.getHouseNumber(),address.getHouseNumber(),address.getId());
    }

    @Override
    public Long save(Address address) {
        return (long) jdbcTemplate.update(SQL_SAVE_ADDRESS,address.getCountry(),address.getCity(),address.getStreet(),address.getHouseNumber(),address.getHouseNumber());
    }

    @Override
    public Address findByCountry(String country) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_COUNTRY,new AddressMapper(),country);
    }

    @Override
    public Address findByCity(String city) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_CITY,new AddressMapper(),city);
    }

    @Override
    public Address findByStreet(String street) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_STREET,new AddressMapper(),street);
    }
}
