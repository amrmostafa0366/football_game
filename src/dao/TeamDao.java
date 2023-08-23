package dao;

import config.DBConnection;
import model.Team;
import model.TeamWon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDao {

    private final Connection connection = DBConnection.getConnection();

    public List<Team> findAll() {
        String select = "SELECT * FROM public.teams";
        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Team> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new
                        Team(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3)));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Couldn't fetch data: " + e.getMessage());
        }
        return null;
    }

    public List<Team> findByLeagueId(int id) {
        String select = "SELECT * FROM public.teams WHERE \"league_id\"=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Team> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new
                        Team(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3)));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Couldn't fetch data: " + e.getMessage());
        }
        return null;
    }

    public List<TeamWon> findByLeagueIdStandingTable(int id) {
        String select = "SELECT teams.name , count(*) as won_matches " +
                "From teams " +
                "INNER JOIN matches on teams.id = matches.winner_team_id " +
                "WHERE teams.league_id =? " +
                "Group by teams.name " +
                "ORDER BY won_matches DESC";
        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TeamWon> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new
                        TeamWon(resultSet.getString(1),
                        resultSet.getInt(2)));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Couldn't fetch data: " + e.getMessage());
        }
        return null;
    }
}

