package com.introtoandroid.tipcalculator_counter_durham;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button clear;
    Button calculate;

    TextView tip;
    TextView total;
    TextView perPerson;

    EditText billAmount;
    EditText people;
    EditText tipOther;

    RadioGroup tips;
    RadioButton tipButton;
    RadioButton otherTip;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize buttons and text views
        clear = (Button) findViewById(R.id.clear);
        calculate = (Button) findViewById(R.id.calculate);

        tip = (TextView) findViewById(R.id.tipTotalAmount);
        total = (TextView) findViewById(R.id.totalPlusTip);
        perPerson = (TextView) findViewById(R.id.totalPerPerson);

        //input fields for user
        billAmount = (EditText) findViewById(R.id.billAmount);
        people = (EditText) findViewById(R.id.people);
        tipOther = (EditText) findViewById(R.id.tipOther);

        //tip radio group
        tips = (RadioGroup) findViewById(R.id.tips);
        otherTip = (RadioButton) findViewById(R.id.tipOtherText);


        //button click listener on clear
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clear all values put in by user
                billAmount.getText().clear();
                people.getText().clear();
                tipOther.getText().clear();
                tips.clearCheck();
                tipOther.getText().clear();

            }
        });

        //button click listener on calculate
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //grab tip radio button
                int selectedID = tips.getCheckedRadioButtonId();
                tipButton = findViewById(selectedID);
                //Toast.makeText(MainActivity.this, tipButton.getText(), Toast.LENGTH_SHORT).show();

                //bill amount in double
                String billAmountString = billAmount.getText().toString();
                Double billAmountNum = Double.parseDouble(billAmountString);
                Double value; //used for total bill calculations
                //calc for num of people
                String numOfPeopleText = ((EditText) findViewById(R.id.people)).getText().toString();
                Double numOfPeople = Double.parseDouble(numOfPeopleText);

                Double split;

                //need to determine the tip amount
                if(tipButton.getText().equals("Other:")){
                    String tipString = ((EditText) findViewById(R.id.tipOther)).getText().toString();
                    Double tipNum = Double.parseDouble(tipString);


                    String temp = ((EditText) findViewById(R.id.tipOther)).getText().toString();
                    //Toast.makeText(MainActivity.this, temp, Toast.LENGTH_SHORT).show();
                    tip.setText(temp);
                    //total bill plus tip
                    value = billAmountNum + tipNum;
                    total.setText(Double.toString(value));

                    //get num of people and divide check by it

                    split = value/numOfPeople;
                    perPerson.setText(split.toString());
                }
                else if(tipButton.getText().equals("10%")){
                //bill amount : billAmountNum
                    // get 10% and set textview
                    Double tipValue = billAmountNum * .10;
                    tip.setText(tipValue.toString());

                    //get total bill and set text view
                    value = tipValue + billAmountNum;
                    total.setText(value.toString());

                    //split bill and set text view
                    split = value/numOfPeople;
                    perPerson.setText(split.toString());

                }
                else if(tipButton.getText().equals("15%")){
                    // get 15% and set textview
                    Double tipValue = billAmountNum * .15;
                    tip.setText(tipValue.toString());

                    //get total bill and set text view
                    value = tipValue + billAmountNum;
                    total.setText(value.toString());

                    //split bill and set text view
                    split = value/numOfPeople;
                    perPerson.setText(split.toString());

                }
                else if(tipButton.getText().equals("20%")){
                    // get 20% and set textview
                    Double tipValue = billAmountNum * .20;
                    tip.setText(tipValue.toString());

                    //get total bill and set text view
                    value = tipValue + billAmountNum;
                    total.setText(value.toString());

                    //split bill and set text view
                    split = value/numOfPeople;
                    perPerson.setText(split.toString());

                }
                else if(tipButton.getText().equals("No Tip")){
                    // get 20% and set textview
                    //Double tipValue = billAmountNum * .20;
                    tip.setText("0");

                    //get total bill and set text view
                    //value = tipValue + billAmountNum;
                    total.setText(billAmountNum.toString());

                    //split bill and set text view
                    split = billAmountNum/numOfPeople;
                    perPerson.setText(split.toString());

                }



            }
        });


        //this shows or hides the text field for other tip
        tips.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.tipOtherText){
                    tipOther.setVisibility(View.VISIBLE);
                    calculate.setEnabled(false);
                }
                else{
                    tipOther.setVisibility(View.INVISIBLE);
                    String bill = billAmount.getText().toString();
                    String ppl = people.getText().toString();
                    //Boolean tips = tips.
                    if(!bill.isEmpty() && !ppl.isEmpty() && tips.getCheckedRadioButtonId() != -1){
                        //check if bill and ppl is  not 0
                        //since I used number edittext, char cannot be inputted
                        Double billAmountNum = Double.parseDouble(bill);
                        Integer pplNum = Integer.parseInt(ppl);
                        if(billAmountNum !=0 && pplNum !=0) {
                            calculate.setEnabled(true);
                        }
                    }
                }
            }
        });

        //make sure tip entered in an int
        tipOther.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!tipOther.getText().toString().isEmpty()){
                    String bill = billAmount.getText().toString();
                    String ppl = people.getText().toString();
                    //Boolean tips = tips.

                    //I am allowing 0 to be inputted into the tips other space
                    //even though there is a no top option
                    if(!bill.isEmpty() && !ppl.isEmpty() && tips.getCheckedRadioButtonId() != -1){
                        //check if bill and ppl is  not 0
                        //since I used number edittext, char cannot be inputted
                        Double billAmountNum = Double.parseDouble(bill);
                        Integer pplNum = Integer.parseInt(ppl);
                        if(billAmountNum != 0 && pplNum != 0) {
                            calculate.setEnabled(true);
                        }
                    }
                    else{
                        calculate.setEnabled(false);
                    }
                }
                else{
                    calculate.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //make sure bill amount entered is an int
        billAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String bill = billAmount.getText().toString();
                String ppl = people.getText().toString();

                if(!bill.isEmpty() && !ppl.isEmpty() && tips.getCheckedRadioButtonId() != -1){
                    //check if bill and ppl is  not 0
                    //since I used number edittext, char cannot be inputted
                    Double billAmountNum = Double.parseDouble(bill);
                    Integer pplNum = Integer.parseInt(ppl);
                    if(billAmountNum!=0 && pplNum != 0) {
                        calculate.setEnabled(true);
                    }
                }
                else{
                    calculate.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //make sure number of people entered is an int
        people.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String bill = billAmount.getText().toString();
                String ppl = people.getText().toString();
                //Boolean tips = tips.
                if(!bill.isEmpty() && !ppl.isEmpty() && tips.getCheckedRadioButtonId() != -1) {
                    //check if bill and ppl is  not 0
                    //since I used number edittext, char cannot be inputted
                    Double billAmountNum = Double.parseDouble(bill);
                    Integer pplNum = Integer.parseInt(ppl);
                    if(billAmountNum !=0 && pplNum != 0) {
                        calculate.setEnabled(true);
                    }
                }
                else{
                    calculate.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


/*

        OnKeyListener mKeyListener = new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculate.setEnabled(true);
                switch (v.getId()) {
                    case R.id.billAmount:
                    case R.id.people:
                    //case R.id.tips:
                        calculate.setEnabled(true);
                        return true;
                }
                return false;
            }

        };
*/


    }
/*

    private OnKeyListener mKeyListener = new OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            calculate.setEnabled(true);
            switch (v.getId()) {
                case R.id.billAmount:
                case R.id.people:
                case R.id.tips:
                    calculate.setEnabled(true);
                    return true;
            }
            return false;
        }

    };
*/


        //To Do: save state methods for text views
    @Override
    protected void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        String tipValue = tip.getText().toString();
        outstate.putString("tip", tipValue);
        String totalValue = total.getText().toString();
        String perPersonValue = perPerson.getText().toString();
        outstate.putString("total", totalValue);
        outstate.putString("perPerson", perPersonValue);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstance){
        super.onRestoreInstanceState(savedInstance);
        String tipVal = savedInstance.getString("tip");
        String totalVal = savedInstance.getString("total");
        String perPersonVal = savedInstance.getString("perPerson");

        tip.setText(tipVal);
        total.setText(totalVal);
        perPerson.setText(perPersonVal);
    }

    private void showErrorAlert(String errorMessage, final int fieldId) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(errorMessage)
                .setNeutralButton("Close",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                findViewById(fieldId).requestFocus();
                            }
                        }).show();
    }


}
