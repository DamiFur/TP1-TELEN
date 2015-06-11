package tl.automatas.afd;

import java.util.*;
import java.lang.*;
import java.io.*;
import tl.automatas.utils.*;

/**
 * Clase principal para el trabajo con Autómatas Finitos determinísticos
 * 
 * 
 * @author Teoría de Lenguajes (DC, Exactas, UBA) - Equipo de TPs
 * @version 2.0
 * @see http://www.dc.uba.ar/materias/tl/2015/c1/practicas
 */

public class AFD {
	
	static Tools tools = new Tools();
	
	public static final String NOMBRE_ARCHIVO = "\\w+.*";
	public static final String CADENA_POSIBLE = ".*";
	
	
	private enum Uso{
		None,                // No se recibieron parámetros 
		Other,               // Cualquier otra combinación de parámetros no prevista
		A_RegexToDFA,        // Conversión de Expresión Regular a DFA
		B_StringRecognition, // Reconocimiento de cadena con un autómata determinado
		C_DOTGeneration,     // Generación de archivo DOT a partir de autómata
		D_DFAIntersection,   // Intersección de autómatas
		E_DFAComplement,     // Complemento de autómatas
		F_DFAEquivalence     // Equivalencia entre autómatas
	}
	
	
	
	public static void main(String[] args) {

		
		try{
			Uso usage= parsearArgumentos(args);

			switch (usage){
				case A_RegexToDFA:
					GenerateDFAFromRegex(args[1], args[3]);
					break;

				case B_StringRecognition:
					RecognizeString(args[1], args[2]);
					break;

				case C_DOTGeneration:
					GenerateDOTFromDFA(args[1], args[3]);
					break;

				case D_DFAIntersection:
					ComputeDFAIntersection(args[2], args[4], args[6]);
					break;

				case E_DFAComplement:
					ComputeDFAComplement(args[2], args[4]);
					break;

				case F_DFAEquivalence:
					ComputeDFAEquivalence(args[2], args[4]);
					break;

				default:
					System.err.println("ERROR: No se recibieron los argumentos esperados");
					mostrarUso();
					break;
			}
		}
		catch (Exception exception){
			System.out.println(exception);
			// Terminación no exitosa (cualquier número mayor a 0)
			System.exit(1);
		}
		
		// Terminación exitosa
		System.exit(0);
		
	}
	
	public static Uso parsearArgumentos(String[] args){
		
		if (args.length == 0) 
			return Uso.None;
		
		{
		String[] target = {"-leng", NOMBRE_ARCHIVO, "-aut", NOMBRE_ARCHIVO};
		if(checkUso(args, target))
			return Uso.A_RegexToDFA;
		}

		{
		String[] target = {"-aut", NOMBRE_ARCHIVO, CADENA_POSIBLE};
		if(checkUso(args, target))
			return Uso.B_StringRecognition;;
		}		

		{
		String[] target = {"-aut", NOMBRE_ARCHIVO, "-dot", NOMBRE_ARCHIVO};
		if(checkUso(args, target))
			return Uso.C_DOTGeneration;
		}		

		{
		String[] target = {"-intersec", "-aut1", NOMBRE_ARCHIVO, "-aut2", NOMBRE_ARCHIVO, "-aut", NOMBRE_ARCHIVO};
		if(checkUso(args, target))
			return Uso.D_DFAIntersection;
		}		

		{
		String[] target = {"-complemento", "-aut1", NOMBRE_ARCHIVO, "-aut", NOMBRE_ARCHIVO};
		if(checkUso(args, target))
			return Uso.E_DFAComplement;
		}		

		{
		String[] target = {"-equival", "-aut1", NOMBRE_ARCHIVO, "-aut2", NOMBRE_ARCHIVO};
		if(checkUso(args, target))
			return Uso.F_DFAEquivalence;
		}		
		
		return Uso.Other;
	}
	
	
	private static void mostrarUso(){
		System.err.println("Uso:\n--------");
		System.err.println("AFD -leng <archivo_regex> -aut <archivo_automata>");
		System.err.println("AFD -aut <archivo_automata> <cadena>");
		System.err.println("AFD -aut <archivo_automata> -dot <archivo_dot>");
		System.err.println("AFD -intersec -aut1 <archivo_automata> -aut2 <archivo_automata> -aut <archivo_automata>");
		System.err.println("AFD -complemento -aut1 <archivo_automata> -aut <archivo_automata>");
		System.err.println("AFD -equival -aut1 <archivo_automata> -aut2 <archivo_automata>\n");
	}

