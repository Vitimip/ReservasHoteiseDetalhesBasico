package com.senai.vitor.reservashotel.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "detalhes_estadia")
public class DetalhesEstadia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String numeroQuarto;

    private Integer andar;

    private boolean possuiFrigobar;
    private boolean possuiVaranda;
    private boolean acessibilidade;

    private String observacoesQuarto;

    @OneToOne(mappedBy = "detalhes")
    @JsonBackReference
    private Reserva reserva;

    public DetalhesEstadia() {}

    // GETTERS E SETTERS

    public Long getId() { return id; }

    public String getNumeroQuarto() { return numeroQuarto; }
    public void setNumeroQuarto(String numeroQuarto) { this.numeroQuarto = numeroQuarto; }

    public Integer getAndar() { return andar; }
    public void setAndar(Integer andar) { this.andar = andar; }

    public boolean isPossuiFrigobar() { return possuiFrigobar; }
    public void setPossuiFrigobar(boolean possuiFrigobar) { this.possuiFrigobar = possuiFrigobar; }

    public boolean isPossuiVaranda() { return possuiVaranda; }
    public void setPossuiVaranda(boolean possuiVaranda) { this.possuiVaranda = possuiVaranda; }

    public boolean isAcessibilidade() { return acessibilidade; }
    public void setAcessibilidade(boolean acessibilidade) { this.acessibilidade = acessibilidade; }

    public String getObservacoesQuarto() { return observacoesQuarto; }
    public void setObservacoesQuarto(String observacoesQuarto) { this.observacoesQuarto = observacoesQuarto; }

    public Reserva getReserva() { return reserva; }
    public void setReserva(Reserva reserva) { this.reserva = reserva; }
}