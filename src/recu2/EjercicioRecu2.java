package recu2;

import recu2.clases.Gusto;
import recu2.clases.PizzaNow;
import recu2.clases.Tamanio;

public class EjercicioRecu2 {
	private static PizzaNow app;

	public static void main(String[] args) {
		app = new PizzaNow();

		int nroPedido = app.crearORecuperarPedido("4777-7777", "9 de Julio 77");
		int nroPedidoACancelar = app.crearORecuperarPedido("2456-9785", "Bermudez 977, Piso 4");
		System.out.println();

		actualizarPedido(nroPedido, Gusto.MUZZARELLA, Tamanio.GRANDE, 2);
		actualizarPedido(nroPedido, Gusto.NAPOLITANA, Tamanio.GRANDE, 1);
		actualizarPedido("4777-7777", Gusto.FUGAZZETA, Tamanio.GRANDE, -1);
		actualizarPedido(-1, Gusto.FUGAZZETA, Tamanio.GRANDE, 1);
		actualizarPedido(88, Gusto.VERDURA, Tamanio.INDIVIDUAL, 1);
		actualizarPedido(nroPedidoACancelar, Gusto.MUZZARELLA, Tamanio.GRANDE, 2);

		listarPedidoCompleto(nroPedido);
		listarPedidoCompleto(5);

		actualizarPedido(nroPedido, Gusto.MUZZARELLA, Tamanio.GRANDE, 0);
		actualizarPedido(nroPedidoACancelar, Gusto.MUZZARELLA, Tamanio.GRANDE, 6);

		app.cancelarPedido(nroPedido);
		app.cancelarPedido("2456-9785");

		app.mostrarPedidosCancelados();

	}

	private static void listarPedidoCompleto(int nroPedido) {
		app.listarPedidoCompleto(nroPedido);
		System.out.println();
	}

	private static void actualizarPedido(int nroPedido, Gusto gusto, Tamanio tamanio, int cantidad) {
		app.actualizarPedido(nroPedido, gusto, tamanio, cantidad);
		System.out.println();
	}

	private static void actualizarPedido(String telefono, Gusto gusto, Tamanio tamanio, int cantidad) {
		app.actualizarPedido(telefono, gusto, tamanio, cantidad);
		System.out.println();
	}

}