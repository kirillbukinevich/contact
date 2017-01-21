package com.itechart.bukinevi.logic.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/**
 * Created by aefrd on 28.09.2016.
 */
public abstract class AbstractDAO {
    protected static final Connection connection = ConnectionFactory.getConnection();
    private static final Logger LOGGER = LogManager.getLogger(AttachmentDAO.class.getName());
    protected Statement stmt;
    protected PreparedStatement preparedStatement;

    public void startEditContact() {
        try {
            connection.setAutoCommit(false);
            LOGGER.info("start edit contact");
        } catch (SQLException e) {
            LOGGER.error(String.format("can't start edit contact %s", e));
        }
    }

    public void saveContact() {
        try {
            if (!connection.getAutoCommit()) {
                connection.commit();
                connection.setAutoCommit(true);
                LOGGER.info("save contact");
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("can't save contact %s", e));
        }
    }

    public void rollBack() {
        try {
            if (!connection.getAutoCommit()) {
                connection.rollback();
                connection.setAutoCommit(true);
                LOGGER.info("cancel edit contact");
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("can't cancel edit contact %s", e));
        }
    }


    protected void updatePrepareStatement(String sqlQuery) {
        try {
            this.preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }
    }

    protected int retriveId(PreparedStatement preparedStatement) throws SQLException {
        ResultSet rs = preparedStatement.getGeneratedKeys();
        int last_inserted_id = 0;
        if (rs.next()) {
            last_inserted_id = rs.getInt(1);
        }
        return last_inserted_id;
    }

    protected void closePreparedStatement(String methodName){
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("can't close preparedStatement method %s to BD %s",methodName, e));
        }

    }

    protected void closeStatement(String methodName){
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("can't close statement method %s to BD %s",methodName, e));
        }

    }

}
