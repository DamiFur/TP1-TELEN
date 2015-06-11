package tl.automatas.utils;

import java.util.List;

public class Automata {
	String[] estadosTotales;
	String[] lenguaje;
	String[] estadosIniciales;
	String[] estadosFinales;
	List<String[]> transiciones;
	
	public Automata(String[] estadosTotales, String[] lenguaje,
			String[] estadosIniciales, String[] estadosFinales,
			List<String[]> transiciones) {
		this.estadosFinales = estadosFinales;
		this.estadosIniciales = estadosIniciales;
		this.estadosTotales = estadosTotales;
		this.lenguaje = lenguaje;
		this.transiciones = transiciones;
	}
	
	public void printAutomata(){
		for(String et : estadosTotales){
			System.out.print(et + " ");
		}
		System.out.println();
		for(String leng : lenguaje){
			System.out.print(leng + " ");
		}
		System.out.println();
		for(String init : estadosIniciales){
			System.out.print(init + " ");
		}
		System.out.println();
		
		for(String fin : estadosFinales){
			System.out.print(fin + " ");
		}
		System.out.println();
		
		for(String[] trans : transiciones){
			for(String str : trans){
				System.out.print(str + " ");				
			}
			System.out.println();
		}

		
	}

	public String[] getEstadosTotales() {
		return estadosTotales;
	}

	public void setEstadosTotales(String[] estadosTotales) {
		this.estadosTotales = estadosTotales;
	}

	public String[] getLenguaje() {
		return lenguaje;
	}

	public void setLenguaje(String[] lenguaje) {
		this.lenguaje = lenguaje;
	}

	public String[] getEstadosIniciales() {
		return estadosIniciales;
	}

	public void setEstadosIniciales(String[] estadosIniciales) {
		this.estadosIniciales = estadosIniciales;
	}

	public String[] getEstadosFinales() {
		return estadosFinales;
	}

	public void setEstadosFinales(String[] estadosFinales) {
		this.estadosFinales = estadosFinales;
	}

	public List<String[]> getTransiciones() {
		return transiciones;
	}

	public void setTransiciones(List<String[]> transiciones) {
		this.transiciones = transiciones;
	}
}
