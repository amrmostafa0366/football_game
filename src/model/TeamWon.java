package model;

public class TeamWon {
    private String team;
    private int wonCounter;

    public TeamWon(String team, int wonCounter) {
        this.team = team;
        this.wonCounter = wonCounter;
    }

    @Override
    public String toString() {
        return team + " : " + wonCounter;
    }
}
