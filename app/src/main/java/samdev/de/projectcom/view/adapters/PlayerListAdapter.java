package samdev.de.projectcom.view.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import samdev.de.projectcom.model.Player;
import samdev.de.projectcom.R;
import samdev.de.projectcom.view.activities.PlayerActivity;

public class PlayerListAdapter extends ArrayAdapter<Player> {

    private final Context context;
    private final List<Player> players;

    public PlayerListAdapter(Context context, List<Player> players) {
        super(context, R.layout.player_list_item, players);
        this.context = context;
        this.players = players;
    }

    private class ViewHolder {
        TextView playerIdTxt;
        TextView playerNameTxt;
        TextView playerTeamTxt;
        ImageView imageView;

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

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.player_list_item, null);
            holder = new ViewHolder();
            holder.playerIdTxt = (TextView) convertView
                    .findViewById(R.id.txt_pl_id);
            holder.playerNameTxt = (TextView) convertView
                    .findViewById(R.id.txt_pl_name);
            holder.playerTeamTxt = (TextView) convertView
                    .findViewById(R.id.txt_pl_team);
            holder.imageView = (ImageView) convertView
                    .findViewById(R.id.playerset);


            SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(context);
            int setPlayerPosition= SP.getInt("setplayer",0);

            if(position == setPlayerPosition) {
                holder.imageView.setImageResource(R.drawable.ic_check_circle_black_36dp);
            }
            else {
                holder.imageView.setImageResource(R.drawable.ic_highlight_off_black_36dp);
            }

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Player player = getItem(position);
        holder.playerIdTxt.setText(String.format("Player Id: %s", player.getPlayerId()));
        holder.playerNameTxt.setText(String.format("Name: %s", player.getPlayerName()));
        holder.playerTeamTxt.setText(String.format("Team: %s", player.getPlayerTeam()));

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
