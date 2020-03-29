
import java.util.Scanner;
import java.util.Random;

public class Menu {

	public static void menu() {
		Scanner sc = new Scanner(System.in);
		int choix;
		System.out.print("Saisissez le nombre de joueurs, pour quitter(3): ");
		choix = Integer.parseInt(sc.nextLine());
		do {	
				switch (choix) {		
					
				case 1:
						Methode.UnContreIa();
					break;
					
				case 2: 
						Methode.UnContreUn();
					break;
				
				case 3:
					System.out.println("GOODBYE, ADIOS, AU REVOIR, HASTA LUEGO, HASTA LA VISTA");
				
				default :
					System.out.println("Mauvais choix! veuillez resaisir un nombre");
					choix = sc.nextInt();
					break;
				}
				
		}while(choix > 3 || choix < 1);
			
			
	}
}
