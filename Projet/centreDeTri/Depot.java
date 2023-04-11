/**
*
* Programme Java pour implémenter un centre de tri
*
* @file 	Depot.java
* @author  	Groupe 5
* @version 	1.0 Premier jet
* @date   	3 avril 2023
* @brief	Projet Java
* 
*/

package centreDeTri;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @class 	Depot
 * @author  Group 5
 * @version 1.0
 * @date 	3 avril 2023
 */

public final class Depot {
	private Date heureDepot; //Heure du dépôt
	private String typeDepot; //Type de dépot dans une poubelle
	private float poids; //Poids des déchets déposés
	private Integer ptGagne; //Point gagné pour un dépôt
	private Menage menage; //Attribut de Menage
	private Poubelle poubelle; //Attribut de Poubelle
	
	/**
	 * @brief	Accesseur de l'attribut heureDepot
	 * @return	l'heure du dépot
	 */
	
	public Date getHeureDepot() {
		return heureDepot;
	}
	
	/**
	 * @brief	Mutateur de l'attribut heureDepot
	 */
	
	public void setHeureDepot(Date heureDepot) {
		this.heureDepot = heureDepot;
	}

	
	/**
	 * @brief	Accesseur de l'attribut typeDepot
	 * @return	le type de déchet déposé
	 */
	
	public String getTypeDepot() {
		return typeDepot;
	}
	
	/**
	 * @brief	Mutateur de l'attribut typeDepot
	 */
	
	public void setTypeDepot(String typeDepot) {
		this.typeDepot = typeDepot;
	}
	
	/**
	 * @brief	Accesseur de l'attribut ptGagne
	 * @return	le nombre de point gagné pour un dépôt
	 */
	
	public Integer getPtGagne() {
		return ptGagne;
	}
	
	/**
	 * @brief	Mutateur de l'attribut ptGagne
	 */
	
	public void setPtGagne(Integer ptGagne) {
		this.ptGagne = ptGagne;
	}
	
	/**
	 * @brief	Accesseur de l'attribut menage
	 * @return	les attributs de Menage
	 */
	
	public Menage getMenage() {
		return menage;
	}
	
	/**
	 * @brief	Mutateur de l'attribut menage
	 */
	
	public void setMenage(Menage menage) {
		this.menage = menage;
	}
	
	/**
	 * @brief	Accesseur de l'attribut poubelle
	 * @return	les attributs de Poubelle
	 */
	
	public Poubelle getPoubelle() {
		return poubelle;
	}
	
	/**
	 * @brief	Mutateur de l'attribut poubelle
	 */
	
	public void setPoubelle(Poubelle poubelle) {
		this.poubelle = poubelle;
	}
	
	/**
	 * @brief	Accesseur de l'attribut poids
	 * @return	le poids des déchets déposes
	 */

	public float getPoids() {
		return poids;
	}
	
	/**
	 * @brief	Mutateur de l'attribut poids
	 */

	public void setPoids(float poids) {
		this.poids = poids;
	}
	
	/**
	 * @brief	Constructeur de la classe Depot
	 */
	
	public Depot(float poids, String typeDechet) {
		this.poids = poids;
		this.typeDepot = typeDechet;
		this.heureDepot = new Date();
	}
	
	/**
	 * @brief	Méthode pour initialiser la liste des contrats à partir du fichier csv
	 */
	
	protected static List<String[]> initialisationListeDepots() {
    	//Définition du chemin vers le fichier CSV et du délimiteur CSV
	    String csvFile = "/home/cytech/Cours/Semestre2/Java/Projet/historiqueDepot.csv";
	    String line = "";
	    String csvDelimiter = ";";

        //Initialisation d'une liste pour stocker les données CSV
        List<String[]> csvData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            //Lecture du fichier CSV ligne par ligne
            boolean headerLine = true;
            while ((line = br.readLine()) != null) {
                if (headerLine) {
                    headerLine = false;
                    continue; //Ignore la première ligne si elle contient l'en-tête
                }
                //Séparation des valeurs de la ligne selon le délimiteur CSV
                String[] row = line.split(csvDelimiter);
                //Ajout des valeurs dans la liste
                csvData.add(row);
            }
        } catch (IOException e) {
            //Gestion des erreurs d'entrée/sortie
            e.printStackTrace();
        }

        //Initialisation d'une chaîne vide à retourner
        List<String[]> result = new ArrayList<>();

        //Parcours des données CSV ligne par ligne
        for (String[] row : csvData) {        	
        	//Ajout de chaque ligne à la liste result
        	result.add(row);
        }
        
