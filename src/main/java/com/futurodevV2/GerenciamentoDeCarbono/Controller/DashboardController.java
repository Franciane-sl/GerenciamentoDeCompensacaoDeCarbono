package com.futurodevV2.GerenciamentoDeCarbono.Controller;

import com.futurodevV2.GerenciamentoDeCarbono.Model.Dtos.DashboardDTO;
import com.futurodevV2.GerenciamentoDeCarbono.Service.DashboardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboards")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<DashboardDTO>> list(){
        List<DashboardDTO> dashboards = this.dashboardService.getDashboard();

        return dashboards.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(dashboards);
    }
}
