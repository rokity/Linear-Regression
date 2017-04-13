package com.regressionelineare.riccardoamadio.regressionelineare;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DecimalFormat;

public class RisultatiActivity extends AppCompatActivity {

    private String coefficente_angolare;
    private String termine_noto;

    private double m;
    private double q;

    private double[] random_x;
    private double[] random_y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risultati);

        Bundle b = getIntent().getExtras();

        this.m = b.getDouble("coefficente_angolare");
        this.q = b.getDouble("termine_noto");

        this.coefficente_angolare = new DecimalFormat("##.##").format(b.getDouble("coefficente_angolare"));
        this.termine_noto = new DecimalFormat("##.##").format(b.getDouble("termine_noto"));

        this.random_x=b.getDoubleArray("random_x");
        this.random_y=b.getDoubleArray("random_y");

        rendering();
    }

    private void rendering()
    {
        TextView coefficente_angolare_view=(TextView)findViewById(R.id.coefficente_angolare);
        coefficente_angolare_view.setText("Coefficente Angolare : \n "+
                this.coefficente_angolare);

        TextView termine_noto_view=(TextView)findViewById(R.id.termine_noto);
        termine_noto_view.setText("Termine Noto : \n "+
                this.termine_noto);

        TextView equazione_view=(TextView)findViewById(R.id.equazione);
        equazione_view.setText("Equazione : \n  y = "+
                this.coefficente_angolare+
        "x +"+this.termine_noto);

        plotting();
    }

    private void plotting()
    {
        GraphView graph = (GraphView) findViewById(R.id.graph);

        DataPoint[] data_points=new DataPoint[this.random_x.length];
        for(int i=0;i<this.random_x.length;i++)
        {
            double y=(this.m*this.random_x[i])+this.q;
            data_points[i] = new DataPoint(this.random_x[i], y);
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(data_points);
        graph.addSeries(series);

        series.setTitle("Regressione Lineare");
        series.setColor(Color.RED);
        series.setThickness(4);

        // set manual X and Y bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(100);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(100);

        // enable scaling and scrolling
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);
        graph.getViewport().setScrollable(true); //zoom horizontal
        graph.getViewport().setScrollableY(true); //zoom vertical
    }

}
