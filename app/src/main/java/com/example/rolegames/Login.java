package com.example.rolegames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    public Button btncad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


    }
    public void IrPraCad(View view){

        findViewById(R.id.btncad);
        Intent intent =  new Intent(Login.this, Cadastro.class);
        startActivity(intent);
    }
}