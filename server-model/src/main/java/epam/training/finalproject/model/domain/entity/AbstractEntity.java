package epam.training.finalproject.model.domain.entity;

public abstract class AbstractEntity {

    private long id;
    private boolean deleted;

    public AbstractEntity(){}

    AbstractEntity(long id) {
        this.id = id;
    }

    public AbstractEntity(long id, boolean deleted) {
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
