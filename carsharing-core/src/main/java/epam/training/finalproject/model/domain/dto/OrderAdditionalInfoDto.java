package epam.training.finalproject.model.domain.dto;

public class OrderAdditionalInfoDto extends AbstractEntityDto {

    private String infoDetails;
    private int paymentForViolation;

    public OrderAdditionalInfoDto() {
    }

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
