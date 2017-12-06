package ufjf.br.slytherin3.modelos;

import java.util.ArrayList;

/**
 * Created by thassya on 05/12/17.
 */

public class Tarefa {

    private String titulo;
    private String descricao;
    private Utils.Dificuldade dificuldade;
    private Utils.Estados estados;
    private ArrayList<Etiqueta> etiquetas;

    public Tarefa() {
    }

    public Tarefa(String titulo, String descricao, Utils.Dificuldade dificuldade, Utils.Estados estados) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dificuldade = dificuldade;
        this.estados = estados;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Utils.Dificuldade getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(Utils.Dificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }

    public Utils.Estados getEstados() {
        return estados;
    }

    public void setEstados(Utils.Estados estados) {
        this.estados = estados;
    }

    public ArrayList<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(ArrayList<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    @Override
    public String toString() {
        return "Tarefa: " + titulo;
    }
}
