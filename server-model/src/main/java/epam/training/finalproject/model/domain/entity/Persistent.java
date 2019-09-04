package epam.training.finalproject.model.domain.entity;

public abstract class Persistent {

    private long id;
    private boolean deleted;

    public Persistent(){}

    Persistent(long id) {
        this.id = id;
    }

    public Persistent(long id, boolean deleted) {
        this.id = id;
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
