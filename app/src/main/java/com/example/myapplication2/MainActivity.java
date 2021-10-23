package com.example.myapplication2;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tv_level, tv_number;

    EditText et_number;

    Button b_confirm;

    Random r;
    int currentLevel = 1;

    String generatedNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_level = findViewById(R.id.tv_level);
        tv_number = findViewById(R.id.tv_number);

        et_number = findViewById(R.id.et_number);
        b_confirm = findViewById(R.id.b_confirm);

        tv_level.setText("Level: " + currentLevel);
        r = new Random();

        //hide the input and the button and show the number
        et_number.setVisibility(View.GONE);
        b_confirm.setVisibility(View.GONE);
        tv_number.setVisibility(View.VISIBLE);



        //display random number
        generatedNumber = generateNumber(currentLevel);
        tv_number.setText(generateNumber(currentLevel));

        //display the elements after a second and hide the number
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                et_number.setVisibility(View.VISIBLE);
                b_confirm.setVisibility(View.VISIBLE);

                tv_number.setVisibility(View.GONE);

                et_number.requestFocus();
            }
        }, 1000);
        b_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                //check if the numbers are same or not
                System.out.println ("Generated Number" + generatedNumber);
                System.out.println ("Typed Number " + et_number.getText().toString());

                if (generatedNumber.equals(et_number.getText().toString()) || currentLevel == 1) {

                    //hide the input and the button and show the number
                    et_number.setVisibility(View.GONE);
                    b_confirm.setVisibility(View.GONE);
                    tv_number.setVisibility(View.VISIBLE);

                    //remove from input
                    et_number.setText("");

                    //increase the level
                    currentLevel++;

                    tv_level.setText("Level: " + currentLevel);

                    //display the random number according to the level
                    generatedNumber = generateNumber(currentLevel);
                    tv_number.setText(generatedNumber);

                    //display the elements after a second and hide the number
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            et_number.setVisibility(View.VISIBLE);
                            b_confirm.setVisibility(View.VISIBLE);

                            tv_number.setVisibility(View.GONE);

                            et_number.requestFocus();
                        }
                    }, 1000);
                }
                else {
                    tv_level.setText("Game Over !! The Number was : " + generatedNumber);
                    b_confirm.setEnabled(false);
                }
            }
        });
    }

    private String generateNumber (int digits) {
        String output = "";
        for (int i = 0; i < digits; i++) {
            int randomDigit = r.nextInt(10);
            output = output + "" + randomDigit;
        }
        return output;
    }
}