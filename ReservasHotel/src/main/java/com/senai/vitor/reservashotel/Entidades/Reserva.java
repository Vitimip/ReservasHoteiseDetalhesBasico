package com.senai.vitor.reservashotel.Entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TB_TABLE")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nomeHospede;

    @Column(nullable = false)
    private String emailHospede;

    @Column(length = 20)
    private String telefoneHospede;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate dataEntrada;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate dataSaida;

    @Column
    private LocalDateTime dataCheckIn;

    @Column
    private LocalDateTime dataCheckOut;

    @Column
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private TipoQuarto tipoQuarto;

    @Column(nullable = false)
    private Status status;

    @Column(length = 500)
    private String observacoes;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "detalhes_id", unique = true)
    @JsonManagedReference
    private DetalhesEstadia detalhes;

    public Reserva() {
    }

    public Reserva(Long id, String nomeHospede, String emailHospede, String telefoneHospede, LocalDate dataEntrada, LocalDate dataSaida, LocalDateTime dataCheckIn, LocalDateTime dataCheckOut, LocalDateTime dataCriacao, TipoQuarto tipoQuarto, Status status, String observacoes, DetalhesEstadia detalhes) {
        this.id = id;
        this.nomeHospede = nomeHospede;
        this.emailHospede = emailHospede;
        this.telefoneHospede = telefoneHospede;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.dataCriacao = dataCriacao;
        this.tipoQuarto = tipoQuarto;
        this.status = status;
        this.observacoes = observacoes;
        this.detalhes = detalhes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeHospede() {
        return nomeHospede;
    }

    public void setNomeHospede(String nomeHospede) {
        this.nomeHospede = nomeHospede;
    }

    public String getEmailHospede() {
        return emailHospede;
    }

    public void setEmailHospede(String emailHospede) {
        this.emailHospede = emailHospede;
    }

    public String getTelefoneHospede() {
        return telefoneHospede;
    }

    public void setTelefoneHospede(String telefoneHospede) {
        this.telefoneHospede = telefoneHospede;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public LocalDateTime getDataCheckIn() {
        return dataCheckIn;
    }

    public void setDataCheckIn(LocalDateTime dataCheckIn) {
        this.dataCheckIn = dataCheckIn;
    }

    public LocalDateTime getDataCheckOut() {
        return dataCheckOut;
    }

    public void setDataCheckOut(LocalDateTime dataCheckOut) {
        this.dataCheckOut = dataCheckOut;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public TipoQuarto getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(TipoQuarto tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public DetalhesEstadia getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(DetalhesEstadia detalhes) {
        this.detalhes = detalhes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(id, reserva.id) && Objects.equals(nomeHospede, reserva.nomeHospede) && Objects.equals(emailHospede, reserva.emailHospede) && Objects.equals(telefoneHospede, reserva.telefoneHospede) && Objects.equals(dataEntrada, reserva.dataEntrada) && Objects.equals(dataSaida, reserva.dataSaida) && Objects.equals(dataCheckIn, reserva.dataCheckIn) && Objects.equals(dataCheckOut, reserva.dataCheckOut) && Objects.equals(dataCriacao, reserva.dataCriacao) && tipoQuarto == reserva.tipoQuarto && status == reserva.status && Objects.equals(observacoes, reserva.observacoes) && Objects.equals(detalhes, reserva.detalhes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeHospede, emailHospede, telefoneHospede, dataEntrada, dataSaida, dataCheckIn, dataCheckOut, dataCriacao, tipoQuarto, status, observacoes, detalhes);
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", nomeHospede='" + nomeHospede + '\'' +
                ", emailHospede='" + emailHospede + '\'' +
                ", telefoneHospede='" + telefoneHospede + '\'' +
                ", dataEntrada=" + dataEntrada +
                ", dataSaida=" + dataSaida +
                ", dataCheckIn=" + dataCheckIn +
                ", dataCheckOut=" + dataCheckOut +
                ", dataCriacao=" + dataCriacao +
                ", tipoQuarto=" + tipoQuarto +
                ", status=" + status +
                ", observacoes='" + observacoes + '\'' +
                ", detalhes=" + detalhes +
                '}';
    }
}