	private static boolean checkUso(String[] args, String[] paramsRegex){		
		if(paramsRegex.length != args.length) // Si difieren en tamaño
			return false;
		
		int pos;
		for(pos=0; pos<paramsRegex.length; pos++){			
			//System.out.println("Argumento: " + args[pos] + " Target: " + paramsRegex[pos]);
			if(! args[pos].matches(paramsRegex[pos])){
				//System.out.println("Parámetro no esperado");
				break;
			}
				
		}
		
		if(pos<paramsRegex.length) //Si termino antes de llegar al final
			return false;
		
		return true;
		
	}

	//TODO: Completar implementaciones de métodos
	
	// ***************************** Ejercicio 3.a*******************************	
	private static void GenerateDFAFromRegex(String leng, String aut){
		
		List<String> lenguaje = tools.leerArchivo(leng);
	
		Automata automataUniversal = crearAutomataUniversal(lenguaje.toArray(new String[lenguaje.size()]));
		
		automataUniversal.printAutomata();
		
		Automata AFD = derivar(automataUniversal);
		AFD.printAutomata();

		
		//System.err.println("Método no implementado: GenerateDFAFromRegex");
	}
	
	private static Automata crearAutomataUniversal(String[] lenguaje){
		
		String[] estadosTotales = new String[2];
		
		estadosTotales[0] = "q0";
		estadosTotales[1] = "q1";
		
		String[] estadoInicial = new String[1];
		
		estadoInicial[0] = "q0";
		
		String[] estadoFinal = new String[1];
		
		estadoFinal[0] = "q1";
		
		List<String[]> transiciones = new ArrayList<String[]>();
		
		String[] transicion = new String[3];
		
		transicion[0] = "q0";
		transicion[2] = "q1";
		transicion[1] = join(lenguaje, "\n");
		
		transiciones.add(transicion);
		
		Automata respuesta = new Automata(estadosTotales, obtenerLetras(lenguaje), estadoInicial, estadoFinal, transiciones);
		
		return respuesta;
		
	}
	
	private static String join(List<String> lista, String separator){
		StringBuilder respuesta = new StringBuilder("");
		for(int i = 0; i < lista.size(); i++){
			respuesta.append(lista.get(i));
			respuesta.append(separator);
		}
		return respuesta.toString();
	}
	
	private static String join(String[] lista, String separator){
		StringBuilder respuesta = new StringBuilder("");
		for(int i = 0; i < lista.length; i++){
			respuesta.append(lista[i]);
			respuesta.append(separator);
		}
		return respuesta.toString();
	}
	
