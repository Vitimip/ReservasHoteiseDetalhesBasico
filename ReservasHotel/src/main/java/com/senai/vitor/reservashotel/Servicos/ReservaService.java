package com.senai.vitor.reservashotel.Servicos;

import com.senai.vitor.reservashotel.Entidades.Reserva;
import com.senai.vitor.reservashotel.Entidades.Status;
import com.senai.vitor.reservashotel.Repositorios.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository repository;


    public Reserva criar(Reserva r) {

        if (r.getNomeHospede() == null || r.getNomeHospede().length() < 3 || r.getNomeHospede().length() > 100)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome inválido");

        if (r.getEmailHospede() == null || !r.getEmailHospede().contains("@"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email inválido");

        if (r.getTelefoneHospede() != null && r.getTelefoneHospede().length() > 20)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Telefone inválido");

        if (r.getDataEntrada() == null || r.getDataSaida() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Datas obrigatórias");

        if (r.getDataEntrada().isBefore(LocalDate.now()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data inválida");

        if (!r.getDataSaida().isAfter(r.getDataEntrada()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Saída inválida");

        if (r.getObservacoes() != null && r.getObservacoes().length() > 500)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Observação muito longa");

        r.setStatus(Status.PENDENTE);
        r.setDataCriacao(LocalDateTime.now());
        r.setDataCheckIn(null);
        r.setDataCheckOut(null);

        return repository.save(r);
    }

    public Reserva confirmar(Long id){

        Reserva r = buscarPorId(id);

        if(r.getStatus() != Status.PENDENTE){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Reserva não pode ser confirmada");
        }

        r.setStatus(Status.CONFIRMADA);
        return repository.save(r);
    }

    public Reserva checkin(Long id){

        Reserva r = buscarPorId(id);

        if(r.getStatus() != Status.CONFIRMADA){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Check-in permitido apenas para reservas confirmadas");
        }

        if(LocalDate.now().isBefore(r.getDataEntrada())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Check-in não permitido antes da data de entrada prevista.");
        }

        r.setStatus(Status.EM_HOSPEDAGEM);
        r.setDataCheckIn(LocalDateTime.now());

        return repository.save(r);
    }

        public Reserva buscarPorId(Long id){
            return repository.findById(id)
                    .orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada"));
        }

        public List<Reserva> listar(){
        return repository.findAll();
        }

    public Reserva checkout(Long id){

        Reserva r = buscarPorId(id);

        if(r.getStatus() != Status.EM_HOSPEDAGEM){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Check-out permitido apenas para hóspedes em hospedagem");
        }

        r.setStatus(Status.CONCLUIDA);
        r.setDataCheckOut(LocalDateTime.now());

        return repository.save(r);
    }

    public Reserva cancelar(Long id){

        Reserva r = buscarPorId(id);

        if(r.getStatus() != Status.PENDENTE &&
                r.getStatus() != Status.CONFIRMADA){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Reserva não pode ser cancelada");
        }

        r.setStatus(Status.CANCELADA);

        return repository.save(r);
    }



    }

