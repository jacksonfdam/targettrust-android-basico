package br.com.targettrust.ttpetshop;

/**
 * Created by Jackson F. de A. Mafra on 14/04/2015.
 */
public class Pet {

    int id;
    String nome;
    String arquivo;
    String foto;
    String descricao;
    String criado_em;

    // constructors
    public Pet() {
    }

    public Pet(String nome, String arquivo) {
        this.nome = nome;
        this.arquivo = arquivo;
        this.foto = arquivo + ".jpg";
        this.descricao = arquivo + ".txt";
    }

    public Pet(int id, String nome, String arquivo) {
        this.id = id;
        this.nome = nome;
        this.arquivo = arquivo;
        this.foto = arquivo + ".jpg";
        this.descricao = arquivo + ".txt";
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
        this.foto = arquivo + ".jpg";
        this.descricao = arquivo + ".txt";
    }

    public void setCriadoEm(String criado_em){
        this.criado_em = criado_em;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getArquivo() {
        return this.arquivo;
    }

    public String getFoto() {
        return this.foto;
    }

    public String getDescricao() {
        return this.descricao;
    }
}