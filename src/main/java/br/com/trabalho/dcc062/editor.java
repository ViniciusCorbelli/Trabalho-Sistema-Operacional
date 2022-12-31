package br.com.trabalho.dcc062;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class editor {

	private static Scanner scanner = new Scanner(System.in);

	private static List<arquivo> arquivos = new ArrayList<arquivo>();
	private static arquivo arquivo;

	private static String[] metodos = new String[] { "Sair", "abrirArquivo", "trocarArquivo", "editarDocumento",
			"verDocumento", "salvarDocumento" };

	public static void main(String[] args) {
		boolean loop = true;
		arquivos.add(new arquivo());
		arquivo = arquivos.get(0);

		do {
			try {
				System.out.println(" 1 - Abrir arquivo. ");
				System.out.println(" 2 - Trocar de arquivo. ");
				System.out.println(" 3 - Editar o documento. ");
				System.out.println(" 4 - Ver o documento. ");
				System.out.println(" 5 - Salvar. ");
				System.out.println(" 0 - Sair. ");
				int resposta = scanner.nextInt();

				if (resposta == 0) {
					loop = false;
				} else {
					editor obj = new editor();
					Class<?> classObj = obj.getClass();
					Method printMessage = classObj.getDeclaredMethod(metodos[resposta]);

					try {
						printMessage.invoke(obj);
					} catch (InvocationTargetException e) {
						System.out.println("Ocorreu um erro interno: " + e.getCause());
					}

				}

			} catch (Exception e) {
				System.out.println("Ocorreu um erro interno: " + e.getCause());
				loop = false;
			}
		} while (loop == true);
		System.out.println("Finalizado com sucesso!");
	}

	private static int sair() {
		return 0;
	}

	private static void abrirArquivo() throws FileNotFoundException {
		System.out.println("Deseja abrir ou arquivo (S/N)");
		scanner.nextLine();
		String resposta = scanner.nextLine();
		
		if (resposta.equalsIgnoreCase("S")) {
			arquivo novoArquivo = new arquivo();
			
			Scanner in = new Scanner(new FileReader(resposta));
			int linha = 0;
			while (in.hasNextLine()) {
				linha++;
			    String texto = scanner.nextLine();
			    arquivo.setTexto(linha, 1, texto);
			}
			
			arquivos.add(novoArquivo);
		} else {
			arquivos.add(new arquivo());
		}
		arquivo = arquivos.get(arquivos.size() - 1);
		System.out.println("Arquivo aberto em " + arquivos.size());
	}

	private static void trocarArquivo() throws Exception {
		System.out.println("Qual arquivo você deseja editar? (Max: " + arquivos.size() + ")");
		int resposta = scanner.nextInt();
		if (resposta > arquivos.size()) {
			throw new Exception("Arquivo não encontrado.");
		}
		arquivo = arquivos.get(resposta - 1);
	}

	private static void editarDocumento() {
		System.out.println("Digite a linha que deseja editar: ");
		int linha = scanner.nextInt();

		System.out.println("Digite a coluna que deseja editar: ");
		int coluna = scanner.nextInt();

		System.out.println("Digite o texto que deseja inserir em [" + linha + ", " + coluna + "] ");
		try {
			System.out.println("Texto atual: " + arquivo.getTexto(linha, coluna));
		} catch (Exception e) {}
		
		scanner.nextLine();
		String texto = scanner.nextLine();

		arquivo.setTexto(linha, coluna, texto);
	}

	private static void verDocumento() {
		arquivo.verDocumento();
	}

	private static void salvarDocumento() throws Exception {
		System.out.println("Qual arquivo será salvo?");
		scanner.nextLine();
		String diretorio = scanner.nextLine();

		try {
			arquivo.salvarDocumento(diretorio);
			System.out.println("Arquivo salvo em: " + diretorio);
		} catch (Exception e) {
			throw new Exception("Erro ao salvar o arquivo:" + e.getCause());
		}
	}

}
