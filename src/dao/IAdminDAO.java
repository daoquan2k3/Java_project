package dao;

import java.sql.SQLException;

public interface IAdminDAO {
    boolean loginAdmin(String username, String password) throws SQLException;
}
