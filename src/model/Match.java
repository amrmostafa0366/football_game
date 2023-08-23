package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class Match {
    private int id;
    private int team1Id;
    private int team2Id;
    private int team1Score;
    private int team2Score;
    private int winnerId;
    private LocalDate date;
    private LocalTime time;


    public Match() {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public Match(int id, int team1Id, int team2Id, int team1Score, int team2Score, int winnerId) {
        this();
        this.id = id;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.team1Score = team1Score;
        this.team2Score = team2Score;
        this.winnerId = winnerId;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(int team1Id) {
        this.team1Id = team1Id;
    }

    public int getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(int team2Id) {
        this.team2Id = team2Id;
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

    public int getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
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
        return "ID: " + id + ", " + team1Id + " VS. " + team2Id +
                ", Winner: " + winnerId + ", " + team1Id + " Score: " + team1Score +
                ", " + team2Id + " Score: " + team2Score +
                ", Date: " + date + ", Time: " + time;
    }
}
