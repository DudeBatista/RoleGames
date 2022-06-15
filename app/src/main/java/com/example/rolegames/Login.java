package com.example.rolegames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    Button btnLogin, btncad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btncad=findViewById(R.id.btncad);

        btncad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent o = new Intent(Login.this, Cadastro.class);
                startActivity(o);
            }
        });
    }

}