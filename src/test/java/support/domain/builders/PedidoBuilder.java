package support.domain.builders;

import support.domain.Pedido;

public class PedidoBuilder {
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean complete;

    public PedidoBuilder(){
        reset();
    }

    public PedidoBuilder comId(int id) {
        this.id = id;
        return this;
    }

    public PedidoBuilder comPetId(int petId) {
        this.petId = petId;
        return this;
    }

    public PedidoBuilder comQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public PedidoBuilder comShipDate(String shipDate) {
        this.shipDate = shipDate;
        return this;
    }

    public PedidoBuilder comStatus(String status) {
        this.status = status;
        return this;
    }

    public PedidoBuilder comComplete(boolean complete) {
        this.complete = complete;
        return this;
    }

    public Pedido build(){
        return new Pedido(id, petId, quantity, shipDate, status, complete);
    }

    public void reset(){
        id = 5;
        petId = 22;
        quantity = 10;
        shipDate = "20/01/2022";
        status = "approved";
        complete = true;
    }
}
