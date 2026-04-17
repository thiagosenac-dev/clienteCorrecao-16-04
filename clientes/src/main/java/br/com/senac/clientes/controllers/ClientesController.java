package br.com.senac.clientes.controllers;

import br.com.senac.clientes.dtos.ClientesRequestDto;
import br.com.senac.clientes.entidades.Clientes;
import br.com.senac.clientes.repositorios.ClientesRepositorio;
import br.com.senac.clientes.services.ClientesService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClientesController {
    private ClientesService clientesService;

    public ClientesController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Clientes>> listar(){
        return ResponseEntity.ok(clientesService.listar());
    }
    @PostMapping("/criar")
    public ResponseEntity<Clientes> criar(
            @RequestBody ClientesRequestDto cliente){
       try{
           return ResponseEntity
                   .ok(clientesService.criar(cliente));
       }catch (Exception e) {
           return ResponseEntity
                   .badRequest()
                   .body(null);
       }
    }

    @PutMapping("/atualizat/{id}")
    public ResponseEntity<Clientes> atualizar(
            @RequestBody ClientesRequestDto cliente,
            @PathVariable Long id){
        try{
            return ResponseEntity.ok(clientesService.atualizar(id, cliente));

        }catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }catch (Exception e){
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }


    @DeleteMapping("/deletar/{id}")
    public  ResponseEntity<Void> deletar (@PathVariable Long id){
        try{
            clientesService.deletar(id);
            return ResponseEntity.ok(null);
        }catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }catch (Exception e){
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }

    }
}
