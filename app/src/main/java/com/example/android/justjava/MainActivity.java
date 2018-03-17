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

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberofcoffes = 0;
    int price = 5;
    boolean isChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
         }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void submitOrder(View view) {

        CheckBox whippedCream = findViewById(R.id.whipped_cream);
        boolean hasWhipCream = whippedCream.isChecked();

        CheckBox chocolate = findViewById(R.id.chocolate);
        boolean hasChocolate = chocolate.isChecked();

        EditText textfield = findViewById(R.id.name);
        String name = textfield.getText().toString();

        int total = numberofcoffes * price;

        if (hasChocolate) {
            price = price + 1;
        }

        if (hasWhipCream) {
            price = price + 1;
        }

        total = numberofcoffes * price;

        String message = getString(R.string.nme)+" : " + name + "\n" + getString(R.string.quantity)+" : " + numberofcoffes + "\n" + getString(R.string.addwhip)+" " + hasWhipCream + "\n" + getString(R.string.addChoc)+" " + hasChocolate + "\n" + getString(R.string.total) +" : \u20B9" + total + "\n"+getString(R.string.thank);

        price = 5;



        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }


    /**
     * This method is called when the order button is clicked.
     */



    public void increment(View view) {
        if (numberofcoffes == 100) {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        numberofcoffes = numberofcoffes + 1;
        display(numberofcoffes);
    }

    public void decrement(View view) {
        if (numberofcoffes == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        numberofcoffes = numberofcoffes - 1;
        display(numberofcoffes);
    }

    /**
     * This method displays the given quantity value on the screen.
     * @param number Quantity
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}

