package com.example.trabalho2bimestre;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalho2bimestre.modelo.Cliente;

public class CadastroClienteActivity extends AppCompatActivity {

    private EditText edNomeCliente;

    private EditText edCpfCliente;

    private Button btSalvar;

    private Button btCancelar;

    private TextView tvClientesCadastrados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        edNomeCliente = findViewById(R.id.edNomeCliente);
        edCpfCliente = findViewById(R.id.edCpfCliente);
        btSalvar = findViewById(R.id.btSalvar);
        btCancelar = findViewById(R.id.btCancelar);
        tvClientesCadastrados = findViewById(R.id.tvClientesCadastrados);

        atualizarListaCliente();

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarCliente();
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelar();
            }
        });
    }

    private void cancelar() {
        this.finish();
    }

    private void salvarCliente() {
        if (edCpfCliente.getText().toString().isEmpty()) {
            edCpfCliente.setError("Informe o CPF do cliente");
        }
        if (edNomeCliente.getText().toString().isEmpty()) {
            edNomeCliente.setError("Informe o nome do cliente");
        }

        Cliente cliente = new Cliente();
        cliente.setCpf(edCpfCliente.getText().toString());
        cliente.setNome(edNomeCliente.getText().toString());

        Controller.getInstance().salvarCliente(cliente);

        Toast.makeText(CadastroClienteActivity.this,
                "Cliente Cadastrado com Sucesso!",
                Toast.LENGTH_LONG).show();

        this.finish();
    }

    private void atualizarListaCliente() {
        String texto = "";
        for (Cliente cliente : Controller.getInstance().retornarCliente()) {
            texto += "Nome: " + cliente.getNome() + " - " + cliente.getCpf() + "\n";
        }

        tvClientesCadastrados.setText(texto);
    }

}
