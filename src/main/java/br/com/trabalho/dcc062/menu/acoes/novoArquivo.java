package br.com.trabalho.dcc062.menu.acoes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import br.com.trabalho.dcc062.editor.Arquivos;
import br.com.trabalho.dcc062.editor.Editor;

public class novoArquivo {

	private Scanner scanner = new Scanner(System.in);
	
	public novoArquivo() {
	}

	public boolean voltar() {
		return true;
	}

	public boolean abrirArquivo() {
		System.out.println("Qual o nome do arquivo?");
		String resposta = scanner.nextLine();
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
			return false;
		}
		
		Arquivos.add(novoeditor);
		return true;
	}
	
	public boolean emBranco() {
		Arquivos.add(new Editor());
		return true;
	}
	
}
