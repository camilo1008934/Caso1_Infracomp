package Main;

public class Comensal {
	String nombre;
	int platos;
	
	Cubierto tenedor;
	Cubierto cuchillo;
	
	public Comensal (int pPlatos, String pNombre) {
		this.nombre=pNombre;
		this.platos=pPlatos;
		tenedor = new Cubierto ("Tenedor");
		cuchillo = new Cubierto ("Cuchillo");
	}
	
	public void comerPlato() {
		
	}
	
	public void cambiarCubiertos() {
		
	}

}
