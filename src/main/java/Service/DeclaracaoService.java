package Service;

import Model.Entity.Declaracao;
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
}
