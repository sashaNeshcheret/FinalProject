package ua.simpleproject.dao.Impl;

import ua.simpleproject.dao.DAOZReportTime;
import ua.simpleproject.entity.ZReportTime;
import ua.simpleproject.exception.ConnectionException;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.transactions.ConnectionWrapper;
import ua.simpleproject.transactions.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DAOZReportTimeImpl implements DAOZReportTime{
    public static final String SQL_UPDATE = "UPDATE z_report_time SET time = now() WHERE id = ?";
    public static final String SQL_SELECT = "SELECT * FROM z_report_time where id = ?";


    public ZReportTime read() throws DAOException{
        ZReportTime zReportTime = null;
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_SELECT);
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                zReportTime = new ZReportTime();
                zReportTime.setId(resultSet.getInt(1));
                zReportTime.setZTime(resultSet.getTimestamp(2));
            }
        } catch (SQLException | ConnectionException e) {
            throw new DAOException("From method read from z_report_time hasn't got ZReportTime", e);
        }
        return zReportTime;
    }
    public void update() throws DAOException{
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_UPDATE);
            preparedStatement.setInt(1, 1);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            throw new DAOException("Method z_report_time throws exception", e);
        }
    }
}
