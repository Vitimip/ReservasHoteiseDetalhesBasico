package com.senai.vitor.reservashotel.Controladores;

import com.senai.vitor.reservashotel.Entidades.Reserva;
import com.senai.vitor.reservashotel.Servicos.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @PostMapping
    public ResponseEntity<Reserva> criar(@RequestBody Reserva reserva){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.criar(reserva));
    }

    @GetMapping
    public List<Reserva> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public Reserva buscar(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PatchMapping("/{id}/confirmar")
    public Reserva confirmar(@PathVariable Long id){
        return service.confirmar(id);
    }

    @PatchMapping("/{id}/checkin")
    public Reserva checkin(@PathVariable Long id){
        return service.checkin(id);
    }

    @PatchMapping("/{id}/checkout")
    public Reserva checkout(@PathVariable Long id){
        return service.checkout(id);
    }

    @PatchMapping("/{id}/cancelar")
    public Reserva cancelar(@PathVariable Long id){
        return service.cancelar(id);
    }
}
