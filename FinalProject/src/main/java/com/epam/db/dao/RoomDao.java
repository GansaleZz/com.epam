package com.epam.db.dao;

import com.epam.criteria.RoomCriteria;
import com.epam.entity.Room;
import com.epam.exceptions.DaoException;
import com.epam.exceptions.FileException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RoomDao extends BaseDao<Integer,Room>{
    List<Room> findAllRoomsByCriteria(RoomCriteria roomCriteria) throws DaoException;
}
