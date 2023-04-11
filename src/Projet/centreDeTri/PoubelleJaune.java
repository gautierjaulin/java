/**
*
* Programme Java pour implémenter un centre de tri
*
* @file 	PoubelleJaune.java
* @author  	Groupe 5
* @version 	1.0 Premier jet
* @date   	3 avril 2023
* @brief	Projet Java
* 
*/

package centreDeTri;

/**
 * @class 	PoubelleJaune
 * @author  Group 5
 * @version 1.0
 * @date 	3 avril 2023
 */

class PoubelleJaune extends Poubelle{
	private Float poids;

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
	 * @brief	Constructeur de PoubelleJaune
	 */
	
	public PoubelleJaune(int idPoubelle, String type, String emplacement) {
		super(idPoubelle, type, emplacement);
	}
	
	/**
	* Vérifie si le dépôt est dans la bonne poubelle
	* @param typeDepot le type de déchet à vérifier
	* @return vrai si le dépôt est bon, faux sinon
	*/
	
	public boolean correspond(String typeDepot,Integer idPoubelle) {
		//Vérification du type déposé par l'utilisateur
		if(typeDepot.equals(String.valueOf(TypeDechet.EMBALLAGE)) || typeDepot.equals(String.valueOf(TypeDechet.CARTON)) || typeDepot.equals(String.valueOf(TypeDechet.PLASTIQUE)) || typeDepot.equals(String.valueOf(TypeDechet.CANNETTE)) || typeDepot.equals(String.valueOf(TypeDechet.CONSERVE)))
		{
			return(true);
		}
		
		return (false);
	}
}
