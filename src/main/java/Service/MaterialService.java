package Service;

import Model.Entity.Material;
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
}
