package poo.demo;

public abstract class Cliente {
    private String id;
    private String nome;
    private String email;
    private String[] telefones;

    public Cliente(String id, String nome, String email, String[] telefones) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefones = telefones;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String[] getTelefones() {
        return telefones;
    }

    public abstract String getIdentificadorUnico();
}