package samdev.de.projectcom.objects;

/**
 * Created by cYa on 12.12.2015.
 */
public class Player {
    private int id;
    private String playerId;
    private String playerName;
    private String playerTeam;

    public Player(){
        super();
    }

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
    public void setPlayerId(String playerId){
        this.playerId = playerId;
    }

    public String getPlayerName(){
        return playerName;
    }
    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    public String getPlayerTeam(){
        return playerTeam;
    }
    public void setPlayerTeam(String playerTeam){
        this.playerTeam = playerTeam;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
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
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", playerId=" + playerId + ", playerName="
                + playerName + ", playerTeam=" + playerTeam + "]";
    }
}
