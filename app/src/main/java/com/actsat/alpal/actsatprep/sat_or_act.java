package com.actsat.alpal.actsatprep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class sat_or_act extends AppCompatActivity {

    private Button chooseACT, chooseSAT;
    private String act = "ACT", sat = "SAT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sat_or_act);
        setTitle("Choose a Test");
        chooseACT = (Button)findViewById(R.id.chooseACT);
        chooseACT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToChoseTest(act);
            }
        });
        chooseSAT = (Button)findViewById(R.id.chooseSAT);
        chooseSAT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToChoseTest(sat);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sat_or_act, menu);
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

    private void goToChoseTest(String test){

        Intent myIntent = new Intent(sat_or_act.this, did_choose_test.class);
        myIntent.putExtra("testChosen", test);
        sat_or_act.this.startActivity(myIntent);

    }
}
