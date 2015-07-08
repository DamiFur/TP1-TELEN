package tl.automatas.utils;

import java.util.*;
import java.io.*;

public class Tools {
	

	public List<String> leerLineasArchivo(String nombreArchivo, int cantLineas){
		
		List<String> lineas = new ArrayList<String>();
		
		File archivo = new File(nombreArchivo);
		
		Scanner scanner;
		try {
			scanner = new Scanner(archivo);
			
			for(int i = 0; i < cantLineas; i++){
				lineas.add(scanner.nextLine());
			}
			
			scanner.close();

		} catch (FileNotFoundException e) {
			System.out.println("no te encontro el archivo");
		}
		
		return lineas;

		
	}
	
	/**
	 * 
	 * 
	 * @param nombreArchivo
	 * @return Una List<String> que contiene todas las líneas del archivo 
	 */
	public List<String> leerArchivo(String nombreArchivo){
		
		List<String> lineas = new ArrayList<String>();
		
		File archivo = new File(nombreArchivo);
		
		Scanner scanner;
		try {
			scanner = new Scanner(archivo);
			
			while(scanner.hasNext()){
				lineas.add(scanner.nextLine());
			}
			
			scanner.close();

		} catch (FileNotFoundException e) {
			System.out.println("no te encontro el archivo");
		}
		
		return lineas;
		
	}
	
	public void escribirArchivo(String nombreArchivo, Automata automata){

		try{
			File archivo = new File(nombreArchivo);
			if(!archivo.exists()){
				archivo.createNewFile();
			}
			
			FileWriter fw = new FileWriter(archivo.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			int i;
			for(i = 0; i < automata.getEstadosTotales().length - 1; i++){
				bw.write(automata.getEstadosTotales()[i] + "\t");
			}
			bw.write(automata.getEstadosTotales()[i] + "\n");
			
			if(automata.getLenguaje().length > 0){
				int j;
				for(j = 0; j < automata.getLenguaje().length - 1; j++){
					bw.write(automata.getLenguaje()[j] + "\t");
				}
				bw.write(automata.getLenguaje()[j] + "\n");
			} else {
				bw.write("<lenguaje vacío>\n");
			}
			
			
			int k;
			for(k = 0; k < automata.getEstadosIniciales().length - 1; k++){
				bw.write(automata.getEstadosIniciales()[k] + "\t");
			}
			bw.write(automata.getEstadosIniciales()[k] + "\n");
			
			int l;
			for(l = 0; l < automata.getEstadosFinales().length - 1; l++){
				bw.write(automata.getEstadosFinales()[l] + "\t");
			}
			bw.write(automata.getEstadosFinales()[l] + "\n");
			
			if(!automata.getTransiciones().isEmpty()){
				int m;
				String[] aux = new String[3];
				for(m = 0; m < automata.getTransiciones().size() - 1; m++){
					aux = automata.getTransiciones().get(m);
					bw.write(aux[0] + "\t");
					bw.write(aux[1] + "\t");
					bw.write(aux[2] + "\n");
				}
				aux = automata.getTransiciones().get(m);
				bw.write(aux[0] + "\t");
				bw.write(aux[1] + "\t");
				bw.write(aux[2]);
			}

			
			bw.close();
			
		} catch(Exception e) {
			System.out.println("escribiste mal el archivo");
		}
		
	}
}
