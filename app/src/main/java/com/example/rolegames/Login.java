package com.example.rolegames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void goToCadastro(View view){
        Intent o = new Intent(getApplicationContext(), Cadastro.class);
        startActivity(o);
    }

    public void goToMain(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Bundle data = new Bundle();
        data.putString("enterType", "login");
        intent.putExtras(data);
        startActivity(intent);
    }
}