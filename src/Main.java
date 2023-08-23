
import config.DBConnection;
import dao.*;
import model.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        LeagueDao leagueDao = new LeagueDao();
        TeamDao teamDao = new TeamDao();
        CoachDao coachDao = new CoachDao();
        PlayerDao playerDao = new PlayerDao();
        RefereeDao refereeDao = new RefereeDao();


        System.out.println("--------------------------------------"); //list all leagues in the database.
        List<League> leagues = leagueDao.findAll();
        for (League league : leagues) {
            System.out.println(league);
        }

        System.out.println("--------------------------------------"); //list all teams in a league.
        List<Team> teams = teamDao.findByLeagueId(1);
        for (Team team : teams) {
            System.out.println(team);
        }

        System.out.println("--------------------------------------"); //list all referees in a league.
        List<Referee> referees = refereeDao.findByLeagueId(1);
        for (Referee referee : referees) {
            System.out.println(referee);
        }

        System.out.println("--------------------------------------"); //list all coaches in a league and their corresponding team.
        List<CoachTeam> coaches = coachDao.findByLeagueId(1);
        for (CoachTeam coach : coaches) {
            System.out.println(coach);
        }

        System.out.println("--------------------------------------"); //list all players in a team.
        List<Player> players = playerDao.findAllByTeamId(1);
        for (Player player : players) {
            System.out.println(player);
        }

        System.out.println("--------------------------------------"); //list all played matches.
        MatchDao matchDao = new MatchDao();
        List<Match> matches = matchDao.findAll();
        for (Match match : matches) {
            System.out.println(match);
        }
        System.out.println("--------------------------------------"); //display all info related to a match.
        System.out.println(matchDao.findByIdAllInfo(5));
        System.out.println("--------------------------------------"); //display all info related to a player.
        System.out.println(playerDao.findById(5));

        System.out.println("--------------------------------------FOOTBALL  GAME--------------------------------------");
        for (int i = 0; i < 5; i++) {
            Game.football(teams);
        }
        System.out.println("--------------------------------------"); // standing table
        List<TeamWon> teamsOrder = teamDao.findByLeagueIdStandingTable(1);
        for (int i = 0; i < teamsOrder.size(); i++) {
            System.out.println(i + 1 + ". " + teamsOrder.get(i));
        }
        DBConnection.closeConnection();
    }


}