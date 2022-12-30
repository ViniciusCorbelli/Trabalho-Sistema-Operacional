package br.com.trabalho.dcc062;

import java.util.Scanner;

public class editor {

	public static void main(String[] args) {
		String[][] editor = new String[20][100];
		boolean loop = true;
		String diretorio = "";

		try (Scanner scanner = new Scanner(System.in)) {
			do {
				System.out.println("Deseja editar o arquivo? (S/N) ");
				String resposta = scanner.nextLine();
				if (resposta.equalsIgnoreCase("s")) {

					System.out.println("Digite a linha que deseja editar (Max: 100): ");
					int linha = scanner.nextInt();
					if (linha > 100 || linha < 1) {
						System.out.println("O tamanho máximo do arquivo é de 100 linhas!");
						continue;
					}

					System.out.println("Digite a coluna que deseja editar (Max: 30): ");
					int coluna = scanner.nextInt();
					if (coluna > 30 || coluna < 1) {
						System.out.println("O tamanho máximo do arquivo é de 30 colunas!");
						continue;
					}
					scanner.nextLine();

					System.out.println("Digite o texto que deseja inserir em (" + linha + ", " + coluna + ") ");
					System.out.println("Texto atual: " + editor[coluna - 1][linha - 1]);
					String texto = scanner.nextLine();
					editor[coluna - 1][linha - 1] = texto;

				} else if (resposta.equalsIgnoreCase("n")) {
					loop = false;
				} else {
					System.out.println("Resposta incorreta.");
				}

			} while (loop == true);
			
			System.out.println("Qual o diretorio o arquivo será salvo?");
			diretorio = scanner.nextLine();
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro interno: " + e.getMessage());
		}

		// Usar chamada de sistema para criar um arquivo e salvar o texto que o usuário criou.
		for (String[] linhas : editor) {
			for (String texto : linhas) {
				System.out.print(texto + " ");
			}
			System.out.println();
		}
		System.out.println("Arquivo salvo em: " + diretorio);

	}

}
