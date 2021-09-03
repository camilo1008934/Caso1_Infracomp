package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Comensal extends Thread {
	int idComensal;
	int platos;
	
	Mesa mesa;
	Lavaplatos lavaplatos;
	Cubierto tenedor;
	Cubierto cuchillo;
	
	
	
	public Comensal (int pPlatos, int pId, Mesa pMesa, Lavaplatos pLavaplatos) {
		idComensal=pId;
		platos=pPlatos;
		mesa=pMesa;
		lavaplatos=pLavaplatos;
		tenedor = null;
		cuchillo = null;
	}
	
	public void comerPlato() {
		cogerCubiertos();
		System.out.println("El comensal #"+idComensal +" esta comiendo el plado #"+platos);
		try {
			sleep((long) Math.floor(Math.random()*(5000-3000+1)+3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("El comensal #"+idComensal +" se ha comido el plado #"+platos);
		platos--;
		dejarCubiertos();
		System.out.println("El comensal #"+idComensal +" ha dejado los cubiertos en el fregadero");
	}
	
	public void dejarCubiertos() {
		lavaplatos.addFregadero(tenedor);
		tenedor=null;
		lavaplatos.addFregadero(cuchillo);
		cuchillo=null;
	}
	
	public void cogerCubiertos() {
		while (tenedor==null || cuchillo==null) {
			cogerTenedor();
			cogerCuchillo();
		}
	}
	
	public void cogerTenedor() {
		while (!mesa.estaDisponible("Tenedor")) {
			if (cuchillo!=null) {
				mesa.addDisponible(cuchillo);
				cuchillo=null;
				System.out.println("El comensal #"+idComensal +" ha dejado un cuchillo debido a que no estaba disponible un tenedor");
			}
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		tenedor=mesa.cogerCubierto("Tenedor");
		System.out.println("El comensal #"+idComensal +" ha cogido un tenedor");
		
	}
	
	public void cogerCuchillo() {
		
		while (!mesa.estaDisponible("Cuchillo")) {
			if (tenedor!=null) {
				mesa.addDisponible(tenedor);
				tenedor=null;
				System.out.println("El comensal #"+idComensal +" ha dejado un tenedor debido a que no estaba disponible un cuchillo");
			}
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		cuchillo=mesa.cogerCubierto("Cuchillo");
		System.out.println("El comensal #"+idComensal +" ha cogido un cuchillo");
		
	}
	
	
	public static void main(String[] args) {
		int numComensales=-1, numTenedores=-1, numCuchillos=-1, numPlatos=-1, tamFregadero=-1;
		
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
