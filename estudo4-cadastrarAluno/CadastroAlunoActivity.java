package com.example.exemploactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CadastroAlunoActivity extends AppCompatActivity {

    private Button btSalvar;

    private EditText edRa;
    private EditText edNome;

    private TextView tvListaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        edRa = findViewById(R.id.edRa);
        tvListaAlunos = findViewById(R.id.tvListaAlunos);
        edNome = findViewById(R.id.edNome);
        btSalvar = findViewById(R.id.btSalvar);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarAluno();
            }
        });

        atualizaLista();
    }

    private void salvarAluno() {
        int ra = 0;

        if (edRa.getText().toString().isEmpty()){
            edRa.setText("O RA do ALuno deve ser informado!");
            return;
        } else {
            try {
                ra = Integer.parseInt(edRa.getText().toString());
            } catch (Exception ex) {
                edRa.setError("Informe um RA válido (somente números)!");
                return;
            }
        }
        if (edNome.getText().toString().isEmpty()) {
            edNome.setText("O Nome do Aluno deve ser informado!");

            return;
        }

        Aluno aluno = new Aluno(ra, edNome.getText().toString());
        aluno.setRa(ra);
        aluno.setNome(edNome.getText().toString());

        Controller.getInstancia().salvarAluno(aluno);

        Toast.makeText(CadastroAlunoActivity.this,
                "Aluno Cadastrado com Sucesso!", Toast.LENGTH_LONG).show();

        finish();
    }

    private void atualizaLista() {
        String texto = "";
        ArrayList<Aluno> lista = Controller.getInstancia().retornarAlunos();

        for (Aluno aluno: lista) {
            texto += aluno.getRa()+ " - " + aluno.getNome()+"\n";
        }
        tvListaAlunos.setText(texto);
    }
}