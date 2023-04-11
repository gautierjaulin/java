/**
*
* Programme Java pour implémenter un centre de tri
*
* @file 	Poubelle.java
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

/**
 * @class 	Poubelle
 * @author  Group 5
 * @version 1.0
 * @date 	3 avril 2023
 */

public class Poubelle {
	private Integer idPoubelle; //Identifiant de la poubelle
	private String type; //Type de déchets que prend la poubelle : 1 pour PbClassique, 2 pour PbVerte, 3 pour PbJaune et 4 pour PbBleue
	private String emplacement; //Emplacement de la poubelle
	private Float capaciteMax; //Capacité maximale d'une poubelle
	private Float capacite; //Capacité actuelle de la poubelle
	private Set<Menage> menage;
	private List<Depot> depotList = new ArrayList<>();

	
	/**
	 * @brief	Accesseur de l'attribut idPoubelle
	 * @return	le numéro de la poubelle
	 */
	
	public Integer getIdPoubelle() {
		return idPoubelle;
	}

	/**
	 * @brief	Mutateur de l'attribut idPoubelle
	 */
	
	public void setIdPoubelle(Integer idPoubelle) {
		this.idPoubelle = idPoubelle;
	}
	
	/**
	 * @brief	Accesseur de l'attribut emplacement
	 * @return	l'emplacement de la poubelle
	 */

	public String getEmplacement() {
		return emplacement;
	}

