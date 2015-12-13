package samdev.de.projectcom.model;

/**
 * Created by cYa on 12.12.2015.
 */
public class Player {
    private int id;
    private String playerId;
    private String playerName;
    private String playerTeam;

    public Player(int id, String playerId, String playerName, String playerTeam){
        super();
        this.id = id;
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerTeam = playerTeam;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getPlayerId(){
        return playerId;
    }

    public String getPlayerName(){
        return playerName;
    }

    public String getPlayerTeam(){
        return playerTeam;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Player other = (Player) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return String.format("Player [id=%d, playerId=%s, playerName=%s, playerTeam=%s]", id, playerId, playerName, playerTeam);
    }
}
