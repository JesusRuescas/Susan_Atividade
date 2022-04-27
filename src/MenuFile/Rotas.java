package MenuFile;

import java.util.ArrayList;

public class Rotas {
	public ArrayList<String> Destinos() {
		ArrayList<String> rotas = new ArrayList<String>();
		rotas.add("Porto Seguro");
		rotas.add("Pernambuco");
		rotas.add("Porto de Galinhas");
		return rotas;

	}

	public void menurotas(Rotas rotas) {
		System.out.println("Iniciando reserva, insira o destino\n");
		System.out.println("1.) " + rotas.Destinos().get(0));
		System.out.println("2.) " + rotas.Destinos().get(1));
		System.out.println("3.) " + rotas.Destinos().get(2));
	}
}
