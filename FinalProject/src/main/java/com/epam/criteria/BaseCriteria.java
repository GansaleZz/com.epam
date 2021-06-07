package com.epam.criteria;

import com.epam.entity.BaseEntity;


public abstract class BaseCriteria<T extends BaseEntity> {
    /**
     * Pattern builder for entities
     */
    protected static abstract class BaseBuilder<T extends BaseEntity> {
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
