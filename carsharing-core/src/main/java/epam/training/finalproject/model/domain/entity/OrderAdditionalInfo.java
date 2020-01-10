package epam.training.finalproject.model.domain.entity;

public class OrderAdditionalInfo extends AbstractEntity {

    private String infoDetails;
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
