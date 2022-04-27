package MenuFile.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Reserva extends Usuario {
	String data;
	String poltrona;
	String classe;
	String rota;
	private Usuario usuario;

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPoltrona() {
		return poltrona;
	}

	public void setPoltrona(String poltrona) {
		this.poltrona = poltrona;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public void reserva(String data, String poltrona, String classe, String rota, Usuario usuario) throws IOException {
		this.data = data;
		this.poltrona = poltrona;
		this.classe = classe;
		this.usuario = usuario;

		File file = new File(this.usuario.cpf + "reserva.txt");

		file.createNewFile();
		System.out.println(file.getAbsolutePath());
		OutputStream os = new FileOutputStream(this.usuario.cpf + "reserva.txt");
		Writer wr = new OutputStreamWriter(os);
		BufferedWriter br = new BufferedWriter(wr);
		br.write(data);
		br.newLine();
		br.write(poltrona);
		br.newLine();
		br.write(classe);
		br.newLine();
		br.write(rota);
		br.newLine();
		br.write(System.setProperty("line.separator", "\r\n"));
		br.close();
	}

	public void cancela(Usuario usuario) throws IOException {
		this.usuario = usuario;
		File limpa = new File(this.usuario.cpf + "reserva.txt");
		limpa.createNewFile();
		System.out.println(limpa.getAbsolutePath());
		OutputStream outs = new FileOutputStream(this.usuario.cpf + "reserva.txt");
		Writer wrt = new OutputStreamWriter(outs);
		BufferedWriter bwr = new BufferedWriter(wrt);
		bwr.write("");
		bwr.newLine();
		bwr.close();

	}

	public void deleta(Usuario usuario) {
		this.usuario = usuario;
		try {
			Files.delete(Paths.get(this.usuario.cpf + "reserva.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void menuclasse() {
		System.out.println("Classe 1º = Luxo");
		System.out.println("Classe 2º = Padrão");
		System.out.println("Classe 3º = Economica");

	}

	public void menupoltrona() {
		System.out.println("Escolha uma poltrona de 1 a 30");
		System.out.println("Poltronas de 1 á 10 ficam localizadas na frente");
		System.out.println("Poltronas de 10 á 20 ficam localizadas no meio");
		System.out.println("Poltronas de 20 á 30 ficam localizadas no fim");

	}
}
