package model.types;

import model.var.Value;

public interface Type {
    boolean equals(Object other);
    Value defaultValue();
}
