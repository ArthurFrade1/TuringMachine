package com.arthur.project;

import java.util.ArrayList;

public class Transicao {
    public String le;
    public String escreve;
    public String nomeEstado;
    public String direcao;

    public Transicao(String le, String escreve, String nomeEstado, String direcao){
        this.le=le;
        this.escreve=escreve;
        this.nomeEstado=nomeEstado;
        this.direcao=direcao;

    }

    public String getLe(){
        return le;
    }

    public String getDirecao(){
        return direcao;
    }

    public String getEscreve(){
        return escreve;
    }

    public String getNomeEstado(){
        return nomeEstado;
    }

    
}
