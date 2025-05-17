package com.futurodevV2.GerenciamentoDeCarbono.Service;

import com.futurodevV2.GerenciamentoDeCarbono.Model.Entity.Cliente;
import com.futurodevV2.GerenciamentoDeCarbono.Model.Exceptions.ResourceNotFoundException;
import com.futurodevV2.GerenciamentoDeCarbono.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

   public List<Cliente> findAllClientes(){
       return clienteRepository.findAll();
   }

   public Cliente findClienteById(Long id){
       return clienteRepository.findById(id).orElseThrow(
               () -> new ResourceNotFoundException("O cliente não foi encontrado pelo id " + id)
       );
   }

   public Cliente create(Cliente cliente){
       return clienteRepository.save(cliente);
   }

   public Cliente update(Long id,Cliente clienteUpdate){
       Cliente clienteExistente = findClienteById(id);
       clienteExistente.setNome(clienteUpdate.getNome());
       clienteExistente.setAtividadeEconomica(clienteUpdate.getAtividadeEconomica());
       clienteExistente.setResponsavel(clienteUpdate.getResponsavel());
       return clienteRepository.save(clienteExistente);
   }

   public void delete(Long id){
       Cliente cliente = findClienteById(id);
       clienteRepository.delete(cliente);
   }
}
