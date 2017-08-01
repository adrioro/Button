/*
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

package com.example.android.lesson1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    EditText nameField;
    int price = quantity * 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * This the input field method
         */
        nameField = (EditText) findViewById(R.id.name_input);

    }


    /**
     * Calculates the price of the order.
     *
     * @param addWhippedCream is wheter or not the user wants whipped cream topping
     * @param addChocolate    is whether or not the user wants chocolate topping
     * @return total price
     */

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {

        int finalPrice = 0;
        int choco = 0;
        int cream = 0;
        int price = quantity * 5;


        /* Conditional statement to allow for the addition of cream or/and chocolate.
         *
         */


        if (addWhippedCream) {
            choco = 1 * quantity;

        }
        if (addChocolate) {
            cream = 2 * quantity;

        } else {
            price = quantity * 5;
        }
        finalPrice = price + choco + cream;
        return finalPrice;

    }


    /**
     * Order SUMMARY text.
     */

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate) {


        /*
         * gets the text that was pulled from the nameField (input field) as an String
         */
        String name = nameField.getText().toString();
        /*
         * prints the name and all the rest of the messages
         */
        String priceMessage = "\nName: " + name;
        priceMessage = priceMessage + "\nAdd whipped cream? " + addWhippedCream;
        priceMessage = priceMessage + "\nAdd chocolate? " + addChocolate;
        priceMessage = priceMessage + "\nQuantity:" + quantity;
        priceMessage = priceMessage + "\nTotal: $" + calculatePrice(addWhippedCream, addChocolate);
        priceMessage = priceMessage + "\nThank you!";
        return priceMessage;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        /*
         * Whipped cream check box
         */
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        /*
         * Delete the Log.v later. It's only to check the value.
         */
        Log.v("MainActivity", "Has whipped cream:" + hasWhippedCream);
        /*
         * Chocolate check box
         */
        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = ChocolateCheckBox.isChecked();
        /*
         * Delete the Log.v later. It's only to check the value.
         */
        Log.v("MainActivity", "Has chocolate:" + hasChocolate);

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate);
    }

        /* Sends email with summary.


         Intent intent = new Intent(Intent.ACTION_SENDTO);
         intent.setData(Uri.parse("mailto:")); // only email apps should handle this
         intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order for " + name);
         intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
         if (intent.resolveActivity(getPackageManager()) != null) {
         startActivity(intent);
         }


         }

         /**
         * This method is called when the plus button is clicked.
         */

    public void increment(View view) {

        quantity = quantity + 1;
        displayQuantity(quantity);

        /*
         * This where the Toast text message is created.
         */

        Context context = getApplicationContext();
        CharSequence text = "Sorry, the maximum order limit per customer is 100 coffees!";
        int duration = Toast.LENGTH_SHORT;

        {

            if (quantity == 100) {
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return;

            }

        }

        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {

        /*
         * This where the Toast text message is created.
         */

        Context context = getApplicationContext();
        CharSequence text = "The minimum order limit per customer is 1 coffee.";
        int duration = Toast.LENGTH_SHORT;


        if (quantity > 1) {
            quantity = quantity - 1;

        }
        if (quantity == 1) {
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        displayQuantity(quantity);


    }


    /**
     * This method displays the given quantity value on the screen.
     */

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}