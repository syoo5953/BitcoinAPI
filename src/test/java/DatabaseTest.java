import model.Database;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseTest {
  Statement statement;
  PreparedStatement preparedStatement;
  Connection connection;
  Database database = new Database();

  @Before
  public void setUp() {
    statement = Mockito.mock(Statement.class);
    preparedStatement = Mockito.mock(PreparedStatement.class);
    connection = Mockito.mock(Connection.class);
  }

  @Test
  public void testDatabase() throws SQLException {
    Mockito.when(connection.prepareStatement(Mockito.eq("INSERT INTO DATA_OUTPUT VALUES(?)"))).thenReturn(preparedStatement);
    Mockito.when(preparedStatement.executeBatch()).thenReturn(new int[]{1, 2, 3});
    database.InsertDB(connection, "data");
    Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
  }

  @Test
  public void testDatabase1() throws SQLException {
    Mockito.when(connection.prepareStatement(Mockito.eq("INSERT INTO DATA_OUTPUT VALUES(?)"))).thenReturn(preparedStatement);
    Mockito.when(preparedStatement.executeBatch()).thenReturn(new int[]{1, 2, 3});
    database.InsertDB(connection, "data");
    Mockito.verify(preparedStatement, Mockito.times(1)).setString(Mockito.eq(1), Mockito.anyString());
  }
}
