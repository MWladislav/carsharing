package epam.training.finalproject.model.domain.entity.enums;

public enum CarEngineType {
    fuel("fuel"),
    electric("electric");

    private String type;
    CarEngineType(String type){
        this.type = type;
    }
}
