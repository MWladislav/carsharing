package epam.training.finalproject.model.domain.entity;

import epam.training.finalproject.model.domain.entity.enums.Gender;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "passport_data", uniqueConstraints =
        { @UniqueConstraint(columnNames = { "passport_number", "identification_number" }) })
public class PassportData extends AbstractEntity {
    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;
    @Column(name = "patronymic", length = 45, nullable = false)
    private String patronymic;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    @Column(name = "passport_number", length = 45, unique = true, nullable = false)
    private String passportNumber;
    @Column(name = "identification_number", length = 45, unique = true, nullable = false)
    private String identificationNumber;
    @Column(name = "gender", length = 6, nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public PassportData() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
