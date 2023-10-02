package com.example.trabalho2bimestre;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalho2bimestre.modelo.Item;

public class CadastroItemActivity extends AppCompatActivity {

    private EditText edCodigoItem;

    private EditText edValorUnitario;

    private EditText edDescricaoItem;

    private Button btSalvar;

    private Button btCancelar;

    private TextView tvItemCadastrados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_item);

        btSalvar = findViewById(R.id.btSalvar);
        edCodigoItem = findViewById(R.id.edCodigoItem);
        edValorUnitario = findViewById(R.id.edValorUnitario);
        edDescricaoItem = findViewById(R.id.edDescricaoItem);
        btCancelar = findViewById(R.id.btCancelar);
        tvItemCadastrados = findViewById(R.id.tvItemCadastrados);

        atualizarListaItem();

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarItem();
                atualizarListaItem();
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                cancelar();
            }
        });
    }

    private void cancelar() {
        this.finish();
    }

    private void salvarItem() {
        if (edCodigoItem.getText().toString().isEmpty()) {
            edCodigoItem.setError("Informe o código do item.");
        }
        if (edDescricaoItem.getText().toString().isEmpty()) {
            edDescricaoItem.setError("Informe a descrição do item.");
        }
        if (edValorUnitario.getText().toString().isEmpty()) {
            edValorUnitario.setError("Informe o valor unitário.");
        }

        Item item = new Item();
        item.setCodigoItem(Integer.parseInt(edCodigoItem.getText().toString()));
        item.setDescricao(edDescricaoItem.getText().toString());
        item.setValorUnitario(Double.parseDouble(edValorUnitario.getText().toString()));

        Controller.getInstance().salvarItem(item);

        Toast.makeText(CadastroItemActivity.this,
                "Item Cadastrado com Sucesso!",
                Toast.LENGTH_LONG).show();
    }

    private void atualizarListaItem() {
        String texto = "";
        for (Item item : Controller.getInstance().retornarItem()) {
            texto+= "Código: " + item.getCodigoItem() + " - " + item.getDescricao() + " - " + item.getValorUnitario() + "\n";
        }

        tvItemCadastrados.setText(texto);
    }
}
