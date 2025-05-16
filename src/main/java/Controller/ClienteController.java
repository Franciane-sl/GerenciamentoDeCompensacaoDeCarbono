package Controller;

import Model.Dtos.ResponseClienteDTO;
import Service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