	private static Automata derivar(Automata aut){
		
		Boolean hayCambios = true;
		String operacion;
		int numero;
		List<String> estadosFinales = new LinkedList<String>(Arrays.asList(aut.getEstadosFinales()));
		List<String[]> transiciones = aut.getTransiciones();

		String[] estadosTotales = aut.getEstadosTotales();
		int lastNode = estadosTotales.length;
		List<String> estadosTotalesList = new LinkedList<String>(Arrays.asList(estadosTotales));
		
		while(hayCambios){
			hayCambios = false;
			
			List<String[]> transicionesCpy = new LinkedList<String[]>(transiciones);
			
			for(String[] trans : transicionesCpy){
				String[] lenguaje = trans[1].split("\n");
				operacion = parseOp(lenguaje[0]);
				

				switch(operacion){
				
				//************CONCATENAR*****************************
				
					case "CONCAT":
//						numero = parseNum(lenguaje[0]);
						
						
						//Dividir el lenguaje en numero
						List<List<String>> lenguajeParseadoConc = divideLanguage(lenguaje);
						String estadoActual = trans[0];

						int j;
						for(j = 0; j < lenguajeParseadoConc.size() - 1; j ++){
							
							//Agrego los estados nuevos a los estados totales
							StringBuilder stateCreator = new StringBuilder("q");
							stateCreator.append(lastNode++);
							estadosTotalesList.add((stateCreator.toString()));

							//Crear una transicion por cada lenguaje nuevo
							String[] nuevaTrans = new String[3];
							nuevaTrans[0] = estadoActual;
							nuevaTrans[1] = join(lenguajeParseadoConc.get(j), "\n");
							nuevaTrans[2] = stateCreator.toString();
							
							transiciones.add(nuevaTrans);
							estadoActual = nuevaTrans[2];
						}
						
						String[] nuevaTransAux = new String[3];
						nuevaTransAux[0] = estadoActual;
						nuevaTransAux[1] = join(lenguajeParseadoConc.get(j), "\n");
						nuevaTransAux[2] = trans[2];
						transiciones.add(nuevaTransAux);
						transiciones.remove(transiciones.indexOf(trans));
						
						hayCambios = true;
						break;
					
					case "OR":
						
						/************************OR****************************************/
						
						numero = parseNum(lenguaje[0]);
						
						
//						//Agrego los estados nuevos a los estados totales. Si el estado destino era final lo agrego a los finales
//						int totalEstadosOr = estadosTotalesList.size();
//						for(int j = 0; j < (numero - 1); j ++){
//							StringBuilder stateCreator = new StringBuilder("q");
//							stateCreator.append(totalEstadosOr + j);
//							estadosTotalesList.add((stateCreator.toString()));
//							if(estadosFinales.contains(trans[2])){
//								estadosFinales.add((stateCreator.toString()));
//							}
//						}
						
//						// Agrego las transiciones del nodo destino en todos los nodos que agregué
//						for(int l = 0; l < (numero - 1); l++){
//							List<String[]> transicionesAuxx = new LinkedList<String[]>(transiciones);
//							for(String[] t : transicionesAuxx){
//								if(t[0].equals(trans[2])){
//									String[] nuevaTransicion = new String[3];
//									StringBuilder stateCreator = new StringBuilder("q");
//									stateCreator.append(totalEstadosOr + l);
//									nuevaTransicion[0] = stateCreator.toString();
//									nuevaTransicion[1] = t[1];
//									nuevaTransicion[2] = t[2];
//									transiciones.add(nuevaTransicion);
//								}
//							}
//						}

						//Dividir El Lenguaje
						List<List<String>> lenguajeParseadoOr = divideLanguage(lenguaje);
						
						
						//Crear una transicion para cada lenguaje nuevo
						trans[1] = join(lenguajeParseadoOr.get(0), "\n");
						for(int x = 1; x < lenguajeParseadoOr.size(); x ++){
							String[] nuevaTrans = new String[3];
							nuevaTrans[0] = trans[0];
							nuevaTrans[1] = join(lenguajeParseadoOr.get(x), "\n");
							nuevaTrans[2] = trans[2];
							
							transiciones.add(nuevaTrans);
						}
						
						hayCambios = true;
						break;
						
					case "STAR":
						
						//Dividir El Lenguaje
						List<String> lenguajeParseadoStar = divideLanguage(lenguaje).get(0);
						
						//Cambio las transiciones que salian del nodo destino ahora salen del fuente
						for(String[] t : transiciones){
							if(t[0].equals(trans[2])){
								t[0] = trans[0];
							}
						}
						
						//Si el estado destino era final ahora el estado inicial es final
						if(estadosFinales.contains(trans[2])){
							estadosFinales.add(trans[0]);
							estadosFinales.remove(estadosFinales.indexOf(trans[2]));
						}
						
						//Eliminamos el estado destino
						estadosTotalesList.remove(estadosTotalesList.indexOf(trans[2]));
						
						//Convierto la transicion que estoy recorriendo en una tansicion sin la estrella de destino a sí mismo
						String LPrima = join(lenguajeParseadoStar, "\n");
						String[] nuevaTransicion = new String[3];
						nuevaTransicion[0] = trans[0];
						nuevaTransicion[1] = LPrima;
						nuevaTransicion[2] = trans[0];
						transiciones.add(nuevaTransicion);
						
						//Saco la transacción vieja que ya fue modificada
						transiciones.remove(transiciones.indexOf(trans));
						
//						//Agrego todas las transiciones del nodo Destino al nodo Fuente
//						List<String[]> transicionesAux = new LinkedList<String[]>(transiciones);
//						for(String[] t : transicionesAux){
//							if(t[0].equals(trans[2])){
//								String[] nuevaTransicion = new String[3];
//								nuevaTransicion[0] = trans[0];
//								nuevaTransicion[1] = t[1];
//								nuevaTransicion[2] = t[2];
//								
//								transiciones.add(nuevaTransicion);
//							}
//						}
//						
//						//Agrego una transicion del nodo destino a sí mismo a traves del lenguaje dividido
//						
//						String LPrima = join(lenguajeParseadoStar, "\n");
//						
//						String[] loopTrans = new String[3];
//						loopTrans[0] = trans[2];
//						loopTrans[1] = LPrima;
//						loopTrans[2] = trans[2];
//						
//						transiciones.add(loopTrans);
//						
//						//Cambio la transicion estrella por una transicion normal
//						trans[1] = LPrima;
						
						hayCambios = true;
						break;
						
					case "PLUS":
						
						//Dividir El Lenguaje
						List<String> lenguajeParseadoPlus = divideLanguage(lenguaje).get(0);
												
						//Agrego una transicion del nodo destino a sí mismo a traves del lenguaje dividido
						
						String LPrimaPlus = join(lenguajeParseadoPlus, "\n");
						
						String[] loopTransP = new String[3];
						loopTransP[0] = trans[2];
						loopTransP[1] = LPrimaPlus;
						loopTransP[2] = trans[2];
						
						transiciones.add(loopTransP);
						
						//Cambio la transicion estrella por una transicion normal
						trans[1] = LPrimaPlus;
						
						hayCambios = true;
						break;
						
				}

			}
			
		}
		
		transiciones = removerEnter(transiciones);
		
		Automata automata = new Automata(estadosTotalesList.toArray(new String[estadosTotalesList.size()]), aut.getLenguaje(), aut.getEstadosIniciales(), estadosFinales.toArray(new String[estadosFinales.size()]), transiciones);
//		automata.printAutomata();
		
		return automata;
	}
	
