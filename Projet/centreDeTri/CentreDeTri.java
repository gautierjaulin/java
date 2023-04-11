/**
*
* Programme Java pour implémenter un centre de tri
*
* @file 	CentreDeTri.java
* @author  	Groupe 5
* @version 	1.0 Premier jet
* @date   	3 avril 2023
* @brief	Projet Java
* 
*/

package centreDeTri;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @class 	CentreDeTri
 * @author  Group 5
 * @version 1.0
 * @date 	3 avril 2023
 */

public class CentreDeTri {
	private String nom; //Nom du centre de tri
	private String adresse; //Adresse du centre de tri
	private static Menage Menages; //Attribut de la classe Menage

	/**
	 * @brief	Accesseur de l'attribut Nom
	 * @return	nom du centre de tri
	 */
	
	public String getNom() {
		return nom;
	}
	
	/**
	 * @brief	Mutateur de l'attribut Nom
	 */
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * @brief	Accesseur de l'attribut Adresse
	 * @return	adresse du centre de tri
	 */
	
	public String getAdresse() {
		return adresse;
	}
	
	/**
	 * @brief	Mutateur de l'attribut Adresse
	 */
	
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	/**
	 * @brief	Accesseur de l'attribut menages
	 * @return	
	 */
	
	public static Menage getMenages() {
		return Menages;
	}
	
	/**
	 * @brief	Mutateur de l'attribut menages
	 */

	public static void setMenages(Menage menages) {
		Menages = menages;
	}

	/**
	 * @brief	Méthode pour définir un centre de tri
	 */
	
    public CentreDeTri(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }
    
	/**
	 * @brief	Méthode pour ajouter une poubelle
	 * @param 	poubelle = Constructeur de Poubelle
	 * @param 	listePoubelles = Liste des poubelles
	 */

    public void ajouterPoubelle(Poubelle poubelle, List<String[]> listePoubelles) {
        String[] poubelleArray = new String[7];
        poubelleArray[0] = String.valueOf(poubelle.getIdPoubelle());
        poubelleArray[1] = poubelle.getType();
        poubelleArray[2] = poubelle.getEmplacement();
        poubelleArray[3] = String.valueOf(poubelle.getCapaciteMax());
        poubelleArray[4] = String.valueOf(poubelle.getCapacite());
        
        // Récupération de la dernière ligne de listePoubelles
        String[] derniereLigne = listePoubelles.get(listePoubelles.size()-1);

        // Création d'un nouveau tableau pour la ligne modifiée
        String[] nouvelleLigne = new String[derniereLigne.length + poubelleArray.length - 2];

        //Copie des deux premiers éléments de la dernière ligne
        nouvelleLigne[0] = derniereLigne[0];
        nouvelleLigne[1] = derniereLigne[1];

        // Ajout des éléments de poubelleArray à la suite
        for (int i = 2; i < nouvelleLigne.length; i++) {
            nouvelleLigne[i] = poubelleArray[i-2];
        }

        // Remplacement de la dernière ligne dans listePoubelles
        listePoubelles.set(listePoubelles.size()-1, nouvelleLigne);

        System.out.println("La poubelle " + poubelle.getIdPoubelle() + " a été ajoutée.");
    }
    
	/**
	 * @brief	Méthode pour retirer une poubelle
	 * @param	idPoubelle = Identifiant d'une poubelle
	 * @param	listePoubelles = Liste des poubelles
	 */

    public void retirerPoubelle(Integer idPoubelle, List<String[]> listePoubelles) {
        // Parcours de la liste des poubelles pour trouver celle à supprimer
        for (int i = 0; i < listePoubelles.size(); i++) {
            String[] poubelleArray = listePoubelles.get(i);
            if (poubelleArray[2].equals(String.valueOf(idPoubelle))) {
                // Suppression de la poubelle de la liste
                listePoubelles.remove(i);

                // Mise à jour de la liste des poubelles dans le fichier
                Poubelle.sauvegarderListePoubelles(listePoubelles);

                // Affichage d'un message
                System.out.println("La poubelle " + idPoubelle + " a été supprimée.");
                return;
            }
        }

        // Si la poubelle n'a pas été trouvée, affichage d'un message d'erreur
        System.out.println("La poubelle " + idPoubelle + " n'a pas été trouvée.");
    }
    
