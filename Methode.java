import java.util.Random;
import java.util.Scanner;

public class Methode {

	static Scanner sc = new Scanner(System.in);

	// Saisie pseudo du joueur 1
	public static String Joueur1() {

		String joueur1;

		System.out.print("Pseudo du joueur 1 (X) : ");
		joueur1 = sc.nextLine();

		while (joueur1.length() == 0) {
			System.out.print("Pseudo du joueur 1 (X) incorrecte! Réessayez : ");
			joueur1 = sc.nextLine();
		}
		return joueur1;
	}

	// Saisie pseudo du joueur 2
	public static String Joueur2() {

		String joueur2;

		System.out.print("Pseudo du joueur 2 (O) : ");
		joueur2 = sc.nextLine();

		while (joueur2.length() == 0) {
			System.out.print("Pseudo du joueur 1 (O) incorrecte! Réessayez : ");
			joueur2 = sc.nextLine();
		}
		return joueur2;
	}

	public static String Joueur3() {

		String joueur3;

		joueur3 = "Ordi";

		return joueur3;
	}

	// Remplissage du tableau de jeu
	public static char[][] remplissagePlateau(int colonne, int ligne) {

		char[][] tableauDeJeu = new char[colonne][ligne];

		for (int a = 0; a < colonne; a++) {
			for (int b = 0; b < ligne; b++) {
				tableauDeJeu[a][b] = ' ';
			}
		}
		return tableauDeJeu;
	}

	// Demande de placement du jeton des joueurs : retourne l'entier
	public static int choixPlacement(boolean pair, String joueur1, String joueur2) {

		int choixPlacement = 0;

		if (pair != true) {
			System.out.print(joueur2 + ", placez votre jeton entre 1 et 7 :");
			choixPlacement = sc.nextInt();
		} else {
			System.out.print(joueur1 + ", placez votre jeton entre 1 et 7 :");
			choixPlacement = sc.nextInt();
		}

		return choixPlacement;
	}

	// Demande de placement du jeton du joueur et de l'ordi : retourne l'entier
	public static int choixPlacementetOrdi(boolean pair, String joueur1, String joueur3) {

		int choixPlacement = 0;

		if (pair != true) {
			choixPlacement = (int) (Math.random() * 7) + 1;
			System.out.print(joueur3 + ", place son jeton entre 1 et 7 :" + choixPlacement);

		} else {
			System.out.print(joueur1 + ", place son jeton entre 1 et 7 :");
			choixPlacement = sc.nextInt();

		}

		return choixPlacement;
	}

	// Détermination de s'il y a un gagnant
	public static boolean Gagnant(int jetonsAlignés, char jetonActuel, int leGagnant) {

		boolean gagnant = false;
		int jetonsAlignésMax = 4;

		if (jetonsAlignés >= jetonsAlignésMax && jetonActuel == 'X') {
			gagnant = true;

		} else if (jetonsAlignés >= jetonsAlignésMax && jetonActuel == 'O') {
			gagnant = true;

		}
		return gagnant;
	}

