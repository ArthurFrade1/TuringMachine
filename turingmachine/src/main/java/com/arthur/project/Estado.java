package com.arthur.project;
import java.util.ArrayList;
public class Estado {
    public ArrayList<Transicao> transicoes= new ArrayList<>();
    private String nome;
    public Boolean isInicial=false;
    public Boolean isFinal=false;
    
    public Estado(String nome){
        this.nome=nome;
        // this.Inicial=Inicial;
        // this.Final=Final;
    } 

    public void adicioanarTransição(Transicao transicao){
        transicoes.add(transicao);
    }

    public String getNome(){
        return nome;
    }

    public void setInicial(){
        isInicial=true;
    }

    public void setFinal(){
        isFinal=true;
    }

    public boolean getIsInicial(){
        return isInicial;
    }

    public boolean getIsFinal(){
        return isFinal;
    }

    public ArrayList<Transicao> getTransicoes(){
        return transicoes;
    }

    
}
