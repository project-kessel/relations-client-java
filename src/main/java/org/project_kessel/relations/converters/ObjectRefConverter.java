package org.project_kessel.relations.converters;

import org.project_kessel.api.relations.v1beta1.ObjectReference;
import org.project_kessel.api.relations.v1beta1.ObjectType;

public interface ObjectRefConverter<T> {
    ObjectType objectType();

    ObjectReference convert(T source);
}
