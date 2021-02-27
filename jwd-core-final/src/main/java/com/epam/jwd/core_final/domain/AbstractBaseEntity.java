package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * id {@link Long} - entity id
 * name {@link String} - entity name
 */


public abstract class AbstractBaseEntity implements BaseEntity {
    private Long id ;
    private String name;

    public AbstractBaseEntity(String name,int id){
        this.name = name;
        this.id = (long) id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
