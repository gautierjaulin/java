/**
*
* Programme Java pour implémenter un centre de tri
*
* @file 	PoubelleClassique.java
* @author  	Groupe 5
* @version 	1.0 Premier jet
* @date   	3 avril 2023
* @brief	Projet Java
* 
*/

package centreDeTri;

/**
 * @class 	PoubelleClassique
 * @author  Group 5
 * @version 1.0
 * @date 	3 avril 2023
 */

class PoubelleClassique extends Poubelle{
	private String produit;
	private Float poids;
	
	/**
	 * @brief	Accesseur de l'attribut produit
	 * @return	le produit déposé
	 */
	
	public String getProduit() {
		return produit;
	}
	
	/**
	 * @brief	Mutateur de l'attribut produit
	 */
	
	public void setProduit(String produit) {
		this.produit = produit;
	}
	
	/**
	 * @brief	Accesseur de l'attribut poids
	 * @return	le poids du produit
	 */
	
	public Float getPoids() {
		return poids;
	}
	
	/**
	 * @brief	Mutateur de l'attribut poids
	 */
	
	public void setPoids(Float poids) {
		this.poids = poids;
	}
	
	/**
	 * @brief	Méthode pour vérifier si le dépôt est dans la bonne poubelle
	 * @return  vrai ou faux en fonction de si le dépôt est bon ou non
	 */
	
	public boolean correspond(String typeDepot,Integer idPoubelle) {
		//Vérification du type déposé par l'utilisateur
		if(typeDepot.equals(String.valueOf(TypeDechet.AUTRE)))
		{
			return(true);
		}
		
		return (false);
	}
	
	/**
	 * @brief	Constructeur de la classe PoubelleClassique
	 */
	
	public PoubelleClassique(int idPoubelle, String type, String emplacement) {
		super(idPoubelle, type, emplacement);
	}
}
