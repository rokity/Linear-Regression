package com.regressionelineare.riccardoamadio.regressionelineare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void manuale(View view){

       Intent manuale=new Intent(this,ManualeActivity.class);
        startActivity(manuale);
    }
    public void random(View view){

        Intent random=new Intent(this,RandomActivity.class);
        startActivity(random);

    }
}
