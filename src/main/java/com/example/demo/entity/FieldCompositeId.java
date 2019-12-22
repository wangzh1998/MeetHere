package com.example.demo.entity;

import javax.persistence.Id;
import java.io.Serializable;

public class FieldCompositeId implements Serializable {

    private Integer fieldId;
    private Integer gymId;

    public FieldCompositeId() {
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = fieldId.hashCode() + gymId.hashCode();
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final FieldCompositeId other = (FieldCompositeId) obj;
        if (fieldId == other.fieldId && gymId == other.gymId)
            return true;
        return false;
    }

}
