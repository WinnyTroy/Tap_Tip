package com.troy.tipster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class Tap_Tip extends AppCompatActivity {

    private static final String TOTAL_WIN = "TOTAL_WIN";
    private static final String CURRENT_ODD = "CURRENT_ODD";
    private static final String WIN_WITHOUT_ODD = "WIN_WITHOUT_ODD";

    private double betBeforeOdds;
    private double oddAmount;
    private double finalBet;

    EditText bill_Edit_text_viewET;
    EditText Odds_Edit_Text_ViewET;
    EditText Wins_Edit_Text_ViewET;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap__tip);

//        Check if application is starting from cold/ warm state

        if(savedInstanceState == null){

            betBeforeOdds = 0.0;
            oddAmount = 1.5;
            finalBet = 0.0;
        }else{

            betBeforeOdds = savedInstanceState.getDouble(WIN_WITHOUT_ODD);
            oddAmount = savedInstanceState.getDouble(CURRENT_ODD);
            finalBet = savedInstanceState.getDouble(TOTAL_WIN);
        }

//        Initializing the EditTEXT Boxes
        bill_Edit_text_viewET = (EditText) findViewById(R.id.bill_Edit_text_view);
        Odds_Edit_Text_ViewET = (EditText) findViewById(R.id.Odds_Edit_Text_View);
        Wins_Edit_Text_ViewET = (EditText) findViewById(R.id.Wins_Edit_Text_View);


//        Setting up a change listener(ultimately gives instructions on how the components should interact)

        bill_Edit_text_viewET.addTextChangedListener(betBeforeoddsListener);


    }

//    Private method created outside onCreate method, but inside tap_tip class
//    It is meant to give instructions to the program, that helps the components interact appropriately
    private TextWatcher betBeforeoddsListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }


//        Whenever the text is changed in the bill area, before the odds, we need to do a couple of things
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

//            first surround this with a try block, to catch the exception of a user inputting
//                    a alphanumeric instead of a numeric value

            try{

                betBeforeOdds = Double.parseDouble(charSequence.toString());
            }

            catch (NumberFormatException e){

                betBeforeOdds = 0.0;
            }

            updateOddsandFinalWin();

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

//    private method used to instruct program on how to update the odds amount and final bill
//    if anything does happen on the change listener method
    private void updateOddsandFinalWin() {

//        gets value in the Odds_Edit_Text_ViewET
        double oddAmount = Double.parseDouble(Odds_Edit_Text_ViewET.getText().toString());

//        calculate the final value in the wins_edit_text_view
        double finalWin =  betBeforeOdds + (betBeforeOdds * oddAmount);

//        updates what appears in that view
        Wins_Edit_Text_ViewET.setText(String.format("%.02f", finalWin));

    }


    protected void onSavedInstanceState(Bundle outState){

        super.onSaveInstanceState(outState);

        outState.putDouble(TOTAL_WIN, finalBet);
        outState.putDouble(CURRENT_ODD, oddAmount);
        outState.putDouble(WIN_WITHOUT_ODD, betBeforeOdds);


    }
}

