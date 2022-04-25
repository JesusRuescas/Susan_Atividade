package MenuFile.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Reserva extends Usuario {
	String data;
	int poltrona;
	int classe;
	String rota;

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

	public int getPoltrona() {
		return poltrona;
	}

	public void setPoltrona(int poltrona) {
		this.poltrona = poltrona;
	}

	public int getClasse() {
		return classe;
	}

	public void setClasse(int classe) {
		this.classe = classe;
	}

	public void reserva(String data, int poltrona, int classe, String rota) throws IOException {
		this.data = data;
		this.poltrona = poltrona;
		this.classe = classe;

		File file = new File(super.cpf + "reserva.txt");

		file.createNewFile();
		System.out.println(file.getAbsolutePath());
		OutputStream os = new FileOutputStream(super.cpf + "reserva.txt");
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
}
