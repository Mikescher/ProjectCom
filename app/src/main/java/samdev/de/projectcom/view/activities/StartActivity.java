package samdev.de.projectcom.view.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.List;

import samdev.de.projectcom.R;
import samdev.de.projectcom.model.Player;
import samdev.de.projectcom.model.SharedPreference;

public class StartActivity extends AppCompatActivity {

    SharedPreference sharedPreference;
    private RelativeLayout mRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);


        final Button btnStart = (Button) findViewById(R.id.buttonstart);
        final EditText userInput = (EditText) findViewById(R.id.startInputId);
        mRoot = (RelativeLayout) findViewById(R.id.root_activity_start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUserInput = userInput.getText().toString();

                addPlayer(mUserInput);


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void addPlayer(String playerId) {

        if (playerId.isEmpty()) {
            Snackbar.make(mRoot, "Ungültige Eingabe", Snackbar.LENGTH_LONG)
                    .show();
        } else {
            // Find free Id to add to the sharedPreference
            List<Player> players;
            sharedPreference = new SharedPreference();
            players = sharedPreference.getPlayers(StartActivity.this);
            Player lastPlayer;
            int size;
            if (players == null) {
                size = 0;
            } else {
                size = players.size();
            }
            int newId;

            if (size == 0) {
                newId = 1;
            } else {
                lastPlayer = players.get(size - 1);
                newId = lastPlayer.getId() + 1;
            }

            Player player = new Player(newId, playerId, "Benzin", "BlackForestBystes");

            sharedPreference.addPlayer(StartActivity.this, player);

            SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            SharedPreferences.Editor editor = SP.edit();
            editor.putBoolean("firstStart", false);
            editor.commit();

            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

}
