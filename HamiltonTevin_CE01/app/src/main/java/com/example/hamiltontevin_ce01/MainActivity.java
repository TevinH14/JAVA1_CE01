//Name - Tevin Hamilton
//class - JAVA 1
//Term - 2002
//assignment - CE01

package com.example.hamiltontevin_ce01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.*;


public class MainActivity extends AppCompatActivity{

    private static final String TAG = "funday";

    private String selectionStringOne = null;
    private String selectionStringTwo = null;

    private Button buttonOne = null;
    private Button buttonTwo = null;
    private Button restartBtn = null;

    private ArrayList<String> alphabetArray = new ArrayList<>();
    private ArrayList<Button> buttonsList = new ArrayList<>();

    private int taps = 0;
    private int numAttempts = 0;
    private int matches = 0;

    private TextView tV = null;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tV = findViewById(R.id.countTextView);
        restartBtn = findViewById(R.id.BtnRestart);
        SetUpButtonClick();
        SetUpArray();
        ShuffleLetters();
    }

    private View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() ==  R.id.BtnRestart){
                for(Button currentBtn : buttonsList){
                    currentBtn.setVisibility(View.VISIBLE);
                    tV.setText(R.string.guess_count);
                }
                tV.setText(R.string.guess_count);
            }
            else{
                taps += 1;
                Button btnTap = findViewById(view.getId());
                String num = String.valueOf(view.getTag());

                int btnTag = Integer.parseInt(num);
                btnTap.setText(alphabetArray.get(btnTag));

                switch(taps){
                    case 1:
                        selectionStringOne = alphabetArray.get(btnTag);
                        buttonOne = btnTap;
                        break;
                    case 2:
                        selectionStringTwo = alphabetArray.get(btnTag);
                        buttonTwo = btnTap;
                        numAttempts += 1;
                        tV.setText(String.valueOf(numAttempts));
                        break;
                    case 3:
                        ClearInputs();
                        buttonOne = btnTap;
                        selectionStringOne =  alphabetArray.get(btnTag);
                        taps += 1;
                        break;

                }
                if(selectionStringOne.equals(selectionStringTwo)) {
                    buttonOne.setVisibility(View.INVISIBLE);
                    buttonTwo.setVisibility(View.INVISIBLE);
                    matches += 1;
                    ClearInputs();
                    if(matches == 8){
                        restartBtn.setVisibility(View.VISIBLE);
                        numAttempts = 0;
                    }
                }
//
            }
        }
    };

    private void ShuffleLetters() {
        Collections.shuffle(alphabetArray);
        Log.i(TAG,"shuffled array " + alphabetArray);
    }

    private void SetUpButtonClick(){

        //Assign on click listener
        restartBtn.setOnClickListener(buttonClick);

        // add buttons to button list
        buttonsList.add((Button)findViewById(R.id.BtnR1C1));
        buttonsList.add((Button)findViewById(R.id.BtnR1C2));
        buttonsList.add((Button)findViewById(R.id.BtnR1C3));
        buttonsList.add((Button)findViewById(R.id.BtnR1C4));
        buttonsList.add((Button)findViewById(R.id.BtnR2C1));
        buttonsList.add((Button)findViewById(R.id.BtnR2C2));
        buttonsList.add((Button)findViewById(R.id.BtnR2C3));
        buttonsList.add((Button)findViewById(R.id.BtnR2C4));
        buttonsList.add((Button)findViewById(R.id.BtnR3C1));
        buttonsList.add((Button)findViewById(R.id.BtnR3C2));
        buttonsList.add((Button)findViewById(R.id.BtnR3C3));
        buttonsList.add((Button)findViewById(R.id.BtnR3C4));
        buttonsList.add((Button)findViewById(R.id.BtnR4C1));
        buttonsList.add((Button)findViewById(R.id.BtnR4C2));
        buttonsList.add((Button)findViewById(R.id.BtnR4C3));
        buttonsList.add((Button)findViewById(R.id.BtnR4C4));

        //loop through the list and assign the onClick listener
        for (int i = 0; i < buttonsList.size(); i++) {
            buttonsList.get(i).setOnClickListener(buttonClick);
        }

    }

    private void SetUpArray(){
        String[] newStringArray = getResources().getStringArray(R.array.alp_String_Array);
        for (int a = 0; a <8 ; a++) {
            for (int i = 0; i < 2; i++) {
                alphabetArray.add(newStringArray[a]);
            }
        }
    }

    private void ClearInputs(){
        buttonOne.setText("");
        buttonTwo.setText("");
        buttonOne = null;
        buttonTwo = null;
        selectionStringOne = null;
        selectionStringTwo = null;
        taps = 0;
    }
}