	private static List<List<String>> divideLanguage(String[] lenguaje){
		List<List<String>> respuesta = new ArrayList<List<String>>();
		List<String> L2 = new ArrayList<String>();
		for(int i = 1; i < lenguaje.length; i++){
			if(esSubLenguaje(lenguaje[i]) && !L2.isEmpty()){
				respuesta.add(L2);
				L2 = new ArrayList<String>();
			}
			L2.add(lenguaje[i].substring(1));
		}
		respuesta.add(L2);
		
		return respuesta;
	}
	
	private static Boolean esSubLenguaje(String str){
		if(str.charAt(0) == '\t' && str.charAt(1) != '\t'){
				return true;
			} else {return false;}
	}
	
	private static String parseOp(String operation){
		StringBuilder respuesta = new StringBuilder("");
		
		for(int i = 0; i < operation.length(); i++){
			if(operation.charAt(i) == '}'){
				break;
			}
			if(operation.charAt(i) != '\t' && operation.charAt(i) != '{'){
				respuesta.append(operation.charAt(i));
			}
		}
		
		return respuesta.toString();
	}
	
	private static int parseNum(String operation){
		int respuesta = 0;
		for(int i = 0; i < operation.length(); i++){
			if(operation.charAt(i) == '}'){
				respuesta = operation.charAt(i + 1) - 48;
				break;
			}
		}
		return respuesta;
	}
	
	private static List<String[]> removerEnter(List<String[]> transiciones){
		List<String[]> respuesta = new LinkedList<String[]>();
		for(String[] t : transiciones){
			String[] nuevaTransicion = new String[3];
			nuevaTransicion[0] = t[0];
			nuevaTransicion[1] = t[1].replace("\n", "");
			nuevaTransicion[2] = t[2];
			
			respuesta.add(nuevaTransicion);
		}
		return respuesta;
	}
	
