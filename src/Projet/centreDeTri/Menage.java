/**
*
* Programme Java pour implémenter un centre de tri
*
* @file 	Menage.java
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
import java.util.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @class 	Menage
 * @author  Group 5
 * @version 1.0
 * @date 	3 avril 2023
 */

public class Menage {
	private Integer idUser; //Identifiant du ménage
	private String nom; //Nom du ménage
	private String adresse; //Adresse du ménage
	private Integer nbPtsFidelite; //Nombre de points de fidélité du ménage
	private String bonAchat; //Bon d'achat des m"nages
	private List<Depot> depotList = new ArrayList<>();

	
	/**
	 * @brief	Accesseur de l'attribut nbPtsFidelite
	 * @return	le nombre de points de fidélité d'un ménage
	 */
	
	public Integer getNbPtsFidelite() {
		return nbPtsFidelite;
	}
	
	/**
	 * @brief	Mutateur de l'attribut nbPtsFidelite
	 */
	
	public void setNbPtsFidelite(Integer nbPtsFidelite) {
		this.nbPtsFidelite = nbPtsFidelite;
	}
	
	/**
	 * @brief	Accesseur de l'attribut idUser
	 * @return	l'identifiant du compte du ménage
	 */
	
	public Integer getIdUser() {
		return idUser;
	}
	
	/**
	 * @brief	Mutateur de l'attribut idUser
	 */
	
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	
	/**
	 * @brief	Accesseur de l'attribut bonAchat
	 * @return	un bon d'achat
	 */
	
	public String getBonAchat() {
		return bonAchat;
	}
	
	/**
	 * @brief	Mutateur de l'attribut bonAchat
	 */
	
	public void setBonAchat(String bonAchat) {
		this.bonAchat = bonAchat;
	}
	
	/**
	 * @brief	Accesseur de l'attribut nom
	 * @return	le nom du ménage
	 */
	
	public String getNom() {
		return nom;
	}
	
	/**
	 * @brief	Mutateur de l'attribut nom
	 */

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * @brief	Accesseur de l'attribut bonAchat
	 * @return	l'adresse du ménage
	 */

	public String getAdresse() {
		return adresse;
	}
	
	/**
	 * @brief	Mutateur de l'attribut adresse
	 */

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	/**
	 * @brief	Accesseur de l'attribut depotList
	 * @return	la liste des dépôts
	 */
	
	public List<Depot> getDepotList() {
		return depotList;
	}
	
	/**
	 * @brief	Mutateur de l'attribut depotlist
	 */

	public void setDepotList(List<Depot> depotList) {
		this.depotList = depotList;
	}
	
	/**
	 * @brief Méthode déposer des déchets dans une poubelle
	 */
	
	public void deposer(Poubelle poubelle, float poids, String typeDechet, Menage menage, Date date, Integer ptGagne) {
		Depot depot = new Depot(poids, typeDechet);
		poubelle.ajouterDepot(depot);
		depotList.add(depot);
	}
	
	/**
	 * @brief Constructeur de la classe Menage
	 */
		
	public Menage(String nom, String adresse) {
		this.idUser = 0; //La valeur d'iduser est gérée lorsque l'on ajoute un ménage
		this.nom = nom;
		this.adresse = adresse;
		this.nbPtsFidelite = 0; //On considère qu'un nouveau ménage n'a pas de points
		this.bonAchat = null; //On considère qu'un nouveau ménage n'a pas de bon d'achats
		this.depotList = null;
	}
	
	/**
	* @brief Méthode pour initialiser la liste des ménages
	*/
	
    protected static List<String[]> initialisationListeMenages() {
    	//Définition du chemin vers le fichier CSV et du délimiteur CSV
	    String csvFile = "/home/cytech/Cours/Semestre2/Java/Projet/listeMenage.csv";
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
        
        return result;
    }
    
	/**
	* @brief Méthode pour afficher la liste des ménages
	*/
	
