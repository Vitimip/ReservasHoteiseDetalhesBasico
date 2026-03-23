package com.senai.vitor.reservashotel.Repositorios;

import com.senai.vitor.reservashotel.Entidades.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("Select r from Reserva r")
    List<Reserva> listarTodasReservas();

    @Query(value = "UPDATE TB_RESERVA WHERE ID IS NOT NULL", nativeQuery = true)
    Reserva criarReserva();

    @Query(value = "SELECT * FROM TB_RESERVA WHERE ID IS NOT NULL", nativeQuery = true)
    List<Reserva> buscarPorId(Long id);

    @Query(value = "UPDATE TB_RESERVA WHERE TIPOQUARTO AND STATUS IS NOT NULL", nativeQuery = true)
    Reserva editarDadosEditaveis();

    @Query(value = "DELETE * FROM TB_RESERVA WHERE ID IS NOT NULL", nativeQuery = true)
    Reserva deletarReservaPorId(Long id);

    @Query(value = "SELECT * FROM TB_RESERVA WHERE STATUS IS NOT NULL", nativeQuery = true)
    List<Reserva> buscarReservasPorStatus(String status);

    @Query(value = "SELECT * FROM TB_RESERVA WHERE TIPOQUARTO IS NOT NULL", nativeQuery = true)
    List<Reserva> buscarReservasPorTipoQuarto(String tipoQuarto);

    @Query(value = "SELECT * FROM TB_RESERVA WHERE DATACHECKIN IS LocalDate.now()", nativeQuery = true)
    List<Reserva> buscarReservasPorDataCeckin(LocalDateTime dataCheckIn);

    @Query(value = "SELECT * FROM reserva r WHERE r.data_entrada BETWEEN :hoje AND :dataLimite",
            nativeQuery = true)
    List<Reserva> buscarProximasReservasNative(
            @Param("hoje") LocalDate hoje,
            @Param("dataLimite") LocalDate dataLimite
    );

    @Query(value = "SELECT * FROM TB_RESERVA WHERE NOME LIKE EMAIL IS NOT NULL", nativeQuery = true)
    List<Reserva> buscarReservasPorNomeEmail(String nome, String email);

    @Query(value = "SELECT * FROM TB_RESERVA WHERE STATUS IS :EM_HOSPEDAGEM", nativeQuery = true)
    List<Reserva> buscarReservasPorStatusEmHospedagem(String status);

    @Query(value = "SELECT * FROM TB_RESERVA WHERE STATUS IS :PENDENDE AND :CONFIRMADA", nativeQuery = true)
    List<Reserva> buscarReservasPorStatusPendenteeConfirmad(String status);

    @Query(value = "SELECT * FROM TB_RESERVA WHERE STATUS IS :CONFIRMADA AND :EM_HOSPEDAGEM AND DATACHECKIN IS NOT NULL", nativeQuery = true)
    List<Reserva> buscarReservasPorStatusConfirmadaeEmHospedagem(String status);

    @Query(value = "SELECT * FROM TB_RESERVA WHERE STATUS IS :EM_HOSPEDAGEM AND :CONCLUIDA AND DATACHECKOUT IS NOT NULL", nativeQuery = true)
    List<Reserva> buscarReservasPorStatusEmHospedagemeConcluida(String status);

    @Query(value = "SELECT * FROM TB_RESERVA WHERE STATUS IS :PENDENTE OR :CONFIRMADA AND :CANCELADA",nativeQuery = true)
    List<Reserva> buscarReservasPorStatusPendenteOuConfirmada(String status);

    @Query(value = "UPDATE TIPOQUARTO FROM TB_RESERVA WHERE TIPOQUARTO IS NOT NULL",nativeQuery = true)
    Reserva editarReservaPorTipoQuarto();

}
