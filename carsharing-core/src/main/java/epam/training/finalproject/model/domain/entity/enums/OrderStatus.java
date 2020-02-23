package epam.training.finalproject.model.domain.entity.enums;

public enum OrderStatus {
    PAID("paid"),
    FROZEN("frozen"),
    CONFIRMED("confirmed"),
    CREATED("created"),
    FINISHED("finished");

    private String status;
    OrderStatus(String status){
        this.status = status;
    }
}