	private static Automata minimizar(Automata aut){
		
		String[] estadosTotales = aut.getEstadosTotales();
		List<String[]> transiciones = aut.getTransiciones();
		
		//Usar un HashMap para mapear cada uno de los estados con su miembro representativo en el DisjointSet
		//Empezar cargandole al mapa todos los estados finales y todos los iniciales tomando como referencia uno de cada conjunto
		//Recorrer las transiciones y por cada simbolo del lenguaje chequear en O(1) si alguna separa dos disjoint sets
		//Si no los separa, el numero representativo de su conjunto pasa a ser el maximo numero utilizado
		//Si los separa, el numero pasa a ser el maximo MAS el numero del conjunto distinto con el que se separó (el numero se multiplica por dos a lo sumo por cada operacion). Otra opcion es guardar en otro hashmap para cada transición (estado del conjunto que estamos recorriendo) con que otro grupo externo se conectan. Después agarro uno y para todos los que conectan con el mismo lugar los agrego en un nuevo conjunto usando el primero que agarre para poner de representante del conjunto
		
		
		return null;
	}
	

	
	

	// ******************************* Ejercicio 3.b ***************************************
	private static void RecognizeString(String aut, String str){

			List<String> automata = tools.leerArchivo(aut);
			
			String estadoActual = automata.get(2);
			
			String[] estadosFinales = automata.get(3).split("\t");
			
			List<String[]> transiciones = new ArrayList<String[]>();
			
			for(int f = 4; f < automata.size(); f++){
				transiciones.add(automata.get(f).split("\t"));
			}
			
			Boolean respuesta = true;
			Boolean cambie = false;
			
			String[] lenguaje = automata.get(1).split("\t");
			
			for(int e = 0; e < str.length(); e++){
				if(!Arrays.asList(lenguaje).contains(String.valueOf(str.charAt(e)))){
					System.out.println("false");
					return;
				} else {
					for(int g = 0; g < transiciones.size(); g++){
						if((transiciones.get(g)[0].equals(estadoActual)) && (transiciones.get(g)[1].equals(String.valueOf(str.charAt(e))))){
							estadoActual = transiciones.get(g)[2];
							cambie = true;
							break;
						}
					}
					if(!cambie){respuesta = false;}
					cambie = false;
				}
			}
			if(!Arrays.asList(estadosFinales).contains(estadoActual)){
				respuesta = false;
			}
			
			System.out.println(respuesta);
	}

	// Ejercicio 3.c
	private static void GenerateDOTFromDFA(String aut, String dot){
		//obtengo todos los datos del archivo de texto
		List<String> automata = tools.leerArchivo(aut);	
		String estadoInicial = automata.get(2); 
		String[] estadosFinales = automata.get(3).split("\t");
		List<String[]> transiciones = new ArrayList<String[]>();
		for(int f = 4; f < automata.size(); f++){
			transiciones.add(automata.get(f).split("\t"));
		}
		
		//en este array voy a guardar las transiciones que ya escribí en el archivo para no repetirlas
		List<String> transicionesGuardadas = new ArrayList<String>();
		
		BufferedOutputStream bout = null;
		try {
			bout = new BufferedOutputStream( new FileOutputStream(dot) );
			
			//empiezo a escribir el texto que va a ir en el archivo
			String line = "strict digraph{";
			line += System.getProperty("line.separator");
			line += "rankdir=LR;";
			line += System.getProperty("line.separator");
			line += "node[shape=none,label=\"\",width=0,height=0];qd;";
			line += System.getProperty("line.separator");
			line += "node[label=\"\\N\",width=0.0,height=0.5];";
			line += System.getProperty("line.separator");
			
			//recorro los estados finales para ponerles un doble círculo
			line += "node[shape=doublecircle];";
			for(String qf : estadosFinales){
				line += qf + ";";
			}
			line += System.getProperty("line.separator");
			line += "node[shape=circle];";
			line += System.getProperty("line.separator");
			line += "qd->" + estadoInicial;
			line += System.getProperty("line.separator");
			
			//empiezo a recorrer las transiciones, si no están en "transiciones guardadas", las escribo, sino no hago nada.
			for(String[] trans : transiciones){
				if(!transicionesGuardadas.contains(trans[0]+","+trans[2])){
					
					//llamo a la funcion transicionEntreDosEstados para calcular el label de la transicion
					line += trans[0] + "->" + trans[2] + "[label=\"" + transicionEntreDosEstados(transiciones,trans[0],trans[2]) + "\"]";
					line += System.getProperty("line.separator");
					
					//como ya la escribí, la agrego a la lista "transiciones guardadas"
					transicionesGuardadas.add(trans[0]+","+trans[2]);
				}			
			}
			line += "}";
			bout.write(line.getBytes());
		} catch (IOException e) {
			System.err.println("Error escribiendo el archivo: ");
			e.printStackTrace();
		} finally{
			 if (bout != null) {
				 try {
					 bout.close();
				 } catch (Exception e) {
				 }
			 }
		}

	}
	
