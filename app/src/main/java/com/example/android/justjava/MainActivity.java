package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 2;
    private boolean addWhippedCream;
    private boolean addChocolate;
    private final int MAX_COUNT = 100;
    private final int MIN_COUNT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String priceMessage = createOrderSummary();

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SENDTO);
        // only email apps should handle this
        sendIntent.setData(Uri.parse("mailto:"));
        // добавляем текст для передачи
        sendIntent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"kazakova.net@yandex.ru"});
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_email_subject, getName()));
        // запускаем активити
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }

    /**
     * Сохраняет выбранные пользователем топпинги
     */
    private void getToppings() {
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkBox);
        addWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        addChocolate = chocolateCheckBox.isChecked();
    }

    /**
     * Формирует итоговое сообщение о заказе
     *
     * @return сообщение о заказе
     */
    private String createOrderSummary() {
        getToppings();

        String priceMessage = getString(R.string.order_summary_name, getName()) + "\n";

        if (addWhippedCream) {
            priceMessage += getString(R.string.order_summary_whipped_cream) + "\n";
        }

        if (addChocolate) {
            priceMessage += getString(R.string.order_summary_add_chocolate) + "\n";
        }

        priceMessage += getString(R.string.order_summary_quantity, quantity) + "\n";
        priceMessage += getString(R.string.order_summary_price,
                NumberFormat.getCurrencyInstance().format(calculatePrice())) + "\n\n";
        priceMessage += getString(R.string.thank_you);

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
            return "Bayer";
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
        int pricePerCup = 5;
        int priceWhippedCream = 1;
        int priceChocolate = 2;

        if (addWhippedCream) {
            pricePerCup += priceWhippedCream;
        }

        if (addChocolate) {
            pricePerCup += priceChocolate;
        }

        return quantity * pricePerCup;
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int quantity) {
        TextView quantityTextView = findViewById(R.id.quantity_textView);
        quantityTextView.setText(String.valueOf(quantity));
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == MAX_COUNT) {
            Toast.makeText(this, "Вы не можете заказать больше 100 чашек!", Toast.LENGTH_SHORT).show();

            return;
        }

        displayQuantity(++quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == MIN_COUNT) {
            Toast.makeText(this, "Вы не можете заказать меньше 1 чашки!", Toast.LENGTH_SHORT).show();

            return;
        }

        displayQuantity(--quantity);
    }
}
