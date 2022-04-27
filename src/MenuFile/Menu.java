package MenuFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import MenuFile.User.Reserva;
import MenuFile.User.Usuario;

public class Menu {

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);
		Rotas rotas = new Rotas();
		Usuario usuario = new Usuario();
		Reserva reserva = new Reserva();
//########################################################################################
		String nome = "", cpf = "", pw = "", senha = "", user = "";
		String rotaselecionada = "", poltrona = "", classe = "", reservacadastrada = "";
		int menu, submenu, submenu1;
		int dia = 0, mes = 0, ano = 0;
//########################################################################################	
		while (true) {
			usuario.menuprincipal();
			System.out.print("\nInsira sua opção: ");
			menu = teclado.nextInt();

			switch (menu) {
			case 1:
				System.out.println("Os destinos disponiveis são: " + rotas.Destinos());
				break;
			case 2:
				System.out.println("Venha voar com a gente, cadastre-se ou faça login!");

				while (true) {
					usuario.menulogin();
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
							senha = Files.readAllLines(Paths.get(cpf + ".txt")).get(2);
							user = Files.readAllLines(Paths.get(cpf + ".txt")).get(0);

							if (senha.equals(pw)) {
								System.out.println("");
								System.out.println("Bem vindo(a) " + user);

								while (true) {
									usuario.menuusuario();
									System.out.print("\nInsira sua opção: ");
									submenu1 = teclado.nextInt();

									switch (submenu1) {
									case 1:
										File reservaexiste = new File(usuario.getCpf() + "reserva.txt");
										if (reservaexiste.exists()) {
											System.out.println("Você já possuí uma reserva no CPF " + usuario.getCpf());
											System.out.println("");
										} else {

											rotas.menurotas(rotas);
											System.out.println("Insira sua opção:");
											int destino = teclado.nextInt();

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

											reserva.menuclasse();
											System.out.println("Digite uma classe:");
											classe = teclado.next();

											int classeint = Integer.parseInt(classe);
											while (classeint < 1 || classeint > 3) {
												System.out.println("Digite uma classe válida, entre 1, 2 e 3:");
												classe = teclado.next();
												classeint = Integer.parseInt(classe);
											}

											reserva.menupoltrona();
											System.out.print("\nInsira sua opção: ");
											poltrona = teclado.next();
											int poltronaint = Integer.parseInt(poltrona);

											while (poltronaint <= 0 || poltronaint > 30) {
												System.out.println("Escolha uma poltrona de 1 a 30");
												System.out.print("\nInsira uma opção válida:");
												poltrona = teclado.next();
												poltronaint = Integer.parseInt(poltrona);
											}

											String data = dia + "/" + mes + "/" + ano;
											reserva.setPoltrona(poltrona);
											reserva.setClasse(classe);
											reserva.setData(data);
											reserva.setRota(rotaselecionada);
											reserva.reserva(data, poltrona, classe, rotaselecionada, usuario);

											System.out.println("Reserva feita no CPF " + usuario.getCpf());
											System.out.println("O destino é " + rotaselecionada + " na data " + data);
										}
										break;

									case 2:
										File existe = new File(usuario.getCpf() + "reserva.txt");
										if (existe.exists()) {

											List<String> linhasreserva = Files.readAllLines(
													Paths.get(usuario.getCpf() + "reserva.txt"),
													Charset.defaultCharset());

											reservacadastrada = "O destino é " + linhasreserva.get(3) + " na data: "
													+ linhasreserva.get(0) + " viajando de " + linhasreserva.get(2)
													+ "º classe na poltrona " + linhasreserva.get(1) + "º";

											System.out.println("Iniciando cancelamento de reserva");
											System.out.println("Encontramos a seguinte reserva em seu cadastro: "
													+ reservacadastrada);
											System.out.println(
													"Para realizar o cancelamento de sua reserva digite sua senha:");
											pw = teclado.next();
											senha = Files.readAllLines(Paths.get(usuario.getCpf() + ".txt")).get(2);
											if (senha.equals(pw)) {
												linhasreserva.clear();
												reserva.cancela(usuario);
												reserva.deleta(usuario);
												System.out.println("Reserva cancelada com sucesso.");
											} else {
												System.out.println("Senha inválida");
												System.exit(0);
											}
										} else
											System.out.println("");
										System.out.println("Não existe nenhuma reserva em seu nome.");
										System.out.println("");
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
				System.exit(0);
				break;
			}
		}
	}
}