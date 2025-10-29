package poo.demo;

import java.time.LocalDate;

public class ClientePF extends Cliente {
    private String cpf;
    private LocalDate dataNascimento;

    public ClientePF(String id, String nome, String email, String[] telefones, String cpf, LocalDate dataNascimento) {
        super(id, nome, email, telefones);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String getIdentificadorUnico() {
        return this.cpf;
    }
}