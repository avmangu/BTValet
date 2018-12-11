package com.example.philipchen.finalm117;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OrderParking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_parking);

        Button btn = (Button) findViewById(R.id.btnPay);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View _view) {
                EditText spotTxt   = (EditText)findViewById(R.id.spotText);
                EditText hoursTxt   = (EditText)findViewById(R.id.hoursText);
                String hours = hoursTxt.getText().toString();
                String spot = spotTxt.getText().toString();

                //TODO: create logic to check if connection successful

                if(true) 
                {
                    //connection successful
                    Intent i = new Intent(OrderParking.this, ParkingConfirmation.class);
                    i.putExtra("state", "checkinsuccess");
                    startActivity(i);
                    Log.d("STATE", hours);
                    Log.d("STATE", spot);
                }
                else
                { 
                    //failure, redo
                    Intent i = new Intent(OrderParking.this, ParkingConfirmation.class);
                    i.putExtra("state", "checkinfailure");
                    startActivity(i);
                }
            }
        });

    }
}
