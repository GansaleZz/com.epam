package com.epam.db.dao;

import com.epam.criteria.RoomCriteria;
import com.epam.entity.Room;
import com.epam.exceptions.DaoException;
import java.util.List;

public interface RoomDao extends BaseDao<Integer,Room>{
    List<Room> findAllRoomsByCriteria(RoomCriteria roomCriteria) throws DaoException;
}
