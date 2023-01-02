package br.com.trabalho.dcc062;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Acoes {

	private Scanner scanner = new Scanner(System.in);
	
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
						Arquivos.getEditor().setTexto(linha, coluna, palavra);
						coluna++;
						if (coluna > Editor.MAX_COLUNAS) {
							linha++;
							coluna = 1;
						}
					}
				    Arquivos.getEditor().setTexto(linha, 1, texto);
				}
			} catch (FileNotFoundException e) {
				throw new Exception(e.getCause());
			}
			
			Arquivos.add(novoeditor);
		} else {
			Arquivos.add(new Editor());
		}
		System.out.println("editor aberto em " + Arquivos.size());
		return true;
	}

	public boolean trocarArquivo() throws Exception {
		System.out.println("Qual editor você deseja editar? (Max: " + Arquivos.size() + ")");
		int resposta = scanner.nextInt();
		if (resposta > Arquivos.size()) {
			throw new Exception("Arquivo não encontrado.");
		}
		Arquivos.setEditor(Arquivos.get(resposta));
		return true;
	}

	public boolean editarDocumento() {
		System.out.println("Digite a linha que deseja editar: ");
		int linha = scanner.nextInt();

		System.out.println("Digite a coluna que deseja editar: ");
		int coluna = scanner.nextInt();

		System.out.println("Digite o texto que deseja inserir na linha " + linha + ", coluna " + coluna + ": ");
		try {
			System.out.println("Texto atual: " + Arquivos.getEditor().getTexto(linha, coluna));
		} catch (Exception e) {}
		
		scanner.nextLine();
		String texto = scanner.nextLine();

		for (String palavra : texto.split(" ")) {
			Arquivos.getEditor().setTexto(linha, coluna, palavra);
			coluna++;
			if (coluna > Editor.MAX_COLUNAS) {
				linha++;
				coluna = 1;
			}
		}
		return true;
	}

	public boolean verDocumento() {
		Arquivos.getEditor().verDocumento();
		return true;
	}

	public boolean salvarDocumento() throws Exception {
		System.out.println("Qual arquivo será salvo?");
		String diretorio = scanner.nextLine();

		try {
			Arquivos.getEditor().salvarDocumento(diretorio);
			System.out.println("Arquivo salvo em: " + diretorio);
		} catch (Exception e) {
			throw new Exception("Erro ao salvar o arquivo:" + e.getCause());
		}
		return true;
	}
	
}