	/**
	 * @brief	Mutateur de l'attribut emplacement
	 */
	
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}
	
	/**
	 * @brief	Accesseur de l'attribut capaciteMax
	 * @return	la capacite maimale de la poubelle
	 */

	public Float getCapaciteMax() {
		return capaciteMax;
	}
	
	/**
	 * @brief	Mutateur de l'attribut capaciteMax
	 */

	public void setCapaciteMax(Float capaciteMax) {
		this.capaciteMax = capaciteMax;
	}
	
	/**
	 * @brief	Accesseur de l'attribut capacite
	 * @return	la capacité de la poubelle
	 */

	public Float getCapacite() {
		return capacite;
	}
	
	/**
	 * @brief	Mutateur de l'attribut capacite
	 */

	public void setCapacite(Float capacite) {
		this.capacite = capacite;
	}
	
	/**
	 * @brief	Accesseur de l'attribut capacite
	 * @return	les attributs de Menage
	 */

	public Set<Menage> getMenage() {
		return menage;
	}
	
	/**
	 * @brief	Accesseur de l'attribut type
	 */

	public String getType() {
		return type;
	}
	
	/**
	 * @brief	Mutateur de l'attribut type
	 */

	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @brief	Constructeur de la classe Poubelle
	 */
	
    public Poubelle(int idPoubelle, String type, String emplacement) {
        this.idPoubelle = idPoubelle;
        this.type = type;
        this.emplacement = emplacement;
        this.capaciteMax = 200f;
        this.capacite = 0f;
    }
    
	/**
	 * @brief	Méthode pour initialiser la liste des poubelles depuis le ficher csv
	 * return	la liste des poubelles
	 */
    
	protected static List<String[]> initialisationListePoubelles() {
    	//Définition du chemin vers le fichier CSV et du délimiteur CSV
	    String csvFile = "/home/cytech/Cours/Semestre2/Java/Projet/listePoubelle.csv";
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
	 * @brief	Méthode pour afficher la liste des poubelles
	 */
	
    static void afficherListePoubelles() {
        //Initialisation de la liste des ménages
    	List<String[]> listePoubelles = initialisationListePoubelles(); 
        
        //Parcours des tableaux dans la liste
        for (String[] tableau : listePoubelles) {
            //Parcours des éléments dans le tableau
            for (String element : tableau) {
                System.out.print(element + " ");
            }
            
            //Saut de ligne entre chaque tableau
            System.out.println();
        }
    }
    
	/**
	 * @brief	Méthode pour sauvegarder la liste des poubelles dans le fichier csv
	 * param	listePoubelles = Liste des poubelles
	 */
    
    public static void sauvegarderListePoubelles(List<String[]> listePoubelles) {
        //Définition du chemin vers le fichier CSV et du délimiteur CSV
        String csvFile = "/home/cytech/Cours/Semestre2/Java/Projet/listePoubelle.csv";
        String csvDelimiter = ";";

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            //Écriture de la première ligne de l'en-tête
            writer.println("idCentre;NomCentre;idPoubelle;type;Emplacement;capaciteMax;capacite");

            //Parcours des données de la listePoubelles
            for (String[] row : listePoubelles) {
                //Écriture de chaque ligne dans le fichier CSV
                writer.println(String.join(csvDelimiter, row));
            }
        } catch (IOException e) {
            //Gestion des erreurs d'entrée/sortie
            e.printStackTrace();
        }
    }
    
	/**
	 * @brief	Méthode pour identifier un utilisateur
	 * @return les informations du ménage s'il existe
	 */
    
    
    public static boolean identifierUser(Integer idUser) {
		//On initialise la liste des ménages
		List<String[]> listeMenages = Menage.initialisationListeMenages();
		
        //On parcoure la liste des ménages enregistrés
        for (String[] p : listeMenages) {
            if (idUser == Integer.parseInt(p[2])) {
            	//On retourne les informations du ménage lorsqu'on l'a trouvé
                return true;
            }
        }
        
        // si aucun ménage ne correspond à l'identifiant, retourner null
        return false;
    }

	
	/**
	 * @brief	Méthode pour calculer la capacité d'une poubelle
	 * @param	poids = Poids des déchets déposés
	 * @param	idPoubelle = Identifiant de la poubelle à actualiser le poids
	 */
	
	public static void calculerCapacite(Float poids, Integer idPoubelle) {
		//On initialise la liste des poubelles
		List<String[]> listePoubelles = Poubelle.initialisationListePoubelles();
		
		for(String[] p : listePoubelles) {
			if(idPoubelle == Integer.parseInt(p[2])) {
				//Mise à jour de la valeur de la quantité de la poubelle
				Float majPoids = Float.parseFloat(p[6]) + poids;
				p[6] = String.valueOf(majPoids);
				
				//Affichage d'un message
				System.out.println("\nLa quantité présente dans la poubelle " + idPoubelle + " est de " + p[6] + "\n");
			}
		}
		
		//Appel de fonction
		sauvegarderListePoubelles(listePoubelles);
	}
	
	/**
	 * @brief	Méthode pour prévenir le centre de tri que la poubelle est pleine
	 * @return	vrai ou faux en fonction de si la capacité max de la poubelle est atteinte ou non
	 */
	
	public boolean envoyerMessage(Integer idPoubelle) {
		//Récupère la liste des poubelles
		List<String[]> listePoubelles = initialisationListePoubelles();
		for(String[] p : listePoubelles) {
			if(idPoubelle == Integer.parseInt(p[2])) {
		        //Retourne true si la capacité maximale est atteinte
		        if (Float.parseFloat(p[6]) >= 190.00f) {
		            return true;
		        }
			}
		}        
        //Retourne false si la capacité maximale n'est pas atteinte
        return false;
	}
	
	/**
	 * @brief	Méthode pour vérifier si le déchet déposé est dans la bonne poubelle
	 * @return	vrai ou faux en fonction de s'il est déposé dans la bonne poubelle ou non
	 */
	
	public boolean correspond(String typeDechet, Integer idPoubelle) {
		//On initialise la liste des poubelles
		List<String[]> listePoubelles = Poubelle.initialisationListePoubelles();
		
		String verifPoubelle = null;
		
		//On récupère le type de la poubelle
		for(String[] p : listePoubelles) {
			if(idPoubelle == Integer.parseInt(p[2])) {
				verifPoubelle = p[3];
			}
		}
		
		//Instances des différentes poubelles
        PoubelleJaune poubelleJaune = new PoubelleJaune(idPoubelle,typeDechet,"");
        PoubelleBleue poubelleBleue = new PoubelleBleue(idPoubelle,typeDechet,"");
        PoubelleVerte poubelleVerte = new PoubelleVerte(idPoubelle,typeDechet,"");
        PoubelleClassique poubelleClassique = new PoubelleClassique(idPoubelle,typeDechet,"");
        
		//Cas en fonction dy tpe de déchet
		switch(verifPoubelle) {
			case "JAUNE":
				return(poubelleJaune.correspond(typeDechet,idPoubelle));
			case "VERTE":
				return(poubelleVerte.correspond(typeDechet,idPoubelle));
			case "BLEUE":
				return(poubelleBleue.correspond(typeDechet,idPoubelle));
			case "AUTRE":
				return(poubelleClassique.correspond(typeDechet,idPoubelle));
		}
		
		//Vérification si le déchet déposé correspond au type de la poubelle
        return (true);
	}
	
	/**
	 * @brief	Méthode pour gérer les points de fidélité
	 */
	
	public int gestionPtsFidelite(Float poids) {
		return Float.valueOf(poids+1).intValue();
	}
	
	/**
	 * @brief	Méthode pour gérer l'ajout de dépôt
	 */
	
	public void ajouterDepot(Depot depot) {
	    this.depotList.add(depot);
	}
}
