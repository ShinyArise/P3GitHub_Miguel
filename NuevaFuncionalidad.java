package programacion;
import java.util.Scanner;
public class P1EJ3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Scanner sc = new Scanner (System.in);
		
		System.out.println("Dime la hora:");
		int hora = sc.nextInt();
		System.out.println("Dime los minutos:");
		int min = sc.nextInt();
		System.out.println("Dime los segundos:");
		int sec = sc.nextInt();
		
		if (hora >= 0 && hora <= 23 && min >= 0 && min <= 59 && sec >= 0 && sec <= 59) {
			
			sec += 10;
			if (sec > 59) {
				
				sec -= 60;
				min += 1;
				if (min > 59) {
					
					min -= 60;
					hora += 1;
					if (hora > 23) {
						
						hora -= 24;
						System.out.println("La hora seria " + hora + ":" + min + ":" + sec + " am");
					}
					else
						if (hora > 12)
							System.out.println("La hora seria " + hora + ":" + min + ":" + sec + "\nLa hora seria " + (hora - 12) + ":" + min + ":" + sec + " pm");
						else if (hora == 12)
							System.out.println("La hora seria " + hora + ":" + min + ":" + sec + " pm");
						else
							System.out.println("La hora seria " + hora + ":" + min + ":" + sec + " am");
				}
				else
					if (hora > 12)
						System.out.println("La hora seria " + hora + ":" + min + ":" + sec  + "\nLa hora seria " + (hora - 12) + ":" + min + ":" + sec + " pm");
					else if (hora == 12)
						System.out.println("La hora seria " + hora + ":" + min + ":" + sec + " pm");
					else
						System.out.println("La hora seria " + hora + ":" + min + ":" + sec + " am");
			}
			else
				if (hora > 12)
					System.out.println("La hora seria " + hora + ":" + min + ":" + sec + "\nLa hora seria " + (hora - 12) + ":" + min + ":" + sec + " pm");
				else if (hora == 12)
					System.out.println("La hora seria " + hora + ":" + min + ":" + sec + " pm");
				else
					System.out.println("La hora seria " + hora + ":" + min + ":" + sec + " am" );
		}
		else
			System.out.println("Hora introducida no valida");
		
		
		
		
		
		
		
		
	}

}
