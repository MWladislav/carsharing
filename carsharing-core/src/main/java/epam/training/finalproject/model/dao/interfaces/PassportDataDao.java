package epam.training.finalproject.model.dao.interfaces;

import epam.training.finalproject.model.domain.entity.PassportData;

import java.util.Optional;

public interface PassportDataDao extends AbstractEntityDao<Long, PassportData> {

    Optional<PassportData> findByFirstNameAndLastName(String firstName,String lastName);

    Optional<PassportData> findByIdentificationNumber(String number);

}
