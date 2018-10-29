package com.example.dell.bazarayonsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class bazar_legal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bazar_legal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView carrito = (TextView)findViewById(R.id.cont);


                String valueCar = carrito.getText().toString();
                /*Intent returnIntent = new Intent();
                returnIntent.putExtra("result",valueCar);
                setResult(Activity.RESULT_OK,returnIntent);

                */

                Intent returnIntent = getIntent();
                returnIntent.putExtra("result",valueCar);
                setResult(RESULT_OK,returnIntent);


                finish();
            }
        });
    }
}
