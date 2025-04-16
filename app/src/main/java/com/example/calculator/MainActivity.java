package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declare UI components
    private TextView solutionTv, resultTv;
    private MaterialButton buttonC, buttonOpenBracket, buttonCloseBracket, buttonDivide;
    private MaterialButton button7, button8, button9, buttonMultiply;
    private MaterialButton button4, button5, button6, buttonSum;
    private MaterialButton button1, button2, button3, buttonSub;
    private MaterialButton buttonAc, button0, buttonDot, buttonEquals;

    private String currentInput = "";  // Current input string
    private String lastOperator = "";  // Store the last operator
    private double operand1 = 0.0;  // Store the first operand for calculation
    private boolean isNewInput = true; // Track if a new number is being entered

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the UI components using findViewById
        solutionTv = findViewById(R.id.solution_tv);
        resultTv = findViewById(R.id.result_tv);

        // Initialize buttons
        buttonC = findViewById(R.id.button_c);
        buttonOpenBracket = findViewById(R.id.button_open_bracket);
        buttonCloseBracket = findViewById(R.id.button_close_bracket);
        buttonDivide = findViewById(R.id.button_divide);

        button7 = findViewById(R.id.button_7);
        button8 = findViewById(R.id.button_8);
        button9 = findViewById(R.id.button_9);
        buttonMultiply = findViewById(R.id.button_multiply);

        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);
        buttonSum = findViewById(R.id.button_sum);

        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        buttonSub = findViewById(R.id.button_sub);

        buttonAc = findViewById(R.id.button_ac);
        button0 = findViewById(R.id.button_0);
        buttonDot = findViewById(R.id.button_dot);
        buttonEquals = findViewById(R.id.button_equals);

        // Handle button C (clear)
        buttonC.setOnClickListener(v -> {
            solutionTv.setText(""); // Clear the solution TextView
            resultTv.setText("0");  // Reset result TextView
            currentInput = "";  // Reset the current input string
            operand1 = 0.0;  // Reset operands
            lastOperator = "";  // Reset operator
        });

        // Handle number buttons (1-9 and 0)
        button1.setOnClickListener(v -> appendNumber("1"));
        button2.setOnClickListener(v -> appendNumber("2"));
        button3.setOnClickListener(v -> appendNumber("3"));
        button4.setOnClickListener(v -> appendNumber("4"));
        button5.setOnClickListener(v -> appendNumber("5"));
        button6.setOnClickListener(v -> appendNumber("6"));
        button7.setOnClickListener(v -> appendNumber("7"));
        button8.setOnClickListener(v -> appendNumber("8"));
        button9.setOnClickListener(v -> appendNumber("9"));
        button0.setOnClickListener(v -> appendNumber("0"));

        // Handle decimal button (.)
        buttonDot.setOnClickListener(v -> {
            if (!currentInput.contains(".")) {
                appendNumber(".");
            }
        });

        // Handle operators (+, -, *, /)
        buttonSum.setOnClickListener(v -> setOperator("+"));
        buttonSub.setOnClickListener(v -> setOperator("-"));
        buttonMultiply.setOnClickListener(v -> setOperator("*"));
        buttonDivide.setOnClickListener(v -> setOperator("/"));

        // Handle equals button
        buttonEquals.setOnClickListener(v -> calculateResult());
    }

    private void appendNumber(String number) {
        if (isNewInput) {
            currentInput = number;  // Replace the current input if it's new
            isNewInput = false;
        } else {
            currentInput += number;  // Append the number to the input string
        }
        solutionTv.setText(currentInput);  // Display input in solution TextView
    }

    private void setOperator(String operator) {
        if (!currentInput.isEmpty()) {
            operand1 = Double.parseDouble(currentInput);  // Store the first operand
            lastOperator = operator;  // Set the operator
            currentInput = "";  // Clear the current input for the next number
            solutionTv.append(" " + operator);  // Display operator in solution TextView
        }
    }

    private void calculateResult() {
        if (!currentInput.isEmpty() && !lastOperator.isEmpty()) {
            double operand2 = Double.parseDouble(currentInput);  // Get the second operand
            double result = 0.0;

            // Perform the calculation based on the operator
            switch (lastOperator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                    } else {
                        resultTv.setText("Error");  // Handle divide by zero error
                        return;
                    }
                    break;
            }

            resultTv.setText(String.valueOf(result));  // Display the result
            solutionTv.setText("");  // Clear the solution TextView
            currentInput = String.valueOf(result);  // Use result for further operations
            operand1 = result;  // Store the result as operand1 for further operations
            lastOperator = "";  // Reset the operator
            isNewInput = true;  // Mark as new input
        }
    }
}

