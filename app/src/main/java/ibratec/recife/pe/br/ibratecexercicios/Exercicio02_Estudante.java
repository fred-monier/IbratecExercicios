package ibratec.recife.pe.br.ibratecexercicios;

import java.io.Serializable;

/**
 * Created by Frederico on 12/09/2017.
 */

public class Exercicio02_Estudante implements Serializable {

    private String nome;
    private String telefone;
    private String endereco;
    private String site;
    private String nota;

    public Exercicio02_Estudante() {
    }

    public Exercicio02_Estudante(String nome, String telefone, String endereco, String site, String nota) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.site = site;
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String toString() {
        return this.nome;
    }
}
