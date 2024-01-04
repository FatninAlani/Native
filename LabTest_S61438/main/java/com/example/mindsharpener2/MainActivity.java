package com.example.mindsharpener2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textView4, textView5, textView6, textView7;
    private RadioGroup radioGroup;
    private RadioButton radioBtn1, radioBtn2, radioBtn3;
    private EditText editTextAnswer;
    private Button buttonCheck;

    private int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        radioGroup = findViewById(R.id.radioGroup);
        radioBtn1 = findViewById(R.id.radioBtn1);
        radioBtn2 = findViewById(R.id.radioBtn2);
        radioBtn3 = findViewById(R.id.radioBtn3);
        editTextAnswer = findViewById(R.id.editTextAnswer);
        buttonCheck = findViewById(R.id.buttonCheck);

        setMaxDigitsForLevel("i3");

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selectedRadioButton = findViewById(checkedId);
            String level = selectedRadioButton.getText().toString();
            setMaxDigitsForLevel(level);
        });

        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void setMaxDigitsForLevel(String level) {
        int maxDigits;
        switch (level) {
            case "i3":
                maxDigits = 1;
                break;
            case "i5":
                maxDigits = 2;
                break;
            case "i7":
                maxDigits = 3;
                break;
            default:
                maxDigits = 1;
        }
        displayRandomQuestion(maxDigits);
    }

    private void displayRandomQuestion(int maxDigits) {
        Random random = new Random();

        int number1 = random.nextInt((int) Math.pow(10, maxDigits));
        int number2 = random.nextInt((int) Math.pow(10, maxDigits));

        int operatorCode = random.nextInt(4);
        String operator;
        switch (operatorCode) {
            case 0:
                operator = "*";
                break;
            case 1:
                operator = "-";
                break;
            case 2:
                operator = "+";
                break;
            case 3:
                operator = "/";
                break;
            default:
                operator = "+";
        }


        textView4.setText(String.valueOf(number1));
        textView5.setText(operator);
        textView6.setText(String.valueOf(number2));
    }

    private void checkAnswer() {
        int userAnswer = Integer.parseInt(editTextAnswer.getText().toString());
        int firstNumber = Integer.parseInt(textView4.getText().toString());
        int secondNumber = Integer.parseInt(textView6.getText().toString());
        String operator = textView5.getText().toString();

        int correctAnswer = calculateAnswer(firstNumber, secondNumber, operator);

        if (userAnswer == correctAnswer) {
            points++;
        } else {
            points--;
        }


        textView7.setText("POINT: " + points);


        setMaxDigitsForLevel(getSelectedLevel());
    }

    private int calculateAnswer(int firstNumber, int secondNumber, String operator) {
        switch (operator) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                return firstNumber / secondNumber;
            default:
                return 0;
        }
    }


    private String getSelectedLevel() {
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
        return selectedRadioButton.getText().toString();
    }
}
