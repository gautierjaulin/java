/**
*
* Programme Java pour implémenter un centre de tri
*
* @file 	Contrat.java
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
import java.util.List;

/**
 * @class 	Contrat
 * @author  Group 5
 * @version 1.0
 * @date 	3 avril 2023
 */

public final class Contrat {
	private String dateDebut = LocalDate.now().toString(); //Date de début du contrat fixée à la date du jour
	private String dateFin; //Date de fin du contrat
	private Float reglesUse; //Règles pour utiliser les bons d'achat
	private String categorieProduit; //Catégories de produit pour lesquelles un ménage peut utiliser ses points dans un commerce
	private boolean renouvelable; //Contrat renouvelable ou non
	
	/**
	 * @brief	Accesseur de l'attribut dateDebut
	 * @return	la date de début du contrat
	 */
	
	public String getDateDebut() {
		return dateDebut;
	}
	
	/**
	 * @brief	Mutateur de l'attribut dateDebut
	 */
	
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	/**
	 * @brief	Accesseur de l'attribut dateFin
	 * @return	la date de fin du contrat
	 */
	
	public String getDateFin() {
		return dateFin;
	}
	
	/**
	 * @brief	Mutateur de l'attribut dateFin
	 */
	
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	
	/**
	 * @brief	Accesseur de l'attribut reglesUse
	 * @return	les règles du contrat
	 */
	
	public Float getReglesUse() {
		return reglesUse;
	}
	
	/**
	 * @brief	Mutateur de l'attribut reglesUse
	 */
	
	public void setReglesUse(Float reglesUse) {
		this.reglesUse = reglesUse;
	}
	
	/**
	 * @brief	Accesseur de l'attribut categorieProduit
	 * @return	la catégorie produit
	 */
	
	public String getCategorieProduit() {
		return categorieProduit;
	}
	
	/**
	 * @brief	Mutateur de l'attribut categorieProduit
	 */
	
	public void setCategorieProduit(String categorieProduit) {
		this.categorieProduit = categorieProduit;
	}
	
	/**
	 * @brief	Accesseur de l'attribut renouvelable
	 * @return	si un contrat est renouvelable ou non
	 */
	
	public boolean isRenouvelable() {
		return renouvelable;
	}
	
	/**
	 * @brief	Mutateur de l'attribut renouvelable
	 */

	public void setRenouvelable(boolean renouvelable) {
		this.renouvelable = renouvelable;
	}
	
	/**
	 * @brief	Constructeur de la classe Contrat
	 */
	
	public Contrat(String dateFin, Float reglesUse, String categorieProduit, Boolean renouvelable) {
		this.dateFin = dateFin;
		this.reglesUse = reglesUse;
		this.categorieProduit = categorieProduit;
		this.renouvelable = renouvelable;
	}
	
	/**
	 * @brief	Méthode pour initialiser la liste des contrats à partir du fichier csv
	 */
	
	protected static List<String[]> initialisationListeContrats() {
    	//Définition du chemin vers le fichier CSV et du délimiteur CSV
	    String csvFile = "/home/cytech/Cours/Semestre2/Java/Projet/listeCommerce.csv";
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
	 * @brief	Méthode pour afficher la liste des contrats
	 */
	
    protected static void afficherListeContrats(Integer idCentre) {
        //Initialisation de la liste des ménages
    	List<String[]> listeContrats = initialisationListeContrats(); 
        
        //Parcours des tableaux dans la liste
        for (String[] tableau : listeContrats) {
        	if(idCentre == Integer.parseInt(tableau[0])) {
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
	 * @brief	Méthode pour enregistrer la liste des contrats dans le fichier csv
	 */
    
    protected static void sauvegarderContrats(List<String[]> listeContrats) {
        //Définition du chemin vers le fichier CSV et du délimiteur CSV
        String csvFile = "/home/cytech/Cours/Semestre2/Java/Projet/listeCommerce.csv";
        String csvDelimiter = ";";

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            //Écriture de la première ligne de l'en-tête
            writer.println("Numéro Centre Tri;Nom centre de tri; Numéro commerce; Nom; DateDebut contrat;dateFin contrat; categoriesProduit; renouvelable");

            //Parcours des données de la listePoubelles
            for (String[] row : listeContrats) {
                //Écriture de chaque ligne dans le fichier CSV
                writer.println(String.join(csvDelimiter, row));
            }
        } catch (IOException e) {
            //Gestion des erreurs d'entrée/sortie
            e.printStackTrace();
        }
    }
}
