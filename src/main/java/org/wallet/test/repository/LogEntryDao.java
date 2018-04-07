package org.wallet.test.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wallet.test.model.LogEntry;

public class LogEntryDao {
	// JDBC Driver Name & Database URL
	private static final String JDBC_DB_URL = "jdbc:mysql://localhost:3307/logservice";
	// JDBC Database Credentials
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "mysql";

	private static final Logger LOGGER = LoggerFactory.getLogger(LogEntryDao.class);

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
}
