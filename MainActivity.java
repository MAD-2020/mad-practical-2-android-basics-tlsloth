package sg.edu.np.WhackAMole;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Integer Score = 0;
    private Button ButtonLeft;
    private Button ButtonMiddle;
    private Button ButtonRight;
    private TextView tv;
    private static final String TAG = "ButtonActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setting up buttons
        ButtonLeft = findViewById(R.id.ButtonLeft);
        ButtonMiddle = findViewById(R.id.ButtonMiddle);
        ButtonRight = findViewById(R.id.ButtonRight);
        tv  = findViewById(R.id.tv);
        Log.v(TAG, "Finished Pre-Initialisation!");
    }
    //Examples for onstart,stop,etc.
    @Override
    protected void onStart(){
        super.onStart();
        setNewMole();
        Log.v(TAG,"Application launched!");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TAG,"Application paused.");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.v(TAG,"Application resumed!");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TAG,"Application stopped!");
    }
    //Setting a random mole after each round
    public void setNewMole()
    {
        //Get random integer between 0 and 2
        Random ran = new Random();
        int randomLocation = ran.nextInt(3);
        //Get an array of buttons and randomly pick a button from random integer
        Button[] bArray = {ButtonLeft,ButtonMiddle,ButtonRight};
        Button mole = bArray[randomLocation];
        //Set text for each button depending on whether its a mole or not
        for (Button b : bArray){
            if (b == mole){
                b.setText("*");
            }
            else{
                b.setText("O");
            }
        }
    }
    //Function to check if the button pressed is a mole or not
    public boolean checkMole(String s){
        if(s == "*"){
            return true;
        }
        return false;
    }
    //Update score function to be run after each round
    public void UpdateScore(){
        String score = String.valueOf(Score);
        tv.setText(score);
    }
    //Onclick button function, to handle the click event handler defined in the xml file
    public void onClickButton(View v){
        //Practical 2.1
        switch(v.getId()){
            case R.id.ButtonLeft:
            Log.v(TAG,"Left Button Clicked!");
            break;

            case R.id.ButtonMiddle:
            Log.v(TAG,"Middle Button Clicked");
            break;

            default:
            Log.v(TAG,"Right Button Clicked");
        }
        //downcast v to button
        Button button = (Button) v;
        //Checks if the button pressed is a mole or not
        if(checkMole((String) button.getText()) == true){
            //increase score
            Score++;
            Log.v(TAG,"You hit the mole!");
        }
        //Only deduct score if score is above 0
        else if(Score != 0){
            Score--;
            Log.v(TAG,"You missed the mole!");
        }
        else{
            Log.v(TAG,"You missed the mole!");
        }
        //Functions to run after each round
        setNewMole();
        UpdateScore();



    }

}
