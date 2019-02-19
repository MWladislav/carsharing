package epam.finalProject.carSharing.model.dao.interfaces;

import epam.finalProject.carSharing.model.domain.entity.PassportData;

public interface PassportDataDao extends PersistentDao<Long,PassportData> {

    PassportData findByFirstName(String firstName);

    PassportData findByLastName(String lastName);

    PassportData findByIdentificationNumber(String number);

}
