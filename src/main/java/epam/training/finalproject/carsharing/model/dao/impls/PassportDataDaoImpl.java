package epam.training.finalproject.carsharing.model.dao.impls;

import epam.training.finalproject.carsharing.model.dao.interfaces.PassportDataDao;
import epam.training.finalproject.carsharing.model.domain.entity.PassportData;
import epam.training.finalproject.carsharing.model.domain.mapper.PassportDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PassportDataDaoImpl implements PassportDataDao {

    private final String SQL_GET_ALL = "select * from passportData";
    private final String SQL_FIND_BY_FIRST_NAME = "select a.*, " +
            "pd.idPassportData, pd.first_name, pd.last_name, pd.patronymic, pd.birth_date, pd.passport_number, pd.identification_number, pd.gender " +
            "from passportData pd " +
            "left join address a on pd.address_idAddress = a.idAddress where first_name=?";
    private final String SQL_FIND_BY_LAST_NAME = "select a.*, " +
            "pd.idPassportData, pd.first_name, pd.last_name, pd.patronymic, pd.birth_date, pd.passport_number, pd.identification_number, pd.gender " +
            "from passportData pd " +
            "left join address a on pd.address_idAddress = a.idAddress where last_name=?";
    private final String SQL_FIND_BY_IDENTIFICATION_NUMBER = "select a.*, " +
            "pd.idPassportData, pd.first_name, pd.last_name, pd.patronymic, pd.birth_date, pd.passport_number, pd.identification_number, pd.gender " +
            "from passportData pd " +
            "left join address a on pd.address_idAddress = a.idAddress where identification_number=?";
    private final String SQL_GET_PASSPORT_DATA_BY_ID = "select a.*, " +
            "pd.idPassportData, pd.first_name, pd.last_name, pd.patronymic, pd.birth_date, pd.passport_number, pd.identification_number, pd.gender " +
            "from passportData pd " +
            "left join address a on pd.address_idAddress = a.idAddress where idPassportData=?";
    private final String SQL_DELETE_PASSPORT_DATA = "delete from passportData where idPassportData = ?";
    private final String SQL_UPDATE_PASSPORT_DATA = "update passportData set first_name=?, last_name=?, patronymic=?," +
            " birth_date=?, passport_number=?,identification_number=?, gender=? where idPassportData = ?";
    private final String SQL_SAVE_PASSPORT_DATA = "insert into passportData" +
            "(first_name,last_name,patronymic,birth_date,passport_number,identification_number,gender) values(?,?,?,?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PassportDataDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public PassportData getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_GET_PASSPORT_DATA_BY_ID,new PassportDataMapper(),id);
    }

    @Override
    public List<PassportData> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL,new PassportDataMapper());
    }

    @Override
    public Long delete(PassportData passportData) {
        return (long) jdbcTemplate.update(SQL_DELETE_PASSPORT_DATA,passportData.getId());
    }

    @Override
    public Long update(PassportData passportData) {
        return (long) jdbcTemplate.update(SQL_UPDATE_PASSPORT_DATA,passportData.getFirstName(),passportData.getLastName(),
                passportData.getPatronomyc(),passportData.getBirthDate(),passportData.getPassportNumber(),
                passportData.getIdentificationNumber(),passportData.getGender(),passportData.getId());
    }

    @Override
    public Long save(PassportData passportData) {
        return (long) jdbcTemplate.update(SQL_SAVE_PASSPORT_DATA,passportData.getFirstName(),passportData.getLastName(),
                passportData.getPatronomyc(),passportData.getBirthDate(),passportData.getPassportNumber(),
                passportData.getIdentificationNumber(),passportData.getGender());
    }

    @Override
    public PassportData findByFirstName(String firstName) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_FIRST_NAME,new PassportDataMapper(),firstName);
    }

    @Override
    public PassportData findByLastName(String lastName) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_LAST_NAME,new PassportDataMapper(),lastName);
    }

    @Override
    public PassportData findByIdentificationNumber(String number) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_IDENTIFICATION_NUMBER,new PassportDataMapper(),number);
    }
}
