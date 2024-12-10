package org.project_kessel.relations.converters;

import org.project_kessel.api.relations.v1beta1.SubjectReference;

public interface SubjectRefConverter<T> {
    SubjectReference convert(T source);
}
