package fr.diginamic.recensement.services;

import java.util.List;
import java.lang.*;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.services.SaisiException;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService{

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws SaisiException {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();
		
		

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();
		
		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();
		
		
		int min =0;
		try {
			min = Integer.parseInt(saisieMin) * 1000; 
		} catch (NumberFormatException e) {
			throw new SaisiException("Attention min devrait etre un entier");
		}
				
		int max = Integer.parseInt(saisieMax) * 1000;
		
		if(!(min>0 || max>0)) {
			throw new SaisiException("Attention min ou max devrait etre superieur a 0");
		}
		
		
		
		
		if(min>max) {
			throw new SaisiException("Attention max devrait etre superieur au min");
		}
		int cpt =0;
		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
				}
				cpt++;
			}
		}
		if(cpt == 0) {
			throw new SaisiException("un code département inconnu");
		}
	}

}
