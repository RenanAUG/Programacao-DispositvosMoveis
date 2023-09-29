package com.example.trabalho2bimestre;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalho2bimestre.modelo.Cliente;
import com.example.trabalho2bimestre.modelo.Item;

import java.util.ArrayList;

public class LancarPedidoActivity extends AppCompatActivity {

    private AutoCompleteTextView tvAddCliente;

    private AutoCompleteTextView tvAddItem;

    private EditText edDescricaoItem;
    private EditText edvalorUnitario;

    private TextView tvListaItens;

    private EditText edValorTotal;

    private EditText edTotalItemAdd;

    private Spinner spFormaPagamento;

    private EditText edQtdParcelas;

    private TextView tvListaParcelas;

    private EditText edValorTotalPedido;

    private Button btSalvarPedido;

    private ArrayList<Cliente> listaCliente;

    private ArrayList<Item> listaItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancar_pedido);
        setTitle("Lançar Pedido");

        tvAddCliente = findViewById(R.id.tvAddCliente);
        tvAddItem = findViewById(R.id.tvAddItem);
        edDescricaoItem = findViewById(R.id.edDescricaoItem);
        edvalorUnitario = findViewById(R.id.edValorUnitario);
        tvListaItens = findViewById(R.id.tvListaItens);
        edValorTotal = findViewById(R.id.edValorTotal);
        edTotalItemAdd = findViewById(R.id.edTotalItemAdd);
        spFormaPagamento = findViewById(R.id.spFormaPagamento);
        edQtdParcelas = findViewById(R.id.edQtdParcelas);
        tvListaParcelas = findViewById(R.id.tvListaParcelas);
        edValorTotalPedido = findViewById(R.id.edValorTotalPedido);
        btSalvarPedido = findViewById(R.id.btSalvarPedido);

        carregarItem();
        carregarCliente();
    }


    private void carregarItem() {
        listaItem = Controller.getInstance().retornarItem();
        Integer[]vetItens = new Integer[listaItem.size()];
        for (int i = 0; i < listaItem.size(); i++) {
            Item item = listaItem.get(i);
            vetItens[i] = item.getCodigoItem();
        }
        ArrayAdapter adapter = new ArrayAdapter(LancarPedidoActivity.this,
                android.R.layout.simple_dropdown_item_1line, vetItens);

        tvAddItem.setAdapter(adapter);
    }

    private void carregarCliente() {

    }

}
