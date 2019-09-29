package com.lavertis.mathfactorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private static String calculateFactorial(int n) {
        BigInteger fact = new BigInteger("1");
        for (int i = 1; i <= n; i++)
            fact = fact.multiply(new BigInteger(i + ""));
        return fact.toString();
    }

    private static String calculateSum(String result) {
        int sum = 0;
        for (int i = 0; i < result.length(); i++)
            sum += Integer.valueOf(result.charAt(i) + "");
        return String.valueOf(sum);
    }

    private String lastNumber;

    public void calculateButtonOnClick(View v) {
        EditText userInput = findViewById(R.id.userInput);
        if (!(userInput.getText().toString().equals(""))) {
            if (userInput.getText().toString().equals(lastNumber)) {
                Toast.makeText(this, "Already calculated", Toast.LENGTH_SHORT).show();
                return;
            }
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            userInput.clearFocus();
            TextView digitsNumber = findViewById(R.id.digitsNumber);
            TextView digitsSum = findViewById(R.id.digitsSum);
            TextView resultBox = findViewById(R.id.resultBox);
            String result = calculateFactorial(Integer.valueOf(userInput.getText().toString()));
            String numberOfDigits = "Number of digits: ";
            digitsNumber.setText(numberOfDigits.concat(String.valueOf(result.length())));
            String sumOfDigits = "Sum of digits: ";
            digitsSum.setText((sumOfDigits.concat(calculateSum(result))));
            resultBox.setText(result);
            lastNumber = userInput.getText().toString();
        }
    }

    private boolean doubleBackToExitPressedOnce = false;

    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finish();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
