package com.android.speakthewords.magicgatherer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.speakthewords.magicgatherer.API.MTGDB;

import java.util.ArrayList;

import info.mtgdb.api.Card;
import info.mtgdb.api.Db;


public class HomeActivity extends Activity {
    MTGDB mtgdb = new MTGDB();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onSearchClick(View v) {
        String searchTerms = ((EditText)findViewById(R.id.home_search)).getText().toString();
        TextView searchResultsField = (TextView)findViewById(R.id.home_card_data);

        mtgdb.search(searchTerms, searchResultsField);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
