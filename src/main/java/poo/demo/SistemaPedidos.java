package poo.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.support.RouterFunctionMapping;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class SistemaPedidos {

    private final RouterFunctionMapping routerFunctionMapping;

    public SistemaPedidos(RouterFunctionMapping routerFunctionMapping) {
        this.routerFunctionMapping = routerFunctionMapping;
    }

    public static void main(String[] args) {
        SpringApplication.run(SistemaPedidos.class, args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Opa, %s", name);
    }

    @GetMapping("/produto")
    public Produto produto() {
        Produto produto = new Produto("1", "teclado", 40.00, "teclado dell", 3);
        return produto;
    }

    @GetMapping("/produtos")
    public List<Produto> produtos() {
        //lista que contém todos os produtos
        List<Produto> produtos = new ArrayList<>();
        try{
            // Abre o arquivo produtos.csv
            FileReader reader = new FileReader("produtos.csv");
            //buffer recebe o arquivo lido --> lê o arquivo rápido
            BufferedReader bufferedReader = new BufferedReader(reader);

            //ler a primeira linha e ignora -- cabeçalho
            String linha = bufferedReader.readLine();

            //le o restante do arquivo, enquanto tiver dados a serem lidos
            while((linha = bufferedReader.readLine()) != null) {
                //divide as linhas em colunas  separadas por vírgula
                String coluna[] = linha.split(",");

                String id = coluna[0];
                String nome = coluna[1];
                double preco = Double.parseDouble(coluna[2]);
                String descricao = coluna[3];
                int estoque = Integer.parseInt(coluna[4]);

                Produto produto = new Produto(id, nome, preco, descricao, estoque);

                //adiciona produtos na lista
                produtos.add(produto);
            }

        } catch (IOException e){

        }
        return produtos;
    }
}