package Service;

import Model.Dtos.DashboardDTO;
import Repository.ItensDaDeclaracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private ItensDaDeclaracaoRepository itensDaDeclaracaoRepository;

    public List<DashboardDTO> getDashboard(){
        return itensDaDeclaracaoRepository.buscarTotaisDashboard();
    }
}
