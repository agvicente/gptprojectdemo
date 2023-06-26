package com.agvicente.gptprojectdemo.entities;

import java.io.Serializable;
import java.util.Objects;

public abstract class BaseEntity implements Serializable {

    private Long id;

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        return Objects.equals(id, that.id);
    }
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public abstract void setId(Long id);

    public abstract Long getId();

}

