package com.example.diogenes.threadaula09;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TelaInicialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        // delay de 1 segundo
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Inicia a MainActivity
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                // Fecha a activity da splash
                finish();
            }
        },3000);
    }
}
