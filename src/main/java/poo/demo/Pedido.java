package poo.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pedido {
    private LocalDate data;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private IPagamento metodoPagamento;
    private StatusPedido statusPedido;
    private double desconto;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.data = LocalDate.now();
        this.itens = new ArrayList<>();
        this.statusPedido = StatusPedido.PENDENTE;
        this.desconto = 0.0;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        Optional<ItemPedido> itemExistente = itens.stream()
                .filter(item -> item.getProduto().getId().equals(produto.getId()))
                .findFirst();

        if (itemExistente.isPresent()) {
            ItemPedido item = itemExistente.get();
            item.setQuantidade(item.getQuantidade() + quantidade);
        } else {
            ItemPedido novoItem = new ItemPedido(produto, quantidade);
            this.itens.add(novoItem);
        }
    }

    public void removerItem(ItemPedido item) {
        this.itens.remove(item);
    }

    public double calcularTotal() {
        return this.itens.stream()
                .mapToDouble(ItemPedido::getSubtotal)
                .sum();
    }

    public double aplicarDesconto(double percentualDesconto) {
        if (percentualDesconto > 0 && percentualDesconto <= 100) {
            double valorDesconto = calcularTotal() * (percentualDesconto / 100.0);
            this.desconto = valorDesconto;
            return calcularTotal() - this.desconto;
        }
        return calcularTotal();
    }

    public boolean confirmarPedido() {
        if (this.statusPedido != StatusPedido.PENDENTE) {
            System.err.println("Erro: O pedido não está com o status PENDENTE.");
            return false;
        }

        if (this.metodoPagamento == null) {
            System.err.println("Erro: Nenhum método de pagamento foi definido.");
            return false;
        }

        double valorFinal = calcularTotal() - this.desconto;
        boolean pagamentoProcessado = this.metodoPagamento.processarPagamento(valorFinal);

        if (pagamentoProcessado) {
            this.statusPedido = StatusPedido.PROCESSANDO;
        } else {
            this.statusPedido = StatusPedido.PENDENTE;
        }

        return pagamentoProcessado;
    }

    // --- GETTERS E SETTERS ---

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setMetodoPagamento(IPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    /**
     * NOVO MÉTODO ADICIONADO AQUI
     * Retorna o método de pagamento associado ao pedido.
     * @return O objeto IPagamento ou null se não houver sido definido.
     */
    public IPagamento getMetodoPagamento() {
        return this.metodoPagamento;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return "Pedido [Data=" + data + ", Cliente=" + cliente.getNome() +
                ", Status=" + statusPedido + ", Total=R$ " + (calcularTotal() - desconto) + "]";
    }
}