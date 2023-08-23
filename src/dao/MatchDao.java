package dao;

import config.DBConnection;
import model.Match;
import model.Match;
import model.MatchInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchDao {
    private final Connection connection = DBConnection.getConnection();

    public List<Match> findAll() {
        String select = "SELECT * FROM public.matches";
        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Match> list = new ArrayList<>();
            while (resultSet.next()) {
                Match match = new Match();
                match.setId(resultSet.getInt(1));
                match.setTeam1Id(resultSet.getInt(2));
                match.setTeam2Id(resultSet.getInt(3));
                match.setTeam1Score(resultSet.getInt(4));
                match.setTeam2Score(resultSet.getInt(5));
                match.setWinnerId(resultSet.getInt(6));
                match.setDate(resultSet.getDate(7).toLocalDate());
                match.setTime(resultSet.getTime(8).toLocalTime());
                list.add(match);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Couldn't fetch data: " + e.getMessage());
        }
        return null;
    }

    public List<MatchInfo> findAllInfo(int id) {
        String select = "SELECT " +
                "matches.id, " +
                "team1.name AS team1, " +
                "team2.name AS team2, " +
                "matches.team1_score AS score1, " +
                "matches.team2_score AS score2, " +
                "winner_team.name AS winner, " +
                "matches.date AS date, " +
                "matches.time AS time " +
                "FROM matches " +
                "INNER JOIN teams AS team1 ON matches.team1_id = team1.id " +
                "INNER JOIN teams AS team2 ON matches.team2_id = team2.id " +
                "LeFT JOIN teams AS winner_team ON matches.winner_team_id = winner_team.id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<MatchInfo> list = new ArrayList<>();
            while (resultSet.next()) {
                MatchInfo match = new MatchInfo();
                match.setId(resultSet.getInt(1));
                match.setTeam1(resultSet.getString(2));
                match.setTeam2(resultSet.getString(3));
                match.setTeam1Score(resultSet.getInt(4));
                match.setTeam2Score(resultSet.getInt(5));
                match.setWinner(resultSet.getString(6));
                match.setDate(resultSet.getDate(7).toLocalDate());
                match.setTime(resultSet.getTime(8).toLocalTime());
                list.add(match);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Couldn't fetch data: " + e.getMessage());
        }
        return null;
    }

    public Match findById(int id) {
        String select = "SELECT * FROM public.matches WHERE \"id\"=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Match match = new Match();
                match.setId(resultSet.getInt(1));
                match.setTeam1Id(resultSet.getInt(2));
                match.setTeam2Id(resultSet.getInt(3));
                match.setTeam1Score(resultSet.getInt(4));
                match.setTeam2Score(resultSet.getInt(5));
                match.setWinnerId(resultSet.getInt(6));
                match.setDate(resultSet.getDate(7).toLocalDate());
                match.setTime(resultSet.getTime(8).toLocalTime());
                return match;
            }
        } catch (SQLException e) {
            System.out.println("Couldn't fetch data: " + e.getMessage());
        }
        return null;
    }

    public MatchInfo findByIdAllInfo(int id) {
        String select = "SELECT " +
                "matches.id, " +
                "team1.name AS team1, " +
                "team2.name AS team2, " +
                "matches.team1_score AS score1, " +
                "matches.team2_score AS score2, " +
                "winner_team.name AS winner, " +
                "matches.date AS date, " +
                "matches.time AS time " +
                "FROM matches " +
                "INNER JOIN teams AS team1 ON matches.team1_id = team1.id " +
                "INNER JOIN teams AS team2 ON matches.team2_id = team2.id " +
                "LeFT JOIN teams AS winner_team ON matches.winner_team_id = winner_team.id " +
                "WHERE matches.id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                MatchInfo match = new MatchInfo();
                match.setId(resultSet.getInt(1));
                match.setTeam1(resultSet.getString(2));
                match.setTeam2(resultSet.getString(3));
                match.setTeam1Score(resultSet.getInt(4));
                match.setTeam2Score(resultSet.getInt(5));
                match.setWinner(resultSet.getString(6));
                match.setDate(resultSet.getDate(7).toLocalDate());
                match.setTime(resultSet.getTime(8).toLocalTime());
                return match;
            }
        } catch (SQLException e) {
            System.out.println("Couldn't fetch data: " + e.getMessage());
        }
        return null;
    }

    public MatchInfo insert(Match match) {
        String insert = "INSERT INTO public.matches( " +
                "team1_id, team2_id, team1_score, team2_score, winner_team_id, date, time) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, match.getTeam1Id());
            preparedStatement.setInt(2, match.getTeam2Id());
            preparedStatement.setInt(3, match.getTeam1Score());
            preparedStatement.setInt(4, match.getTeam2Score());
            preparedStatement.setInt(5, match.getWinnerId());
            preparedStatement.setDate(6, Date.valueOf(match.getDate()));
            preparedStatement.setTime(7, Time.valueOf(match.getTime()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int generatedId = resultSet.getInt(1);
                MatchInfo matchInfo = findByIdAllInfo(generatedId);
                return matchInfo;
            }
        } catch (SQLException e) {
            System.out.println("Couldn't insert data: " + e.getMessage());
        }
        return null;
    }
}
