package com.example.slicemeuppizza;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgType;
    RadioGroup rgSize;
    RadioGroup rgCrust;
    CheckBox cbTomato;
    CheckBox cbOnion;
    CheckBox cbExCheese;
    CheckBox cbPineapple;
    CheckBox cbMushrooms;
    Button btnProcess;
    Button btnNewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI elements
        rgType = findViewById(R.id.rgType);
        rgSize = findViewById(R.id.rgSize);
        rgCrust = findViewById(R.id.rgCrust);
        cbTomato = findViewById(R.id.cbTomato);
        cbOnion = findViewById(R.id.cbOnion);
        cbExCheese = findViewById(R.id.cbExCheese);
        cbPineapple = findViewById(R.id.cbPineapple);
        cbMushrooms = findViewById(R.id.cbMushrooms);
        btnProcess = findViewById(R.id.btnProcess);
        btnNewOrder = findViewById(R.id.btnNewOrder);

        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get selected pizza type
                int selectedTypeId = rgType.getCheckedRadioButtonId();
                RadioButton selectedTypeRadioButton = findViewById(selectedTypeId);
                String pizzaType = selectedTypeRadioButton.getText().toString();

                // Get selected size
                int selectedSizeId = rgSize.getCheckedRadioButtonId();
                RadioButton selectedSizeRadioButton = findViewById(selectedSizeId);
                String pizzaSize = selectedSizeRadioButton.getText().toString();

                // Get selected crust
                int selectedCrustId = rgCrust.getCheckedRadioButtonId();
                RadioButton selectedCrustRadioButton = findViewById(selectedCrustId);
                String crustType = selectedCrustRadioButton.getText().toString();

                // Get selected toppings
                ArrayList<String> toppings = new ArrayList<>();
                if (cbTomato.isChecked()) {
                    toppings.add("Tomatoes");
                }
                if (cbOnion.isChecked()) {
                    toppings.add("Onion");
                }
                if (cbExCheese.isChecked()) {
                    toppings.add("Extra Cheese");
                }
                if (cbPineapple.isChecked()) {
                    toppings.add("Pineapple");
                }
                if (cbMushrooms.isChecked()) {
                    toppings.add("Mushroom");
                }

                // Create intent and pass data
                Intent intent = new Intent(MainActivity.this, OrderDetailsActivity.class);
                intent.putExtra("pizzaType", pizzaType);
                intent.putExtra("pizzaSize", pizzaSize);
                intent.putExtra("crustType", crustType);
                intent.putStringArrayListExtra("toppings", toppings);
                startActivity(intent);
            }
        });

        // You can add an OnClickListener for the btnNewOrder if needed
    }
}