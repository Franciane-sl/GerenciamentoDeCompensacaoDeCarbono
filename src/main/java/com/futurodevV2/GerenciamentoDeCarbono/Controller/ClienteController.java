package com.futurodevV2.GerenciamentoDeCarbono.Controller;

import com.futurodevV2.GerenciamentoDeCarbono.Model.Dtos.RequestClienteDTO;
import com.futurodevV2.GerenciamentoDeCarbono.Model.Dtos.ResponseClienteDTO;
import com.futurodevV2.GerenciamentoDeCarbono.Model.Entity.Cliente;
import com.futurodevV2.GerenciamentoDeCarbono.Service.ClienteService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ResponseClienteDTO>> list(){
        List<ResponseClienteDTO> clientes = this.clienteService.findAllClientes().stream()
                .map(cliente -> modelMapper.map(cliente, ResponseClienteDTO.class)).collect(Collectors.toList());

        return clientes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseClienteDTO> getById(@PathVariable Long id){
        Cliente cliente = clienteService.findClienteById(id);
        ResponseClienteDTO clienteDTO =  modelMapper.map(cliente, ResponseClienteDTO.class);
        return ResponseEntity.ok(clienteDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseClienteDTO> create(@RequestBody @Valid RequestClienteDTO clienteDTO) throws Exception{
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        Cliente clienteCriado = clienteService.create(cliente);
        ResponseClienteDTO  clienteCriadoDTO = modelMapper.map(clienteCriado, ResponseClienteDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriadoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseClienteDTO> update(@PathVariable Long id, @RequestBody @Valid RequestClienteDTO clienteDTO) throws Exception{
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        Cliente clienteUpdate = clienteService.update(id, cliente);
        ResponseClienteDTO clienteUpdateDTO = modelMapper.map(clienteUpdate, ResponseClienteDTO.class);

        return ResponseEntity.ok(clienteUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        this.clienteService.delete(id);
        return ResponseEntity.ok("O cliente foi deletado com sucesso.");
    }
}
