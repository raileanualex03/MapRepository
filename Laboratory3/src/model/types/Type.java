package model.types;

import model.var.Value;

public interface Type {
    boolean equals(Type other);
    Value defaultValue();
}
