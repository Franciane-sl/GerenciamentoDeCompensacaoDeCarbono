package com.futurodevV2.GerenciamentoDeCarbono.Service;

import com.futurodevV2.GerenciamentoDeCarbono.Model.Dtos.DashboardDTO;
import com.futurodevV2.GerenciamentoDeCarbono.Repository.ItensDaDeclaracaoRepository;
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
