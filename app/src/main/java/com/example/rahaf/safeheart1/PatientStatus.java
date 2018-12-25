package com.example.rahaf.safeheart1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PatientStatus extends AppCompatActivity {
    TextView heart_rate;
    Button homePage;
    String familyID="",first_name="",patientID="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_status);
        heart_rate = (TextView)findViewById(R.id.heartRate);
        homePage = (Button) findViewById(R.id.home);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            familyID = extras.getString("ID");
            first_name = extras.getString("firstName");
            patientID = extras.getString("patientID");
        }
        loadStatus();


        homePage.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(PatientStatus.this, User_Activity.class);
                                            intent.putExtra("ID", familyID);
                                            intent.putExtra("firstName", first_name);
                                            intent.putExtra("patientID", patientID);
                                            startActivity(intent);
                                        }
                                    }


        );
    }

    public void loadStatus()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.PATIENT_STATUS,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("tag1", "onResponse: "+ response);

                        try {

                          JSONObject jsonObject =new JSONObject(response);
                          String status = jsonObject.getString("status");
                          heart_rate.setText(status + "BPM");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PatientStatus.this,error.getMessage(),Toast.LENGTH_SHORT).show();;

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> postParam = new HashMap<String, String>();
                postParam.put(Config.KEY_USERNAME, patientID);
                return postParam;
            }


            @Override
            public byte[] getBody() throws AuthFailureError {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put(Config.KEY_USERNAME, patientID);

                }catch(JSONException e)
                {

                }

                return jsonObject.toString().getBytes() ;
            }
            @Override
            public String getBodyContentType() {
                return "application/json";

            }


        };
        Volley.newRequestQueue(this).add(stringRequest);

    }


    }



