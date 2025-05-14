package Service;

import Model.Entity.Material;
import Model.Exceptions.ResourceNotFoundException;
import Repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    @Autowired
    MaterialRepository materialRepository;

    public List<Material> findAllMaterial(){
        return materialRepository.findAll();
    }

    public Material findMaterialById(Long id) {
        return materialRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("O material não foi encontrado pelo id " + id)
        );
    }

    public Material create(Material material){
        return materialRepository.save(material);
    }

    public Material update(Long id, Material materialUpdate){
        Material materialExistente = findMaterialById(id);
        materialExistente.setNome(materialUpdate.getNome());
        materialExistente.setPercentualDeCompensacao(materialUpdate.getPercentualDeCompensacao());
        return materialRepository.save(materialExistente);
    }

    public void delete(Long id){
        Material material = findMaterialById(id);
        materialRepository.delete(material);
    }
}
