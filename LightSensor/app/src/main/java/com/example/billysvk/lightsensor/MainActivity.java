package com.example.billysvk.lightsensor;
import android.graphics.Color;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    TextView textView;
    View my_view;
    Sensor LightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        LightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        textView = (TextView) findViewById(R.id.textView1);
        my_view = (View) findViewById(R.id.my_view);
        my_view.setBackgroundColor(Color.WHITE);// set initial colour
    }

    public void Light(View view) {
        sensorManager.registerListener(this, LightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.values[0] == 0.0) {
            textView.setText(Float.toString(event.values[0]));
            my_view.setBackgroundColor(Color.BLACK);
        } else if ((event.values[0] > 0.0) && (event.values[0] <= 500.0)) {
            my_view.setBackgroundColor(Color.YELLOW);
            textView.setText(Float.toString(event.values[0]));
        } else if (event.values[0] > 500.0)
            my_view.setBackgroundColor(Color.RED);
        textView.setText(Float.toString(event.values[0]));
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