        //Retourne la liste des contrats
        return result;
    }
	
	/**
	 * @brief	Méthode pour afficher la liste des dépôts d'un ménage
	 */
	
	protected static void afficherListeDepots(Integer idMenage) {
        //Initialisation de la liste des ménages
    	List<String[]> listeDepots = initialisationListeDepots(); 
        
        //Parcours des tableaux dans la liste
        for (String[] tableau : listeDepots) {
        	if(idMenage == Integer.parseInt(tableau[0])) {
                //Parcours des éléments dans le tableau
                for (String element : tableau) {
                    System.out.print(element + " ");
                }
                
                //Saut de ligne entre chaque tableau
                System.out.println();
        	}
        }
    }
    
	/**
	 * @brief	Méthode pour enregistrer la liste des dépôts dans le fichier csv
	 * @param	listeDepots = Liste des dépôts
	 */
    
	protected static void sauvegarderDepots(List<String[]> listeDepots) {
        //Définition du chemin vers le fichier CSV et du délimiteur CSV
        String csvFile = "/home/cytech/Cours/Semestre2/Java/Projet/historiqueDepot.csv";
        String csvDelimiter = ";";

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            //Écriture de la première ligne de l'en-tête
            writer.println("idUser;heureDepot;typeDepot;poids;ptGagne;idPoubelle");

            //Parcours des données de la listePoubelles
            for (String[] row : listeDepots) {
                //Écriture de chaque ligne dans le fichier CSV
                writer.println(String.join(csvDelimiter, row));
            }
        } catch (IOException e) {
            //Gestion des erreurs d'entrée/sortie
            e.printStackTrace();
        }
    }
    
	/**
	 * @brief	Méthode pour ajouter un dépôt
	 * @param	idMenage = Identifiant du ménage qui vient de faire un dépôt
	 * @param	listeDepots = Liste des dépôts
	 * @param	idPoubelle = Identifiant de la poubelle dans laquelle le dépôt a été fait
	 * @param	depot = Contstructeur de Depot
	 */
    
    public static void ajouterDepot(Integer idMenage, List<String[]> listeDepots, Integer idPoubelle, Depot depot) {
    	//On initialise la liste des poubelles
    	List<String[]> listePoubelles = Poubelle.initialisationListePoubelles();
    	
    	//On initialise des booléens pour vérifier la justesse des informations
    	boolean verifPoubelle = false;
    	boolean verifMenage = false;
    	
    	//Si la poubelle existe, on ajoute le dépôt
    	for (String[] p : listePoubelles) {
    		//Si la poubelle existe dans la liste des poubelles
    		if(idPoubelle == Integer.parseInt(p[2])) {
    			verifPoubelle = true;
    			
    			if(Float.parseFloat(p[6]) + depot.getPoids() <= 200.00) {
    			
	    			//On initialise la liste des ménages
	    	    	List<String[]> listeMenages = Menage.initialisationListeMenages();
	    	    	
	    	    	for(String[] row : listeMenages) {
	    	    		//Si le ménage existe
	    	    		if(idMenage == Integer.parseInt(row[2])) {
	    	    			verifMenage = true;
	    	    			
	    	    	    	String[] DepotArray = new String[6];
	    	    	        DepotArray[0] = String.valueOf(idMenage);
	    	    	        DepotArray[1] = LocalDate.now().toString();
	    	    	        DepotArray[2] = depot.getTypeDepot();
	    	    	        DepotArray[3] = String.valueOf(depot.getPoids());
	    	    	        DepotArray[5] = String.valueOf(idPoubelle);
	    	    	           	    	        
	    	    	        //Incrémentation de la capacité de la poubelle
	    	    	        Poubelle.calculerCapacite(depot.getPoids(),idPoubelle);
	    	    	        
	    	    	        //Instances des différentes poubelles
	    	    	        Poubelle poubelle = new Poubelle(idPoubelle,depot.getTypeDepot(),"");
	    	    	           	   
	    	    	        int nbpts = Integer.parseInt(row[5]);
	    	    	        
	    	    	        if(poubelle.correspond(depot.getTypeDepot(), idPoubelle)) {
	    	    	        	nbpts += poubelle.gestionPtsFidelite(depot.getPoids());
	    	    	        }
	    	    	        else {
	    	    	        	nbpts += -poubelle.gestionPtsFidelite(depot.getPoids());
	    	    	        }
	    	    	        
	    	    	        if(nbpts < 0) {
	    	    	        	row[5] = String.valueOf(0);
	    	    	        }
	    	    	        else {
	    	    	        	row[5] = String.valueOf(nbpts);
	    	    	        }
	    	    	        
	    	    	        //Modificationd e la valeur dans le fichier
	    	    	        DepotArray[4] = row[5];
	    	    	        
	    	    	        //Ajout de DepotArray à la liste listeDepots
	    	    	        listeDepots.add(DepotArray);
	
	    	    	        System.out.println("Le dépôt du ménage " + idMenage + " a été ajouté.");
	    	    		}
	    	    		
	    	        	//Sauvegarde de la liste des ménages et affichage
	    	    		Menage.sauvegarderMenages(listeMenages);
	    	    		Menage.afficherListeMenages();
	    	    	}
    			}
    			else {
    				//Affichage d'un message d'erreur
    				System.out.println("Erreur : La poubelle est pleine.");
    			}
    		}
    	}
    	
    	if(!verifPoubelle) {
    		//Affichage d'un message d'erreur 
			System.out.println("Erreur : La poubelle n'existe pas.");
    	}
    	else {
    		if(!verifMenage) {
    			//Affichage d'un message d'erreur 
    			System.out.println("Erreur : Le ménage n'existe pas.");
    		}		
    	}
    }
    
	/**
	 * @brief	Méthode principale
	 */
	
	public static void main(String[] args) { 	    	
		//Affichage d'un message
		System.out.println("Ajouter un dépôt :\n");
		
		//On initialise la liste des contrats
		List<String[]> listeDepots = Depot.initialisationListeDepots();

		//On créee un nouveau depot
		Depot new_depot = new Depot(0.8f,"VERRE");
    	
		ajouterDepot(17,listeDepots,2,new_depot);
		
		//Affichage d'un message
    	System.out.println("\nLe dépôt n'a pas été ajouté car le ménage n'existe pas.\n");
		
    	//Affichage d'un message
    	System.out.println("Ajouter un dépôt :\n");
    	
		//On créee un nouveau depot
		new_depot = new Depot(0.8f,"VERRE");
    	
		ajouterDepot(1,listeDepots,23,new_depot);
		
		//Affichage d'un message
		System.out.println("\nListe des dépôts du ménage 1 :\n");
		
		//Sauvegarde de la liste des dépôts et affichage
		Depot.sauvegarderDepots(listeDepots);
	    Depot.afficherListeDepots(1);
	    
	    //Affichage d'un message
    	System.out.println("\nLe dépôt n'a pas été ajouté car la poubelle n'existe pas, comme on peut le voir dans la liste de ses dépôts.\n");
    	
	  	//Affichage d'un message
	  	System.out.println("Ajouter un dépôt :\n");
	    
		new_depot = new Depot(3.4f,"VERRE");
		
		ajouterDepot(3,listeDepots,4,new_depot);
			
		//Affichage d'un message
		System.out.println("\nListe des dépôts du ménage 3 :\n");
	   
		//Sauvegarde de la liste des ajouterDepotpoubelles et affichage
		Depot.sauvegarderDepots(listeDepots);
	    Depot.afficherListeDepots(3);
	    
	    //Affichage d'un message
    	System.out.println("\nLe dépôt a bien été ajouté.");
    	
	  	//Affichage d'un message
	  	System.out.println("Ajouter un dépôt :\n");
	    
		new_depot = new Depot(45.2f,"VERRE");
		
		ajouterDepot(7,listeDepots,1,new_depot);
			
		//Affichage d'un message
		System.out.println("\nListe des dépôts du ménage 7 :\n");
	   
		//Sauvegarde de la liste des poubelles et affichage
		Depot.sauvegarderDepots(listeDepots);
	    Depot.afficherListeDepots(7);
	    
	    //Affichage d'un message
    	System.out.println("\nLe dépôt a été ajouté. Or, comme c'est dans la mauvaise poubelle, l'utilisateur n'a pas gagné de points.");
    	
	  	//Affichage d'un message
	  	System.out.println("Ajouter un dépôt :\n");
	    
		new_depot = new Depot(45.2f,"VERRE");
		
		ajouterDepot(7,listeDepots,4,new_depot);
			
		//Affichage d'un message
		System.out.println("\nListe des dépôts du ménage 7 :\n");
	   
		//Sauvegarde de la liste des poubelles et affichage
		Depot.sauvegarderDepots(listeDepots);
	    Depot.afficherListeDepots(7);
	    
	    //Affichage d'un message
    	System.out.println("\nLe dépôt n'a pas été effectué car la poubelle était pleine.");
	}
}
