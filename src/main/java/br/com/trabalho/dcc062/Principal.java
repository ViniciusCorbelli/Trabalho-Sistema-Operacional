package br.com.trabalho.dcc062;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

	private static Scanner scanner = new Scanner(System.in);

	private static List<Menu> metodos = new ArrayList<Menu>();

	public static void main(String[] args) {
		metodos.add(new Menu("Sair", "sair"));
		metodos.add(new Menu("Abrir arquivo", "abrirArquivo"));
		metodos.add(new Menu("Trocar de arquivo", "trocarArquivo"));
		metodos.add(new Menu("Editar o documento", "editarDocumento"));
		metodos.add(new Menu("Ver o documento.", "verDocumento"));
		metodos.add(new Menu("Salvar", "salvarDocumento"));

		boolean loop = true;

		do {
			try {
				for (int i = 0; i < metodos.size(); i++) {
					System.out.println(i + " - " + metodos.get(i).nome);
				}
				int resposta = scanner.nextInt();

				Acoes obj = new Acoes();
				Class<?> classObj = obj.getClass();
				Method printMessage = classObj.getDeclaredMethod(metodos.get(resposta).funcao);

				try {
					loop = (boolean) printMessage.invoke(obj);
				} catch (InvocationTargetException e) {
					System.out.println("Ocorreu um erro interno: " + e);
				}

			} catch (Exception e) {
				System.out.println("Ocorreu um erro interno: " + e.getCause());
				loop = false;
			}
		} while (loop == true);
		System.out.println("Finalizado com sucesso!");
	}
}