	// MethodeJoueurAunContreUn
	public static void UnContreUn() {
		Scanner sc = new Scanner(System.in);// Positionnement du jeton sur le tableau

		// Déclaration des variables

		int ligne = 6;
		int colonne = 7;
		int tour = 0;
		int choixPlacement;
		int indiceColonne;
		int indiceLigne;

		boolean win = false;
		boolean pair = true;

		String joueur1;
		String joueur2;
		char jetonActuel = 0;

		char[][] tableauDeJeu;
		// Saisie des pseudos des joueurs
		joueur1 = Methode.Joueur1();
		joueur2 = Methode.Joueur2();

		// Remplissage du tableau de jeu
		tableauDeJeu = Methode.remplissagePlateau(colonne, ligne);

		// StartGame
		do {
			System.out.println("C'est le tour n° : " + tour);
			pair = tour % 2 == 0;

			// Affichage du tableau
			for (int c = 0; c < (colonne * 4) + 1; c++) {
				System.out.print("=");
			}
			System.out.println();
			for (int d = 0; d < ligne; d++) {
				System.out.print("| ");
				for (int e = 0; e < colonne; e++) {
					System.out.print(tableauDeJeu[e][d] + " | ");
				}
				System.out.println();
			}
			for (int c = 0; c < (colonne * 4) + 1; c++) {
				System.out.print("=");
			}
			System.out.println();
			System.out.println("| 1 | 2 | 3 | 4 | 5 | 6 | 7 |");
			System.out.println();

			// Demande la saisie du jeton en fonction de celui qui commence
			choixPlacement = Methode.choixPlacement(pair, joueur1, joueur2);
			System.out.println();

			// Demande la saisie forcée de placement du jeton en fonction de celui qui
			// commence
			while (choixPlacement <= 0 || choixPlacement > 7 || tableauDeJeu[choixPlacement - 1][0] != ' ') {
				System.out.print("Impossible de placer ! Réessayez :");
				choixPlacement = sc.nextInt();
			}

			System.out.println();
			// Positionnement du jeton sur le plateau
			indiceColonne = choixPlacement - 1;
			indiceLigne = ligne - 1;
			// gère la gravité
			while (tableauDeJeu[indiceColonne][indiceLigne] != ' ') {
				indiceLigne--;
			}

			if (pair != true) {
				tableauDeJeu[indiceColonne][indiceLigne] = 'O';
				jetonActuel = 'O';
			} else {
				tableauDeJeu[indiceColonne][indiceLigne] = 'X';
				jetonActuel = 'X';
			}

			// Vérification de s'il y a un gagnant
			int jetonsAlignésHorizontalement = 0;
			int placementColonne = indiceColonne;
			int placementLigne = indiceLigne;

			// regarder Horizontalement
			while (placementColonne < colonne - 1 && tableauDeJeu[placementColonne][placementLigne] == jetonActuel) {
				jetonsAlignésHorizontalement++;
				placementColonne++;
			}

			placementColonne = indiceColonne;
			jetonsAlignésHorizontalement = jetonsAlignésHorizontalement - 1;

			while (placementColonne >= 0 && tableauDeJeu[placementColonne][placementLigne] == jetonActuel) {
				jetonsAlignésHorizontalement++;
				placementColonne--;
			}

			// regarder Verticalement
			int jetonsAlignésVerticalement = 0;
			placementColonne = indiceColonne;
			placementLigne = indiceLigne;

			while (placementLigne < ligne && tableauDeJeu[placementColonne][placementLigne] == jetonActuel) {
				placementLigne++;
				jetonsAlignésVerticalement++;
			}

			// regarder Haut-Gauche
			int jetonsAlignésHG = -1;
			placementColonne = indiceColonne;
			placementLigne = indiceLigne;

			while (placementColonne >= 0 && placementLigne >= 0
					&& tableauDeJeu[placementColonne][placementLigne] == jetonActuel) {
				placementLigne--;
				placementColonne--;
				jetonsAlignésHG++;
			}

			placementColonne = indiceColonne;
			placementLigne = indiceLigne;

			while (placementColonne < colonne && placementLigne < ligne
					&& tableauDeJeu[placementColonne][placementLigne] == jetonActuel) {
				placementLigne++;
				placementColonne++;
				jetonsAlignésHG++;
			}

			// regarderHaut-Droite
			int jetonsAlignésHD = -1;
			placementColonne = indiceColonne;
			placementLigne = indiceLigne;

			while (placementLigne >= 0 && placementColonne < colonne
					&& tableauDeJeu[placementColonne][placementLigne] == jetonActuel) {
				placementLigne--;
				placementColonne++;
				jetonsAlignésHD++;
			}

			placementColonne = indiceColonne;
			placementLigne = indiceLigne;

			while (placementLigne < ligne && placementColonne >= 0
					&& tableauDeJeu[placementColonne][placementLigne] == jetonActuel) {
				placementLigne++;
				placementColonne--;
				jetonsAlignésHD++;
			}

			// Détermination de s'il y a un gagnant, si oui, quel joueur a gagné
			if (jetonsAlignésHorizontalement >= 4 || jetonsAlignésVerticalement >= 4 || jetonsAlignésHG >= 4
					|| jetonsAlignésHD >= 4) {

				int jetonsAlignés = 4;
				// Détermination de s'il y a un gagnant
				win = Methode.Gagnant(jetonsAlignés, jetonActuel, jetonsAlignés);
				// Détermination de quel joueur a gagné

			}

			tour++;

			// Fin de la partie si le tableau est remplie
			if (tour == 41) {

				System.out.println("tableau plein");

				Menu.menu();

			}
		} while (!win);

		// Affichage final du tableau
		for (int c = 0; c < (colonne * 4) + 1; c++) {
			System.out.print("=");
		}
		System.out.println();
		for (int d = 0; d < ligne; d++) {
			System.out.print("| ");
			for (int e = 0; e < colonne; e++) {
				System.out.print(tableauDeJeu[e][d] + " | ");
			}
			System.out.println();
		}
		for (int c = 0; c < (colonne * 4) + 1; c++) {
			System.out.print("=");
		}
		System.out.println();
		System.out.println("| 1 | 2 | 3 | 4 | 5 | 6 | 7 |");
		System.out.println();

		// Annonce du gagnant
		if (jetonActuel == 'X') {
			System.out.println("Le gagnant est : " + joueur1);
		} else if (jetonActuel == 'O') {
			System.out.println("Le gagnant est : " + joueur2);
		}

		Menu.menu();
	}

