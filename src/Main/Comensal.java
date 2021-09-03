package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Comensal {
	String nombre;
	int platos;
	
	Cubierto tenedor;
	Cubierto cuchillo;
	
	
	
	public Comensal (int pPlatos, String pNombre) {
		nombre=pNombre;
		platos=pPlatos;
		tenedor = new Cubierto ("Tenedor");
		cuchillo = new Cubierto ("Cuchillo");
	}
	
	public void comerPlato() {
		
	}
	
	public void cogerTenedor() {
		
	}
	
	public void cogerCuchillo() {
		
	}
	
	
	public static void main(String[] args) {
		int numComensales, numTenedores, numCuchillos, numPlatos, tamFregadero;
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File("data/propiedades.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String linea = null;
		try {
			linea = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			String[] data_combo = linea.split("=");
			
			if (data_combo[0].equals("numComensales")) {
				numComensales = Integer.parseInt(data_combo[1]);				
			}else if (data_combo[0].equals("numCubiertosTenedores")) {
				numTenedores = Integer.parseInt(data_combo[1]);
			}else if(data_combo[0].equals("numCubiertosCuchillos")) {
				numCuchillos = Integer.parseInt(data_combo[1]);
			}else if (data_combo[0].equals("numPlatos")) {
				numPlatos = Integer.parseInt(data_combo[1]);
			}else if (data_combo[0].equals("tamFregadero")) {
				tamFregadero = Integer.parseInt(data_combo[1]);
			}
			
			try {
				linea=br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
	}

}
