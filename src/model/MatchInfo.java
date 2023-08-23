package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class MatchInfo {
    private int id;
    private String team1;
    private String team2;
    private int team1Score;
    private int team2Score;
    private String winner;
    private LocalDate date;
    private LocalTime time;

    public MatchInfo() {
    }

    public MatchInfo(int id, String team1, String team2, int team1Score, int team2Score, String winner, LocalDate date, LocalTime time) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.team1Score = team1Score;
        this.team2Score = team2Score;
        this.winner = winner;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public int getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(int team1Score) {
        this.team1Score = team1Score;
    }

    public int getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(int team2Score) {
        this.team2Score = team2Score;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", " + team1 + " VS. " + team2 +
                ", Winner: " + winner + ", " + team1 + " Score: " + team1Score +
                ", " + team2 + " Score: " + team2Score +
                ", Date: " + date + ", Time: " + time;
    }
}
