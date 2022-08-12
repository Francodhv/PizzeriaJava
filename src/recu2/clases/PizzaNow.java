package recu2.clases;

import java.util.ArrayList;

public class PizzaNow {

	private static final String MSG_CANCELACION_PEDIDO_NRO = "*** Cancelacion del pedido %s\n";
	private static final String MSG_CANCELACION_PEDIDO_TEL = "*** Cancelacion del pedido tel %s\n";
	private static final String MSG_LISTADO_COMPLETO_NRO = "*** Listado Completo del pedido %s\n";
	private static final String MSG_LISTADO_COMPLETO_TEL = "*** Listado Completo del pedido tel %s\n";
	private static final String MSG_DET_ACTUALIZACION_NRO = "*** Actualizacion para el pedido %s\n--- %s %s de %s \n";
	private static final String MSG_DET_ACTUALIZACION_TEL = "*** Actualizacion para el pedido tel %s\n--- %s %s de %s \n";
	private static final String PEDIDO_CREADO = "Creado pedido %s para %s (%s)\n";
	private static final String TITULO_CANCELADOS = "*** Pedidos cancelados del dia ***";
	private static final String ERROR_PEDIDO_INVALIDO = "Pedido invalido";
	private static final String ERROR_PEDIDO_INEXISTENTE = "Pedido inexistente";
	private int proximoNroPedido;
	private ArrayList<Pedido> pedidos;
	private ArrayList<Pedido> pedidosCancelados;

	public PizzaNow() {
		proximoNroPedido = 1;
		pedidos = new ArrayList<>();
		pedidosCancelados = new ArrayList<>();
	}

	public void actualizarPedido(int nroPedido, Gusto gusto, Tamanio tamanio, int cantidad) {
		System.out.printf(MSG_DET_ACTUALIZACION_NRO, nroPedido, cantidad, tamanio, gusto);
		if (nroDePedidoValido(nroPedido))
			actualizarPedido(buscarPedido(nroPedido), gusto, tamanio, cantidad);
		else
			System.out.println(ERROR_PEDIDO_INVALIDO);
	}

	public void actualizarPedido(String telefono, Gusto gusto, Tamanio tamanio, int cantidad) {
		System.out.printf(MSG_DET_ACTUALIZACION_TEL, telefono, cantidad, tamanio, gusto);
		if (telefonoValido(telefono))
			actualizarPedido(buscarPedido(telefono), gusto, tamanio, cantidad);
		else
			System.out.println(ERROR_PEDIDO_INVALIDO);
	}

	private void actualizarPedido(Pedido pedido, Gusto gusto, Tamanio tamanio, int cantidad) {
		if (pedido != null)
			System.out.println(pedido.actualizar(gusto, tamanio, cantidad));
		else
			System.out.println(ERROR_PEDIDO_INEXISTENTE);
	}

	private Pedido buscarPedido(int nroPedido) {
		Pedido pedido = null;
		int pos = 0;
		while (pos < pedidos.size() && !pedidos.get(pos).mismoPedido(nroPedido))
			pos++;
		if (pos < pedidos.size())
			pedido = pedidos.get(pos);
		return pedido;
	}

	private Pedido buscarPedido(String telefono) {
		Pedido pedido = null;
		int pos = 0;
		while (pos < pedidos.size() && !pedidos.get(pos).mismoPedido(telefono))
			pos++;
		if (pos < pedidos.size())
			pedido = pedidos.get(pos);
		return pedido;
	}

	public void cancelarPedido(int nroPedido) {
		System.out.printf(MSG_CANCELACION_PEDIDO_NRO, nroPedido);
		if (nroDePedidoValido(nroPedido))
			cancelarPedido(buscarPedido(nroPedido));
		else
			System.out.println(ERROR_PEDIDO_INVALIDO);
	}

	public void cancelarPedido(String telefono) {
		System.out.printf(MSG_CANCELACION_PEDIDO_TEL, telefono);
		if (telefonoValido(telefono))
			cancelarPedido(buscarPedido(telefono));
		else
			System.out.println(ERROR_PEDIDO_INVALIDO);
	}

	private void cancelarPedido(Pedido pedido) {
		if (pedido != null) {
			pedidos.remove(pedido);
			pedidosCancelados.add(pedido);
		} else
			System.out.println(ERROR_PEDIDO_INEXISTENTE);
	}

	public int crearORecuperarPedido(String telefono, String domicilio) {
		Pedido pedido = buscarPedido(telefono);
		if (pedido == null)
			pedido = registrarPedido(telefono, domicilio);
		System.out.printf(PEDIDO_CREADO, pedido.getNumero(), telefono, domicilio);
		return pedido.getNumero();
	}

	private int getProximoNumeroPedido() {
		int actual = proximoNroPedido;
		proximoNroPedido++;
		return actual;
	}

	public void listarPedidoCompleto(int nroPedido) {
		System.out.printf(MSG_LISTADO_COMPLETO_NRO, nroPedido);
		if (nroDePedidoValido(nroPedido))
			listarPedidoCompleto(buscarPedido(nroPedido));
		else
			System.out.println(ERROR_PEDIDO_INVALIDO);
	}

	public void listarPedidoCompleto(String telefono) {
		System.out.printf(MSG_LISTADO_COMPLETO_TEL, telefono);
		if (telefonoValido(telefono))
			listarPedidoCompleto(buscarPedido(telefono));
		else
			System.out.println(ERROR_PEDIDO_INVALIDO);
	}

	private void listarPedidoCompleto(Pedido pedido) {
		if (pedido != null)
			pedido.listarCompleto();
		else
			System.out.println(ERROR_PEDIDO_INEXISTENTE);
	}

	public void mostrarPedidosCancelados() {
		System.out.println(TITULO_CANCELADOS);
		for (Pedido pedido : pedidosCancelados) {
			pedido.listarCompleto();
			System.out.println();
		}
	}

	private boolean nroDePedidoValido(int nroPedido) {
		return nroPedido > 0 && nroPedido < proximoNroPedido;
	}

	private Pedido registrarPedido(String telefono, String domicilio) {
		Pedido pedido = new Pedido(getProximoNumeroPedido(), telefono, domicilio);
		pedidos.add(pedido);
		return pedido;
	}

	private boolean telefonoValido(String telefono) {
		return telefono != null && !telefono.isEmpty();
	}

}