package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity> {

    protected static abstract class BaseBuilder<T extends  BaseEntity> {
        protected T actualClass;

        protected abstract T getActual();

        protected BaseBuilder() {
            actualClass = getActual();
        }

        public T build() {
            return actualClass;
        }
    }
}
