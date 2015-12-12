package samdev.de.projectcom.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import samdev.de.projectcom.objects.Player;
import samdev.de.projectcom.R;
import samdev.de.projectcom.SharedPreference;

/**
 * Created by cYa on 12.12.2015.
 */
public class PlayerListAdapter extends ArrayAdapter<Player> {

    private Context context;
    List<Player> players;
    SharedPreference sharedPreference;

    public PlayerListAdapter(Context context, List<Player> players) {
        super(context, R.layout.player__list_item, players);
        this.context = context;
        this.players = players;
        sharedPreference = new SharedPreference();
    }

    private class ViewHolder {
        TextView playerIdTxt;
        TextView playerNameTxt;
        TextView playerTeamTxt;

    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Player getItem(int position) {
        return players.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.player__list_item, null);
            holder = new ViewHolder();
            holder.playerIdTxt = (TextView) convertView
                    .findViewById(R.id.txt_pl_id);
            holder.playerNameTxt = (TextView) convertView
                    .findViewById(R.id.txt_pl_name);
            holder.playerTeamTxt = (TextView) convertView
                    .findViewById(R.id.txt_pl_team);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Player player = (Player) getItem(position);
        holder.playerIdTxt.setText("Player Id: " + player.getPlayerId());
        holder.playerNameTxt.setText("Name: " + player.getPlayerName());
        holder.playerTeamTxt.setText("Team: " + player.getPlayerTeam());


        return convertView;
    }


    @Override
    public void add(Player player) {
        super.add(player);
        players.add(player);
        notifyDataSetChanged();
    }

    @Override
    public void remove(Player player) {
        super.remove(player);
        players.remove(player);
        notifyDataSetChanged();
    }
}
