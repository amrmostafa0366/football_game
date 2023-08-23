package dao;

import config.DBConnection;
import model.Player;
import model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDao {
    private final Connection connection = DBConnection.getConnection();

    public List<Player> findAll() {
        String select = "SELECT * FROM public.players";
        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Player> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Player(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Couldn't fetch data: " + e.getMessage());
        }
        return null;
    }

    public List<Player> findAllByTeamId(int id) {
        String select = "SELECT * FROM public.players WHERE \"team_id\"=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Player> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Player(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Couldn't fetch data: " + e.getMessage());
        }
        return null;
    }

    public Player findById(int id) {
        String select = "SELECT * FROM public.players WHERE \"id\"=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Player(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3));
            }
        } catch (SQLException e) {
            System.out.println("Couldn't fetch data: " + e.getMessage());
        }
        return null;
    }


}
