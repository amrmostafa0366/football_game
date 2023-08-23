package dao;

import config.DBConnection;
import model.Referee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RefereeDao {

    private final Connection connection = DBConnection.getConnection();

    public List<Referee> findAll() {
        String select = "SELECT * FROM public.referees";
        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Referee> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Referee(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Couldn't fetch data: " + e.getMessage());
        }
        return null;
    }

    public List<Referee> findByLeagueId(int id) {
        String select = "SELECT * FROM public.referees WHERE referees.league_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Referee> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Referee(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Couldn't fetch data: " + e.getMessage());
        }
        return null;
    }
}