	//esta función sirve para cuando dos estados tienen más de una transición. Por ejemplo, si desde q1 puedo llegar hasta q2 tanto por a, como por b, 
	//en el label sobre la flecha debería decir "a,b". Entonces, lo que hace esta función es para dos estados, recorrer todas las transiciones
	//y acumular los labels en un string.
	public static String transicionEntreDosEstados(List<String[]> transiciones, String estado1, String estado2){
		String labelTransicion = "";
		
		for(String[] trans : transiciones){
			if(trans[0].equals(estado1) && trans[2].equals(estado2)){
				labelTransicion += trans[1] + ",";
			}
		}
		
		labelTransicion = labelTransicion.substring(0,labelTransicion.length()-1);
		
		return labelTransicion;
	}

	// Ejercicio 3.d
	private static void ComputeDFAIntersection(String aut1, String aut2, String aut){
		System.err.println("Método no implementado: ComputeDFAIntersection");
	}

	// Ejercicio 3.e
	private static void ComputeDFAComplement(String aut1, String aut){
		
		List<String> automata = tools.leerArchivo(aut1);
		
		List<String[]> automataT = agregarTrampa(automata);
		
		List<List<String>> automataC = invertirEstadosFinales(automataT);
		
		//FALTA AGREGAR EL ESTADO TRAMPA A LOS ESTADOS FINALES
		
		for(List<String> str : automataC){
			for(String s : str){
				System.out.print(s + " ");
			}
			System.out.println();
		}
		
	}

	// Ejercicio 3.f
	private static void ComputeDFAEquivalence(String aut1, String aut2)	{
		System.err.println("Método no implementado: ComputeDFAEquivalence");
	}
	
//	private static List<Lenguaje> derivar(List<String> lenguaje){
//		
//		List<String> letras = obtenerLetras(lenguaje);
//		
//		for(int d = 0; d < letras.size(); d++){
////			derivarPor(lenguaje, letras.get(d));
//		}
//		
//		List<Lenguaje> respuesta = new ArrayList<Lenguaje>();
//		
//		List<Transiciones> transiciones = new ArrayList<Transiciones>();
//		
//		Lenguaje resp1 = new Lenguaje();
//		
//		Transiciones trans = new Transiciones();
//		
//		trans.to = letras;
//		
//		transiciones.add(trans);
//		
//		resp1.transiciones = transiciones;
//		
//		respuesta.add(resp1);
//		
//		return respuesta;
//		
//	}
	
	private static String[] obtenerLetras(String[] lenguaje){
		Boolean vaAdentro = true;
		Set<String> respuesta = new HashSet<String>();
		for(int a = 0; a < lenguaje.length; a++){
			vaAdentro = true;
			for(int b = 0; b < lenguaje[a].length(); b++)
			if(lenguaje[a].charAt(b) == '{'){
				vaAdentro = false;
				break;
			} else {
				if(lenguaje[a].charAt(b) != '\t'){
					break;
				}
			}
			if(vaAdentro){
				respuesta.add(sanitizar(lenguaje[a]));
			}
		}
		return respuesta.toArray(new String[respuesta.size()]);
	}
	
	
	private static String sanitizar(String input){
		StringBuilder respuesta = new StringBuilder("");
		for(int c = 0; c < input.length(); c++){
			if(input.charAt(c) != '\t'){
				respuesta.append(input.charAt(c));
			}
		}
		return respuesta.toString();
	}
	
