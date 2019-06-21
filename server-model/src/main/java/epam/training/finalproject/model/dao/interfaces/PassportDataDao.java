package epam.training.finalproject.model.dao.interfaces;

import epam.training.finalproject.model.domain.entity.PassportData;

public interface PassportDataDao extends PersistentDao<Long, PassportData> {

    PassportData findByFirstName(String firstName);

    PassportData findByLastName(String lastName);

    PassportData findByIdentificationNumber(String number);

}
