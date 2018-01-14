package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int pricePerCup = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkBox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        Log.d("MainActivity", "Add whipped cream? " + String.valueOf(hasWhippedCream));

        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        Log.d("MainActivity", "Add chocolate? " + String.valueOf(hasChocolate));

        displayMessage(createOrderSummary(hasWhippedCream, hasChocolate));
    }

    /**
     * Формирует итоговое сообщение о заказе
     *
     * @param addWhippedCream выбран ли топпинг
     * @param addChocolate
     * @return сообщение о заказе
     */
    private String createOrderSummary(boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: Natalia Kazakova\n";
        priceMessage += "Add whipped cream? " + String.valueOf(addWhippedCream) + "\n";
        priceMessage += "Add chocolate? " + String.valueOf(addChocolate) + "\n";
        priceMessage += "Quantity: " + quantity + "\n";
        priceMessage += "Total: " + calculatePrice() + " руб." + "\n\n";
        priceMessage += "Thank you!";
        return priceMessage;
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice() {
        return quantity * pricePerCup;
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int quantity) {
        TextView quantityTextView = findViewById(R.id.quantity_textView);
        quantityTextView.setText("" + quantity);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;

        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;

        displayQuantity(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_textView);
        orderSummaryTextView.setText(message);
    }
}
