import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
class Match {
    private Team team1;
    private Team team2;
    private int goalsTeam1;
    private int goalsTeam2;

    public Match(Team team1, Team team2, int goalsTeam1, int goalsTeam2) {
        this.team1 = team1;
        this.team2 = team2;
        this.goalsTeam1 = goalsTeam1;
        this.goalsTeam2 = goalsTeam2;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public int getGoalsTeam1() {
        return goalsTeam1;
    }

    public int getGoalsTeam2() {
        return goalsTeam2;
    }
}

class Group {
    private String name;
    private List<Team> teams;

    public Group(String name) {
        this.name = name;
        this.teams = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }
}

class Team {
    private String name;
    private int points;
    private int wins;
    private int draws;
    private int losses;
    private int goalsScored;
    private int goalsConceded;

    public Team(String name) {
        this.name = name;
        this.points = 0;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
        this.goalsScored = 0;
        this.goalsConceded = 0;
    }

    public int getMatchesPlayed(){
        return wins + draws + losses;
    }
    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getLosses() {
        return losses;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public void addWin() {
        points += 3;
        wins++;
    }

    public void addDraw() {
        points += 2;
        draws++;
    }

    public void addLoss() {
        points += 1;
        losses++;
    }

    public void addGoals(int scored, int conceded) {
        goalsScored += scored;
        goalsConceded += conceded;
    }
}

class ChampionsLeague {
    private List<Group> groups;

    private List<Match> matches;

    public ChampionsLeague() {
        this.groups = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public void addGroup(Group group) {
        groups.add(group);
    }


    public void readMatchesFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");

                String team1Name = parts[0];
                String team2Name = parts[2];
                String goalsTeam1 = parts[1].split(":")[0];
                String goalsTeam2 = parts[1].split(":")[1];

                int goals1 = Integer.parseInt(goalsTeam1);
                int goals2 = Integer.parseInt(goalsTeam2);

                Team team1 = findTeamByName(team1Name);
                Team team2 = findTeamByName(team2Name);

                if (team1 != null && team2 != null) {
                    Match match = new Match(team1, team2, goals1, goals2);
                    matches.add(match);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readGroupsFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");

                String groupName = parts[0];
                Group group = new Group(groupName);

                for (int i = 1; i < parts.length; i++) {
                    String teamName = parts[i];
                    Team team = new Team(teamName);
                    group.addTeam(team);
                }

                addGroup(group);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Team findTeamByName(String teamName) {
        for (Group group : groups) {
            for (Team team : group.getTeams()) {
                if (team.getName().equals(teamName)) {
                    return team;
                }
            }
        }
        return null;
    }
    public void calculateGroupResults() {
        for (Group group : groups) {
            for (Match match : matches) {
                if (group.getTeams().contains(match.getTeam1()) && group.getTeams().contains(match.getTeam2())) {
                    int goalsTeam1 = match.getGoalsTeam1();
                    int goalsTeam2 = match.getGoalsTeam2();

                    if (goalsTeam1 > goalsTeam2) {
                        match.getTeam1().addWin();
                        match.getTeam2().addLoss();
                    } else if (goalsTeam1 < goalsTeam2) {
                        match.getTeam1().addLoss();
                        match.getTeam2().addWin();
                    } else {
                        match.getTeam1().addDraw();
                        match.getTeam2().addDraw();
                    }

                    match.getTeam1().addGoals(goalsTeam1, goalsTeam2);
                    match.getTeam2().addGoals(goalsTeam2, goalsTeam1);
                }
            }
        }
    }

    public void printGroupTablesToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (Group group : groups) {
                writer.println("Группа " + group.getName() + ":");
                for (Team team : group.getTeams()) {
                    writer.println(team.getName() + " Очки:" + team.getPoints() + " Победы:" + team.getWins() + " Ничьи:" + team.getDraws() +
                            " Поражения:" + team.getLosses() + " Забитых голов:" + team.getGoalsScored() + " Пропущенных голов:" + team.getGoalsConceded());
                }
                writer.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printResultsToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            List<Team> sortedTeams = new ArrayList<>();
            for (Group group : groups) {
                for (Team team : group.getTeams()) {
                    sortedTeams.add(team);            }
            }

            sortedTeams.sort(Comparator.comparing(Team::getPoints).reversed().thenComparing(Team::getMatchesPlayed));        for (Team team : sortedTeams) {
                writer.println(team.getName() + " | " + team.getPoints() + " | " + team.getMatchesPlayed());        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();    }
    }

}

public class Main {
    public static void main(String[] args) {

        ChampionsLeague championsLeague = new ChampionsLeague();

        championsLeague.readGroupsFromFile("Groups.txt");

        championsLeague.readMatchesFromFile("Game.txt");

        championsLeague.calculateGroupResults();

        championsLeague.printGroupTablesToFile("GroupsOut.txt");

        championsLeague.printResultsToFile("Results.txt");
    }
}