package Service;

import Model.Entity.ItensDaDeclaracao;
import Model.Exceptions.ResourceNotFoundException;
import Repository.ItensDaDeclaracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItensDaDeclaracaoService {

    @Autowired
     private ItensDaDeclaracaoRepository itensDaDeclaracaoRepository;

    public List<ItensDaDeclaracao> findAllItensDaDeclaracao(){
        return itensDaDeclaracaoRepository.findAll();
    }

    public ItensDaDeclaracao findItensDaDeclaracaoById(Long id){
        return itensDaDeclaracaoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("O item não foi encontrado pelo id. " + id)
        );
    }

    public ItensDaDeclaracao create(ItensDaDeclaracao itensDaDeclaracao){
        return itensDaDeclaracaoRepository.save(itensDaDeclaracao);
    }

    public void delete(Long id){
        ItensDaDeclaracao itensDaDeclaracao = findItensDaDeclaracaoById(id);
        itensDaDeclaracaoRepository.delete(itensDaDeclaracao);
    }
}
