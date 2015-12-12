package samdev.de.projectcom.activitys;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import samdev.de.projectcom.R;

public class PlayerActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RelativeLayout mRoot;
    public FloatingActionButton mFAB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRoot = (RelativeLayout) findViewById(R.id.root_activity_player);

        mFAB = (FloatingActionButton) findViewById(R.id.fabplayer);
        mFAB.setOnClickListener(mFabClickListener);


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

                            Snackbar.make(mRoot,mUserInput + " hinzugefügt", Snackbar.LENGTH_LONG)
                                    .show();

                        }

                    })
                    .setNegativeButton(R.string.alertbuttoncancel, null);

            Dialog dialog = alertBuilder.create();
            dialog.show();
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player, menu);
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
        if(id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
