package recu2.clases;

import java.util.ArrayList;

public class Pedido {

	private static final String DESC_DOMICILIO = "Domicilio: %s\n";
	private static final String DESC_PEDIDO = "Pedido: %s\n";
	private static final String DESC_TELEFONO = "Telefono: %s\n";
	private static final String ERROR_CANTIDAD_INVALIDA = "Cantidad de pizzas incorrecta";
	private static final String PEDIDO_OK = "Pedido %s actualizado";
	private static final String PEDIDO_VACIO = "Pedido %s se ha quedado sin pizzas.";
	private static final String ERROR_ITEM_INEXISTENTE = "Item inexistente.";
	private static final String PIZZAS_REMOVIDAS = "%s pizza(s) %s de %s removida(s).";
	private static final String SIN_PIZZAS = "Sin pizzas.";
	private int numero;
	private String telefono;
	private String domicilio;
	private ArrayList<Item> items;

	public Pedido(int numero, String telefono, String domicilio) {
		this.numero = numero;
		this.telefono = telefono;
		this.domicilio = domicilio;
		items = new ArrayList<>();
	}

	public String actualizar(Gusto gusto, Tamanio tamanio, int cantidad) {
		String mensaje = "";
		if (cantidad < 0)
			mensaje = ERROR_CANTIDAD_INVALIDA;
		else {
			Item item = buscarItem(gusto, tamanio);
			if (cantidad == 0) {
				if (item == null)
					mensaje = ERROR_ITEM_INEXISTENTE;
				else {
					items.remove(item);
					mensaje = String.format(PIZZAS_REMOVIDAS, item.getCantidad(), tamanio, gusto);
					if (items.isEmpty())
						mensaje += " " + String.format(PEDIDO_VACIO, numero);
				}
			} else {
				if (item == null)
					items.add(new Item(gusto, tamanio, cantidad));
				else
					item.setCantidad(cantidad);
				mensaje = String.format(PEDIDO_OK, numero);
			}
		}
		return mensaje;
	}

	private Item buscarItem(Gusto gusto, Tamanio tamanio) {
		Item item = null;
		int pos = 0;
		while (pos < items.size() && !items.get(pos).esCoincidente(gusto, tamanio)) {
			pos++;
		}
		if (pos < items.size())
			item = items.get(pos);
		return item;
	}

	public int getNumero() {
		return numero;
	}

	public void listarCompleto() {
		mostrarDatosPropios();
		if (items.isEmpty())
			System.out.println(SIN_PIZZAS);
		else
			for (Item item : items)
				System.out.println(item);
	}

	public boolean mismoPedido(int numero) {
		return this.numero == numero;
	}

	public boolean mismoPedido(String telefono) {
		return this.telefono.equals(telefono);
	}

	private void mostrarDatosPropios() {
		System.out.printf(DESC_PEDIDO, numero);
		System.out.printf(DESC_TELEFONO, telefono);
		System.out.printf(DESC_DOMICILIO, domicilio);
	}

}
