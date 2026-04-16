package br.com.senac.clientes.controllers;

import br.com.senac.clientes.dtos.ClientesRequestDto;
import br.com.senac.clientes.entidades.Clientes;
import br.com.senac.clientes.repositorios.ClientesRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClientesController {
    private ClientesRepositorio clientesRepositorio;

    public ClientesController(ClientesRepositorio clientesRepositorio) {
        this.clientesRepositorio = clientesRepositorio;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Clientes>> listar(){
        return ResponseEntity.ok(clientesRepositorio.findAll());
    }
    @PostMapping("/criar")
    public ResponseEntity<Clientes> criar(@RequestBody ClientesRequestDto cliente){
       Clientes clientesPersist = new Clientes();
       clientesPersist.setNome(cliente.getNome());
       clientesPersist.setEmail(cliente.getEmail());
       clientesPersist.setDoucmento(cliente.getDocumento());
       clientesPersist.setIdade(cliente.getIdade());

       Clientes retorno = clientesRepositorio.save(clientesPersist);
       return ResponseEntity.ok(retorno);
    }

    @PutMapping("/atualizat/{id}")
    public ResponseEntity<Clientes> atualizar(
            @RequestBody ClientesRequestDto cliente,
            @PathVariable Long id){
        if(clientesRepositorio.existsById(id)){
        Clientes clientesPersist = new Clientes();
        clientesPersist.setNome(cliente.getNome());
        clientesPersist.setEmail(cliente.getEmail());
        clientesPersist.setDoucmento(cliente.getDocumento());
        clientesPersist.setIdade(cliente.getIdade());
        clientesPersist.setId(id);

        Clientes retorno = clientesRepositorio.save(clientesPersist);

        return ResponseEntity.ok(retorno);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/deletar/{id}")
    public  ResponseEntity<Void> deletar (@PathVariable Long id){
        if(clientesRepositorio.existsById(id)){
            clientesRepositorio.deleteById(id);
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.badRequest().body(null);
    }




}
