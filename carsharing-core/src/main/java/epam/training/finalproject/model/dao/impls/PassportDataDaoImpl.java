package epam.training.finalproject.model.dao.impls;

import epam.training.finalproject.model.dao.interfaces.PassportDataDao;
import epam.training.finalproject.model.domain.entity.PassportData;
import epam.training.finalproject.model.domain.mapper.PassportDataMapper;
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
public class PassportDataDaoImpl implements PassportDataDao {

    private static Logger LOGGER = Logger.getLogger(PassportDataDaoImpl.class);

    private final String SQL_GET_ALL = "select * from passport_data";
    private final String SQL_FIND_BY_FIRST_NAME = "select pd.* from passport_data pd where first_name=? and last_name=?";
    private final String SQL_FIND_BY_IDENTIFICATION_NUMBER = "select pd.* from passport_data pd where identification_number=?";
    private final String SQL_GET_PASSPORT_DATA_BY_ID = "select pd.* from passport_data pd where idPassportData=?";
    private final String SQL_DELETE_PASSPORT_DATA = "update passport_data set deleted=? where idPassportData = ?";
    private final String SQL_UPDATE_PASSPORT_DATA = "update passport_data set first_name=?, last_name=?, patronymic=?," +
            " birth_date=?, passport_number=?,identification_number=?, gender=? where idPassportData = ?";
    private final String SQL_SAVE_PASSPORT_DATA = "insert into passport_data" +
            "(first_name,last_name,patronymic,birth_date,passport_number,identification_number,gender) values(?,?,?,?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PassportDataDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<PassportData> findByFirstNameAndLastName(String firstName,String lastName) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_FIND_BY_FIRST_NAME,
                    new PassportDataMapper(),firstName,lastName)));
        }
        catch (DataAccessException ex){
            LOGGER.error("Passport data with first name "+firstName+" and last name "+lastName+" is not found",
                    ex.getCause());
            return Optional.empty();
        }
    }

    @Override
    public Optional<PassportData> findByIdentificationNumber(String number) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_FIND_BY_IDENTIFICATION_NUMBER,
                    new PassportDataMapper(),number)));
        }
        catch (DataAccessException ex){
            LOGGER.error("Passport data with identification number "+number+" is not found",ex.getCause());
            return Optional.empty();
        }
    }

    @Override
    public Optional<PassportData> getById(Long id) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_GET_PASSPORT_DATA_BY_ID,
                    new PassportDataMapper(),id)));
        }
        catch (DataAccessException ex){
            LOGGER.error("Passport data with id "+id+" is not found",ex.getCause());
            return Optional.empty();
        }
    }

    @Override
    public List<PassportData> getAll() {
        try {
            return jdbcTemplate.query(SQL_GET_ALL,new PassportDataMapper());
        }
        catch (DataAccessException ex){
            LOGGER.error("Any passport data are not found",ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public Long delete(PassportData passportData) {
        try {
            return (long) jdbcTemplate.update(SQL_DELETE_PASSPORT_DATA,passportData.isDeleted(), passportData.getId());
        }
        catch (DataAccessException ex){
            LOGGER.error("Passport data with id "+passportData.getId()+" has invalid credentials",ex.getCause());
            return -1L;
        }
    }

    @Override
    public Long update(PassportData passportData) {
        try {
            return (long) jdbcTemplate.update(SQL_UPDATE_PASSPORT_DATA,passportData.getFirstName(),passportData.getLastName(),
                    passportData.getPatronymic(),passportData.getBirthDate(),passportData.getPassportNumber(),
                    passportData.getIdentificationNumber(),passportData.getGender(),passportData.getId());
        }
        catch (DataAccessException ex){
            LOGGER.error("Passport data with id "+passportData.getId()+" has invalid credentials",ex.getCause());
            return -1L;
        }
    }

    @Override
    public Long save(PassportData passportData) {
        try {
            return (long) jdbcTemplate.update(SQL_SAVE_PASSPORT_DATA,passportData.getFirstName(),passportData.getLastName(),
                    passportData.getPatronymic(),passportData.getBirthDate(),passportData.getPassportNumber(),
                    passportData.getIdentificationNumber(),passportData.getGender());
        }
        catch (DataAccessException ex){
            LOGGER.error("Passport data has invalid credentials",ex.getCause());
            return -1L;
        }
    }
}