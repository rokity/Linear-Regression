package com.regressionelineare.riccardoamadio.regressionelineare;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Random;


public class RandomActivity extends AppCompatActivity {

    private double[] random_x=new double[100];
    private double[] random_y=new double[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        randomize();
    }



    public void calcola(View v) throws MyException {
        Library l=new Library(this.random_x,this.random_y);
        double coefficente_angolare=l.coefficente_angolare();
        double termine_noto=l.termine_noto();
        Intent risultato=new Intent(this,RisultatiActivity.class);
        risultato.putExtra("coefficente_angolare",coefficente_angolare);
        risultato.putExtra("termine_noto",termine_noto);
        risultato.putExtra("random_x",this.random_x);
        risultato.putExtra("random_y",this.random_y);
        startActivity(risultato);

    }

    private void randomize(){
        Random random = new Random();

        TableLayout tl=(TableLayout)findViewById(R.id.table);
        for(int i=0;i<100;i++)
        {
            this.random_x[i] = 0 + (random.nextDouble() * (100 - 0));
            this.random_y[i] = 0 + (random.nextDouble() * (100 - 0));

            TableRow tr1 = new TableRow(this);
            tr1.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

            TextView textx = new TextView(this);
            textx.setText(String.valueOf(new DecimalFormat("##.##").format(random_x[i])));
            textx.setGravity(Gravity.LEFT);
            textx.setPadding(60,0,0,0);
            TextView texty = new TextView(this);
            texty.setGravity(Gravity.RIGHT);
            texty.setPadding(0,0,60,0);
            texty.setText(String.valueOf(new DecimalFormat("##.##").format(random_y[i])));

            tr1.addView(textx);tr1.addView(texty);
            tl.addView(tr1, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }
}