	/**
	 * @brief	Méthode pour collecter une poubelle pleine
	 * @param	listePoubelles = Liste des poubelles
	 * @param	poubelle = Constructeur de poubelle
	 * @return 	Le nombre de collectes effectuées
	 */

    public Integer collecterDechets(List<String[]> listePoubelles, Poubelle poubelle, Integer nbCollectes) {    	
    	// Mise à jour de la quantité de déchets dans la liste des poubelles
        for (String[] p : listePoubelles) {
            // Si la poubelle est pleine, on la vide
            if (poubelle.envoyerMessage(Integer.parseInt(p[2]))) {
            	p[6] = "0.00";
            	p[4] = this.adresse;
            	nbCollectes++; 
            	
				// Si la poubelle n'a pas été trouvée, affichage d'un message d'erreur
		        System.out.println("La poubelle " + p[2] + " a été vidée.");
            }
        }
        
        //On retourne le nombre de collectes pour incrémenter nos statistiques
        return(nbCollectes);
    }
    
	/**
	 * @brief	Méthode pour placer une poubelle
	 * @param	listePoubelles = liste des poubelles
	 * @param	adresse = Adresse de la poubelle à placer
	 */

    public void placerPoubelle(List<String[]> listePoubelles, String adresseCentre, String adressePoubelle) {
        for(String[] p : listePoubelles) {
        	if(p[4].contentEquals(adresseCentre)) {
        		switch (Integer.parseInt(p[2])){
        			case 1 : 
        				p[4] = adressePoubelle;
        				
        		        //Affichage d'un message
        		        System.out.println("La poubelle " + p[2] + " a été placée.");
        				break;
        			case 2 :
        				p[4] = adressePoubelle;
        				
        		        //Affichage d'un message
        		        System.out.println("La poubelle " + p[2] + " a été placée.");
        				break;
        			case 3:
        				p[4] = adressePoubelle;
        				
        		        //Affichage d'un message
        		        System.out.println("La poubelle " + p[2] + " a été placée.");
        				break;
        			case 4 :
        				p[4] = adressePoubelle;
        				
        		        //Affichage d'un message
        		        System.out.println("La poubelle " + p[2] + " a été placée.");
        				break;
        			case 5:
        				p[4] = adressePoubelle;
        				
        		        //Affichage d'un message
        		        System.out.println("La poubelle " + p[2] + " a été placée.");
        				break;
        			case 6 : 
        				p[4] = adressePoubelle;
        				
        		        //Affichage d'un message
        		        System.out.println("La poubelle " + p[2] + " a été placée.");
        				break;
        			case 7:
        				p[4] = adressePoubelle;
        				
        		        //Affichage d'un message
        		        System.out.println("La poubelle " + p[2] + " a été placée.");
        				break;
        			case 8 : 
        				p[4] = adressePoubelle;
        				
        		        //Affichage d'un message
        		        System.out.println("La poubelle " + p[2] + " a été placée.");
        				break;
        			case 9:
        				p[4] = adressePoubelle;
        				
        		        //Affichage d'un message
        		        System.out.println("La poubelle " + p[2] + " a été placée.");
        				break;	
        		}
        	}
        }
    }
    
	/**
	 * @brief	Méthode pour consulter les statistiques sur le nombre de collectes
	 * @param	centreTri = Numéro du centre de tri
	 */
	
	private static int StatNbPoints(Integer centreTri) {      
        //On récupère la liste des ménages et leurs informations
		List<String[]> listeMenages = Menage.initialisationListeMenages();
        
        //Initialisation de variables de statistiques
    	int nbPtsGagne = 0; //Nombre de points gagné par centre de tri
 
        //Parcours des données CSV ligne par ligne
        for (String[] row : listeMenages) {
        	//Récupération des données du fichier colonne par colonne
        	int idCentre = Integer.parseInt(row[0]);
            int nbPtsFidelite = Integer.parseInt(row[5]);
            
            //Vérifie si le nom du centre de tri correspond à celui recherché
            if (idCentre == centreTri) {
            	//Incrémentation des statistiques
            	nbPtsGagne += nbPtsFidelite;
            }
        }
		
        //On retourne le nombre de points gagnés
        return nbPtsGagne;
	}
	
