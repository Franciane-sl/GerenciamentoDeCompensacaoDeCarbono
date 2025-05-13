package Service;

import Model.Entity.Cliente;
import Repository.ClienteRepository;
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
}
