package ufjf.br.slytherin3.modelos;

/**
 * Created by thassya on 05/12/17.
 */

public class Etiqueta {
    private int id;
    private String descricao;

    public Etiqueta() {
    }

    public Etiqueta(String descricao) {
        this.descricao = descricao;
    }

    public Etiqueta(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
