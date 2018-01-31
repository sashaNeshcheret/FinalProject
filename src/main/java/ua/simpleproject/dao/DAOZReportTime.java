package ua.simpleproject.dao;

import ua.simpleproject.entity.ZReportTime;
import ua.simpleproject.exception.DAOException;

import java.sql.Time;
import java.sql.Timestamp;

public interface DAOZReportTime {
    ZReportTime read () throws DAOException;
    void update() throws DAOException;
}
