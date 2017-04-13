package com.regressionelineare.riccardoamadio.regressionelineare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class ManualeActivity extends AppCompatActivity {

    private ArrayList<Double> list_manual_x = new ArrayList<Double>();
    private ArrayList<Double> list_manual_y = new ArrayList<Double>();;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manuale);
    }


    public void avanti(View v)
     {
        EditText x=(EditText) findViewById(R.id.x);
        EditText y=(EditText) findViewById(R.id.y);
        if(this.isEmpty(x)==false && this.isEmpty(y)==false)
        {
            try
            {
                this.list_manual_x.add(Double.parseDouble(x.getText().toString()));
            }catch(NumberFormatException e)
            {
                x.setError("Testo non corretto");
            }
            try
            {
                this.list_manual_y.add(Double.parseDouble(y.getText().toString()));
            }catch(NumberFormatException e)
            {
                y.setError("Testo non corretto");
            }

            x.setText("");y.setText("");
        }
    }


    public void calcola(View v) throws MyException
    {
        EditText ex=(EditText) findViewById(R.id.x);
        EditText ey=(EditText) findViewById(R.id.y);

        if(TextUtils.isEmpty(ex.getText().toString())==false ||
                TextUtils.isEmpty(ey.getText().toString())==false)
            avanti(ex);

        if(list_manual_x.size() != list_manual_y.size() ||
                list_manual_x.size() < 2 )
        {
            Toast.makeText(this,"Input non valido",Toast.LENGTH_LONG).show();
        }
        else
        {

            double[] x=new double[this.list_manual_x.size()];
            double[] y=new double[this.list_manual_y.size()];
            for(int i=0;i<this.list_manual_x.size();i++)
            {
                x[i] = this.list_manual_x.get(i);
                y[i] = this.list_manual_y.get(i);
            }


            Library l=new Library(x,y);
            double coefficente_angolare=l.coefficente_angolare();
            double termine_noto=l.termine_noto();
            Intent risultato=new Intent(this,RisultatiActivity.class);
            risultato.putExtra("coefficente_angolare",coefficente_angolare);
            risultato.putExtra("termine_noto",termine_noto);
            risultato.putExtra("random_x",x);
            risultato.putExtra("random_y",y);
            startActivity(risultato);
        }

    }

    private boolean isEmpty(EditText input)
    {
        String coordinata = input.getText().toString();

        if(TextUtils.isEmpty(coordinata))
        {
            input.setError("Testo vuoto");
            return true;
        }

        return false;
    }
}
