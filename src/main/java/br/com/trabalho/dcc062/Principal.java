package br.com.trabalho.dcc062;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

	private static Scanner scanner = new Scanner(System.in);
	private static List<Menu> menu = new ArrayList<Menu>();

	public static void main(String[] args) {
		new Arquivos();
		
		menu.add(new Menu("Sair", "sair"));
		menu.add(new Menu("Abrir arquivo", "abrirArquivo"));
		menu.add(new Menu("Trocar de arquivo", "trocarArquivo"));
		menu.add(new Menu("Editar o documento", "editarDocumento"));
		menu.add(new Menu("Ver o documento.", "verDocumento"));
		menu.add(new Menu("Salvar", "salvarDocumento"));

		boolean loop = true;

		do {
			try {
				for (int i = 0; i < menu.size(); i++) {
					System.out.println(i + " - " + menu.get(i).nome);
				}
				int resposta = scanner.nextInt();

				Acoes obj = new Acoes();
				Class<?> classObj = obj.getClass();
				Method printMessage = classObj.getDeclaredMethod(menu.get(resposta).funcao);

				try {
					loop = (boolean) printMessage.invoke(obj);
				} catch (InvocationTargetException e) {
					System.out.println("Ocorreu um erro interno (1): " + e.getCause());
				}

			} catch (Exception e) {
				System.out.println("Ocorreu um erro interno (2): " + e.getCause());
				loop = false;
			}
		} while (loop == true);
		System.out.println("Finalizado com sucesso!");
	}
}