	/**
	 * @brief	Méthode pour consulter les statistiques sur le nombre de ménages
	 * @param	centreTri = Numéro du centre de tri
	 */
	
	private static void StatNbMenage(Integer centreTri) {
		//On récupère la liste des ménages
		List<String[]> listeMenages = Menage.initialisationListeMenages();
		
		//Initialisation de variables
		ArrayList<Integer> tab = new ArrayList<Integer>(); //Liste vide d'entiers
		int nbMenage = 0; //Compteur du nombre de ménages
		String nomCentre = ""; //Chaîne vide qui va prendre le nom du centre

		for (String[] row : listeMenages) {
			if (row.length > 0) { // Vérifie que la ligne contient au moins une colonne
				try {
					int idCentre = Integer.parseInt(row[0].trim()); // Convertit la première colonne en entier
					if (!tab.contains(idCentre)) {
						tab.add(idCentre);
					}
					if (idCentre == centreTri) { // Vérifie si le centre de tri correspond à celui recherché
						//Incrémentation des statistiques
						nbMenage += 1;
						nomCentre = row[1]; // Récupère le nom du centre de tri depuis la deuxième colonne
					}
	           	}
	           	catch (NumberFormatException e) {
	        	   	//Ignore les lignes qui ne contiennent pas un entier valide dans la première colonne
	           	}
	       	}
		}

		if (nomCentre != "") {
			System.out.println("Le centre de tri " + nomCentre + " compte " + nbMenage + " ménage(s).");
		} 	else {
	       System.out.println("Le centre de tri que vous avez choisi ne comptient aucun ménage.");
		}
	}
	
	/**
	 * @brief	Méthode pour ajouter un contrat
	 * @param 	poubelle = Constructeur de Poubelle
	 * @param 	listePoubelles = Liste des poubelles
	 */

    private void ajouterContrat(Commerce commerce,Contrat contrat, List<String[]> listeContrat) {
        String[] contratArray = new String[8];
        contratArray[0] = String.valueOf(listeContrat.size());
        contratArray[1] = commerce.getNom();
        contratArray[2] = LocalDate.now().toString();
        contratArray[3] = String.valueOf(contrat.getDateFin());
        contratArray[4] = String.valueOf(contrat.getCategorieProduit());
        contratArray[5] = String.valueOf(contrat.isRenouvelable());
        
        // Récupération de la dernière ligne de listePoubelles
        String[] derniereLigne = listeContrat.get(listeContrat.size()-1);

        // Création d'un nouveau tableau pour la ligne modifiée
        String[] nouvelleLigne = new String[derniereLigne.length + contratArray.length - 2];

        //Copie des deux premiers éléments de la dernière ligne
        nouvelleLigne[0] = derniereLigne[0];
        nouvelleLigne[1] = derniereLigne[1];

        // Ajout des éléments de poubelleArray à la suite
        for (int i = 2; i < nouvelleLigne.length; i++) {
            nouvelleLigne[i] = contratArray[i-2];
        }

        // Remplacement de la dernière ligne dans listePoubelles
        listeContrat.set(listeContrat.size()-1, nouvelleLigne);

        System.out.println("La contrat du commerce " + commerce.getNom() + " a été ajoutée.");
    }
	
	/**
	 * @brief	Méthode principale
	 */
	
