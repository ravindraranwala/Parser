package org.wallet.test.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wallet.test.model.LogEntry;

public class LogEntryDao {
	// JDBC Driver Name & Database URL
	private final String JDBC_DB_URL;
	// JDBC Database Credentials
	private final String JDBC_USER;
	private final String JDBC_PASS;

	private final Logger LOGGER = LoggerFactory.getLogger(LogEntryDao.class);

	private static LogEntryDao instance;

	private LogEntryDao() {
		ResourceBundle rb = ResourceBundle.getBundle("config");
		JDBC_DB_URL = rb.getString("jdbc.url");
		JDBC_USER = rb.getString("jdbc.username");
		JDBC_PASS = rb.getString("jdbc.password");
	}

	public void saveLogEntried(List<LogEntry> entries) {
		try (Connection connObj = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
				// Statement stmtObj = connObj.createStatement();
				PreparedStatement prepareStatement = connObj
						.prepareStatement("INSERT INTO logentry (date, ip, request) values(?, ?,?)")) {
			connObj.setAutoCommit(false);
			entries.forEach(entry -> {
				try {
					// stmtObj.addBatch("INSERT INTO logentry (date, ip, request) values('" +
					// entry.getDate() + "','"
					// + entry.getIp() + "','" + entry.getRequest() + "')");
					prepareStatement.setString(1, entry.getDate());
					prepareStatement.setString(2, entry.getIp());
					prepareStatement.setString(3, entry.getRequest());
					prepareStatement.addBatch();
				} catch (SQLException e) {
					LOGGER.error("Error occurred while inserting data in to the Database", e);
				}
			});

			// stmtObj.executeBatch();
			prepareStatement.executeBatch();
			connObj.commit();
		} catch (Exception e) {
			LOGGER.error("Error occurred while inserting data in to the Database", e);
		}
	}

	public static LogEntryDao getInstance() {
		if (instance == null) {
			instance = new LogEntryDao();
		}
		return instance;
	}
}
