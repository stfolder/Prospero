package sstar.prospero.entity;

import java.util.List;

/**
 * Created by Sergey.Tarasenko on 01.03.2015.
 */
public class Operation {
    private int operationId;
    private String caption;
    List<Operation> operations;

    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
    
    @Override
    public String toString() {
        return caption;
    }
}
