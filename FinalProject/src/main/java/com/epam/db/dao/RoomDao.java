package com.epam.db.dao;

import com.epam.criteria.RoomCriteria;
import com.epam.entity.Room;
import com.epam.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface RoomDao extends BaseDao<Long,Room>{
    Optional<Room> findRoomByCriteria(RoomCriteria roomCriteria) throws DaoException;

    List<Room> findAllRoomsByCriteria(RoomCriteria roomCriteria) throws DaoException;
}
