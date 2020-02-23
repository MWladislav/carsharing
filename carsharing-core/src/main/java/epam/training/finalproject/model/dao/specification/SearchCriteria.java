package epam.training.finalproject.model.dao.specification;

import java.util.List;

public class SearchCriteria {
    private String key;
    private String operation;
    private List<Object> values;
    private Object value;

    public SearchCriteria() {
    }

    public SearchCriteria(String key, String operation, List values) {
        this.key = key;
        this.operation = operation;
        if (values != null && values.size() == 1){
            this.value = values.get(0);
        } else {
            this.values = values;
        }
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setValues(List<Object> values) {
        if (values != null && values.size() == 1){
            this.value = values.get(0);
        } else {
            this.values = values;
        }
    }

    public String getKey() {
        return key;
    }

    public String getOperation() {
        return operation;
    }

    public List<Object> getValues() {
        return values;
    }

    public Object getValue() {
        return value;
    }
}
