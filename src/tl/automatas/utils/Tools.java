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
			System.out.println("eh amigo no te encontro el archivo");
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
			System.out.println("eh amigo no te encontro el archivo");
		}
		
		return lineas;
		
	}
}
