package com.example.rahaf.safeheart1;

import android.Manifest;
import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class User_Activity extends AppCompatActivity {
    ImageButton   notification_button ;

    Button location , patient_button,Status_button,calling_button;
    TextView userName;
    String familyID="",first_name="",patientID="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userName =(TextView)findViewById(R.id.name);
        patient_button = (Button)findViewById(R.id.Status);
        calling_button=(Button)findViewById(R.id.chating);

        location=(Button)findViewById(R.id.Location);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            familyID = extras.getString("ID");
            first_name = extras.getString("firstName");
            patientID = extras.getString("patientID");
        }
        userName.setText(first_name);
        Log.d("tag", "onResponse: "+ patientID);



    }


    public void CallingActivity(View view)
    {
        calling_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String num = getIntent().getStringExtra("num");
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:"+num));
                        if (ActivityCompat.checkSelfPermission(User_Activity.this,
                                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(callIntent);


                    }
                }
        );
    }


    public void MapsActivity(View view)
    {
        location.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(User_Activity.this,MapsActivity.class);
                        startActivity(intent);

                    }
                }
        );
    }



    public void PatientAcivity(View view) {

                        Intent intent = new Intent(User_Activity.this,PatientStatus.class);
        intent.putExtra("ID", familyID);
        intent.putExtra("firstName", first_name);
                        intent.putExtra("patientID",patientID);
                        startActivity(intent);

    }
}
