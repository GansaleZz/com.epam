package com.epam.db.dao;

import com.epam.criteria.Impl.RoomCriteria;
import com.epam.entity.Room;
import com.epam.exceptions.DaoException;
import java.util.List;

public interface RoomDao extends BaseDao<Integer,Room>{

    /**
     * Method that use to find all rooms on db by special criteria
     * @param roomCriteria - parameter by which rooms are searching on db
     * @return list of rooms with a specific criteria
     */
    List<Room> findAllRoomsByCriteria(RoomCriteria roomCriteria) throws DaoException;
}
