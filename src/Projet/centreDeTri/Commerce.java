/**
*
* Programme Java pour implémenter un centre de tri
*
* @file Commerce.java
* @author   Groupe 5
* @version 1.0 Premier jet
* @date   3 avril 2023
* @brief Projet Java
*
*/

package centreDeTri;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @class Commerce
 * @author  Group 5
 * @version 1.0
 * @date 3 avril 2023
 */

public class Commerce {
	private String nom; //Nom du commerce
	
	/**
	* @brief Accesseur de l'attribut Nom
	* @return nom du centre de tri
	*/
	
	public String getNom() {
		return nom;
	}
	
	/**
	* @brief Mutateur de l'attribut Nom
	* @return nom du centre de tri
	*/
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	* @brief Constructeur de la classe Commerce
	*/
	
	public Commerce(String nom) {
		this.nom = nom;
	}

	/**
	* @brief Méthode des produits proposés par les commerces
	* @return La chaîne de caractères des produits achetables avec des points
	*/
	
	private static String proposeProduit(Integer idCommerce, List<String[]> listeContrats) { 
	   //Initialisation de la variable pour stocker les produits proposés et le nom du commerce
	   String produits = null;
	   String nom = null;
	   
	   //Recherche des produtis proposés pour un idCommerce donné
	   for(String[] p : listeContrats) {
		   if(idCommerce == Integer.parseInt(p[2])) {
			   produits = p[7];
			   nom = p[2];
		   }
	   }
	
	   //Retour de la reduc proposé (ou null s'il n'y en a pas)
	   return "Le commerce " + nom + " propose les produits suivant : " + produits + ".";
	}
	
	/**
	 * @brief	Méthode pour renouveler un contrat
	 * @param	idCommerce = Identifiant du commerce pour lequel il faut renouveler son contrat
	 * @param	dateFin = Nouvelle date de fin du contrat
	 * @param 	listeContrats = Liste des contrats
	 */
	
	private static String renouvelerContrat(Integer idCommerce, String dateFin,List<String[]> listeContrats) {
    	//Mise à jour des dates de contrat
        for (String[] p : listeContrats) {
        	if(Integer.parseInt(p[2]) == idCommerce) {
        		LocalDate dateFinContrat = LocalDate.parse(p[5]);
	            String renouvelable = p[8];
	            
	            //Récupération de la date actuelle
	            String dateActuelle = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        	
	          //On vérifie si le contrat est renouvelable
	            if (renouvelable.equals("true")){
	            	//On vérifie si la date de fin du contrat est passée
	            	if(LocalDate.parse(dateActuelle).isAfter(dateFinContrat)) {
	            		//On met à jour la valeur de début et de fin
	                	p[4] = dateActuelle;
	                	p[5] = dateFin;
	                	
	                	//On retourne un message
	                    return "Le contrat a été modifié.";
	                }
	            	//Sinon on retourne un message
	                else {
	                    return "Votre contrat n'est pas encore terminé.";
	                }
	            }
	            //Sinon on retourne un message d'erreur
	            else{
	            	return "Votre contrat n'est pas renouvelable.";
	            }
            }
        }
        
        //Retourne un message d'erreur
        return "Vérifiez que vous ayez renseigné le bon identifiant.";
	}
	
	/**
	 * @brief	Méthode principale
	 */
	
	public static void main(String[] args) {
		//Affichage d'un message
		System.out.println("\nProduit proposés :\n");
		
		//On initialise la liste des contrats
		List<String[]> listeContrats = Contrat.initialisationListeContrats();
				
		//On récupère la valeur
		String produits = proposeProduit(2, listeContrats);
		
		//Affichage d'un message
		System.out.println(produits);
		
		//Affichage d'un message
		System.out.println("\nRenouveler un contrat :\n");
		
    	//Appel de fonction
    	String message = renouvelerContrat(1, "2027-01-03", listeContrats);
    	
    	//Affichage d'un message
    	System.out.println(message);
    	
    	//Affichage d'un message
    	System.out.println("\nDans cet exemple, on voit que l'on ne peut pas renouveler un contrat n'étant pas terminé. Il n'a donc pas été ajouté dans la liste de Contrats de son centre de tri.");
    	
    	//Appel de fonction
    	message = renouvelerContrat(2, "2027-01-03", listeContrats);
    	
    	//Affichage d'un message
    	System.out.println("\n" + message);
    	
    	//Affichage d'un message
    	System.out.println("\nDans cet exemple, on voit que la date de fin du contrat de ce commerce a été modifiée. Avant : 2023-03-14 et après : 2027-01-03.");
 
	    //Affichage d'un message
	    System.out.println("\nContrats du centre de tri EcoTri :");
	   
	    //Sauvegarde de la liste des poubelles et affichage
  		Contrat.sauvegarderContrats(listeContrats);
  	    Contrat.afficherListeContrats(1);
	}
}