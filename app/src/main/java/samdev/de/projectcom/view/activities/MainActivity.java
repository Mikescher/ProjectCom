package samdev.de.projectcom.view.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import samdev.de.projectcom.R;
import samdev.de.projectcom.model.Player;
import samdev.de.projectcom.model.SharedPreference;
import samdev.de.projectcom.view.fragments.NavigationDrawerFragment;

public class MainActivity extends ActionBarActivity {

    private static final String SELECTED_ITEM_ID = "selected";
    private static final String FIRST_TIME = "first_time";
    
    private Toolbar toolbar;
    ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean firstStart = SP.getBoolean("firstStart", true);

        if(firstStart)
        {
            startActivity(new Intent(this, StartActivity.class));
        }

        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavigationDrawerFragment drawerFragment =
                (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);


        // Set Player in the Header of the Navigation Drawer

        int setPlayerPosition = SP.getInt("setplayer", 0);
        SharedPreference sharedPreference = new SharedPreference();
        List<Player> players;
        players = sharedPreference.getPlayers(MainActivity.this);
        if(players != null && players.size() > 0) {
            Player player = players.get(setPlayerPosition);
            TextView textView = (TextView) findViewById(R.id.textDrawerHeader1);
            textView.setText(player.getPlayerName() + "\n" + player.getPlayerTeam());
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

}
