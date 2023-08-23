package dao;

import config.DBConnection;
import model.Coach;
import model.CoachTeam;
import model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoachDao {

    private final Connection connection = DBConnection.getConnection();

    public List<Coach> findAll() {
        String select = "SELECT * FROM public.coaches";
        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Coach> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Coach(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Couldn't fetch data: " + e.getMessage());
        }
        return null;
    }

    public List<CoachTeam> findByLeagueId(int id) {
        String query =
                "SELECT coaches.name, teams.name " +
                        "FROM coaches " +
                        "INNER JOIN teams ON teams.id = coaches.team_id " +
                        "WHERE teams.league_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<CoachTeam> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new CoachTeam(resultSet.getString(1), resultSet.getString(2)));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Couldn't fetch data: " + e.getMessage());
        }
        return null;
    }
}
