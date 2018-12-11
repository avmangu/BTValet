package com.example.philipchen.finalm117;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ParkingConfirmation extends AppCompatActivity {

    TextView tv1;
    String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_parking_confirmation);
        tv1 = (TextView)findViewById(R.id.textview123);

        state = getIntent().getStringExtra("state");

        if(state.equals("checkinsuccess"))
        {
            //TODO: Animesh was talking about showing an updated map here.
            tv1.setText("Check-in success.");
        }
        else if(state.equals("checkinfailure")) {
            tv1.setText("Failure at check-in, try again.");
        }
        else if(state.equals("checkoutsuccess")){
            tv1.setText("Check-out success");
        }
        else if(state.equals("checkoutoverpark")){
            tv1.setText("Overparked. You will be charged a penalty of $10.");
        }

        Button btn = (Button) findViewById(R.id.exitToMain);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View _view) {
                Intent i = new Intent(ParkingConfirmation.this, MainActivity.class);
                startActivity(i);
            }
        });


    }
}
