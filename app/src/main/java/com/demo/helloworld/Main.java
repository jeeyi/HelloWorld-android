package com.demo.helloworld;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class Main extends AppCompatActivity {

    private static final String OUTPUT_MESSAGE = "output_message";
    private String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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

    public void enter(View view) {
        TextView outputMessage = (TextView) findViewById(R.id.output_message);
        EditText editText = (EditText) findViewById(R.id.enter_your_name);
        String yourName = editText.getText().toString();
        String myCompany =  getString(R.string.box);
        Random random = new Random();
        int count;

        Resources res = getResources();
        String line1;
        String line2;

        if (yourName == null || yourName.length() == 0) {
            yourName = getString(R.string.default_name);
        }

        if (myCompany.contentEquals(yourName)) {
            message = getString(R.string.go_name, yourName);
        } else {
            count =  random.nextInt(3);
            line1 = String.format(getString(R.string.hello_name), yourName);
            line2 = res.getQuantityString(R.plurals.number_of_songs, count, count);
            message = String.format("%s\n%s", line1, line2);
        }
        recreate();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putString(OUTPUT_MESSAGE, message);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        message = savedInstanceState.getString(OUTPUT_MESSAGE);
        TextView outputMessage = (TextView) findViewById(R.id.output_message);
        outputMessage.setText(message);
    }
}
