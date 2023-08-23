package model;

public class CoachTeam {
    private String coach;
    private String team;

    public CoachTeam(String coach, String team) {
        this.coach = coach;
        this.team = team;
    }

    @Override
    public String toString() {
        return"Coach: " + coach +", Team: " + team;
    }
}
