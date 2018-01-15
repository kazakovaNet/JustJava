package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int pricePerCup = 5;
    boolean hasWhippedCream;
    boolean hasChocolate;
    String name = "Bayer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        displayMessage(createOrderSummary());
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayMessage(createOrderSummary());
    }

    /**
     * Сохраняет выбранные пользователем топпинги
     */
    private void getToppings() {
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkBox);
        hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        hasChocolate = chocolateCheckBox.isChecked();
    }

    /**
     * Формирует итоговое сообщение о заказе
     *
     * @return сообщение о заказе
     */
    private String createOrderSummary() {
        getToppings();

        String priceMessage = "Name: " + getName() + "\n";
        priceMessage += "Add whipped cream? " + String.valueOf(hasWhippedCream) + "\n";
        priceMessage += "Add chocolate? " + String.valueOf(hasChocolate) + "\n";
        priceMessage += "Quantity: " + quantity + "\n";
        priceMessage += "Total: " + calculatePrice() + " руб." + "\n\n";
        priceMessage += "Thank you!";

        return priceMessage;
    }

    /**
     * Получает имя, введенное пользователем
     *
     * @return имя
     */
    private String getName() {
        EditText nameEditText = findViewById(R.id.name_editText);
        String enteredName = String.valueOf(nameEditText.getText());

        if (enteredName.equals("")) {
            return name;
        } else {
            return enteredName;
        }
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
