package com.actsat.alpal.actsatprep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class did_choose_test extends AppCompatActivity {

    private Button button1, button2, button3, button4;
    private String act = "ACT", sat = "SAT";
    private String getHelp1         = "getHelp";
    private String improveScore2    = "improveScore";
    private String aboutTheTest3    = "aboutTheTest";
    public String testChosen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_did_choose_test);
        Intent intent = getIntent();
        testChosen = intent.getStringExtra("testChosen");
        setTitle(testChosen);
        //where to go for help
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToSwipeable(getHelp1);
            }
        });
        //tips to improve score
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToSwipeable(improveScore2);
            }
        });
        //about the test
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToSwipeable(aboutTheTest3);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_did_choose_test, menu);
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

    private void goToSwipeable(String whatWasClicked){
        Intent myIntent = new Intent(did_choose_test.this, swipeable.class);
        myIntent.putExtra("infoChosen", whatWasClicked);
        myIntent.putExtra("testChosen", testChosen);
        did_choose_test.this.startActivity(myIntent);
    }
}
