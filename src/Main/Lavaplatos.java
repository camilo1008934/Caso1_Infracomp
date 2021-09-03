package Main;
import java.util.ArrayList;

public class Lavaplatos extends Thread{
	int sizeFregadero;
	ArrayList<Cubierto> fregadero;
	Mesa mesa;

	public Lavaplatos(int size, Mesa pMesa) {
		sizeFregadero = size;
		mesa=pMesa;
		fregadero=new ArrayList<Cubierto>();
	}
	
	public synchronized void addFregadero (Cubierto aCubierto) {
		while (fregadero.size()+1>sizeFregadero) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		fregadero.add(aCubierto);		
		System.out.println("En el fregadero hay "+fregadero.size()+" cubiertos.");
		notifyAll();
	}
	
	public synchronized void lavar() {
		if (fregadero.size()<=0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			sleep((long) Math.floor(Math.random()*(2000-1000+1)+1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mesa.addDisponible(fregadero.get(0));
		fregadero.remove(0);
		System.out.println("Se ha lavado un cubierto, en el fregadero hay "+fregadero.size()+" cubiertos.");
		notifyAll();
		
	}
	
	
	public void run() {
		while (true) {
			lavar();
		}
	}
	
}
