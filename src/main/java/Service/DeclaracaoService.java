package Service;

import Model.Dtos.RequestDeclaracaoDTO;
import Model.Entity.Cliente;
import Model.Entity.Declaracao;
import Model.Entity.ItensDaDeclaracao;
import Model.Exceptions.BadRequestException;
import Model.Exceptions.ResourceNotFoundException;
import Repository.ClienteRepository;
import Repository.DeclaracaoRepository;
import Repository.ItensDaDeclaracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeclaracaoService {

    @Autowired
    DeclaracaoRepository declaracaoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ItensDaDeclaracaoRepository itensDaDeclaracaoRepository;

    public List<Declaracao> findAllDeclaracao(){
        return declaracaoRepository.findAll();
    }

    public Declaracao findDeclaracaoById (Long id){
       return declaracaoRepository.findById(id).orElseThrow(
               () -> new ResourceNotFoundException("A declaração não foi encontrada pelo id " + id)
       );
    }

    public Declaracao create(RequestDeclaracaoDTO dto){

        Cliente cliente = clienteRepository.findById(dto.getClienteId()).orElseThrow(
                () -> new ResourceNotFoundException("O cliente não foi encontrado pelo id: " + dto.getClienteId())
        );

        if (dto.getDataInicialDoPeriodo().isAfter(dto.getDataFinalDoPeriodo())) {
            throw new BadRequestException("A data inicial do periodo não deve ser maior que á data final.");
        }

        List<ItensDaDeclaracao> itensDaDeclaracao = itensDaDeclaracaoRepository
                .findByClienteEPeriodo(cliente.getId(), dto.getDataInicialDoPeriodo(), dto.getDataFinalDoPeriodo());

        double totalDeMateriais = itensDaDeclaracao.stream()
                .mapToDouble(ItensDaDeclaracao::getToneladasDeclaradas)
                .sum();

        double totalDeCompensacao = itensDaDeclaracao.stream()
                .mapToDouble(ItensDaDeclaracao::getToneladasCompensacao)
                .sum();

        Declaracao declaracao = new Declaracao();
        declaracao.setCliente(cliente);
        declaracao.setDataDaDeclaracao(LocalDate.now());
        declaracao.setDataInicialDoPeriodo(dto.getDataInicialDoPeriodo());
        declaracao.setDataFinalDoPeriodo(dto.getDataFinalDoPeriodo());
        declaracao.setTotalDeMateriais(totalDeMateriais);
        declaracao.setTotalDeCompensacao(totalDeCompensacao);

        return declaracaoRepository.save(declaracao);
    }
}
