package epam.training.finalproject.carsharing.model.dao.interfaces;

import epam.training.finalproject.carsharing.model.domain.entity.PassportData;

public interface PassportDataDao extends PersistentDao<Long,PassportData> {

    PassportData findByFirstName(String firstName);

    PassportData findByLastName(String lastName);

    PassportData findByIdentificationNumber(String number);

}
