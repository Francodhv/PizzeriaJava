package recu2.clases;

public class Item {

	private static final String FORMATO_SALIDA = "%s %s(s) de %s";
	private int cantidad;
	private Gusto gusto;
	private Tamanio tamanio;

	public Item(Gusto sabor, Tamanio tamanio, int cantidad) {
		this.gusto = sabor;
		this.tamanio = tamanio;
		this.setCantidad(cantidad);
	}

	public boolean esCoincidente(Gusto sabor, Tamanio tamanio) {
		return this.gusto.equals(sabor) && this.tamanio.equals(tamanio);
	}

	public int getCantidad() {
		return cantidad;
	}

	public boolean setCantidad(int cantidad) {
		boolean ok = false;
		if (cantidad > 0) {
			this.cantidad = cantidad;
			ok = true;
		}
		return ok;
	}

	@Override
	public String toString() {
		return String.format(FORMATO_SALIDA, cantidad, tamanio, gusto);
	}
}
