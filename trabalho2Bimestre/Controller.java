package com.example.trabalho2bimestre;

import com.example.trabalho2bimestre.modelo.Cliente;
import com.example.trabalho2bimestre.modelo.Item;
import com.example.trabalho2bimestre.modelo.Pedido;

import java.util.ArrayList;

public class Controller {

    private static Controller instancia;

    private ArrayList<Cliente> listaCliente;

    private ArrayList<Item> listaItem;

    private ArrayList<Pedido> listaPedido;

    public static Controller getInstance() {
        if (instancia == null) {
            return instancia = new Controller();
        } else {
            return instancia;
        }
    }

    private Controller() {
        listaCliente = new ArrayList<>();
        listaItem = new ArrayList<>();
        listaPedido = new ArrayList<>();
    }

    public void salvarCliente(Cliente cliente) {
        listaCliente.add(cliente);
    }

    public ArrayList<Cliente> retornarCliente() {
        return listaCliente;
    }

    public void salvarItem(Item item){
        listaItem.add(item);
    }

    public ArrayList<Item> retornarItem() {
        return listaItem;
    }

    public void salvarPedido(Pedido pedido) {
        listaPedido.add(pedido);
    }

    public ArrayList<Pedido> retornarPedido() {
        return listaPedido;
    }
}