	private static List<String[]> agregarTrampa(List<String> automata){
		
		String[] estadosTotales = automata.get(0).split("\t");
		
		String[] lenguaje = automata.get(1).split("\t");
		
		String[] estadoInicial = automata.get(2).split("\t");
		
		String[] estadosFinales = automata.get(3).split("\t");
		
		List<String[]> transiciones = new ArrayList<String[]>();
		
		for(int f = 4; f < automata.size(); f++){
			transiciones.add(automata.get(f).split("\t"));
		}
		
		Boolean hayQueAgregarT = false;
		for(String estado : estadosTotales){
			for(String trans : lenguaje){
				if(!existeTransicion(transiciones, estado, trans)){
					hayQueAgregarT = true;
					String[] nuevaTransicion = {estado, trans, "T"};
					transiciones.add(nuevaTransicion);
				}				
			}
			if(hayQueAgregarT){
				for(String trans : lenguaje){
					String[] nuevaTransicion = {"T", trans, "T"};
					transiciones.add(nuevaTransicion);				
				}
				String estadosTotalesStr = automata.get(0) + "\tT";
				estadosTotales = estadosTotalesStr.split("\t");
			}


		}
		
		List<String[]> respuesta = new ArrayList<String[]>();
		
		respuesta.add(0, estadosTotales);
		respuesta.add(1, lenguaje);
		respuesta.add(2, estadoInicial);
		respuesta.add(3, estadosFinales);
		respuesta.addAll(transiciones);
		
		return respuesta;
		
	}
	
	private static Boolean existeTransicion(List<String[]> transiciones, String estado, String trans){
		for(String[] t : transiciones){
			if(t[0].equals(estado) && t[1].equals(trans)){
				return true;
			}
		}
		return false;
	}
	
	private static List<List<String>> invertirEstadosFinales(List<String[]> automataT){
		String[] estadosTotales = automataT.get(0);
		String[] estadosFinales = automataT.get(3);
		List<String> estadosResult = new ArrayList<String>();
		
		List<List<String>> respuesta = new ArrayList<List<String>>();
		respuesta.add(0, Arrays.asList(automataT.get(0)));
		respuesta.add(1, Arrays.asList(automataT.get(1)));
		respuesta.add(2, Arrays.asList(automataT.get(2)));
		
		for(int i = 0; i < estadosTotales.length; i++){
			if(!Arrays.asList(estadosFinales).contains(estadosTotales[i])){
				estadosResult.add(estadosTotales[i]);
			}
		}
		respuesta.add(3, estadosResult);
		for(int j = 4; j < automataT.size(); j++){
			respuesta.add(Arrays.asList(automataT.get(j)));
		}

		
		return respuesta;
	}

	public static class Lenguaje{
		List<Transiciones> transiciones;
		Boolean esFinal;
	}
	
	public static class Transiciones{
		String from;
		String through;
		List<String> to;
	}
	
	
	// ******************************* Minimizar autómata ********************************************************
	public static void MinimizarAutomata(String aut, String autMin)
	{
		List<String> automata = tools.leerArchivo(aut);	
		String[] estados = automata.get(0).split("\t");
		ArrayList<String> estadosNoFinales = new ArrayList<String>();
		String[] estadosFinales = automata.get(3).split("\t");
		List<String[]> transiciones = new ArrayList<String[]>();
		for(int f = 4; f < automata.size(); f++){
			transiciones.add(automata.get(f).split("\t"));
		}
		
		List<Grupo> grupos = new ArrayList<Grupo>();
		
		for(String est : estados){
			if(!Arrays.asList(estadosFinales).contains(est)){
				estadosNoFinales.add(est);
			}
		}
		
		grupos.add(new Grupo(estadosNoFinales,1));
		grupos.add(new Grupo(Arrays.asList(estadosFinales),2));
		
		int numeroGrupo=2;
		
		for(Grupo grupo : grupos){
			List<String[]> gruposPorEstado = new ArrayList<String[]>();
			for(String estado : grupo.estados()){
				for(String[] trans : transiciones){
					if(trans[0].equals(estado)){
						if(GrupoDeEstado(grupos,trans[2]) != grupo.numero()){
							
						}
					}
				}
			}
		}
	}
	
	static public int GrupoDeEstado(List<Grupo> grupos, String estado){
		for(Grupo grupo : grupos){
			for(String est : grupo.estados()){
				if(est.equals(estado)){
					return grupo.numero();
				}
			}
		}
		return 0;
	}
	
	static public class Grupo
	{
		private List<String> estados;
		private int numero;
		
		public Grupo(){
		
		}
		
		public Grupo(List<String> _estados, int _numero){
			estados = _estados;
			numero = _numero;
		}
		
		public List<String> estados(){
			return estados;
		}
		
		public int numero(){
			return numero;
		}
		
	}
	
}
