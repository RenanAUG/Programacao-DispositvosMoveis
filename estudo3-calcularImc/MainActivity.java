package com.example.projetocalcularimc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edPeso;

    private EditText edAltura;

    private Button btHomem;

    private Button btMulher;

    private Button btLimpar;

    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edAltura = findViewById(R.id.edAltura);
        edPeso = findViewById(R.id.edPeso);
        tvResultado = findViewById(R.id.tvResultado);
        btMulher = findViewById(R.id.btMulher);
        btHomem = findViewById(R.id.btHomem);
        btLimpar = findViewById(R.id.btLimpar);

        btHomem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tipoIMC(btHomem, calcularIMC());
            }
        });

        btMulher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tipoIMC(btMulher, calcularIMC());

            }
        });

        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edAltura.setText("");
                edPeso.setText("");
                tvResultado.setText("");
            }
        });
    }

    private double calcularIMC() {
        double resultado;

        double valor = Double.parseDouble(edPeso.getText().toString());

        double valor2 = Double.parseDouble(edAltura.getText().toString());

        resultado = valor / (valor2*valor2);

        return resultado;
    }

    private void tipoIMC(Button tipoBotao, double valor) {
        if (tipoBotao == btHomem) {
            if (valor < 20.7) {
                tvResultado.setText("Seu peso está abaixo do normal!\n IMC é de " + valor);
            } else if (valor >= 20.7 && valor <= 26.4) {
                tvResultado.setText("Seu peso está normal!\n IMC é de " + valor);
            } else if (valor >= 25.8 && valor <= 27.3) {
                tvResultado.setText("Seu peso está marginalmente acima do ideal!\n IMC é de " + valor);
            } else if (valor >= 27.8 && valor <= 31.1) {
                tvResultado.setText("Seu peso está acima do ideal!\n IMC é de " + valor);
            } else {
                tvResultado.setText("Você está obeso!\n IMC é de " + valor);
            }
        } else {
            if (valor < 19.1) {
                tvResultado.setText("Seu peso está abaixo do normal!\n IMC é de " + valor);
            } else if (valor >= 19.1 && valor <= 25.8) {
                tvResultado.setText("Seu peso está normal!\n IMC é de " + valor);
            } else if (valor >= 25.8 && valor <= 27.3) {
                tvResultado.setText("Seu peso está marginalmente acima do ideal!\n IMC é de " + valor);
            } else if (valor >= 27.3 && valor <= 32.3) {
                tvResultado.setText("Seu peso está acima do ideal!\n IMC é de " + valor);
            } else {
                tvResultado.setText("Você está obeso!\n IMC é de " + valor);
            }
        }
    }
}