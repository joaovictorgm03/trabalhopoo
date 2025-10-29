package poo.demo;

import java.time.LocalDate;

public class PagamentoBoleto implements IPagamento {
    private String codigoBarras;
    private LocalDate dataVencimento;
    private double valor;
    private StatusPagamento statusPagamento;

    public PagamentoBoleto(String codigoBarras, LocalDate dataVencimento) {
        this.codigoBarras = codigoBarras;
        this.dataVencimento = dataVencimento;
        this.statusPagamento = StatusPagamento.PENDENTE;
    }

    @Override
    public boolean processarPagamento(double valor) {
        System.out.println("Gerando boleto no valor de R$ " + valor);
        this.valor = valor;
        // Simula a geração do boleto, que é sempre bem-sucedida
        this.statusPagamento = StatusPagamento.APROVADO; // Aprovado significa "gerado com sucesso"
        System.out.println("Boleto gerado com sucesso. Código: " + this.codigoBarras);
        return true;
    }

    @Override
    public StatusPagamento getStatus() {
        return this.statusPagamento;
    }
}