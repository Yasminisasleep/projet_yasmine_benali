package com.epita.projet.projet_yasmine_benali.service;
import com.epita.projet.projet_yasmine_benali.dto.SimulationCreditDTO;
import com.epita.projet.projet_yasmine_benali.exception.ProxiBanqueException;
import org.springframework.stereotype.Service;
@Service
public class SimulationCreditService {
    private static final double TAUX_CONSOMMATION = 0.05;
    private static final double TAUX_IMMOBILIER = 0.025;
    public SimulationCreditDTO simulerCreditConsommation(double montant, int dureeEnMois) {
        return simulerCredit(montant, dureeEnMois, TAUX_CONSOMMATION, "CONSOMMATION");
    }
    public SimulationCreditDTO simulerCreditImmobilier(double montant, int dureeEnMois) {
        return simulerCredit(montant, dureeEnMois, TAUX_IMMOBILIER, "IMMOBILIER");
    }
    public SimulationCreditDTO simulerCredit(double montant, int dureeEnMois, double tauxAnnuel, String typeCredit) {
        if (montant <= 0) {
            throw new ProxiBanqueException("Le montant du credit doit etre positif");
        }
        if (dureeEnMois <= 0) {
            throw new ProxiBanqueException("La duree du credit doit etre positive");
        }
        SimulationCreditDTO simulation = new SimulationCreditDTO();
        simulation.setMontant(montant);
        simulation.setDureeEnMois(dureeEnMois);
        simulation.setTauxAnnuel(tauxAnnuel);
        simulation.setTypeCredit(typeCredit);
        double tauxMensuel = tauxAnnuel / 12;
        double mensualite;
        if (tauxMensuel == 0) {
            mensualite = montant / dureeEnMois;
        } else {
            mensualite = montant * tauxMensuel / (1 - Math.pow(1 + tauxMensuel, -dureeEnMois));
        }
        double coutTotal = mensualite * dureeEnMois;
        double coutInterets = coutTotal - montant;
        simulation.setMensualite(Math.round(mensualite * 100.0) / 100.0);
        simulation.setCoutTotal(Math.round(coutTotal * 100.0) / 100.0);
        simulation.setCoutInterets(Math.round(coutInterets * 100.0) / 100.0);
        return simulation;
    }
}