    protected static void afficherListeMenages() {
        //Initialisation de la liste des ménages
    	List<String[]> listeMenages = initialisationListeMenages(); 
        
        //Parcours des tableaux dans la liste
        for (String[] tableau : listeMenages) {
            //Parcours des éléments dans le tableau
            for (String element : tableau) {
                System.out.print(element + " ");
            }
            
            //Saut de ligne entre chaque tableau
            System.out.println();
        }
    }
    
	/**
	 * @brief	Méthode pour enregistrer la liste des ménages dans le fichier csv
	 * @param	listeMenages = Liste des ménages
	 */
    
    public static void sauvegarderMenages(List<String[]> listeMenages) {
        //Définition du chemin vers le fichier CSV et du délimiteur CSV
        String csvFile = "/home/cytech/Cours/Semestre2/Java/Projet/listeMenage.csv";
        String csvDelimiter = ";";

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            //Écriture de la première ligne de l'en-tête
            writer.println("Numéro Centre Tri;Nom centre de tri; Numéro Menage; Nom; Adresse; Points Fidelite; Bon Achat");

            //Parcours des données de la listePoubelles
            for (String[] row : listeMenages) {
                //Écriture de chaque ligne dans le fichier CSV
                writer.println(String.join(csvDelimiter, row));
            }
        } catch (IOException e) {
            //Gestion des erreurs d'entrée/sortie
            e.printStackTrace();
        }
    }
    
	/**
	 * @brief	Méthode pour ajouter un ménage
	 * @param 	poubelle = Constructeur de Poubelle
	 * @param 	listePoubelles = Liste des poubelles
	 */

    private static void ajouterMenage(Menage menage, List<String[]> listeMenages) {    	
    	String[] contratArray = new String[7];
        contratArray[0] = String.valueOf(listeMenages.size());
        contratArray[1] = menage.getNom();
        contratArray[2] = menage.getAdresse();
        contratArray[3] = String.valueOf(menage.getNbPtsFidelite());
        contratArray[4] = menage.getBonAchat();
        
        // Récupération de la dernière ligne de listePoubelles
        String[] derniereLigne = listeMenages.get(listeMenages.size()-1);

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
        listeMenages.set(listeMenages.size()-1, nouvelleLigne);

        System.out.println("Le ménage " + menage.getNom() + " a été ajouté.");
    }
    
    private static String generateBonAchat(Integer idUser,List<String[]> listeMenages) {
    	//initialisation du bon d'achat
        int valeurBon = 0;

        // Parcours des données CSV ligne par ligne
        for (String[] row : listeMenages) {
            if (idUser.equals(Integer.parseInt(row[2]))){
            	if(row[6].equals("null")) {
	                int nbPts = Integer.parseInt(row[5]);
	                if (nbPts > 10) {
	                    valeurBon = nbPts / 10;
	                    nbPts -= valeurBon*10;
	                    String bonAchat = "Voici votre bon d'achat : " + valeurBon + "BA";
	
	                    //Met à jour le nombre de points de fidélité dans la liste listeMenages
	                    row[5] = String.valueOf(nbPts);
	                    row[6] = bonAchat;
	
	                    // Retourne le bon d'achat
	                    return bonAchat;
	                }
	                else {
	                    return "Vous n'avez pas assez de points.\nIl en faut au moins 10 pour commencer à générer des bons.\nEt vous n'en avez " + nbPts + ".";
	                }
	            }
	            else{
	                int nbPts = Integer.parseInt(row[5]);
	                if (nbPts > 10) {
	                    valeurBon = nbPts / 10;
	                    nbPts -= valeurBon*10;
	                    String bonAchat = "Voici votre bon d'achat : " + valeurBon + "BA" + row[2];
	
	                    //Met à jour le nombre de points de fidélité dans la liste listeMenages
	                    row[5] = String.valueOf(nbPts);
	                    int test = row[6].indexOf("BA");
	         
	                    row[6] = Integer.parseInt(row[6].substring(0,test)) + valeurBon + "BA";
		
	                    // Retourne le bon d'achat
	                    return bonAchat;
	                }
	                else {
	                    return "Vous n'avez pas assez de points.\nIl en faut au moins 10 pour commencer à générer des bons.\nEt vous n'en avez " + nbPts + ".";
	                }
	            }
            }
        }

        // Retourne un message d'erreur
        return "Vérifiez que vous ayez renseigné le bon identifiant.";
    }
    
    private static String consulterPointsFidelite(Integer idUser) {
        //Initialisation de la liste des ménages
    	List<String[]> listeMenages = initialisationListeMenages(); 
        
        //Parcours des tableaux dans la liste
        for (String[] row : listeMenages) {
        	if(idUser == Integer.parseInt(row[2])) {
        		return "Vous avez : " + row[5] + " points.";
        	}
        }
        
     // Retourne un message d'erreur
        return "Vérifiez que vous ayez renseigné le bon identifiant.";
    }
    
	/**
	 * @brief	Méthode principale
	 */

	public static void main(String[] args) {		    	    		    		
		//Affichage d'un message
		System.out.println("\nAjouter un ménage :\n");
		
		//On initialise la liste des ménages
		List<String[]> listeMenages = Menage.initialisationListeMenages();
		
		//On considère dans cet exemple qu'ils appartiennent au centre de tri 1
		listeMenages.add(new String[]{"1", "EcoTri"});

		//On crée un nouveau ménage
    	Menage new_menage = new Menage("Paul","3 Boulevard des Pyrénées");
    	
    	//On ajoute le nouveau ménage
    	Menage.ajouterMenage(new_menage,listeMenages);
			
	    //Affichage d'un message
	    System.out.println("\nListe des Ménages : \n");
	   
		//Sauvegarde de la liste des poubelles et affichage
		Menage.sauvegarderMenages(listeMenages);
	    Menage.afficherListeMenages();
	    
	    //Affichage d'un message
	    System.out.println("\nOn constate que le ménage a bien été ajouté.");
		    	
    	//Affichage d'un message
    	System.out.println("\nListe des dépôts du ménage 8 : \n");
    	
    	//Appel de fonction
    	Depot.afficherListeDepots(8);
    	
    	//Affichage d'un message
    	System.out.println("On constate qu'il n'a aucun dépôt car il vient d'être créé.");
    	
    	//Affichage d'un message
    	System.out.println("\nListe des dépôts du ménage 1 : \n");
    	
    	//Appel de fonction
    	Depot.afficherListeDepots(1);
    	
    	//Affichage d'un message
    	System.out.println("\nOn constate qu'il a plusieurs dépôts.");

    	//Affichage d'un message
	    System.out.println("\nConvertir ses points en bon d'achat :");
    	System.out.println("\nDonnez votre identifiant d'utilisateur : 5");	    
    		
    	//Appel de fonction
    	String bonAchat = generateBonAchat(5, listeMenages);
    	
    	//Affichage d'un message
    	System.out.println(bonAchat + "\n");
    	System.out.println("On peut voir que le nombre de bons d'achats augmentent.");
    	
    	//Sauvegarde de la liste des poubelles et affichage
		Menage.sauvegarderMenages(listeMenages);
	    Menage.afficherListeMenages();
    	
		//Affichage et lecture de messages
	    System.out.println("\nConsulter son nombre de points :");
		System.out.println("\nDonnez votre identifiant d'utilisateur : 1");
		
		String result = consulterPointsFidelite(1);
		
		//Affichage d'un message
    	System.out.println(result + "\n");

		//Sauvegarde de la liste des poubelles et affichage
		Menage.sauvegarderMenages(listeMenages);
	    Menage.afficherListeMenages();
	}
}
