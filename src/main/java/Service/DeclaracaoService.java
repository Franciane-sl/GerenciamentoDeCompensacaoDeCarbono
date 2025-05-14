package Service;

import Model.Entity.Declaracao;
import Model.Exceptions.ResourceNotFoundException;
import Repository.DeclaracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeclaracaoService {

    @Autowired
    DeclaracaoRepository declaracaoRepository;

    public List<Declaracao> findAllDeclaracao(){
        return declaracaoRepository.findAll();
    }

    public Declaracao findDeclaracaoById (Long id){
       return declaracaoRepository.findById(id).orElseThrow(
               () -> new ResourceNotFoundException("A declaração não foi encontrada pelo id " + id)
       );
    }
}
