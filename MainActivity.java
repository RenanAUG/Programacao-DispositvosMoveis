package com.example.calcularir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

        private EditText edRendaBruta;
        private EditText edDependentes;
        private EditText edTotalDeducao;
        private Button btCalcular;
        private TextView tvRendaBruta;
        private TextView tvBaseCalculo;
        private TextView tvImposto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edDependentes = findViewById(R.id.edDependentes);
        btCalcular = findViewById(R.id.btCalcular);
        edRendaBruta = findViewById(R.id.edRendaBruta);
        edTotalDeducao = findViewById(R.id.edTotalDeducao);
        tvRendaBruta = findViewById(R.id.tvRendaBruta);
        tvBaseCalculo = findViewById(R.id.tvBaseCalculo);
        tvImposto = findViewById(R.id.tvImposto);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvRendaBruta.setText("Renda Bruta Mensal:" + calcularRenda());

                tvImposto.setText("Imposto Devido:" + calcularImposto());

                calcularBaseCalculo(calcularRenda(), calcularDependente());
            }
        });
    }

    private double calcularRenda() {

        return Double.parseDouble(edRendaBruta.getText().toString()) * 12;
    }

    private double calcularImposto() {
        double resultadoRenda = Double.parseDouble(edRendaBruta.getText().toString()) * 12;
        double imposto;
        double aliquota = 2.75 / 10;
        double resultadoValor;

        if (resultadoRenda < 18000) {
            imposto = 0;

        } else if (resultadoRenda < 27000) {
            imposto = 0.15;
            resultadoValor = resultadoRenda * imposto;

            return resultadoValor;
        } else {
            imposto = 1350;
            resultadoValor = (resultadoRenda * aliquota) + imposto ;

            return resultadoValor;
        }
        return 0;
    }

    private double calcularDependente() {
        int dependente = Integer.parseInt(edDependentes.getText().toString());

        return dependente * 300 * 12 ;
    }

    private double calcularBaseCalculo(double valorRenda, double dependente){
        double valorDeducao = Double.parseDouble(edTotalDeducao.getText().toString());
        double resultado = valorRenda - dependente - valorDeducao;

        tvBaseCalculo.setText("Base de Cálculo:" + resultado);

        return resultado;
    }

}