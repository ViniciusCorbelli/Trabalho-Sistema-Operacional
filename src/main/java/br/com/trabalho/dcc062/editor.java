package br.com.trabalho.dcc062;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class editor {

	static List<String[]> editor = new ArrayList<String[]>();
	static int qtdLinhas = 0;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		boolean loop = true;

		do {
			try {
				System.out.println(" 1 - Editar o documento. ");
				System.out.println(" 2 - Ver o documento. ");
				System.out.println(" 3 - Salvar. ");
				System.out.println(" 0 - Sair. ");
				int resposta = scanner.nextInt();

				switch (resposta) {
				case 1:
					editarDocumento();
					break;

				case 2:
					verDocumento();
					break;

				case 3:
					salvarDocumento();
					break;

				case 0:
					loop = false;
					break;
				}
			} catch (Exception e) {
				System.out.println("Ocorreu um erro interno: " + e);
				loop = false;
			}
		} while (loop == true);
		System.out.println("Finalizado com sucesso!");
	}

	private static void editarDocumento() throws Exception {
		System.out.println("Digite a linha que deseja editar: ");
		int linha = scanner.nextInt();

		System.out.println("Digite a coluna que deseja editar: ");
		int coluna = scanner.nextInt();

		System.out.println("Digite o texto que deseja inserir em [" + linha + ", " + coluna + "] ");
		if (qtdLinhas >= linha) {
			System.out.println("Texto atual: " + editor.get(linha - 1)[coluna - 1]);
		}
		scanner.nextLine();
		String texto = scanner.nextLine();

		verificaLinhas(linha);
		editor.get(linha - 1)[coluna - 1] = texto;
	}

	private static void verificaLinhas(int linha) {
		if (qtdLinhas < linha) {
			for (int i = qtdLinhas; i < linha; i++) {
				editor.add(new String[20]);
				qtdLinhas = linha;
			}
		}
	}

	private static void verDocumento() throws Exception {
		for (String[] linhas : editor) {
			for (String texto : linhas) {
				if (texto != null) {
					System.out.print(texto + " ");
				}
			}
			System.out.println();
		}
	}

	private static void salvarDocumento() throws Exception {
		System.out.println("Qual arquivo será salvo?");
		scanner.nextLine();
		String diretorio = scanner.nextLine();

		FileWriter arq = new FileWriter(diretorio);
		try (PrintWriter printArq = new PrintWriter(arq)) {
			for (String[] linhas : editor) {
				for (String texto : linhas) {
					if (texto != null) {
						printArq.print(texto + " ");
					}
				}
				printArq.println();
			}
			System.out.println("Arquivo salvo em: " + diretorio);
		} catch (Exception e) {
			throw new Exception("Erro ao salvar o arquivo:" + e.getMessage());
		}

	}

}
