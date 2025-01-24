package com.example.asciiapps;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    //This is my declare variable for String number array later we will use this one for comparing
    String [] number = {"65","66","67","68","69","70","71","72","73","74"};
    //This is my image array for random image in my application
    int[] imgCharacter = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.jn};
    int[] imgNumber = {R.drawable.num65,R.drawable.num66,R.drawable.num67,R.drawable.num68,R.drawable.num69,R.drawable.num70,R.drawable.num71,R.drawable.num72,R.drawable.num73,R.drawable.num74};

    //the below is declare variable for my button, edit text, imageview na the index position
    Button button1, button2;
    EditText editText;
    ImageView imgView1, imgView2, imgView3;
    int indexPosition1;
    int indexPosition2;
    //this is my declare variable for the remaining life
    int life = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        button1 = findViewById(R.id.bttn1);
        button2 = findViewById(R.id.bttn2);
        editText = findViewById(R.id.eText);
        imgView1 = findViewById(R.id.iView1);
        imgView2 = findViewById(R.id.iView2);
        imgView3 = findViewById(R.id.iView3);

        shuffleImage();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    //This is my alertDialog method i created this one instead of Toast.
    public void showDialogAlert(String Title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(Title);
        builder.setMessage(Message);

        builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog ad = builder.create();
        ad.show();
    }

    public void showDialog(String Title, String Message){
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle(Title);
        builder2.setMessage(Message);

        builder2.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                shuffleImage();
                dialog.dismiss();
            }
        });

        AlertDialog ad2 = builder2.create();
        ad2.show();
    }
    //I created also a method to minimize and will instantiate the method itself, so I random my image again when the player is everytime click play again.
    public void shuffleImage(){
        indexPosition1 = new Random().nextInt(10);
        imgView1.setImageResource(imgCharacter[indexPosition1]);
        imgView2.setImageResource(imgNumber[indexPosition1]);

        indexPosition2 = new Random().nextInt(10);
        imgView3.setImageResource(imgCharacter[indexPosition2]);
        button2.setVisibility(View.INVISIBLE);

    }
    //I created a boolean flag for my while loop
    boolean flag = true;

    public void submitButton(View v){
        //I implement a try catch and thread sleep so the application would not read fast. The purpose of this to have a rest while looping the comparison.
        try
        {
            while(flag)
            {
                if(editText.getText().toString().equals(number[indexPosition2])){
                    //Toast.makeText(this, "well done", Toast.LENGTH_SHORT).show();
                    showDialog("Response: ", "Well done");

                    editText.setText("");
                    break;


                }else{
                    if(life < 1){
                        //Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
                        showDialogAlert("Game Over!!", "Better luck next time");
                        button1.setVisibility(View.INVISIBLE);
                        button2.setVisibility(View.VISIBLE);
                        break;
                    }else{
                        showDialogAlert("Response:", "Try again! " + "You have only " + life + " remaining try");
                        //Toast.makeText(this, "try again "+ life, Toast.LENGTH_SHORT).show();
                        editText.setText("");
                        life--;
                        break;
                    }
                }

            }

            Thread.sleep(500);
        }catch (Exception ignored){

        }


    }
    //I make a clean method for onclick listener instead of making so many override i create a simple one to be able easy understand
    //I put in my xml inside of button the android:onClick="playAgainButton"
    public void playAgainButton(View v){
        editText.setText("");
        life = 2;

        button1.setVisibility(View.VISIBLE);
        //I instantiate my method which the shuffleImage
        shuffleImage();
    }

    //same as above android:onClick="homePage"
    public void homePage (View v){

        startActivity(new Intent(GameActivity.this, MainActivity.class));
    }
}