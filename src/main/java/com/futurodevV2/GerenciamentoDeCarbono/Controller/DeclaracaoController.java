package com.futurodevV2.GerenciamentoDeCarbono.Controller;

import com.futurodevV2.GerenciamentoDeCarbono.Model.Dtos.RequestDeclaracaoDTO;
import com.futurodevV2.GerenciamentoDeCarbono.Model.Dtos.ResponseDeclaracaoDTO;
import com.futurodevV2.GerenciamentoDeCarbono.Model.Entity.Declaracao;
import com.futurodevV2.GerenciamentoDeCarbono.Service.DeclaracaoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/declaracao")
public class DeclaracaoController {

    @Autowired
    DeclaracaoService declaracaoService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ResponseDeclaracaoDTO>> list(){
        List<ResponseDeclaracaoDTO> declaracoes = this.declaracaoService.findAllDeclaracao().stream()
                .map(declaracao -> modelMapper.map(declaracao, ResponseDeclaracaoDTO.class)).collect(Collectors.toList());

        return declaracoes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(declaracoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDeclaracaoDTO> getById(@PathVariable Long id){
        Declaracao declaracao = declaracaoService.findDeclaracaoById(id);
        ResponseDeclaracaoDTO declaracaoDTO =  modelMapper.map(declaracao, ResponseDeclaracaoDTO.class);
        return ResponseEntity.ok(declaracaoDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseDeclaracaoDTO> create(@RequestBody @Valid RequestDeclaracaoDTO declaracaoDTO) throws Exception{
        Declaracao declaracao = modelMapper.map(declaracaoDTO, Declaracao.class);
        Declaracao declaracaoCriada = declaracaoService.create(declaracaoDTO);
        ResponseDeclaracaoDTO declaracaoCriadaDTO = modelMapper.map(declaracaoCriada, ResponseDeclaracaoDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(declaracaoCriadaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        this.declaracaoService.delete(id);
        return ResponseEntity.ok("A declaração foi deletada com sucesso.");
    }
}

