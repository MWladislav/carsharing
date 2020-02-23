package epam.training.finalproject.model.domain.entity.enums;

public enum RoleName {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private String rn;
    RoleName(String rn){
        this.rn = rn;
    }
}
