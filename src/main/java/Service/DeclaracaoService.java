package Service;

import Model.Dtos.RequestDeclaracaoDTO;
import Model.Dtos.RequestItensDaDeclaracaoDTO;
import Model.Entity.Cliente;
import Model.Entity.Declaracao;
import Model.Entity.ItensDaDeclaracao;
import Model.Entity.Material;
import Model.Exceptions.BadRequestException;
import Model.Exceptions.ResourceNotFoundException;
import Repository.ClienteRepository;
import Repository.DeclaracaoRepository;
import Repository.ItensDaDeclaracaoRepository;
import Repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeclaracaoService {

    @Autowired
    private DeclaracaoRepository declaracaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private ItensDaDeclaracaoRepository itensDaDeclaracaoRepository;

    public Declaracao create(RequestDeclaracaoDTO dto){

        Cliente cliente = clienteRepository.findById(dto.getClienteId()).orElseThrow(
                () -> new ResourceNotFoundException("O cliente não foi encontrado pelo id: " + dto.getClienteId())
        );

        if (dto.getDataInicialDoPeriodo().isAfter(dto.getDataFinalDoPeriodo())){
            throw new BadRequestException("A data inicial do periodo deve ser menor que a data final.");
        }

        Declaracao declaracao = new Declaracao();
        declaracao.setCliente(cliente);
        declaracao.setDataDaDeclaracao(LocalDate.now());
        declaracao.setDataInicialDoPeriodo(dto.getDataInicialDoPeriodo());
        declaracao.setDataFinalDoPeriodo(dto.getDataFinalDoPeriodo());

        List<ItensDaDeclaracao> itensSalvos = new ArrayList<>();
        double totalDeMateriais = 0;
        double totalDeCompensacao = 0;

        for(RequestItensDaDeclaracaoDTO itemDTO : dto.getItens()){

                if (itemDTO.getToneladasDeclaradas() <= 0){
                    throw new BadRequestException("As toneladas declaradas devem ser maiores que zero.");
                }

                Material material = materialRepository.findById(itemDTO.getMaterialId()).orElseThrow(
                        () -> new ResourceNotFoundException("Material não encontrado com ID: " + itemDTO.getMaterialId()));

                double percentual = material.getPercentualDeCompensacao();
                double toneladasCompensacao = itemDTO.getToneladasDeclaradas() * percentual / 100;

                ItensDaDeclaracao item = new ItensDaDeclaracao();
                item.setDeclaracao(declaracao);
                item.setMaterial(material);
                item.setPercentualDeCompensacao(percentual);
                item.setToneladasDeclaradas(itemDTO.getToneladasDeclaradas());
                item.setToneladasCompensacao(toneladasCompensacao);

                totalDeMateriais += itemDTO.getToneladasDeclaradas();
                totalDeCompensacao += toneladasCompensacao;

                itensSalvos.add(item);
        }

        declaracao.setTotalDeMateriais(totalDeMateriais);
        declaracao.setTotalDeCompensacao(totalDeCompensacao);
        declaracao.setItensDasDeclaracoes(itensSalvos);

        Declaracao declaracaoSalva = declaracaoRepository.save(declaracao);
        itensDaDeclaracaoRepository.saveAll(itensSalvos);

        return declaracaoSalva;
    }

}
