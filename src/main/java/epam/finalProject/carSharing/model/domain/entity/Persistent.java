package epam.finalProject.carSharing.model.domain.entity;

public abstract class Persistent {

    private long id;

    public Persistent(){}

    Persistent(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
