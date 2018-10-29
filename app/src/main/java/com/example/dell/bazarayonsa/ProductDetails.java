package com.example.dell.bazarayonsa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.ActionBar;

import com.squareup.picasso.Picasso;



public class ProductDetails extends AppCompatActivity {
    String[] product_title,product_image,product_price,product_price_ofera,product_save,product_image1,product_image2,product_image3,product_gender,product_desc;
    int position;
    String getimage1,getimage2,getimage3;

    ImageView productimage1;
    TextView producttitle,productprice,productsave,productgender,productdesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

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


        ImageView cart =(ImageView)findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Cart",Toast.LENGTH_SHORT).show();

            }
        });


        Button buy = (Button) findViewById(R.id.buy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView carrito = (TextView)findViewById(R.id.cont);
                String valueCar = carrito.getText().toString();


                try {
                    if(valueCar.equals("")){
                        valueCar = "0";
                    }


                    int cont = Integer.parseInt(valueCar);
                    cont = cont + 1;
                    carrito.setText(String.valueOf(cont ));


                    Toast.makeText(getApplicationContext(),String.valueOf(cont),Toast.LENGTH_SHORT).show();

                }catch (Exception e){

                }

            }
        });


        Intent i =getIntent();
        position =i.getExtras().getInt("position");
        product_title=i.getStringArrayExtra("producttitle");
        product_image=i.getStringArrayExtra("productimage");
        product_price=i.getStringArrayExtra("productprice");
        product_save=i.getStringArrayExtra("productsave");

        product_image1=i.getStringArrayExtra("productimage");
        product_image2=i.getStringArrayExtra("productimage1");
        product_image3=i.getStringArrayExtra("productimage2");

        product_desc=i.getStringArrayExtra("productdesc");


        producttitle=(TextView)findViewById(R.id.producttitle);
        producttitle.setText(product_title[position]);

        productprice=(TextView)findViewById(R.id.productprice);
        productprice.setText(product_price[position]);


        productsave=(TextView)findViewById(R.id.productsave);
        productsave.setText(product_save[position]);


        productdesc=(TextView)findViewById(R.id.productdescrip);
        productdesc.setText(product_desc[position]);

         getimage1 = product_image1[position];
         getimage2 = product_image2[position];
         getimage3 = product_image3[position];

        productimage1 = (ImageView)findViewById(R.id.productimage1);
        Picasso.with(getApplicationContext())
                .load(getimage1)
                .into(productimage1);


        ImageButton image1 = (ImageButton)findViewById(R.id.images1);
        Picasso.with(getApplicationContext())
                .load(getimage1)
                .into(image1);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productimage1 = (ImageView)findViewById(R.id.productimage1);
                Picasso.with(getApplicationContext())
                        .load(getimage1)
                        .into(productimage1);
             //   watchimage1.setVisibility(View.VISIBLE);


            }
        });


        ImageButton image2 = (ImageButton)findViewById(R.id.images2);
        Picasso.with(getApplicationContext())
                .load(getimage2)
                .into(image2);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productimage1 = (ImageView)findViewById(R.id.productimage1);
                Picasso.with(getApplicationContext())
                        .load(getimage2)
                        .into(productimage1);
               // watchimage1.setVisibility(View.VISIBLE);


            }
        });


        ImageButton image3 = (ImageButton)findViewById(R.id.images3);
        Picasso.with(getApplicationContext())
                .load(getimage3)
                .into(image3);
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productimage1 = (ImageView)findViewById(R.id.productimage1);
                Picasso.with(getApplicationContext())
                        .load(getimage3)
                        .into(productimage1);
              //  watchimage1.setVisibility(View.VISIBLE);


            }
        });




    }

}
