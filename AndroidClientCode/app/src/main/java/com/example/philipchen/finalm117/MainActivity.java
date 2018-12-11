package com.example.philipchen.finalm117;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btnScan);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View _view) {

                //TODO: create the logic whether check-in or check-out
                if(true) 
                {//check-in
                    Intent i = new Intent(MainActivity.this, OrderParking.class);
                    startActivity(i);
                }
                else
                { //checkout
                    
                    Intent i = new Intent(MainActivity.this, ParkingConfirmation.class);
                    //TODO: create the logic to check whether they overparked or not
                    
                    if(true)
                    {//success, no overpark
                        i.putExtra("state", "checkoutsuccess");
                    }
                    else
                    {//failure, overparked
                        i.putExtra("state", "checkoutoverpark");
                    }
                    startActivity(i);
                }
            }
        });
    }
}
