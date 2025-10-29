package poo.demo;

public class ClientePJ extends Cliente {
    private String cnpj;
    private String razaoSocial;
    private String inscricaoEstadual;

    public ClientePJ(String id, String nome, String email, String[] telefones, String cnpj, String razaoSocial) {
        super(id, nome, email, telefones);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    @Override
    public String getIdentificadorUnico() {
        return this.cnpj;
    }
}