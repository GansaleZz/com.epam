package com.epam.db.dao;

import com.epam.criteria.Impl.RequestCriteria;
import com.epam.entity.Request;
import com.epam.exceptions.DaoException;
import java.util.List;

public interface RequestDao extends BaseDao<Integer,Request>{

    /**
     * Method that use to find all requests on db by special criteria
     * @param requestCriteria - parameter by which requests are searching on db
     * @return list of requests with a specific criteria
     */
    List<Request> findAllRequestsByCriteria(RequestCriteria requestCriteria) throws DaoException;
}
