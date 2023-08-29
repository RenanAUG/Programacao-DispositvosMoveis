package com.example.meuprimeiroprojeto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edTexto;
    private Button btMudarTexto;
    private TextView tvTextoAlterado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edTexto = findViewById(R.id.edTexto);
        btMudarTexto = findViewById(R.id.btMudarTexto);
        tvTextoAlterado = findViewById(R.id.tvTextoAlterado);

        btMudarTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mudarTexto();
            }
        });
    }

    private void mudarTexto() {
        String texto = edTexto.getText().toString();

        tvTextoAlterado.setText(texto);
    }
}