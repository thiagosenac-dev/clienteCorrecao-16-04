package br.com.senac.clientes.repositorios;

import br.com.senac.clientes.entidades.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepositorio extends JpaRepository<Clientes, Long> {
}
