package model;

public class Team {
    private int id;
    private String name;
    private int leagueId;

    public Team() {
    }

    public Team(int id, String name, int leagueId) {
        this.id = id;
        this.name = name;
        this.leagueId = leagueId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    @Override
    public String toString() {
        return "ID: "+id +", Name: "+name + ", League: "+leagueId;
    }
}
