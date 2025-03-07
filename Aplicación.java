package programacion;
import java.util.Scanner;

public class Pruebas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		Scanner sc = new Scanner (System.in);
		System.out.println("Introduce tu numero de DNI sin letra");
		int dni = sc.nextInt();
		if (dni >=0 && dni <= 99999999) { //Valido el número 
			int letra = dni%23;
			char l1 = '?';
			switch (letra) { //Cambio el valor de l1 en función del resto
			case 0:
				l1 = 'T';
				break;
			case 1:
				l1 = 'R';
				break;
			case 2:
				l1 = 'W';
				break;
			case 3:
				l1 = 'A';
				break;
			case 4:
				l1 = 'G';
				break;
			case 5:
				l1 = 'M';
				break;
			case 6:
				l1 = 'Y';
				break;
			case 7:
				l1 = 'F';
				break;
			case 8:
				l1 = 'P';
				break;
			case 9:
				l1 = 'D';
				break;
			case 10:
				l1 = 'X';
				break;
			case 11:
				l1 = 'B';
				break;
			case 12:
				l1 = 'N';
				break;
			case 13:
				l1 = 'J';
				break;
			case 14:
				l1 = 'Z';
				break;
			case 15:
				l1 = 'S';
				break;
			case 16:
				l1 = 'Q';
				break;
			case 17:
				l1 = 'V';
				break;
			case 18:
				l1 = 'H';
				break;
			case 19:
				l1 = 'L';
				break;
			case 20:
				l1 = 'C';
				break;
			case 21:
				l1 = 'K';
				break;
			case 22:
				l1 = 'E';
				break;
			}

			if (dni <10) //Compruebo la longitud del numero para saber cuantos ceros añadir
				System.out.println("0000000" + dni + l1);
			else if (dni < 100)	
				System.out.println("000000" + dni + l1);
			else if (dni < 1000)
				System.out.println("00000" + dni + l1);
			else if (dni < 10000)
				System.out.println("0000" + dni + l1);
			else if (dni < 100000)
				System.out.println("000" + dni + l1);
			else if (dni < 1000000)
				System.out.println("00" + dni + l1);
			else if (dni < 10000000)
				System.out.println("0" + dni + l1);
			else
				System.out.println("" + dni + l1);
		}
		else
			System.out.println("Numero de DNI no valido");
	}

}
