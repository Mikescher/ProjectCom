package samdev.de.projectcom.view.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.List;

import samdev.de.projectcom.model.Player;
import samdev.de.projectcom.R;
import samdev.de.projectcom.model.SharedPreference;
import samdev.de.projectcom.view.adapters.PlayerListAdapter;

public class PlayerActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RelativeLayout mRoot;
    public FloatingActionButton mFAB;
    SharedPreference sharedPreference;
    ListView playerList;
    PlayerListAdapter playerListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        sharedPreference = new SharedPreference();
        playerList = (ListView) findViewById(R.id.list_player);

        playerList
                .setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view,final int position, long id) {

                        android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(PlayerActivity.this).create();
                        alertDialog.setTitle("Löschen");
                        alertDialog.setMessage("Soll diese Player Id gelöscht werden?");
                        alertDialog.setIcon(R.drawable.delete);
                        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_POSITIVE, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        removePlayer(position);
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEGATIVE, "Abbrechen", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();


                        return true;
                    }
                });


        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        ActionBar supportActionbar = getSupportActionBar();

        if (supportActionbar != null) {
            supportActionbar.setHomeButtonEnabled(true);
            supportActionbar.setDisplayHomeAsUpEnabled(true);
        }

        mRoot = (RelativeLayout) findViewById(R.id.root_activity_player);

        mFAB = (FloatingActionButton) findViewById(R.id.fabplayer);
        mFAB.setOnClickListener(mFabClickListener);

        setList();
    }

    private View.OnClickListener mFabClickListener = new View.OnClickListener(){
        public void onClick(View v){
            View view = (LayoutInflater.from(PlayerActivity.this)).inflate(R.layout.player_input, null);

            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(PlayerActivity.this);
            alertBuilder.setView(view);
            final EditText userInput = (EditText) view.findViewById(R.id.input_playerid);

            alertBuilder.setTitle(R.string.alertplayertitle);
            alertBuilder.setCancelable(true)
                    .setPositiveButton(R.string.alertbuttonok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String mUserInput =userInput.getText().toString();

                            addPlayer(mUserInput);
                        }

                    })
                    .setNegativeButton(R.string.alertbuttoncancel, null);

            Dialog dialog = alertBuilder.create();
            dialog.show();
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_clearlist) {
            clearList();
        }
        if(id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void clearList(){

        android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(PlayerActivity.this).create();
        alertDialog.setTitle("Alle löschen");
        alertDialog.setMessage("Sollen alle Player ID`s gelöscht werden?");
        alertDialog.setIcon(R.drawable.delete);
        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        List<Player> players;
                        players = sharedPreference.getPlayers(PlayerActivity.this);
                        int size = players.size();
                        for (int i = 0; i < size; i++) {
                            sharedPreference.removePlayer(PlayerActivity.this, players.get(i));
                        }
                        setList();
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEGATIVE, "Abbrechen", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();


    }
    private void addPlayer(String playerId){

        if(playerId == "") {
            Snackbar.make(mRoot,"Ungültige Eingabe", Snackbar.LENGTH_LONG)
                    .show();
        }
        else {
            // Find free Id to add to the sharedPreference
            List<Player> players;
            players = sharedPreference.getPlayers(PlayerActivity.this);
            Player lastPlayer;
            int size = players.size();
            int newId;
            if(size == 0){
                newId = 1;
            }
            else{
                lastPlayer = (Player) players.get(size - 1);
                newId = lastPlayer.getId() + 1;
            }

            Player player = new Player(newId,playerId, "Benzin", "BlackForestBystes");

            sharedPreference.addPlayer(PlayerActivity.this, player);

            setList();
            Snackbar.make(mRoot,playerId + " hinzugefügt", Snackbar.LENGTH_LONG)
                    .show();
        }

    }

    private void setList() {
        List<Player> players;
        players = sharedPreference.getPlayers(PlayerActivity.this);
        if(players != null)
        {
            playerListAdapter = new PlayerListAdapter(PlayerActivity.this, players);
            playerList.setAdapter(playerListAdapter);
        }
    }

    private void removePlayer(int position){


        List<Player> players;
        players = sharedPreference.getPlayers(PlayerActivity.this);
        sharedPreference.removePlayer(PlayerActivity.this, players.get(position));

        setList();
    }
}
