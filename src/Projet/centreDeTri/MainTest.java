package centreDeTri;

import java.util.Scanner;

public class MainTest {
	public static void main(String[] args) {
		//Affichage d'un menu
		System.out.println("-- MENU --\n");
		System.out.println("1 - Classe Depot");
		System.out.println("2 - Classe Menage");
		System.out.println("3 - Classe CentreDeTri");
		System.out.println("4 - Classe Commerce");
		
		//Lecture du choix de l'utilisateur
		Scanner sc = new Scanner(System.in);
		System.out.printf("\nSaisir un entier : ");
		
		//Déclaration et initialisation de cette variable par lecture de l'entrée
		int choix = sc.nextInt();
		
		switch(choix) {
			case 1:
				Depot.main(null);
				break;
			case 2:
				Menage.main(null);
				break;
			case 3:
				CentreDeTri.main(null);
				break;
			case 4:
				Commerce.main(null);
				break;
			default:
				System.out.println("\nVous n'avez rien choisi.");
				break;
		}
		
		//Fermeture de l'entrée
		sc.close();
	}
}
