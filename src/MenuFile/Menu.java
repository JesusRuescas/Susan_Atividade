package MenuFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import MenuFile.User.Usuario;

public class Menu {

	public static void main(String[] args) throws IOException {
		Scanner teclado = new Scanner(System.in);
		Rotas rotas = new Rotas();
		Usuario usuario = new Usuario();
//########################################################################################
		String nome = "";
		String cpf = "";
		String pw = "";
		int menu, submenu, submenu1;
		int dia = 0, mes = 0, ano = 0;
		int poltrona = 0, classe = 0;
//########################################################################################	

		while (true) {
			System.out.println(" ");
			System.out.println("Menu de vigagem");
			System.out.println("1.) Rotas");
			System.out.println("2.) Login/Cadastro");
			System.out.println("3.) Sair.");
			System.out.print("\nInsira sua opção: ");
			menu = teclado.nextInt();

			switch (menu) {
			case 1:
				System.out.println("Os destinos disponiveis são: " + rotas.Destinos());
				break;
			case 2:
				System.out.println("Apenas usuários cadastrados podem fazer uma reserva.");

				while (true) {
					System.out.println(" ");
					System.out.println("1.) Cadastre-se");
					System.out.println("2.) Login");
					System.out.println("3.) Sair.");
					System.out.print("\nInsira sua opção: ");
					submenu = teclado.nextInt();

					switch (submenu) {
					case 1:
						System.out.println("Digite seu nome");
						nome = teclado.next();

						usuario.setNome(nome);

						System.out.println("Digite seu CPF");
						cpf = teclado.next();
						while (cpf.length() != 11) {
							System.out.println("Digite um CPF válido");
							cpf = teclado.next();
						}
						usuario.setCpf(cpf);

						System.out.println("Digite sua senha");
						pw = teclado.next();
						usuario.setSenha(pw);

						File file = new File(usuario.getCpf() + ".txt");
						if (file.exists()) {
							System.out.println("Você já possuí um cadastro");
							System.exit(0);
						} else {
							usuario.registra(nome, cpf, pw);
							System.out.println("");
							System.out.println("Bem vindo(a) " + usuario.getNome());
							System.out.println("");
						}
						break;

					case 2:
						System.out.println("Digite seu CPF");
						cpf = teclado.next();

						usuario.setCpf(cpf);

						System.out.println("Digite sua senha");
						pw = teclado.next();
						usuario.setSenha(pw);

						try {
							String senha = Files.readAllLines(Paths.get(cpf + ".txt")).get(2);
							String user = Files.readAllLines(Paths.get(cpf + ".txt")).get(0);

							if (senha.equals(pw)) {
								System.out.println("");
								System.out.println("Bem vindo(a) " + user);

								while (true) {
									System.out.println(" ");
									System.out.println("Menu do usuário");
									System.out.print("1.) Fazer reserva\n");
									System.out.print("2.) Cancelar reserva\n");
									System.out.print("3.) Sair.\n");
									System.out.print("\nInsira sua opção: ");
									submenu1 = teclado.nextInt();

									switch (submenu1) {
									case 1:
										System.out.println("Iniciando reserva, insira o destino\n");
										System.out.println("1.) " + rotas.Destinos().get(0));
										System.out.println("2.) " + rotas.Destinos().get(1));
										System.out.println("3.) " + rotas.Destinos().get(2));
										System.out.println("Insira sua opção:");
										int destino = teclado.nextInt();

										String rotaselecionada = "";

										if (destino == 1) {
											rotaselecionada = rotas.Destinos().get(0);

										} else if (destino == 2) {
											rotaselecionada = rotas.Destinos().get(1);

										} else if (destino == 3) {
											rotaselecionada = rotas.Destinos().get(2);
										} else {
											System.out.println("Você digitou uma opção inválida!");
											System.out.println("Saindo");
											System.exit(0);
										}

										System.out.println("Digite o dia:");
										dia = teclado.nextInt();
										while (dia <= 0 || dia > 31) {
											System.out.println("Digite um dia válido:");
											dia = teclado.nextInt();
										}

										System.out.println("Digite o mes:");
										mes = teclado.nextInt();
										while (mes <= 0 || mes > 12) {
											System.out.println("Digite um mes válido:");
											mes = teclado.nextInt();
										}

										System.out.println("Digite o ano:");
										ano = teclado.nextInt();
										while (ano < 2022) {
											System.out.println("Digite um ano válido:");
											ano = teclado.nextInt();
										}

										System.out.println("Classe 1º = Luxo");
										System.out.println("Classe 2º = Padrão");
										System.out.println("Classe 3º = Economica");
										System.out.println("Digite uma classe:");
										classe = teclado.nextInt();
										while (classe < 1 || classe > 3) {
											System.out.println("Digite uma classe válida, entre A, B e C:");
											classe = teclado.nextInt();
										}

										System.out.println("Escolha uma poltrona de 1 a 30");
										System.out.println("Poltronas de 1 á 10 ficam localizadas na frente");
										System.out.println("Poltronas de 10 á 20 ficam localizadas no meio");
										System.out.println("Poltronas de 20 á 30 ficam localizadas no fim");
										System.out.print("\nInsira sua opção: ");
										poltrona = teclado.nextInt();

										while (poltrona <= 0 && poltrona > 30) {
											System.out.println("Escolha uma poltrona de 1 a 30");
											System.out.print("\nInsira uma opção válida:");
											poltrona = teclado.nextInt();
										}

										String poltronaselecionada = poltrona + "º";
										String classeselecionada = classe + "º";

										String data = dia + "/" + mes + "/" + ano;
//										Reserva reserva = new Reserva();
//										reserva.setPoltrona(poltrona);
//										reserva.setClasse(classe);
//										reserva.setData(data);
//										reserva.setRota(rotaselecionada);
//										reserva.reserva(data, poltrona, classe, rotaselecionada);

										File reservalog = new File(cpf + "reserva.txt");
										reservalog.createNewFile();
										System.out.println(reservalog.getAbsolutePath());
										OutputStream os = new FileOutputStream(cpf + "reserva.txt");
										Writer wr = new OutputStreamWriter(os);
										BufferedWriter br = new BufferedWriter(wr);
										br.write(data);
										br.newLine();
										br.write(rotaselecionada);
										br.newLine();
										br.write(poltronaselecionada.toString());
										br.newLine();
										br.write(classeselecionada.toString());
										br.newLine();
										br.write(System.setProperty("line.separator", "\r\n"));
										br.close();

										System.out.println("Reserva feita no CPF " + cpf);
										System.out.println("O destino é " + rotaselecionada + " na data " + data);
										break;

									case 2:
										List<String> linhasreserva = Files.readAllLines(Paths.get(cpf + "reserva.txt"),
												Charset.defaultCharset());
										String reservacadastrada = "O destino é " + linhasreserva.get(1) + " na data: "
												+ linhasreserva.get(0) + " Viajando de classe: " + linhasreserva.get(3)
												+ " Na poltrona " + linhasreserva.get(2);

										System.out.println("Iniciando cancelamento de reserva");
										System.out.println(
												"Encontramos a seguinte reserva em seu cadastro: " + reservacadastrada);
										System.out.println(
												"Para realizar o cancelamento de sua reserva digite sua senha:");
										pw = teclado.next();
										senha = Files.readAllLines(Paths.get(cpf + ".txt")).get(2);
										if (senha.equals(pw)) {
											linhasreserva.clear();
											File limpa = new File(cpf + "reserva.txt");
											limpa.createNewFile();
											System.out.println(limpa.getAbsolutePath());
											OutputStream outs = new FileOutputStream(cpf + "reserva.txt");
											Writer wrt = new OutputStreamWriter(outs);
											BufferedWriter bwr = new BufferedWriter(wrt);
											bwr.write("");
											bwr.newLine();
											bwr.close();

											System.out.println("Reserva cancelada com sucesso.");
										} else {
											System.out.println("Senha inválida");
											System.exit(0);
										}
										break;
									case 3:
										System.out.println("Saindo");
										System.exit(0);
										break;
									default:
										System.out.println("Opção inválida!");
										break;
									}
								}
							} else
								System.out.println("Usuário ou senha inválidos!");
							System.exit(0);

						} catch (NoSuchFileException e) {
							System.out.println("Usuário ou senha inválidos!");
							System.exit(0);
						}
						break;
					case 3:
						System.out.println("Saindo");
						System.exit(0);
						break;

					default:
						System.out.println("Opção inválida!");
						System.exit(0);
						break;
					}
				}
			case 3:
				System.out.println("Saindo");
				System.exit(0);
				break;
			default:
				System.out.println("Opção inválida!");
				break;
			}
		}
	}
}