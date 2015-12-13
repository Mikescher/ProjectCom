package samdev.de.projectcom.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import samdev.de.projectcom.model.Player;

public class SharedPreference {

    public static final String PREFS_NAME = "Player_Pref";
    public static final String ALL_PLAYER = "Player_All";

    public SharedPreference() {
        super();
    }

    public void savePlayers(Context context, List<Player> favorites){
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(ALL_PLAYER, jsonFavorites);

        editor.apply();
    }

    public void addPlayer(Context context, Player player) {
        List<Player> players = getPlayers(context);
        if (players == null)
            players = new ArrayList<>();
        players.add(player);
        savePlayers(context, players);
    }

    public void removePlayer(Context context, Player player) {
        ArrayList<Player> favorites = getPlayers(context);
        if (favorites != null) {
            favorites.remove(player);
            savePlayers(context, favorites);
        }
    }

    public ArrayList<Player> getPlayers(Context context) {
        SharedPreferences settings;
        List<Player> players;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if (settings.contains(ALL_PLAYER)) {
            String jsonFavorites = settings.getString(ALL_PLAYER, null);
            Gson gson = new Gson();
            Player[] playerItems = gson.fromJson(jsonFavorites, Player[].class);

            players = Arrays.asList(playerItems);
            players = new ArrayList<>(players);
        } else
            return null;

        return (ArrayList<Player>) players;
    }
}
