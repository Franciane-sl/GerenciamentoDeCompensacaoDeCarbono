package com.futurodevV2.GerenciamentoDeCarbono.Controller;

import com.futurodevV2.GerenciamentoDeCarbono.Model.Dtos.RequestItensDaDeclaracaoDTO;
import com.futurodevV2.GerenciamentoDeCarbono.Model.Dtos.ResponseItensDaDeclaracaoDTO;
import com.futurodevV2.GerenciamentoDeCarbono.Model.Entity.ItensDaDeclaracao;
import com.futurodevV2.GerenciamentoDeCarbono.Service.ItensDaDeclaracaoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/itensDasDeclaracoes")
public class ItensDaDeclaracaoController {

    @Autowired
    private ItensDaDeclaracaoService itensDaDeclaracaoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ResponseItensDaDeclaracaoDTO>> list(){
        List<ResponseItensDaDeclaracaoDTO> itensDasDeclaracoes = this.itensDaDeclaracaoService.findAllItensDaDeclaracao().stream()
                .map(itensDaDeclaracao -> modelMapper.map(itensDaDeclaracao, ResponseItensDaDeclaracaoDTO.class)).collect(Collectors.toList());

        return itensDasDeclaracoes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(itensDasDeclaracoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseItensDaDeclaracaoDTO> getById(@PathVariable Long id){
        ItensDaDeclaracao itensDaDeclaracao = itensDaDeclaracaoService.findItensDaDeclaracaoById(id);
        ResponseItensDaDeclaracaoDTO itensDTO =  modelMapper.map(itensDaDeclaracao, ResponseItensDaDeclaracaoDTO.class);
        return ResponseEntity.ok(itensDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseItensDaDeclaracaoDTO> create(@RequestBody @Valid RequestItensDaDeclaracaoDTO itensDaDeclaracaoDTO){
        ItensDaDeclaracao itensDaDeclaracao = modelMapper.map(itensDaDeclaracaoDTO, ItensDaDeclaracao.class);
        ItensDaDeclaracao itemCriado = itensDaDeclaracaoService.create(itensDaDeclaracao);
        ResponseItensDaDeclaracaoDTO itemCriadoDTO = modelMapper.map(itemCriado, ResponseItensDaDeclaracaoDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(itemCriadoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        this.itensDaDeclaracaoService.delete(id);
        return ResponseEntity.ok("O item foi deletado com sucesso.");
    }
}
