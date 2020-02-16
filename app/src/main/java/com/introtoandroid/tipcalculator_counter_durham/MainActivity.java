package com.introtoandroid.tipcalculator_counter_durham;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import org.w3c.dom.Text;

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
                Toast.makeText(MainActivity.this, tipButton.getText(), Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(MainActivity.this, temp, Toast.LENGTH_SHORT).show();
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

                }
                else{
                    tipOther.setVisibility(View.INVISIBLE);
                }
            }
        });



        //To Do: save state methods for text views


        //To Do: make sure you can't press calculate if edit texts are empty or set to 0, or not tip selected

       /* View.OnKeyListener mKeyListener = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String billAmountString = billAmount.getText().toString();

                switch (v.getId()) {
                    case R.id.billAmount:
                        if(billAmountString.isEmpty()){

                        }
                    case R.id.people:
                    case R.id.tips:
                        //if()
                }
                return false;
            }

        };*/

    }


}
