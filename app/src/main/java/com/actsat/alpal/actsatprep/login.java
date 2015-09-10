package com.actsat.alpal.actsatprep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    private EditText nameInput, ageInput, gradeInput, schoolInput, ethnicityInput, email_twitterInput;
    private Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nameInput = (EditText) findViewById(R.id.nameInput);
        ageInput = (EditText) findViewById(R.id.ageInput);
        gradeInput = (EditText) findViewById(R.id.gradeInput);
        schoolInput = (EditText) findViewById(R.id.schoolInput);
        ethnicityInput = (EditText) findViewById(R.id.ethnicityInput);
        email_twitterInput = (EditText) findViewById(R.id.emailTwitterInput);
        submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                validateInput();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    private void validateInput(){
        if (nameInput.getText().toString().equals("") ||
                ageInput.getText().toString().equals("") ||
                gradeInput.getText().toString().equals("") ||
                schoolInput.getText().toString().equals("") ){
//            fill all da stuff out
            Toast.makeText(getApplicationContext(), "Please fill out all the required fields!",
                    Toast.LENGTH_LONG).show();

        }else {
            try{
                int age = Integer.parseInt( ageInput.getText().toString() );
            }catch (NumberFormatException e){
                Toast.makeText(getApplicationContext(), "Your age must be a number!",
                        Toast.LENGTH_LONG).show();
                return;
            }
            submitData();
            advanceToTests();
        }
    }

    private void submitData(){

    }

    private void advanceToTests(){
        Intent myIntent = new Intent(login.this, sat_or_act.class);
        login.this.startActivity(myIntent);
    }
}
