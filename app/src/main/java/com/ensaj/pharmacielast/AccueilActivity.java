package com.ensaj.pharmacielast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccueilActivity extends AppCompatActivity {
    Button sign_in, sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
    }
    public void goToSignUp(View view){
        Intent intent = new Intent(AccueilActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }
    // End Of Go To Sign Up Activity.

    public void goToSignIn(View view){
        Intent intent = new Intent(AccueilActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }
    // End Of Go To Sign In Activity.
    public void goToClientSide(View view){
        Intent intent = new Intent(AccueilActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}