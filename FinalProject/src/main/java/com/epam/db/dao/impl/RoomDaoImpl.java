package com.epam.db.dao.impl;

import com.epam.criteria.RoomCriteria;
import com.epam.db.dao.RoomDao;
import com.epam.entity.Room;
import com.epam.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public class RoomDaoImpl implements RoomDao {
    @Override
    public List<Room> findAllEntities() throws DaoException {
        return null;
    }

    @Override
    public Optional<Room> findEntityById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean create(Long id) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Room room) throws DaoException {
        return false;
    }

    @Override
    public Optional<Room> update(Room room) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<Room> findRoomByCriteria(RoomCriteria roomCriteria) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<Room> findAllRoomsByCriteria(RoomCriteria roomCriteria) throws DaoException {
        return null;
    }
}
