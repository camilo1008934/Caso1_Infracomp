package Main;

public class Cubierto {
	String tipo;
	boolean sucio;
	
	public Cubierto(String pTipo) {
		tipo=pTipo;
		sucio=false;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void ensuciarCubierto() {
		sucio=true;
	}
	
	public void limpiarCubierto() {
		sucio=false;
	}
}
