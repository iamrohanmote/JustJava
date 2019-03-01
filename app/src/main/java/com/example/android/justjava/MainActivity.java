/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox WhippedCreamCheckBox = (CheckBox) findViewById(R.id.has_whipped_cream);
        boolean hasWhippedCream = WhippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.has_chocolate);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        EditText Text = (EditText) findViewById(R.id.Name_Edit_Text);
        String NameText = Text.getText().toString();
        EditText Phone = (EditText) findViewById(R.id.Phone_Edit_Text);
        String PhoneText = Phone.getText().toString();
        EditText Email = (EditText) findViewById(R.id.Email_Edit_Text);
        String EmailText = Email.getText().toString();
        int price = calculatePrice(hasWhippedCream,hasChocolate);

        String finalSummary = createOrderSummary(price,hasWhippedCream,hasChocolate,NameText,PhoneText,EmailText);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java Summary for "+NameText);
        intent.putExtra(Intent.EXTRA_EMAIL,EmailText);
        intent.putExtra(Intent.EXTRA_TEXT,finalSummary);
        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivity(intent);
        }

        //displayMessage(finalSummary);

    }

    private int calculatePrice(boolean hasWhippedCream,boolean hasChocolate)
    {
        int basicPrice = 5;
        if(hasWhippedCream)
            basicPrice += 3;
            if (hasChocolate)
                basicPrice += 2;
            return quantity * basicPrice;


    }

    private String createOrderSummary(int price,boolean addWhippedCream,boolean addChocolate,String addName,String addPhone,String addEmail)
    {
        String summary = "Hello "+addName+"!";
        summary += "\nPhone Number = "+addPhone;
        summary += "\nEmail Address = "+addEmail;
        summary = summary + "\nQuantity = "+quantity;
        summary += "\nAdded Whipped Cream?($3 each) = "+addWhippedCream;
        summary += "\nAdded Chocolate?($2 each) = "+addChocolate;
        summary = summary + "\nTotal = $"+price;
        summary = summary + "\nThank You!\nVisit Again!";
        return summary;
    }
    public void increment(View view)
    {
        quantity = quantity + 1;
        if(quantity>100) {
            Toast.makeText(MainActivity.this, "Cannot order beyond 100 Coffess!", Toast.LENGTH_SHORT).show();
            quantity = 100;
        }
        displayQuantity(quantity);
    }

    public void decrement(View view)
    {
        quantity = quantity - 1;

            if (quantity < 1)
            {
                Toast.makeText(MainActivity.this, "How will you order Negative Number of Coffess!", Toast.LENGTH_SHORT).show();
            quantity = 1;
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

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }
}