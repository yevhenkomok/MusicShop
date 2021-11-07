package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int quantity = 0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;

    HashMap goodsMap;
    String goodsName;
    double price;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.nameEditText);

        createSpinner();
        createMap();

    }

    void createSpinner () {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();

        spinnerArrayList.add("headphones");
        spinnerArrayList.add("speaker");
        spinnerArrayList.add("microphone");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    void createMap () {
        goodsMap = new HashMap();
        goodsMap.put("headphones", 100.0);
        goodsMap.put("speaker", 300.0);
        goodsMap.put("microphone", 150.0);
    }

    public void increaseQuantity(View view) {
        quantity = quantity + 1;
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);
    }

    public void decreaseQuantity(View view) {
        quantity = quantity - 1;
        if (quantity < 0) {
            quantity = 0;
        }
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double)goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);

        ImageView goodsImageView = findViewById(R.id.goodsImageView);

        switch (goodsName) {
            case "headphones":
                goodsImageView.setImageResource(R.drawable.earphones);
                break;
            case "speaker":
                goodsImageView.setImageResource(R.drawable.speaker);
                break;
            case "microphone":
                goodsImageView.setImageResource(R.drawable.microphone);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.earphones);
                break;

        }



//        if (goodsName.equals("headphones")) {
//            goodsImageView.setImageResource(R.drawable.earphones);
//        } else if(goodsName.equals("speaker")) {
//            goodsImageView.setImageResource(R.drawable.speaker);
//        }else if(goodsName.equals("microphone")) {
//            goodsImageView.setImageResource(R.drawable.microphone);
//        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {
        Order order = new Order();

        order.userName = userNameEditText.getText().toString();
       // Log.d("userName", order.userName);

        order.goodsName = goodsName;
      //  Log.d("goodsName", order.goodsName);

        order.quantity = quantity;
      //  Log.d("quantity", "" + order.quantity);

        order.price = price;

        order.orderPrice = quantity * price;
      //  Log.d("orderPrice", "" + order.orderPrice);

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userNameForIntent", order.userName);
        orderIntent.putExtra("goodsName",  order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("price", order.price);
        orderIntent.putExtra("orderPrice", order.orderPrice);

        startActivity(orderIntent);
    }
}