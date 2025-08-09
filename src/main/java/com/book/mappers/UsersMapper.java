package com.book.mapper;

import com.book.dto.UsersDTO;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersMapper {

    public static UsersDTO mapToDTO(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password"); // Get password if available
        String role = resultSet.getString("role");

        return new UsersDTO(id, username, email, password, role);
    }
}