	public static void main(String[] args) {
		
		//On initialise le nombre de collectes à 0;
		Integer nbCollectes = 0;
				
		//Affichage d'un message
		System.out.println("\nAjouter une poubelle :\n");
		
		//On initialise la liste des poubelles
		List<String[]> listePoubelles = Poubelle.initialisationListePoubelles();
		
		//On considère dans cet exemple qu'elles appartiennent au centre de tri 1
		listePoubelles.add(new String[]{"1", "EcoTri"});

		//On créee un centre de tri
		CentreDeTri monCentreDeTri = new CentreDeTri("EcoTri","Avenue Jean Mermoz");
		Poubelle new_poubelle = new Poubelle(8,"Bleue","Boulevard Michel");
		
		//Ajout de la poubelle
		monCentreDeTri.ajouterPoubelle(new_poubelle, listePoubelles);

		listePoubelles.add(new String[]{"1", "EcoTri"});
		new_poubelle = new Poubelle(9,"AUTRE","Boulevard");
		monCentreDeTri.ajouterPoubelle(new_poubelle, listePoubelles);
			
	    //Affichage d'un message
	    System.out.println("\nListe des poubelles : \n");
	   
		//Sauvegarde de la liste des poubelles et affichage
		Poubelle.sauvegarderListePoubelles(listePoubelles);
	    Poubelle.afficherListePoubelles();
	    
	    //Affichage d'un message
	    System.out.println("\nOn voit ici que les poubelles ont été correctement ajoutées.");
		
	    //Affichage d'un message
		System.out.println("\nRetirer une poubelle : \n");
		monCentreDeTri.retirerPoubelle(8, listePoubelles);
		
		//Affichage d'un message
	    System.out.println("Liste des poubelles : \n");
		
		//Sauvegarde de la liste des poubelles et affichage
		Poubelle.sauvegarderListePoubelles(listePoubelles);
	    Poubelle.afficherListePoubelles();
		
		//Affichage d'un message
	    System.out.println("\nOn voit ici que la poubelle d'identifiant 8 a été correctement supprimée.");
		
	    //Affichage d'un message
		System.out.println("\nCollecter une poubelle : \n");
		
		//Appel de fonction
		nbCollectes += monCentreDeTri.collecterDechets(listePoubelles, new_poubelle, nbCollectes);
		
		//Affichage d'un message
	    System.out.println("Liste des poubelles : \n");
		
		//Sauvegarde de la liste des poubelles et affichage
		Poubelle.sauvegarderListePoubelles(listePoubelles);
	    Poubelle.afficherListePoubelles();
		
		//Affichage d'un message
	    System.out.println("\nOn voit ici que la poubelle d'identifiant 6 a été correctement collectée. Sa quantité est de 0.00 et son adresse est celle du centre de tri : Avenue Jean Mermoz.");
		
	    //Affichage d'un message
		System.out.println("\nPlacer une poubelle : \n");
		
		//Appel de fonction
		monCentreDeTri.placerPoubelle(listePoubelles, monCentreDeTri.getAdresse(),"Parc de la République");
		
		//Sauvegarde de la liste des poubelles et affichage
		Poubelle.sauvegarderListePoubelles(listePoubelles);
	    Poubelle.afficherListePoubelles();
		
		//Affichage d'un message
	    System.out.println("\nOn voit ici que la poubelle d'identifiant 6 a été correctement placée. Son adresse est redevenue la même, soit Parc de la République.");
	   
	    //Affichage de messages
	    System.out.println("\nStatistiques d'un centre de tri sur son nombre de collectes : \n");
	    System.out.println("Le centre de tri " + monCentreDeTri.getNom() + " a fait " + nbCollectes + " collectes pour un total de " + StatNbPoints(1) + " points.");
	    System.out.println("\nStatistiques du nombre de ménage dans un centre de tri :\n");	      
	   
	    //Appel de fonction
	    StatNbMenage(1);	 
	   	    
		//Affichage d'un message
		System.out.println("\n\nAjouter un contrat :\n");
		
		//On initialise la liste des contrats
		List<String[]> listeContrats = Contrat.initialisationListeContrats();
		
		//On considère dans cet exemple qu'ils appartiennent au centre de tri 1
		listeContrats.add(new String[]{"1", "EcoTri"});

		//On créee un nouveau contrat
		Commerce new_commerce = new Commerce("zzTop");
    	Contrat new_contrat = new Contrat("2023-12-05",0.6f,"[Fruits,Légumes]",true);
    	
    	//Appel de fonction
    	monCentreDeTri.ajouterContrat(new_commerce,new_contrat, listeContrats);

		listeContrats.add(new String[]{"1", "EcoTri"});
		new_commerce = new Commerce("Travel");
    	new_contrat = new Contrat("2023-11-09",0.7f,"[Fruits]",true);;
		monCentreDeTri.ajouterContrat(new_commerce,new_contrat,listeContrats);
			
	    //Affichage d'un message
	    System.out.println("\nListe des Contrats : \n");
	   
		//Sauvegarde de la liste des poubelles et affichage
		Contrat.sauvegarderContrats(listeContrats);
	    Contrat.afficherListeContrats(1);
	    
	    //Affichage d'un message
	    System.out.println("\nOn constate que les deux contrats ont bien été ajoutés.");
	}
}
