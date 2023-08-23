package model;

import dao.MatchDao;
import dao.PlayerDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    public static void football(List<Team> teams) {

        if (!teams.isEmpty() && teams.size() >= 2) {
            MatchDao matchDao = new MatchDao();
            PlayerDao playerDao = new PlayerDao();
            Random random = new Random();
            Team team1 = teams.get(random.nextInt(teams.size()));
            Team team2 = teams.get(random.nextInt(teams.size()));
            while (team1.getId() == team2.getId()) {
                team2 = teams.get(random.nextInt(teams.size()));
            }

            int score1 = random.nextInt(0, 5);
            int score2 = random.nextInt(0, 5);
            while (score1 == score2) {
                score2 = random.nextInt(0, 5);
            }

            Team winner;
            if (score1 > score2) {
                winner = team1;
            } else {
                winner = team2;
            }

            Match match = new Match(0, team1.getId(), team2.getId(), score1, score2, winner.getId());
            MatchInfo matchInfo = matchDao.insert(match);
            System.out.println(matchInfo);

            List<Player> team1players = playerDao.findAllByTeamId(team1.getId());
            List<Player> team2players = playerDao.findAllByTeamId(team2.getId());

            List<Player> team1Goals = getGoalsPlayers(team1players,score1);
            List<Player> team2Goals = getGoalsPlayers(team2players,score2);

            System.out.println(matchInfo.getTeam1() + " Goals: ");
            for (Player player : team1Goals) {
                System.out.println(player);
            }

            System.out.println(matchInfo.getTeam2() + " Goals: ");
            for (Player player : team2Goals) {
                System.out.println(player);
            }
        }
    }

    private static List<Player> getGoalsPlayers(List<Player> team, int goals) {
        Random random = new Random();
        List<Player> list = new ArrayList<>();
        for (int i = 0; i < goals; i++) {
            list.add(team.get(random.nextInt(team.size())));
        }
        return list;
    }

}
