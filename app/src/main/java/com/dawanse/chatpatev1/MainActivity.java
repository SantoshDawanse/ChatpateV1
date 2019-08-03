package com.dawanse.chatpatev1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout regularBtn, customizeBtn, salesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        regularBtn = findViewById(R.id.main_regular_ll);
        customizeBtn = findViewById(R.id.main_customize_ll);
        salesBtn = findViewById(R.id.main_sales_ll);

        regularBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                startActivity(new Intent(getApplicationContext(), RegularChatpate.class));
            }
        });

        customizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                startActivity(new Intent(getApplicationContext(), CustomizeChatpate.class));
            }
        });

        salesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                startActivity(new Intent(getApplicationContext(), SalesActivity.class));
            }
        });
    }

}
