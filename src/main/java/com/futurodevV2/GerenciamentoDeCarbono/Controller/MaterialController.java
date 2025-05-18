package com.futurodevV2.GerenciamentoDeCarbono.Controller;

import com.futurodevV2.GerenciamentoDeCarbono.Model.Dtos.RequestMaterialDTO;
import com.futurodevV2.GerenciamentoDeCarbono.Model.Dtos.ResponseMaterialDTO;
import com.futurodevV2.GerenciamentoDeCarbono.Model.Entity.Material;
import com.futurodevV2.GerenciamentoDeCarbono.Service.MaterialService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ResponseMaterialDTO>> list(){
        List<ResponseMaterialDTO> materiais = this.materialService.findAllMaterial().stream()
                .map(material -> modelMapper.map(material, ResponseMaterialDTO.class)).collect(Collectors.toList());

        return materiais.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(materiais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMaterialDTO> getById(@PathVariable Long id){
        Material material = materialService.findMaterialById(id);
        ResponseMaterialDTO materialDTO =  modelMapper.map(material, ResponseMaterialDTO.class);
        return ResponseEntity.ok(materialDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseMaterialDTO> create(@RequestBody @Valid RequestMaterialDTO materialDTO){
        Material material = modelMapper.map(materialDTO, Material.class);
        Material materialCriado = materialService.create(material);
        ResponseMaterialDTO  materialCriadoDTO = modelMapper.map(materialCriado, ResponseMaterialDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(materialCriadoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMaterialDTO> update(@PathVariable Long id, @RequestBody @Valid RequestMaterialDTO materialDTO){
        Material material = modelMapper.map(materialDTO, Material.class);
        Material materialUpdate = materialService.update(id, material);
        ResponseMaterialDTO materialUpdateDTO = modelMapper.map(materialUpdate, ResponseMaterialDTO.class);

        return ResponseEntity.ok(materialUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        this.materialService.delete(id);
        return ResponseEntity.ok("O material foi deletado com sucesso.");
    }
}

