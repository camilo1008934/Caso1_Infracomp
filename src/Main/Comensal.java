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
		tenedor = new Cubierto ("Cuchillo");
	}

}