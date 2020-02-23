package epam.training.finalproject.model.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "order_additional_info")
public class OrderAdditionalInfo extends AbstractEntity {
    @Column(name = "info_details", nullable = false)
    private String infoDetails;
    @Column(name = "payment_for_violation", nullable = false)
    private int paymentForViolation;

    public String getInfoDetails() {
        return infoDetails;
    }

    public void setInfoDetails(String infoDetails) {
        this.infoDetails = infoDetails;
    }

    public int getPaymentForViolation() {
        return paymentForViolation;
    }

    public void setPaymentForViolation(int paymentForViolation) {
        this.paymentForViolation = paymentForViolation;
    }
}
