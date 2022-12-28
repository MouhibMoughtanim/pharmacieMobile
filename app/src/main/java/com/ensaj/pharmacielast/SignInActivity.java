package com.ensaj.pharmacielast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ensaj.pharmacielast.helpers.StringHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {

    Button sign_in_btn;
    EditText et_email, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        // Hook Edit Text Fields:
        et_email = findViewById(R.id.email);
        et_password = findViewById(R.id.password);

        // Hook Button:
        sign_in_btn = findViewById(R.id.sign_in_btn);

        // Set Sign In Button On Click Listener:
        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticateUser();

            }
        });


    }
    // End Of On Create Activity.

    public void authenticateUser(){
        // Check For Errors:
        if( !validateEmail() || !validatePassword()){
            return;
        }
        // End Of Check For Errors.

        // Instantiate The Request Queue:
        RequestQueue queue = Volley.newRequestQueue(SignInActivity.this);
        // The URL Posting TO:
        String url = "http://10.0.2.2:9071/api/v1/user/login";

        // Set Parameters:
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", et_email.getText().toString());
        params.put("password", et_password.getText().toString());

        // Set Request Object:
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Get Values From Response Object:
                            String first_name = (String) response.get("first_name");
                            String last_name = (String) response.get("last_name");
                            String email = (String) response.get("email");
                            boolean etat = (boolean) response.get("etat");

                            if(etat){
                                // Set Intent Actions:
                                Intent goToAdminSide = new Intent(SignInActivity.this,Main2Activity.class);
                                // Pass Values To Profile Activity:
                                goToAdminSide.putExtra("first_name", first_name);
                                goToAdminSide.putExtra("last_name", last_name);
                                goToAdminSide.putExtra("email", email);
                                // Start Activity:
                                startActivity(goToAdminSide);
                                finish();
                            }else if (!etat){
                                Toast.makeText(SignInActivity.this, "Your registration has been successfull, but the admin hasn't accepted you yet ! ", Toast.LENGTH_LONG).show();
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                        }
                        // End Of Try Block.
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println(error.getMessage());
                Toast.makeText(SignInActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
            }
        });// End Of Set Request Object.

       queue.add(jsonObjectRequest);

    }

    public void goToHome(View view){
        Intent intent = new Intent(SignInActivity.this, AccueilActivity.class);
        startActivity(intent);
        finish();
    }
    // End Of Go To Home Intent Method.


    public void goToSigUpAct(View view){
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }
    // End Of Go To Sign Up Intent Method.


    public boolean validateEmail(){
        String email = et_email.getText().toString();
        // Check If Email Is Empty:
        if(email.isEmpty()){
            et_email.setError("Email cannot be empty!");
            return false;
        }else if(!StringHelper.regexEmailValidationPattern(email)){
            et_email.setError("Please enter a valid email");
            return false;
        }else{
            et_email.setError(null);
            return true;
        }// Check If Email Is Empty.
    }
    // End Of Validate Email Field.

    public boolean validatePassword() {
        String password = et_password.getText().toString();

        // Check If Password and Confirm Field Is Empty:
        if (password.isEmpty()) {
            et_password.setError("Password cannot be empty!");
            return false;
        } else {
            et_password.setError(null);
            return true;
        }// Check Password and Confirm Field Is Empty.
    }
    // End Of Validate Password;
}
// End Of Sign In Activity Class.