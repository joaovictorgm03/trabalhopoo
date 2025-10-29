package poo.demo;

import java.time.LocalDate;

public class PagamentoCartaoCredito implements IPagamento {
    private String numeroCartao;
    private String nomeTitular;
    private LocalDate dataValidade;
    private StatusPagamento statusPagamento;

    public PagamentoCartaoCredito(String numeroCartao, String nomeTitular, LocalDate dataValidade) {
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.dataValidade = dataValidade;
        this.statusPagamento = StatusPagamento.PENDENTE;
    }

    @Override
    public boolean processarPagamento(double valor) {
        System.out.println("Processando pagamento com Cartão de Crédito no valor de R$ " + valor);
        // Simula uma lógica de autorização
        if (valor < 5000.00) {
            this.statusPagamento = StatusPagamento.APROVADO;
            System.out.println("Pagamento APROVADO.");
            return true;
        } else {
            this.statusPagamento = StatusPagamento.RECUSADO;
            System.out.println("Pagamento RECUSADO.");
            return false;
        }
    }

    @Override
    public StatusPagamento getStatus() {
        return this.statusPagamento;
    }
}