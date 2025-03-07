package Practica5_Elizabeth_Miguel;

/**@author Elizabeth Suarez (DAM) - Miguel Gallego (DAM) */

import java.util.Scanner;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Prueba1 {
	public static final String RESET = "\u001B[0m";
	public static boolean acc = false;
	private static Tablero tablero = new Tablero();
	private static Scanner sc = new Scanner(System.in);
	static int conTurnos =1;
	private static Clip musicaClip;
	private static FloatControl musicaControl;
	static boolean salida =false;
	static String respuesta ;

	public static void main(String[] args) throws InterruptedException {
		iniciarMusicaFondo("Undertale Ost： 098 - Battle Against a True Hero.wav");
		while (true) {
			if(salida == true) {
				break;
			}
			System.out.println("Bienvenido al Juego de Estrategia por Turnos!");
			mostrarMenu() ;	
		}
	}
	
	/**
	 *@author Elizabeth Suarez
	 *Muestra un menu de colores a elegir por el usuario para sus personajes en el juego
	 *@return Retorna el color elegido
	 */
	public static String menuColores() {

		int opcion = 0;

		String menu = 
				"\u001B[36m╔══════════════════════════════╗\n" +
						"║  \u001B[35m»»» \u001B[33mMENÚ DE COLORES \u001B[35m«««\u001B[36m  ║\n" +
						"╠══════════════════════════════╣\n" +
						"║ \u001B[37m1. \u001B[31mRojo    \u001B[37m►► \u001B[31m█\u001B[37m      ║\n" +
						"║ \u001B[37m2. \u001B[32mVerde   \u001B[37m►► \u001B[32m█\u001B[37m      ║\n" +
						"║ \u001B[37m3. \u001B[33mAmarillo\u001B[37m►► \u001B[33m█\u001B[37m      ║\n" +
						"║ \u001B[37m4. \u001B[34mAzul    \u001B[37m►► \u001B[34m█\u001B[37m      ║\n" +
						"║ \u001B[37m5. \u001B[35mMorado  \u001B[37m►► \u001B[35m█\u001B[37m      ║\n" +
						"╠══════════════════════════════╣\n" +
						"║ \u001B[37mIngrese opción (1-5): \u001B[36m     ║\n" +
						"╚══════════════════════════════╝\u001B[0m";

		System.out.println(menu);

		boolean entradaValida = false;
		do {
			System.out.print("\u001B[36m║ \u001B[37m➤ \u001B[0m"); 
			opcion = sc.nextInt();

			if(opcion >= 1 && opcion <= 5) {
				entradaValida = true;
			} else {
				System.out.println("\u001B[36m╠══════════════════════════════╣\n║\u001B[31m ¡Error! Debe ser entre 1-5 \u001B[36m║\n\u001B[36m╠══════════════════════════════╣");
			}
		} while(!entradaValida);

		System.out.println("\u001B[36m╚══════════════════════════════╝\u001B[0m");

		switch(opcion) {
		case 1: return "rojo";
		case 2: return "verde";
		case 3: return "amarillo"; 
		case 4: return "azul";
		case 5: return "morado";
		default: return " ";
		}
	}
	
	/**
	 * @author Miguel Gallego
	 * Metodo para la creacion del ejercito
	 * @param jugador Indica el jugador que llamo al metodo
	 * @param colMin Indica la columna minima en la que puede posicionar sus personajes el jugador
	 * @param colMax Indica la columna maxima en la que puede posicionar sus personajes el jugador
	 */
	private static void crearEjercito(Jugador jugador, int colMin, int colMax) {
		int costeTotal = 0;
		final String BORDE = "\u001B[36m║";
		final String RESET = "\u001B[0m";

		String titulo = "\u001B[36m╔══════════════════════════════════╗\n" +
				BORDE + " \u001B[33mCREACIÓN DE EJÉRCITO - " + 
				jugador.getNombre().toUpperCase() + RESET + " \u001B[36m║\n" +
				"╠══════════════════════════════════╣\n";

		while(costeTotal < 50) {
			String menu = titulo +
					BORDE + " \u001B[37mCoste disponible: \u001B[33m" + (50 - costeTotal) + "₲ \u001B[36m║\n" +
					"╠══════════════════════════════════╣\n" +
					BORDE + " \u001B[37mSelecciona un personaje:       \u001B[36m║\n" +
					BORDE + " \u001B[32m[C]\u001B[37maballero \u001B[36m(25₲) ►► \u001B[34m🛡️ \u001B[36m║\n" +
					BORDE + " \u001B[32m[S]\u001B[37moldado   \u001B[36m(10₲) ►► \u001B[33m⚔️ \u001B[36m║\n" +
					BORDE + " \u001B[32m[L]\u001B[37mancero   \u001B[36m(5₲)  ►► \u001B[35m⛏ \u001B[36m║\n" +
					BORDE + " \u001B[32m[A]\u001B[37mrquero   \u001B[36m(15₲) ►► \u001B[31m🏹 \u001B[36m║\n" +
					BORDE + " \u001B[31m[X]\u001B[37m Salir               \u001B[36m     ║\n" +
					"╠══════════════════════════════════╣\n" +
					BORDE + " \u001B[37mOpción: \u001B[36m                     ║\n" +
					"╚══════════════════════════════════╝" + RESET; 

			System.out.println(menu);
			System.out.print("\u001B[36m║ \u001B[37m➤ " + RESET);

			char opcion = sc.next().toLowerCase().charAt(0);
			System.out.println("\u001B[36m╚══════════════════════════════════╝" + RESET);


			Personaje personaje = null;
			int coste = 0;

			switch(opcion) {
			case 'c':
				personaje = new Caballero(0, 0, jugador.getColor());
				coste = personaje.getCosto();
				break;
			case 's':
				personaje = new Soldado(0, 0, jugador.getColor());
				coste = personaje.getCosto();
				break;
			case 'l':
				personaje = new Lancero(0, 0, jugador.getColor());
				coste = personaje.getCosto();
				break;
			case 'a':
				personaje = new Arquero(0, 0, jugador.getColor());
				coste = personaje.getCosto();
				break;
			case 'h':
				personaje = new Hechicero(0, 0, jugador.getColor());
				break;
			case 'x':
				if (jugador.getCantidadPersonajes()==0) {
					System.out.println(
							"\u001B[31m╔══════════════════════════════╗\n" +
									"║ \u001B[37m¡Ejercito Vacio! \u001B[31m      ║\n" +
									"╚══════════════════════════════╝" + RESET);
					continue;
				}
				break;
			default:
				System.out.println(
						"\u001B[31m╔══════════════════════════════╗\n" +
								"║ \u001B[37m¡PERSONAJE NO DISPONIBLE! \u001B[31m║\n" +
								"╚══════════════════════════════╝" + RESET);
				continue;
			}
			if (opcion == 'x')break;

			if(costeTotal + coste > 50) {
				System.out.println(
						"\u001B[31m╔══════════════════════════════╗\n" +
								"║ \u001B[37m¡SUPERAS EL COSTE MÁXIMO! \u001B[31m║\n" +
								"╚══════════════════════════════╝" + RESET);
				continue;
			}


			System.out.println("\u001B[36m╔══════════════════════════════════╗\n" +
					"\u001B[36m║ \u001B[37mIngrese posición (fila y columna) \u001B[36m║\n" +
					"\u001B[36m╚══════════════════════════════════╝" + RESET);
			int columna;
			int fila = obtenerCoordenada("fila", 0, 7);
			if (jugador.getNombre().equals("JUGADOR_A")) {
				columna = obtenerCoordenada("columna", 0, 1);
			}else {
				columna = obtenerCoordenada("columna", 6, 7);
			}


			if(columna < colMin || columna > colMax || tablero.ocupado(fila, columna)) {
				System.out.println(
						"\u001B[31m╔══════════════════════════════╗\n" +
								"║ \u001B[37m¡POSICIÓN INVÁLIDA!      \u001B[31m║\n" +
								"╚══════════════════════════════╝" + RESET);
				continue;
			}

			personaje.setPosicion(fila, columna);
			jugador.agregarPersonaje(personaje);
			tablero.colocarPersonaje(personaje, fila, columna);
			costeTotal += coste;

			if(50 - costeTotal == 5 ) {
				System.out.println(
						"\u001B[33m╔══════════════════════════════╗\n" +
								"║ \u001B[37m¡ÚLTIMOS RECURSOS!       \u001B[33m║\n" +
								"║ \u001B[37mFondos restantes: " + (50 - costeTotal) + "₲ \u001B[33m║\n" +
								"╚══════════════════════════════╝" + RESET);
				continue;
			}
		}

		System.out.println(
				"\u001B[32m╔══════════════════════════════╗\n" +
						"║ \u001B[37m¡EJÉRCITO COMPLETADO!    \u001B[32m║\n" +
						"╚══════════════════════════════╝" + RESET);

	}
	
	/**
	 * @author Elizabeth Suarez
	 * Indica el inicio del juego y da comienzo a la musica
	 * @param jugadorA
	 * @param jugadorB
	 */
	public static void inicioJuego(Jugador jugadorA, Jugador jugadorB) {

		System.out.println( "======================================");
		crearEjercito(jugadorA, 0, 1);
		System.out.println( "======================================");
		crearEjercito(jugadorB, 6, 7);
		cambiarMusica("Undertale OST - Ruins Extended.wav");
		tablero.imprimirTablero();

	}

	/**
	 * @author Miguel Gallego
	 * Obtiene la coordenada y comprueba si es posible posicionarse ahi
	 * @param tipo Tipo fila/columna en la que se mueve el personaje
	 * @param min Valor minimo de tablero donde puede posicionarse
	 * @param max Valor maximo de tablero donde puede posicionarse
	 * @return
	 */
	private static int obtenerCoordenada(String tipo, int min, int max) {
		int valor;
		do {
			System.out.print("\u001B[36m║ \u001B[37m" + tipo + " (" + min + "-" + max + "): \u001B[0m");
			valor = sc.nextInt();
			if(valor < min || valor > max) {
				System.out.println(
						"\u001B[31m╔══════════════════════════════╗\n" +
								"║ \u001B[37m¡VALOR FUERA DE RANGO!   \u001B[31m║\n" +
								"╚══════════════════════════════╝" + RESET);
			}
		} while(valor < min || valor > max);
		return valor;

	}

	/**
	 * @author Elizabeth Suarez
	 * Despliega el menu de acciones y llama a los diferentes metodos
	 * @param jugador Jugador que invoca el metodo
	 * @param enemigo Jugador enemigo
	 * @throws InterruptedException Excepcion para detener el programa
	 */
	private static void turnoJugador(Jugador jugador, Jugador enemigo) throws InterruptedException {
		final String BORDE = "\u001B[36m║";
		do {
			String menu = 
					"\u001B[36m╔══════════════════════════════╗\n" +
							BORDE + " \u001B[33m» TURNO " + conTurnos + " - " + 
							jugador.getNombre().toUpperCase() + " « \u001B[36m║\u001B[33m ⏳ \n\u001B[36m" +
							"╠══════════════════════════════╣\n" +
							BORDE + " \u001B[37m[\u001B[34mM\u001B[37m]OVER   \u001B[36m►► \u001B[34m🚶\u001B[36m     ║\n" +
							BORDE + " \u001B[37m[\u001B[31mA\u001B[37m]TACAR  \u001B[36m►► \u001B[31m⚔\u001B[36m     ║\n" +
							BORDE + " \u001B[37m[\u001B[32mS\u001B[37m]ANAR   \u001B[36m►► \u001B[32m❤\u001B[36m     ║\n" +
							"╠══════════════════════════════╣\n" +
							BORDE + " \u001B[37mSeleccione opción: \u001B[36m        ║\n" +
							"╚══════════════════════════════╝\u001B[0m";

			System.out.println(menu);
			System.out.print("\u001B[36m║ \u001B[37m➤ \u001B[0m"); 

			char opcion = sc.next().toLowerCase().charAt(0);
			System.out.println("\u001B[36m╚══════════════════════════════╝\u001B[0m");

			switch(opcion) {
			case 'm':
				moverPersonaje(jugador);
				break;
			case 'a':
				atacar(jugador, enemigo);
				break;
			case 's':
				curar(jugador);
				Thread.sleep(4000);
				acc=true;
				break;
			default:
				System.out.println(
						"\u001B[31m╔══════════════════════════════╗\n" +
								"║ \u001B[37m¡OPCIÓN INVÁLIDA! \u001B[31m       ║\n" +
						"╚══════════════════════════════╝\u001B[0m");
			}
		} while(!acc);

		acc = false;
		tablero.imprimirTablero();
	}
	
	/**
	 * @author Miguel Gallego
	 * Mueve a los personajes a otra casilla y comprueba si es posible, si esta ocupada, o si esta fuera de rango
	 * @param jugador Jugador que invoca el metodo
	 * @throws InterruptedException Excepcion para detener el programa 
	 */
	private static void moverPersonaje(Jugador jugador) throws InterruptedException {
		int nuevaFila = -1;
		int nuevaColumna = -1;

		do {
			System.out.println("Indica fila y columna del personaje a mover:");
			int fila = sc.nextInt();
			int columna = sc.nextInt();

			while (!(esPosicionValida(fila, columna))) {
				System.out.println(
						"\u001B[31m╔══════════════════════════════╗\n" +
								"║ \u001B[37m¡POSICIÓN INVÁLIDA!      \u001B[31m║\n" +
								"╚══════════════════════════════╝" + RESET);
				System.out.println("Vuelva a intentar (0-7):");
				fila = sc.nextInt();
				columna = sc.nextInt();
			}

			Personaje [] [] p = tablero.getPersonajes();

			if (tablero.ocupado(fila, columna) && jugador.getColor().equals(p [fila] [columna].getColor())){
				while(true){
					System.out.println("Indica nueva fila y columna:");
					nuevaFila = sc.nextInt();
					nuevaColumna = sc.nextInt();

					while (!(esPosicionValida(fila, columna))) {
						System.out.println(
								"\u001B[31m╔══════════════════════════════╗\n" +
										"║ \u001B[37m¡POSICIÓN INVÁLIDA!      \u001B[31m║\n" +
										"╚══════════════════════════════╝" + RESET);
						System.out.println("Vuelva a intentar (fila - columna):");
						nuevaFila = sc.nextInt();
						nuevaColumna = sc.nextInt();
					}

					if (tablero.ocupado(nuevaFila, nuevaColumna)) {
						System.out.println(
								"\u001B[31m╔══════════════════════════════╗\n" +
										"║ \u001B[37m¡CASILLA OCUPADA!      \u001B[31m║\n" +
										"╚══════════════════════════════╝" + RESET);
					}else {
						int distancia = Math.abs(nuevaFila - fila) + Math.abs(nuevaColumna - columna);


						if (Math.abs(nuevaFila - fila) == Math.abs(nuevaColumna - columna)) {
							if (distancia/2 > p[fila] [columna].getMovimientoMax()){
								System.out.println(
										"\u001B[31m╔══════════════════════════════╗\n" +
												"║ \u001B[37m¡POSICIÓN INVÁLIDA!      \u001B[31m║\n" +
												"╚══════════════════════════════╝" + RESET);
								System.out.println(p[fila] [columna].getNombre() + " no puede moverse tan lejos. Máximo: " 
										+ p[fila] [columna].getMovimientoMax() + " casillas.");
								continue;
							}
						}else if (distancia > p[fila] [columna].getMovimientoMax()) {
							System.out.println(
									"\u001B[31m╔══════════════════════════════╗\n" +
											"║ \u001B[37m¡POSICIÓN INVÁLIDA!      \u001B[31m║\n" +
											"╚══════════════════════════════╝" + RESET);
							System.out.println(p[fila] [columna].getNombre() + " no puede moverse tan lejos. Máximo: " 
									+ p[fila] [columna].getMovimientoMax() + " casillas.");
							continue;
						}
						if (p[fila] [columna].getSimbolo() != 'H'){
							reproducirSFX("Soldiers Marching Sound Effect.wav");
						}else {
							reproducirSFX("Teleportación ＂Teleport＂ (Sound Effect) - League of Legends.wav");
						}
						Thread.sleep(4000);
						tablero.moverPersonaje(fila, columna, nuevaFila, nuevaColumna);
						break;
					}
				}
				break;
			}

			System.out.println(
					"\u001B[31m╔══════════════════════════════╗\n" +
							"║ \u001B[37m¡MOVIMIENTO INVÁLIDO!      \u001B[31m║\n" +
							"╚══════════════════════════════╝" + RESET);
		}while(true);
		acc = true;
	}

	/**
	 * @author Elizabeth Suarez
	 * Metodo para atacar al personaje objetivo 
	 * @param jugador Jugador que invoca el metodo
	 * @param enemigo Jugador enemigo
	 * @throws InterruptedException Excepcion para detener el programa
	 */
	private static void atacar(Jugador jugador, Jugador enemigo) throws InterruptedException {
		Personaje[][] p = tablero.getPersonajes();

		int fila, columna, filaEnemigo, columnaEnemigo;
		Personaje atacante;

		do {
			System.out.println("Indica fila y columna del atacante:");
			fila = sc.nextInt();
			columna = sc.nextInt();
		} while (!esPosicionValida(fila, columna) || !jugador.ejercitoMio(fila, columna, p));

		atacante = p[fila][columna];

		if (atacante.getSimbolo()=='H'){
			int x =atacante.getx();
			int y = atacante.gety();
			for (int i = 0; i < p.length; i++){
				for (int j = 0; j < p[0].length; j++){
					p[i][j] = null;
				}
			}
			p[x][y] = atacante;
			reproducirSFX("DARK ENERGY - Sound Effect.wav");
			System.out.println( atacante.getNombre()+" arrasa con todo el tablero de un solo golpe. ¡Nadie queda en pie! 🔥☠");
			Thread.sleep(4000);
			enemigo.eliminarEjercito();
			acc= true; 

			return;
		}


		if (!hayEnemigoEnRango(atacante, fila, columna, p, jugador)) {
			System.out.println(
					"\u001B[31m╔══════════════════════════════╗\n" +
							"║ \u001B[37m¡POSICIÓN INVÁLIDA!      \u001B[31m║\n" +
							"╚══════════════════════════════╝" + RESET);
			System.out.println("\u001B[0mNo hay enemigos en rango de ataque.\u001B[0m");
			acc= false; 
			return;
		}


		do {
			System.out.println("Indica fila y columna del enemigo:");
			filaEnemigo = sc.nextInt();
			columnaEnemigo = sc.nextInt();


			if (!esPosicionValida(filaEnemigo, columnaEnemigo)) {
				System.out.println(
						"\u001B[31m╔══════════════════════════════╗\n" +
								"║ \u001B[37m¡POSICIÓN INVÁLIDA!      \u001B[31m║\n" +
								"╚══════════════════════════════╝" + RESET);
				continue;
			}

		} while (!esPosicionValida(filaEnemigo, columnaEnemigo) ||  jugador.ejercitoMio(filaEnemigo, columnaEnemigo, p) 
				|| p[filaEnemigo][columnaEnemigo] == null ||  !esAtaqueValido(atacante, fila, columna, filaEnemigo, columnaEnemigo)  
				);

		reproducirSFX("Sonido De espada Sonido Edit.wav");
		tablero.atacar(fila, columna, filaEnemigo, columnaEnemigo);

		acc= true; 
	}
	
	/**
	 * @author Miguel Gallego
	 * Comprueba que haya un enemigo en el rango del personaje escogido
	 * @param atacante Personaje que va a atacar
	 * @param fila Fila en la que se encuentra
	 * @param columna Columna en la que se encuentra
	 * @param p Array que contiene el ltablero
	 * @param jugador Jugador que invoca al metodo
	 * @return
	 */
	private static boolean hayEnemigoEnRango(Personaje atacante, int fila, int columna, Personaje[][] p, Jugador jugador) {
		int radio = atacante.getRadioAtaque();


		for (int i = -radio; i <= radio; i++) {  
			for (int j = -radio; j <= radio; j++) { 
				int nuevaFila = fila + i;
				int nuevaColumna = columna + j;

				if (esPosicionValida(nuevaFila, nuevaColumna) && p[nuevaFila][nuevaColumna] != null) {
					if (!jugador.ejercitoMio(nuevaFila, nuevaColumna, p)) {
						return true; 
					}
				}
			}
		}

		return false; 
	}
	
	/**
	 * @author Elizabeth Suarez
	 * Metodo para curar a todos los personajes de un jugador
	 * @param jugador Jugador que invoca al metodo
	 * @throws InterruptedException Excepcion que detiene el programa
	 */
	private static void curar(Jugador jugador) throws InterruptedException {
		System.out.println(jugador.getNombre() + " decide curar a sus personajes ❤");
		reproducirSFX("Curación ＂Heal＂ (Sound Effect) - League of Legends.wav");
		System.out.println();
		jugador.curarEjercito();

	}
	
	/**
	 * @author Miguel Gallego
	 * Comprueba si la posicion indicada esta dentro del tablero
	 * @param fila Fila escogida
	 * @param columna Columna escogida
	 * @return true/false 
	 */
	private static boolean esPosicionValida(int fila, int columna) {
		return fila >= 0 && fila < 8 && columna >= 0 && columna < 8;
	}

	/**
	 * @author Elizabeth Suarez
	 * Comprueba si la posicion del atacante y el enemigo son validas
	 * @param atacante Tipo de personaje atacante
	 * @param filaAtacante Fila del atacante
	 * @param columnaAtacante Columna del atacante
	 * @param filaEnemigo Fila del enemigo
	 * @param columnaEnemigo Columna del enemigo
	 * @return true/false
	 */
	private static boolean esAtaqueValido(Personaje atacante, int filaAtacante, int columnaAtacante, int filaEnemigo, int columnaEnemigo) {
		int distancia = Math.abs(filaAtacante - filaEnemigo) + Math.abs(columnaAtacante - columnaEnemigo);


		if (Math.abs(filaEnemigo - filaAtacante) == Math.abs(columnaEnemigo - columnaAtacante)) {
			if (distancia/2 > atacante.getRadioAtaque()){
				System.out.println(
						"\u001B[31m╔══════════════════════════════╗\n" +
								"║ \u001B[37m¡POSICIÓN INVÁLIDA!      \u001B[31m║\n" +
								"╚══════════════════════════════╝" + RESET);
				System.out.println(atacante.getNombre() + " no puede atacar tan lejos. Máximo: " 
						+ atacante.getRadioAtaque() + " casillas.");
				acc = false;
				return false;
			}
		}else if (distancia > atacante.getRadioAtaque()) {
			System.out.println(
					"\u001B[31m╔══════════════════════════════╗\n" +
							"║ \u001B[37m¡POSICIÓN INVÁLIDA!      \u001B[31m║\n" +
							"╚══════════════════════════════╝" + RESET);
			System.out.println(atacante.getNombre() + " no puede atacar tan lejos. Máximo: " 
					+ atacante.getRadioAtaque() + " casillas.");
			acc = false;
			return false;
		}
		return true;

	}
	
	/**
	 * @author Miguel Gallego
	 * Muestra un menu
	 * @throws InterruptedException Excepcion para detener el programa
	 */
	public static void mostrarMenu() throws InterruptedException {
		System.out.println(
				"\u001B[36m╔═══════════════════════════════════════════╗\n" +
						"║ \u001B[33m     MIGABETH CONQUEST\u001B[36m         ║\n" +
						"╠═══════════════════════════════════════════╣\n" +
						"║ \u001B[37m    1. Nueva Partida   ►\u001B[36m       ║\n" +
						"║ \u001B[37m    2. Personajes      ►►\u001B[36m      ║\n" +
						"║ \u001B[37m    3. Creditos        ►►►\u001B[36m     ║\n" +
						"║ \u001B[37m    4. Salir           ►►►►\u001B[36m    ║\n" +
						"╠═══════════════════════════════════════════╝\u001B[36m \n"   +
						"║ \u001B[37mIngrese opción (1-4): \u001B[36m         ║\n" +
						"╚═══════════════════════════════════════════╝\u001B[0m"
				);
		System.out.print("\u001B[36m║ \u001B[37m➤ \u001B[0m"); // Indicador de entrada
		int opcion =sc.nextInt() ;
		System.out.println("\u001B[36m╚════════════════════════════════╝\u001B[0m");


		switch (opcion) {
		case 1:
			cambiarMusica("Undertale Ost： 087 - Hopes and Dreams.wav");
			System.out.println("Elija el Color del jugador A");
			String color = menuColores();
			Jugador jugadorA = new Jugador("JUGADOR_A", color);

			System.out.println("Elija el Color del jugador B");
			color = menuColores();
			while (color.equals(jugadorA.getColor()) ) {
				System.out.println("El Jugador_A ya ha elegido ese color, selecciona otro");
				color = menuColores();	
			}
			Jugador jugadorB = new Jugador("JUGADOR_B", color);

			inicioJuego( jugadorA, jugadorB);

			while (!jugadorA.ejercitoEliminado() && !jugadorB.ejercitoEliminado()) {
				turnoJugador(jugadorA,jugadorB);
				if (jugadorB.ejercitoEliminado()) break;
				turnoJugador(jugadorB,jugadorB);

				conTurnos ++;
			}

			mostrarFinalDelJuego (jugadorA,jugadorB, conTurnos);

			break;
		case 2:
			cambiarMusica("DotaFlute.wav");
			mostrarPersonajes () ;
			sc.nextLine();
			System.out.println("Presione enter para salir");
			try {
				System.in.read(); 
			} catch (IOException e) {
				e.printStackTrace();
			}
			detenerMusica();
			iniciarMusicaFondo("Undertale Ost： 098 - Battle Against a True Hero.wav");
			sc.nextLine();
			mostrarMenu();
			break;
		case 3:
			cambiarMusica("Deltarune Chapter 2 OST： 03 - My Castle Town.wav");
			mostrarCreditos();
			sc.nextLine();
			System.out.println("Presione enter para salir");
			try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			detenerMusica();
			iniciarMusicaFondo("Undertale Ost： 098 - Battle Against a True Hero.wav");
			sc.nextLine();
			mostrarMenu();
			break;
		case 4:
			detenerMusica();
			salida =true;
			break;
		default:
			System.out.println("\u001B[36m║\u001B[0m Debe ser entre 1-4 \u001B[36m     ║\n"
					+ "\u001B[36m╚════════════════════════════════╝\n\u001B[0m");
		}

	}

	/**
	 * @author Elizabeth Suarez
	 * Muestra el final de juego
	 * @param jugadorA JugadorA
	 * @param jugadorB JugadorB
	 * @param conTurnos Cantidad de turnos 
	 * @throws InterruptedException Excepción para detener el programa
	 */
	public static void mostrarFinalDelJuego(Jugador jugadorA, Jugador jugadorB, int conTurnos) throws InterruptedException {
		System.out.println();
		cambiarMusica("Power Star Get! - Super Mario Galaxy Music.wav");
		System.out.println("\u001B[36m╔══════════════════════════════════════╗\n" +
				"║ \u001B[33m¡El juego ha terminado!\u001B[36m            ║\n" +
				"╠══════════════════════════════════════╣");

		if (jugadorA.ejercitoEliminado()) {
			System.out.println("║ \u001B[37m¡" + jugadorB.getNombre() + " ha ganado!\u001B[36m              ║\n" +
					"║ \u001B[37mSu victoria se realizó en " + conTurnos + " Turnos\u001B[36m ║");
			conTurnos = 0;
		} else {
			System.out.println("║ \u001B[37m¡" + jugadorA.getNombre() + " ha ganado!\u001B[36m              ║\n" +
					"║ \u001B[37mSu victoria se realizó en " + conTurnos + " Turnos\u001B[36m ║");
			conTurnos = 0;
		}

		System.out.println("╚══════════════════════════════════════╝\u001B[0m");


		Thread.sleep(6000);
		cambiarMusica("Undertale OST： 071 - Undertale.wav");
		tablero.eliminartablero();
		jugadorA.eliminarEjercito();
		jugadorB.eliminarEjercito();

		System.out.println("Desea ver el Teaser del nuevo personaje? (s/n)");
		sc.nextLine();
		respuesta = sc.nextLine();

		if (respuesta.equalsIgnoreCase("s")) {

			System.out.println("\u001B[36m╔════════════════════════════════════════════════════════════════════════════╗\n" +
					"║ \u001B[33mDesde lo más profundo de la muerte, surge un poder que desafía a los vivos.\u001B[36m\n" +
					"║ \u001B[33mUn guerrero que no necesita ejércitos… porque los crea con los caídos.\u001B[36m\n" +
					"╠════════════════════════════════════════════════════════════════════════════╣");
			reproducirSFX("Efecto de sonido de suspenso para trailer.wav");
			Thread.sleep(1500);
			System.out.print("║ \u001B[1m\u001B[31m💀 EL NIGROMANTE 💀\u001B[0m\u001B[36m\n" +
					"║ \u001B[1m\u001B[0m    ☠️ Domina la sombra de los que han caído.\u001B[0m\u001B[36m\n" +
					"║ \u001B[1m\u001B[0m    ⚔️ Convierte a sus enemigos en su propia legión.\u001B[0m\u001B[36m\n" +
					"║ \u001B[1m\u001B[0m    🔮 La muerte no lo detiene… la fortalece.\u001B[0m\u001B[36m\n" +       
					"╠═══════════════════════════════════════════════════════════════════════════╣\n"+
					"║ \u001B[1m\u001B[31m⚠\u001B[33m Prepárate, porque cuando él llegue… la guerra nunca termina.\u001B[0m\u001B[36m\n" +
					"╚═══════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
		} else {
			System.out.println("\u001B[36m╔═════════════════════════════════════════════════════════════════╗\n" +
					"║ \u001B[1m\u001B[31m⚔️\u001B[33m  El gran ejército ha vencido.\u001B[0m\u001B[36m\n" +
					"╠═════════════════════════════════════════════════════════════════╣\n"+
					"║ \u001B[1m\u001B[0mLas espadas se alzan, la victoria es eterna...\u001B[0m\u001B[36m\n" +
					"║ \u001B[1m\u001B[0mpero en la taberna ya están apostando por la próxima guerra.\u001B[33m 🍻\u001B[0m\u001B[36m\n" +
					"║ \u001B[1m\u001B[0mEl destino del reino ha sido sellado... ¿o no?\u001B[33m 🏆\u001B[0m\u001B[36m\n" +
					"╚═════════════════════════════════════════════════════════════════╝\u001B[0m");
		}
		System.out.println("Presione enter para salir");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		detenerMusica();
		iniciarMusicaFondo("Undertale Ost： 098 - Battle Against a True Hero.wav");
		sc.nextLine();
		mostrarMenu();
	}
	
	/**
	 * @author Elizabeth Suarez
	 * Funcion que muestra los creditos y los participantes en el proceso de desarrollo
	 * @throws InterruptedException Excepción para detener el programa
	 */
	public static void mostrarCreditos() throws InterruptedException {
		System.out.println("\u001B[36m╔═══════════════════════════════════════════════════════════════════╗\n" +
				"║ \u001B[33m            MIGABETH CONQUEST CREDITOS\u001B[36m                          ║\n" +
				"╠═══════════════════════════════════════════════════════════════════╣\n" +
				"║ \u001B[33m►Dirección:\u001B[36m                                                     ║\n" +
				"║ \u001B[37m   Directores del juego Eli y Miguel\u001B[36m                            ║\n" +
				"╠═══════════════════════════════════════════════════════════════════╣"  );
		Thread.sleep(1000);
		System.out.println("║ \u001B[33m►Diseño del Juego:\u001B[36m                                              ║\n" +
				"║ \u001B[37m   Diseñador de juego (Game Designer) Javier Sánchez \u001B[36m           ║\n" +
				"║ \u001B[37m   Diseñador de interfaz de usuario  Elizabeth Suárez\u001B[36m           ║\n" +
				"║ \u001B[37m   Diseñador de mecánicas (Gameplay Designer) Miguel Gallego\u001B[36m    ║\n" +
				"╠═══════════════════════════════════════════════════════════════════╣" );
		Thread.sleep(1000);
		System.out.println( "║ \u001B[33m►Programación y Desarrollo:\u001B[36m                                     ║\n" +
				"║ \u001B[37m   Programador de gameplay (Gameplay Programmer) Eli y Miguel\u001B[36m   ║\n" +
				"╠═══════════════════════════════════════════════════════════════════╣" );
		Thread.sleep(1000);
		System.out.println( "║ \u001B[33m►Arte y Animación:\u001B[36m                                              ║\n" +
				"║ \u001B[37m    Director de arte (Art Director) Elizabeth Suárez \u001B[36m           ║\n" +
				"║ \u001B[37m    Artista de personajes (Character Artist) Miguel Gallego\u001B[36m     ║\n" +
				"╠═══════════════════════════════════════════════════════════════════╣" );
		Thread.sleep(1000);
		System.out.println( "║ \u001B[33m►Pruebas y Control de Calidad:\u001B[36m                                  ║\n" +
				"║ \u001B[37m    Tester Elizabeth Suárez\u001B[36m                                     ║\n" +
				"║ \u001B[37m    Líder de Tester Miguel Gallego\u001B[36m                              ║\n" +
				"╠═══════════════════════════════════════════════════════════════════╣" );
		Thread.sleep(1000);
		System.out.println( "║ \u001B[33m►Efectos de Sonido:\u001B[36m                                             ║\n" +
				"║ \u001B[37m    Música de Fondo Ruins – Undertale OST\u001B[36m                       ║\n" +
				"║ \u001B[37m    Música de Menú Battle Against a True Hero – Undertale OST \u001B[36m  ║\n" +
				"║ \u001B[37m    Música de Selección Hopes and Dreams – Undertale OST \u001B[36m       ║\n" +
				"║ \u001B[37m    Música de Presentación de Personajes Main Theme - Dota \u001B[36m     ║\n" +
				"║ \u001B[37m    Música de Victoria Power Start Get- Super Mario Galaxy\u001B[36m      ║\n" +
				"║ \u001B[37m    Música de Fin Undertale - Undertale OST\u001B[36m                     ║\n" +
				"║ \u001B[37m    Música de Créditos My Castle Town - Deltarune\u001B[36m               ║\n" +
				"║ \u001B[37m    Sonido de Teletransporte - League of Legends\u001B[36m                ║\n" +
				"║ \u001B[37m    Sonido de curación – League of Legends\u001B[36m                      ║\n" +
				"║ \u001B[37m    Sonido de ataque – DARK ENERGY - Sound Effect\u001B[36m               ║\n" +
				"║ \u001B[37m    Sonido de Espadas – Sound Edit\u001B[36m                              ║\n" +
				"║ \u001B[37m    Desarrollo y Diseño Eli y Miguel\u001B[36m                            ║\n" +
				"╠═══════════════════════════════════════════════════════════════════╣" );
		Thread.sleep(1000);
		System.out.println( "║ \u001B[33m►Agradecimientos Especiales:\u001B[36m                                    ║\n" +
				"║ \u001B[37m     Beta testers (Facu,Dani,Lynn.........)\u001B[36m                     ║\n" +
				"╚═══════════════════════════════════════════════════════════════════╝\u001B[0m");
	}

	/**
	 * @author Miguel Gallego
	 * Muestra las opciones de personajes que existen en el menu
	 * @throws InterruptedException Excepción para detener el programa
	 */
	public static void mostrarPersonajes () throws InterruptedException {
		Personaje c = new Caballero();
		Personaje s = new Soldado();
		Personaje l = new Lancero();
		Personaje a= new Arquero();
		Personaje h = new Hechicero();
		System.out.println("\u001B[36m╔════════════════════════════════════════════════════════════════════════╗\n" +
				"║ \u001B[33m                    MIGABETH CONQUEST PERSONAJES\u001B[36m                       ║\n" +
				"╠════════════════════════════════════════════════════════════════════════╣\u001B[33m   ");   
		c.info(); Thread.sleep(1000);
		System.out.println("\u001B[36m╠════════════════════════════════════════════════════════════════════════╣\u001B[33m   " );
		s.info ();Thread.sleep(1000);
		System.out.println("\u001B[36m╠════════════════════════════════════════════════════════════════════════╣\u001B[33m   " );
		a.info();Thread.sleep(1000);
		System.out.println("\u001B[36m╠════════════════════════════════════════════════════════════════════════╣\u001B[33m   " );
		l.info();Thread.sleep(1000);
		System.out.println("\u001B[36m╠════════════════════════════════════════════════════════════════════════╣\u001B[31m" );
		h.info();
		System.out.print("\u001B[36m╚════════════════════════════════════════════════════════════════════════╝\u001B[0m\n");
	}


	//Funciones para controlar la musica y efectos de sonido

	/**
	 * @author Elizabeth Suarez
	 * Reproduce Música de fondo que cambia durante la ejecucion del programa
	 * @param archivo archivo de audio a reproducir
	 */
	public static void reproducirMusica(String archivo) {
		try {
			File file = new File(archivo);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY); 
			clip.start();

			while (clip.isOpen()) {
				Thread.sleep(100);
			}
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author Miguel Gallego
	 * Inicia la reproducción de la primera Música de fondo
	 * @param archivo archivo de audio a reproducir
	 */
	private static void iniciarMusicaFondo(String archivo) {
		new Thread(() -> {
			try {
				File file = new File(archivo);
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
				musicaClip = AudioSystem.getClip();
				musicaClip.open(audioStream);
				musicaControl = (FloatControl) musicaClip.getControl(FloatControl.Type.MASTER_GAIN);
				musicaClip.loop(Clip.LOOP_CONTINUOUSLY);
				musicaClip.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	/**
	 * @author Elizabeth Suarez
	 * Con esta función se controla el cambio de Música
	 * @param nuevoArchivo nuevo archivo a reproducir luego de detener la música
	 */
	private static void cambiarMusica(String nuevoArchivo) {
		detenerMusica();
		iniciarMusicaFondo(nuevoArchivo);
	}

	/**
	 * @author Miguel Gallego 
	 * Con esta función se detiene la musica
	 */
	private static void detenerMusica() {
		if (musicaClip != null && musicaClip.isRunning()) {
			musicaClip.stop();
			musicaClip.close();
		}
	}

	/**
	 * @author Elizabeth Suarez
	 * Función que controla la reproduccion de los efectos de sonidos durante la ejecucion 
	 * @param archivo archivo de efecto de sonido a reproducir
	 */
	private static void reproducirSFX(String archivo) {
		new Thread(() -> {
			try {
				File file = new File(archivo);
				if (!file.exists()) {
					System.err.println("Error: Archivo de sonido no encontrado -> " + archivo);
					return;
				}

				AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
				Clip sfxClip = AudioSystem.getClip();
				sfxClip.open(audioStream);

				FloatControl sfxControl = (FloatControl) sfxClip.getControl(FloatControl.Type.MASTER_GAIN);
				sfxControl.setValue(+6.0f);

				ajustarVolumenMusicaGradual(-12.0f, 500); 

				sfxClip.start();
				Thread.sleep(4000); 

				ajustarVolumenMusicaGradual(0.0f, 500);

				sfxClip.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	/**
	 * @author Miguel Gallego
	 * Con esta Función se ajusta el volumen de la música de fondo para escuchar los efectos de sonido
	 * @param objetivoDB  volumen que desea bajar
	 * @param duracionMs Durante cuanto tiempo desea bajar
	 */
	private static void ajustarVolumenMusicaGradual(float objetivoDB, int duracionMs) {
		if (musicaControl != null) {
			float volumenActual = musicaControl.getValue();
			int pasos = 20; 
			float diferencia = (objetivoDB - volumenActual) / pasos;

			for (int i = 0; i < pasos; i++) {
				float nuevoVolumen = volumenActual + (diferencia * i);
				musicaControl.setValue(nuevoVolumen);
				try {
					Thread.sleep(duracionMs / pasos);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			musicaControl.setValue(objetivoDB);
		}
	}

}


