package com.epita.projet.projet_yasmine_benali.controller;
import com.epita.projet.projet_yasmine_benali.dto.SimulationCreditDTO;
import com.epita.projet.projet_yasmine_benali.service.SimulationCreditService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/simulations")
public class SimulationCreditController {
    private final SimulationCreditService simulationCreditService;
    public SimulationCreditController(SimulationCreditService simulationCreditService) {
        this.simulationCreditService = simulationCreditService;
    }
    @GetMapping("/credit-consommation")
    public SimulationCreditDTO simulerCreditConsommation(
            @RequestParam double montant,
            @RequestParam int duree) {
        return simulationCreditService.simulerCreditConsommation(montant, duree);
    }
    @GetMapping("/credit-immobilier")
    public SimulationCreditDTO simulerCreditImmobilier(
            @RequestParam double montant,
            @RequestParam int duree) {
        return simulationCreditService.simulerCreditImmobilier(montant, duree);
    }
    @PostMapping("/credit")
    public SimulationCreditDTO simulerCredit(@RequestBody SimulationCreditDTO simulationDTO) {
        return simulationCreditService.simulerCredit(
                simulationDTO.getMontant(),
                simulationDTO.getDureeEnMois(),
                simulationDTO.getTauxAnnuel(),
                simulationDTO.getTypeCredit()
        );
    }
}
