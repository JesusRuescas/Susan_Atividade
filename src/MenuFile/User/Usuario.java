package MenuFile.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Usuario {
	private String nome;
	protected String cpf;
	private String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void registra(String nome, String cpf, String senha) throws IOException {
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;

		File file = new File(cpf + ".txt");

		file.createNewFile();
		System.out.println(file.getAbsolutePath());
		OutputStream os = new FileOutputStream(cpf + ".txt"); // nome do arquivo que será escrito
		Writer wr = new OutputStreamWriter(os); // criação de um escritor
		BufferedWriter br = new BufferedWriter(wr); // adiciono a um escritor de buffer
		br.write(nome);
		br.newLine();
		br.write(cpf);
		br.newLine();
		br.write(senha);
		br.newLine();
		br.write(System.setProperty("line.separator", "\r\n"));
		br.close();
	}

	public void menuprincipal() {
		System.out.println(" ");
		System.out.println("Menu de vigagem");
		System.out.println("1.) Rotas");
		System.out.println("2.) Login/Cadastro");
		System.out.println("3.) Sair.");

	}

	public void menulogin() {
		System.out.println(" ");
		System.out.println("1.) Cadastre-se");
		System.out.println("2.) Login");
		System.out.println("3.) Sair.");

	}

	public void menuusuario() {
		System.out.println("Menu do usuário");
		System.out.print("1.) Fazer reserva\n");
		System.out.print("2.) Cancelar reserva\n");
		System.out.print("3.) Sair.\n");

	}
}