package br.com.senac.clientes.services;
import br.com.senac.clientes.dtos.ClientesRequestDto;
import br.com.senac.clientes.entidades.Clientes;
import br.com.senac.clientes.repositorios.ClientesRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesService {
    private ClientesRepositorio clientesRepositorio;

    public ClientesService(ClientesRepositorio clientesRepositorio) {
        this.clientesRepositorio = clientesRepositorio;
    }

    public List<Clientes> listar() {
        return clientesRepositorio.findAll();
    }

    public Clientes criar(ClientesRequestDto cliente) {
        Clientes clientePersist = this.clientesResquestDtoParaClientes(cliente);

        return clientesRepositorio.save(clientePersist);
    }

    public Clientes atualizar(Long id, ClientesRequestDto cliente){
        if (clientesRepositorio.existsById(id)){
        Clientes clientesPersist = this.clientesResquestDtoParaClientes(cliente);
        clientesPersist.setId(id);

        return clientesRepositorio.save(clientesPersist);
        }
        throw new RuntimeException("Cliente não encontrado!");
    }

    public void deletar(Long id){
       if (clientesRepositorio.existsById(id)){
           clientesRepositorio.deleteById(id);
       }
       throw new RuntimeException("Cliente não encontrado!");
    }

    private Clientes clientesResquestDtoParaClientes(ClientesRequestDto entrada){
        Clientes saida = new Clientes();
        saida.setNome(entrada.getNome());
        saida.setDoucmento(entrada.getDocumento());
        saida.setIdade(entrada.getIdade());
        saida.setEmail(entrada.getEmail());

        return saida;
    }



}
