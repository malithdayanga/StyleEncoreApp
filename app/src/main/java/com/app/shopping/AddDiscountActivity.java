package com.app.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddDiscountActivity extends AppCompatActivity {
    private EditText priceEtxt;
    private EditText DiscountEtxt;
    private TextView showDiscounttxt;
    private TextView showDisAmounttxt;
    private Button AddDisBtn , DisHomeBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discount);


        priceEtxt = findViewById(R.id.discountPriceEdittxt);
        DiscountEtxt = findViewById(R.id.edittext_number_2);
        showDiscounttxt = findViewById(R.id.textview_resultDis);
        showDisAmounttxt = findViewById(R.id.DisAmountTxt);
        AddDisBtn = findViewById(R.id.button_Dis);
        DisHomeBtn = findViewById(R.id.buttonDHome);

        AddDisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (priceEtxt.getText().toString().length() == 0) {
                    priceEtxt.setText("0");
                }

                if (DiscountEtxt.getText().toString().length() == 0) {
                    DiscountEtxt.setText("0");
                }


                int total = Integer.parseInt(priceEtxt.getText().toString());
                int Dis = Integer.parseInt(DiscountEtxt.getText().toString());

                int DisAmount = total * Dis / 100;
                int finalPrice = total - DisAmount;

                showDisAmounttxt.setText("Dis Amount :" + DisAmount +"Rs");

                showDiscounttxt.setText("Final Price :" + finalPrice +"Rs");


            }
        });
        DisHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }

        });

    }
}