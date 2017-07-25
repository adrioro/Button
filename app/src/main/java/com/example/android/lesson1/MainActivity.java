/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

package com.example.android.lesson1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    EditText nameField;
    int price = quantity * 6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * This the input field method
         */
        nameField = (EditText) findViewById(R.id.name_input);

    }


    /**
     * Calculates the price of the order.
     *
     * @return total price
     */

    private int calculatePrice() {

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = ChocolateCheckBox.isChecked();


        if (hasWhippedCream) {
            price = price + 1;

        }
        if (hasChocolate) {
            price = price + 2;

        } else {
            price = quantity * 6;
        }
        return price;

    }


    /**
     * Order SUMMARY text.
     */

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate) {


        /**
         * gets the text that was pulled from the nameField (input field) as an String
         */
        String name = nameField.getText().toString();
        /**
         * prints the name and all the rest of the messages
         */
        String priceMessage = "\nName: " + name;
        priceMessage = priceMessage + "\nAdd whipped cream? " + addWhippedCream;
        priceMessage = priceMessage + "\nAdd chocolate? " + addChocolate;
        priceMessage = priceMessage + "\nQuantity:" + quantity;
        priceMessage = priceMessage + "\nTotal: $" + calculatePrice();
        priceMessage = priceMessage + "\nThank you!";
        return priceMessage;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        /**
         * Whipped cream check box
         */
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        /**
         * Delete the Log.v later. It's only to check the value.
         */
        Log.v("MainActivity", "Has whipped cream:" + hasWhippedCream);


        /**
         * Chocolate check box
         */
        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = ChocolateCheckBox.isChecked();
        /**
         * Delete the Log.v later. It's only to check the value.
         */
        Log.v("MainActivity", "Has chocolate:" + hasChocolate);


        int price = calculatePrice();
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);

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
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);

    }

    /**
     * This method displays the given quantity value on the screen.
     */

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}