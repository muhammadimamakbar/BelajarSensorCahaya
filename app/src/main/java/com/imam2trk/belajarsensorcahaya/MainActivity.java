package com.imam2trk.belajarsensorcahaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar pbCahaya;
    TextView txtNilaiMax, txtHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pbCahaya = findViewById(R.id.nilaiProgress);
        txtNilaiMax = findViewById(R.id.nilaiMax);
        txtHasil = findViewById(R.id.nilaiOutput);

        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sm.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (sensor == null){
            Toast.makeText(getApplicationContext(), "Tidak ada sensor tersedia",
                    Toast.LENGTH_SHORT).show();
        } else {
            float max = sensor.getMaximumRange();
            pbCahaya.setMax(480);
            txtNilaiMax.setText("Nilai Max: "+max);
            sm.registerListener(lightSensorEventListener,
                    sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    SensorEventListener lightSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType()==Sensor.TYPE_LIGHT){
                float nilaidiBaca = sensorEvent.values[0];
                pbCahaya.setProgress((int)nilaidiBaca) ;
                txtHasil.setText("Nilai saat ini: "+nilaidiBaca+"Lux");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}