package br.com.trabalho.dcc062;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Acoes {

	private Scanner scanner = new Scanner(System.in);
	private static Arquivos arquivos = new Arquivos();
	
	public Acoes() {
	}

	public boolean sair() {
		return false;
	}

	public boolean abrirArquivo() throws Exception {
		System.out.println("Deseja abrir ou editor (S/N)");
		String resposta = scanner.nextLine();
		
		if (resposta.equalsIgnoreCase("S")) {
			Editor novoeditor = new Editor();
			
			try {
				Scanner in = new Scanner(new FileReader(resposta));
				int linha = 0;
				while (in.hasNextLine()) {
					linha++;
					scanner.nextLine();
				    String texto = scanner.nextLine();
				    
				    int coluna = 1;
				    for (String palavra : texto.split(" ")) {
						arquivos.getEditor().setTexto(linha, coluna, palavra);
						coluna++;
						if (coluna > arquivos.getEditor().MAX_COLUNAS) {
							linha++;
							coluna = 1;
						}
					}
				    arquivos.getEditor().setTexto(linha, 1, texto);
				}
			} catch (FileNotFoundException e) {
				throw new Exception(e.getCause());
			}
			
			arquivos.add(novoeditor);
		} else {
			arquivos.add(new Editor());
		}
		System.out.println("editor aberto em " + arquivos.size());
		return true;
	}

	public boolean trocarArquivo() throws Exception {
		System.out.println("Qual editor você deseja editar? (Max: " + arquivos.size() + ")");
		int resposta = scanner.nextInt();
		if (resposta > arquivos.size()) {
			throw new Exception("Arquivo não encontrado.");
		}
		arquivos.setEditor(arquivos.get(resposta));
		return true;
	}

	public boolean editarDocumento() {
		System.out.println("Digite a linha que deseja editar: ");
		int linha = scanner.nextInt();

		System.out.println("Digite a coluna que deseja editar: ");
		int coluna = scanner.nextInt();

		System.out.println("Digite o texto que deseja inserir na linha " + linha + ", coluna " + coluna + ": ");
		try {
			System.out.println("Texto atual: " + arquivos.getEditor().getTexto(linha, coluna));
		} catch (Exception e) {}
		
		scanner.nextLine();
		String texto = scanner.nextLine();

		for (String palavra : texto.split(" ")) {
			arquivos.getEditor().setTexto(linha, coluna, palavra);
			coluna++;
			if (coluna > arquivos.getEditor().MAX_COLUNAS) {
				linha++;
				coluna = 1;
			}
		}
		return true;
	}

	public boolean verDocumento() {
		arquivos.getEditor().verDocumento();
		return true;
	}

	public boolean salvarDocumento() throws Exception {
		System.out.println("Qual arquivo será salvo?");
		scanner.nextLine();
		String diretorio = scanner.nextLine();

		try {
			arquivos.getEditor().salvarDocumento(diretorio);
			System.out.println("Arquivo salvo em: " + diretorio);
		} catch (Exception e) {
			throw new Exception("Erro ao salvar o arquivo:" + e.getCause());
		}
		return true;
	}
	
}
