package com.actsat.alpal.actsatprep;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;

public class login extends AppCompatActivity {

    private EditText nameInput, ageInput, gradeInput, schoolInput, emailOrInstagramInput, twitterInput;
    private Button submitButton;
    private RelativeLayout backgroundView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Check in");

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        Boolean didLogIn = sharedPref.getBoolean(getString(R.string.didLogIn), false);

        if (didLogIn){
            advanceToTests();
        }else {
            // Enable Local Datastore.
            Parse.enableLocalDatastore(this);

            Parse.initialize(this, "Kb3GFyrBfNLa2OEwIAmo26fSPUQw1mwHUZ5CjF8i", "Ix9HHnXltiqqiaqvtaBo6VXTkYSjA0ZMOsUiO6VF");


            backgroundView = (RelativeLayout) findViewById(R.id.backgroundView);
            backgroundView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        hideKeyboard();
                    }
                }
            });
            nameInput = (EditText) findViewById(R.id.nameInput);
            ageInput = (EditText) findViewById(R.id.ageInput);
            gradeInput = (EditText) findViewById(R.id.gradeInput);
            schoolInput = (EditText) findViewById(R.id.schoolInput);
            emailOrInstagramInput = (EditText) findViewById(R.id.emailOrInstagramInput);
            twitterInput = (EditText) findViewById(R.id.twitterInput);
            submitButton = (Button) findViewById(R.id.submitButton);

            submitButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    validateInput();
                }
            });
        }


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
            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(getString(R.string.didLogIn), true);
            editor.commit();
            advanceToTests();
        }
//        advanceToTests();
    }

    private void submitData(){
        ParseObject userObject = new ParseObject("UserObject");
        userObject.put("name",              nameInput.getText().toString());
        userObject.put("age",               ageInput.getText().toString());
        userObject.put("grade",             gradeInput.getText().toString());
        userObject.put("school",            schoolInput.getText().toString());
        userObject.put("email_or_instagram",emailOrInstagramInput.getText().toString());
        userObject.put("twitter",           twitterInput.getText().toString());

        userObject.saveInBackground();
    }

    private void advanceToTests(){
        Intent myIntent = new Intent(login.this, sat_or_act.class);
        login.this.startActivity(myIntent);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(nameInput.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(ageInput.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(schoolInput.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(gradeInput.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(emailOrInstagramInput.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(twitterInput.getWindowToken(), 0);
    }
}
