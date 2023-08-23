package dao;

import config.DBConnection;
import model.League;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeagueDao {

    private final Connection connection = DBConnection.getConnection();



    public List<League> findAll() {
        String select = "SELECT * FROM public.leagues";
        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<League> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new
                        League(resultSet.getInt(1),
                        resultSet.getString(2)));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Couldn't fetch data: " + e.getMessage());
        }
        return null;
    }

}

