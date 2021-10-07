package model;

import java.sql.*;

public class Database {

  public Connection DBConnection() {
    String jdbcUrl = "jdbc:sqlite:exam_period.db";

    Connection conn = null;
    try {
      conn = DriverManager.getConnection(jdbcUrl);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return conn;
  }

  public Statement createTable(Connection conn) throws SQLException {
    String CREATE_TABLE_SQL =
        "CREATE TABLE IF NOT EXISTS DATA_OUTPUT (" + "DATA_OUTPUT varchar(100))";

    Statement stmt = conn.createStatement();
    stmt.execute(CREATE_TABLE_SQL);
    return stmt;
  }

  public void InsertDB(Connection conn, String data) throws SQLException {
    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO DATA_OUTPUT" + " VALUES(?)");
    pstmt.setString(1, data);
    pstmt.addBatch();
    try {
      pstmt.executeBatch();
    } catch (SQLException e) {
      System.out.println("Error message: " + e.getMessage());
      return;
    }
  }

  public void getResult(Statement stmt) {
    try {
      int i = 1;
      ResultSet rs;
      rs = stmt.executeQuery("SELECT * FROM DATA_OUTPUT");
      System.out.println("Database Data");
      System.out.println("--------------------------------------");
      while (rs.next()) {
        System.out.println(rs.getString(i++));
        i = 1;
      }
      System.out.println("--------------------------------------");

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
