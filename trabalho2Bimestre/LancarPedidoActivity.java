package com.example.trabalho2bimestre;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalho2bimestre.modelo.Cliente;
import com.example.trabalho2bimestre.modelo.Item;
import com.example.trabalho2bimestre.modelo.Pedido;

import java.util.ArrayList;
import java.util.Random;

public class LancarPedidoActivity extends AppCompatActivity {

    private AutoCompleteTextView tvAddCliente;

    private Spinner spAddItem;

    private EditText edQtdItem;
    private EditText edvalorUnitario;

    private TextView tvListaItens;

    private EditText edValorTotal;

    private EditText edValorTotalPedido;

    private EditText edTotalItemAdd;

    private RadioButton rdAPrazo;

    private RadioButton rdAVista;

    private EditText edQtdParcelas;

    private EditText edCodigoPedido;

    private TextView tvListaParcelas;

    private Button btSalvarPedido;

    private ImageButton btAddItem;

    private ImageButton btAddParcelas;

    private ArrayList<Cliente> listaCliente;

    private ArrayList<Item> listaItem;

    private ArrayList<Pedido> listaPedido;

    private LinearLayout layoutParcelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancar_pedido);
        setTitle("Lançar Pedido");

        tvAddCliente = findViewById(R.id.tvAddCliente);
        spAddItem = findViewById(R.id.spAddItem);
        edQtdItem = findViewById(R.id.edQtdItem);
        edvalorUnitario = findViewById(R.id.edValorUnitario);
        tvListaItens = findViewById(R.id.tvListaItens);
        edValorTotal = findViewById(R.id.edValorTotal);
        edValorTotalPedido = findViewById(R.id.edValorTotalPedido);
        edTotalItemAdd = findViewById(R.id.edTotalItemAdd);
        rdAPrazo = findViewById(R.id.rdAPrazo);
        rdAVista = findViewById(R.id.rdAVista);
        edQtdParcelas = findViewById(R.id.edQtdParcelas);
        tvListaParcelas = findViewById(R.id.tvListaParcelas);
        btSalvarPedido = findViewById(R.id.btSalvarPedido);
        btAddItem = findViewById(R.id.btAddItem);
        btAddParcelas = findViewById(R.id.btAddParcelas);
        layoutParcelas = findViewById(R.id.layoutParcelas);
        edCodigoPedido = findViewById(R.id.edCodigoPedido);

        carregarItem();
        carregarCliente();

        edTotalItemAdd.setEnabled(false);
        edValorTotal.setEnabled(false);
        edValorTotalPedido.setEnabled(false);

        Random random = new Random();
        int codigoPedido = random.nextInt(1000) + 500;

        edCodigoPedido.setText(String.valueOf(codigoPedido));

        btAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarItem();
                atualizarListaItem();
            }
        });

        rdAPrazo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutParcelas.setVisibility(View.VISIBLE);
            }
        });

        rdAVista.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View view){
            layoutParcelas.setVisibility(View.GONE);
            double valorDesconto = Double.parseDouble(edValorTotal.getText().toString()) * 0.50;
            double resultado = Double.parseDouble(edValorTotal.getText().toString()) - valorDesconto;
            edValorTotalPedido.setText(String.valueOf(resultado));
            }
        });

        btSalvarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarPedido();
            }
        });

        btAddParcelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addParcelas();
            }
        });
    }

    private void carregarItem() {
        listaItem = Controller.getInstance().retornarItem();
        String[]vetItens = new String[listaItem.size()];
        for (int i = 0; i < listaItem.size(); i++) {
            Item item = listaItem.get(i);
            vetItens[i] = String.valueOf(item.getCodigoItem());
            String valorFixo = "0";
            edQtdItem.setText(valorFixo);
            edvalorUnitario.setText(String.valueOf(item.getValorUnitario()));
        }
        ArrayAdapter adapter = new ArrayAdapter(LancarPedidoActivity.this,
                android.R.layout.simple_dropdown_item_1line, vetItens);

        spAddItem.setAdapter(adapter);
    }

    private void carregarCliente() {
        listaCliente = Controller.getInstance().retornarCliente();
        String[]vetCliente = new String[listaCliente.size()];
        for (int i = 0; i < listaCliente.size(); i++) {
            Cliente cliente = listaCliente.get(i);
            vetCliente[i] = cliente.getNome();
        }
        ArrayAdapter adapter3 = new ArrayAdapter(LancarPedidoActivity.this,
                android.R.layout.simple_dropdown_item_1line, vetCliente);

        tvAddCliente.setAdapter(adapter3);
    }

    public void adicionarItem() {
        if (edQtdItem.getText().toString().isEmpty() || edQtdItem.getText().toString().equals("0")) {
            edQtdItem.setError("Informe a quantidade de Itens");
        }
        if (edvalorUnitario.getText().toString().isEmpty()) {
            edvalorUnitario.setError("Informe o Valor Unitário do Item");
        }

        Pedido pedido = new Pedido();
        pedido.setCodigoItem(Integer.parseInt(spAddItem.getItemAtPosition(0).toString()));
        pedido.setQuantidade(Integer.parseInt(edQtdItem.getText().toString()));
        pedido.setValorUnitario(Double.parseDouble(edvalorUnitario.getText().toString()));

        Controller.getInstance().salvarPedido(pedido);

        double valorTotal = 0;
        int quantidadeTotal = 0;

        for (Pedido p : Controller.getInstance().retornarPedido()) {
            valorTotal = valorTotal + (p.getValorUnitario() * p.getQuantidade());
            quantidadeTotal = quantidadeTotal + p.getQuantidade();
        }

        edValorTotal.setText(String.valueOf(valorTotal));
        edTotalItemAdd.setText(String.valueOf(quantidadeTotal));
    }

    private void atualizarListaItem() {
        String texto = "";
        for (Pedido pedido : Controller.getInstance().retornarPedido()) {
            texto+= "Cod: " + pedido.getCodigoItem() + " - Qtd: " + pedido.getQuantidade() + " - Valor: R$" + pedido.getValorUnitario() + "\n";
        }

        tvListaItens.setText(texto);
    }

    private void addParcelas() {
        Pedido pedido = new Pedido();
        pedido.setQtdParcelas(Integer.parseInt(edQtdParcelas.getText().toString()));

        Controller.getInstance().retornarPedido();

        double valorAcrescentado = Double.parseDouble(edValorTotal.getText().toString()) * 0.50;
        double resultado = Double.parseDouble(edValorTotal.getText().toString()) + valorAcrescentado;
        double valorParcelas = resultado / Double.parseDouble(edQtdParcelas.getText().toString());
        edValorTotalPedido.setText(String.valueOf(resultado));
        String texto = "";
        for (int i = 0; i < Integer.parseInt(edQtdParcelas.getText().toString()); i++) {
            texto = texto + "\n Parcelas: " + (i + 1) + " - Valor: R$" + valorParcelas + "\n";
        }

        tvListaParcelas.setText(texto);
    }

    private void salvarPedido() {
        Pedido pedido = new Pedido();
        pedido.setNomeCliente(tvAddCliente.getText().toString());
        pedido.setQtdParcelas(Integer.parseInt(edQtdParcelas.getText().toString()));
        pedido.setValorTotal(Double.parseDouble(edValorTotalPedido.getText().toString()));
        pedido.setQuantidade(Integer.parseInt(edTotalItemAdd.getText().toString()));
        pedido.setCodigoItem(Integer.parseInt(spAddItem.getItemAtPosition(0).toString()));
        pedido.setCodigoPedido(Integer.parseInt(edCodigoPedido.getText().toString()));

        Controller.getInstance().salvarPedido(pedido);

        Toast.makeText(LancarPedidoActivity.this,
                "Pedido Cadastrado com Sucesso!",
                Toast.LENGTH_LONG).show();

        this.finish();
    }

}
