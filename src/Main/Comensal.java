package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class Comensal extends Thread {
	int idComensal;
	int platos;
	int espera;
	
	private static CyclicBarrier barrera;
	Mesa mesa;
	Lavaplatos lavaplatos;
	Cubierto tenedor;
	Cubierto cuchillo;
	
	
	
	public Comensal (int pPlatos, int pId, Mesa pMesa, Lavaplatos pLavaplatos) {
		idComensal=pId;
		platos=pPlatos;
		espera=pPlatos/2;
		mesa=pMesa;
		lavaplatos=pLavaplatos;
		tenedor = null;
		cuchillo = null;
	}
	
	public void comerPlato() {
		cogerCubiertos();
		System.out.println("El comensal #"+idComensal +" esta comiendo el plato #"+platos);
		try {
			sleep((long) Math.floor(Math.random()*(5000-3000+1)+3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("El comensal #"+idComensal +" se ha comido el plato #"+platos);
		
		
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
	
	public synchronized void cogerTenedor() {
		while (!mesa.estaDisponible("Tenedor")) {
			if (cuchillo!=null) {
				System.out.println("El comensal #"+idComensal +" ha dejado un cuchillo debido a que no estaba disponible un tenedor");
				mesa.addDisponible(cuchillo);
				cuchillo=null;
				
			}
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		System.out.println("El comensal #"+idComensal +" ha cogido un tenedor");
		tenedor=mesa.cogerCubierto("Tenedor");
		
	}
	
	public synchronized void cogerCuchillo() {
		
		while (!mesa.estaDisponible("Cuchillo")) {
			if (tenedor!=null) {
				System.out.println("El comensal #"+idComensal +" ha dejado un tenedor debido a que no estaba disponible un cuchillo");
				mesa.addDisponible(tenedor);
				tenedor=null;
				
			}
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("El comensal #"+idComensal +" ha cogido un cuchillo");		
		cuchillo=mesa.cogerCubierto("Cuchillo");
		
		
	}
	
	public void run() {
		while (platos>0) {
			if (platos==espera) {
				System.out.println("Hilos esperando "+ (Comensal.barrera.getNumberWaiting()+1));
				try {
					Comensal.barrera.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
			comerPlato();
		}
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
		barrera=new CyclicBarrier(numComensales-1);
		
		
		Mesa ms = new Mesa(numTenedores, numCuchillos);		
		Lavaplatos lp = new Lavaplatos(tamFregadero, ms);
		lp.start();
		for (int i=1; i<=numComensales; i++) {
			new Comensal(numPlatos, i, ms, lp).start();
		}
		
	}

}
