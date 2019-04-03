package epam.training.finalproject.carsharing.model.domain.mapper;

import epam.training.finalproject.carsharing.model.domain.entity.Address;
import epam.training.finalproject.carsharing.model.domain.entity.PassportData;
import epam.training.finalproject.carsharing.model.domain.entity.enums.Gender;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PassportDataMapper implements RowMapper<PassportData> {
    public PassportData mapRow(ResultSet resultSet, int i) throws SQLException {
        PassportData passportData = new PassportData();
        passportData.setId(resultSet.getInt("idPassportData"));
        passportData.setFirstName(resultSet.getString("first_name"));
        passportData.setLastName(resultSet.getString("last_name"));
        passportData.setPatronomyc(resultSet.getString("patronymic"));
        passportData.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
        passportData.setPassportNumber(resultSet.getString("passport_number"));
        passportData.setIdentificationNumber(resultSet.getString("identification_number"));
        passportData.setGender(Gender.valueOf(resultSet.getString("gender").toUpperCase()));

        Address address = new AddressMapper().mapRow(resultSet,i);
        passportData.setAddress(address);
        return passportData;
    }
}
