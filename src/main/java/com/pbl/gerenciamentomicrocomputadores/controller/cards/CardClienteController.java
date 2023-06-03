package com.pbl.gerenciamentomicrocomputadores.controller.cards;

import com.pbl.gerenciamentomicrocomputadores.controller.MyListener;
import com.pbl.gerenciamentomicrocomputadores.model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CardClienteController {

    @FXML
    private Label idCliente;

    @FXML
    private Label nomeCliente;

    private Cliente cliente;

    private MyListener<Cliente> myListener;

    @FXML
    public void click (MouseEvent mouseEvent) {

        this.myListener.onClickListener(cliente);
    }

    public void setInfo (Cliente cliente, MyListener<Cliente> myListener) {

        this.cliente = cliente;
        this.myListener = myListener;

        idCliente.setText(Integer.toString(cliente.getId()));
        nomeCliente.setText(cliente.getNome());
    }

}