	public static void UnContreIa() {
		Scanner sc = new Scanner(System.in);

		// Déclaration des variables

		int ligne = 6;
		int colonne = 7;
		int tour = 0;
		int choixPlacement;
		int indiceColonne;
		int indiceLigne;

		boolean win = false;
		boolean pair = true;

		String joueur1;
		String joueur3;
		char jetonActuel = 0;

		char[][] tableauDeJeu;
		// Saisie des pseudos des joueurs
		joueur1 = Methode.Joueur1();
		joueur3 = Methode.Joueur3();

		// Remplissage du tableau de jeu
		tableauDeJeu = Methode.remplissagePlateau(colonne, ligne);

		// StartGame
		do {
			System.out.println("C'est le tour n° : " + tour);
			pair = tour % 2 == 0;

			// Affichage du tableau
			for (int c = 0; c < (colonne * 4) + 1; c++) {
				System.out.print("=");
			}
			System.out.println();
			for (int d = 0; d < ligne; d++) {
				System.out.print("| ");
				for (int e = 0; e < colonne; e++) {
					System.out.print(tableauDeJeu[e][d] + " | ");
				}
				System.out.println();
			}
			for (int c = 0; c < (colonne * 4) + 1; c++) {
				System.out.print("=");
			}
			System.out.println();
			System.out.println("| 1 | 2 | 3 | 4 | 5 | 6 | 7 |");
			System.out.println();

			// Demande la saisie du jeton en fonction de celui qui commence
			choixPlacement = Methode.choixPlacementetOrdi(pair, joueur1, joueur3);
			System.out.println();

			// Demande la saisie forcée de placement du jeton en fonction de celui qui
			// commence
			while (choixPlacement <= 0 || choixPlacement > 7 || tableauDeJeu[choixPlacement - 1][0] != ' ') {
				if (pair == true) {
					System.out.print("Impossible de placer ! Réessayez :");
					choixPlacement = sc.nextInt();
				} else {
					choixPlacement = (int) (Math.random() * 7) + 1;
				}
			}

			System.out.println();
			// Positionnement du jeton sur le plateau
			indiceColonne = choixPlacement - 1;
			indiceLigne = ligne - 1;

			while (tableauDeJeu[indiceColonne][indiceLigne] != ' ') {
				indiceLigne--;
			}

			if (pair != true) {
				tableauDeJeu[indiceColonne][indiceLigne] = 'O';
				jetonActuel = 'O';
			} else {
				tableauDeJeu[indiceColonne][indiceLigne] = 'X';
				jetonActuel = 'X';
			}

			// Vérification de s'il y a un gagnant
			int jetonsAlignésHorizontalement = 0;
			int placementColonne = indiceColonne;
			int placementLigne = indiceLigne;

			// regarder Horizontalement
			while (placementColonne < colonne - 1 && tableauDeJeu[placementColonne][placementLigne] == jetonActuel) {
				jetonsAlignésHorizontalement++;
				placementColonne++;
			}

			placementColonne = indiceColonne;
			jetonsAlignésHorizontalement = jetonsAlignésHorizontalement - 1;

			while (placementColonne >= 0 && tableauDeJeu[placementColonne][placementLigne] == jetonActuel) {
				jetonsAlignésHorizontalement++;
				placementColonne--;
			}

			// regarder Verticalement
			int jetonsAlignésVerticalement = 0;
			placementColonne = indiceColonne;
			placementLigne = indiceLigne;

			while (placementLigne < ligne && tableauDeJeu[placementColonne][placementLigne] == jetonActuel) {
				placementLigne++;
				jetonsAlignésVerticalement++;
			}

			// regarder Haut-Gauche
			int jetonsAlignésHG = -1;
			placementColonne = indiceColonne;
			placementLigne = indiceLigne;

			while (placementColonne >= 0 && placementLigne >= 0
					&& tableauDeJeu[placementColonne][placementLigne] == jetonActuel) {
				placementLigne--;
				placementColonne--;
				jetonsAlignésHG++;
			}

			placementColonne = indiceColonne;
			placementLigne = indiceLigne;

			while (placementColonne < colonne && placementLigne < ligne
					&& tableauDeJeu[placementColonne][placementLigne] == jetonActuel) {
				placementLigne++;
				placementColonne++;
				jetonsAlignésHG++;
			}

			// regarderHaut-Droite
			int jetonsAlignésHD = -1;
			placementColonne = indiceColonne;
			placementLigne = indiceLigne;

			while (placementLigne >= 0 && placementColonne < colonne
					&& tableauDeJeu[placementColonne][placementLigne] == jetonActuel) {
				placementLigne--;
				placementColonne++;
				jetonsAlignésHD++;
			}

			placementColonne = indiceColonne;
			placementLigne = indiceLigne;

			while (placementLigne < ligne && placementColonne >= 0
					&& tableauDeJeu[placementColonne][placementLigne] == jetonActuel) {
				placementLigne++;
				placementColonne--;
				jetonsAlignésHD++;
			}

			// Détermination de s'il y a un gagnant, si oui, quel joueur a gagné
			if (jetonsAlignésHorizontalement >= 4 || jetonsAlignésVerticalement >= 4 || jetonsAlignésHG >= 4
					|| jetonsAlignésHD >= 4) {

				int jetonsAlignés = 4;
				// Détermination de s'il y a un gagnant
				win = Methode.Gagnant(jetonsAlignés, jetonActuel, jetonsAlignés);
				// Détermination de quel joueur a gagné

			}

			tour++;

			// Fin de la partie si le tableau est remplie
			if (tour == 41) {

				System.out.println("tableau plein");

				Menu.menu();
			}
		} while (!win);

		// Affichage final du tableau
		for (int c = 0; c < (colonne * 4) + 1; c++) {
			System.out.print("=");
		}
		System.out.println();
		for (int d = 0; d < ligne; d++) {
			System.out.print("| ");
			for (int e = 0; e < colonne; e++) {
				System.out.print(tableauDeJeu[e][d] + " | ");
			}
			System.out.println();
		}
		for (int c = 0; c < (colonne * 4) + 1; c++) {
			System.out.print("=");
		}
		System.out.println();
		System.out.println("| 1 | 2 | 3 | 4 | 5 | 6 | 7 |");
		System.out.println();

		// Annonce du gagnant
		if (jetonActuel == 'X') {
			System.out.println("Le gagnant est : " + joueur1);
		} else if (jetonActuel == 'O') {
			System.out.println("Le gagnant est : " + joueur3);
		}

		Menu.menu();
	}

}
