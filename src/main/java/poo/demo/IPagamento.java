package poo.demo;

public interface IPagamento {
    boolean processarPagamento(double valor);
    StatusPagamento getStatus();
}