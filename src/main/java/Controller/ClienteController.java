package Controller;

import Model.Dtos.RequestClienteDTO;
import Model.Dtos.ResponseClienteDTO;
import Model.Entity.Cliente;
import Service.ClienteService;
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

    @PostMapping
    public ResponseEntity<ResponseClienteDTO> create(@RequestBody @Valid RequestClienteDTO clienteDTO) throws Exception{
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        Cliente clienteCriado = clienteService.create(cliente);
        ResponseClienteDTO  clienteCriadoDTO = modelMapper.map(clienteCriado, ResponseClienteDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriadoDTO);
    }
}
