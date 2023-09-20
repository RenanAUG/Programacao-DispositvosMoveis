package com.example.trabalho2bimestre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btCadastrarCliente;
    private Button btCadastrarItem;
    private Button btLancarPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCadastrarCliente = findViewById(R.id.btCadastrarCliente);
        btCadastrarItem = findViewById(R.id.btCadastrarItem);
        btLancarPedido = findViewById(R.id.btLancarPedido);

        btLancarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(CadastroClienteActivity.class);
            }
        });

        btCadastrarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(CadastroItemActivity.class);
            }
        });

        btCadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(LancarPedidoActivity.class);
            }
        });
    }

    private void abrirActivity(Class<?> activity) {
        Intent intent = new Intent(MainActivity.this, activity);
        startActivity(intent);
    }
}