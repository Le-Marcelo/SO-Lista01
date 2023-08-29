package view;

import javax.swing.JOptionPane;

import controller.KillController;

public class Main {

	public static void main(String[] args) {
		int opcao = 0;
		
		KillController ctrl = new KillController();
		
		do {
			opcao = Integer.parseInt(JOptionPane.showInputDialog(	"Selecione o processo desejado: \n"
																		+ "1- Lista de Processos (Console)\n"
																		+ "2- Matar processo por PID\n"
																		+ "3- Matar processo por Nome\n"
																		+ "9- Sair"));
			
			switch(opcao) {
				case 1:
					ctrl.listaProcessos();
					break;
					
				case 2:
					ctrl.mataPid(JOptionPane.showInputDialog("Digite o PID do processo:"));
					break;
					
				case 3:
					ctrl.mataNome(JOptionPane.showInputDialog("Digite o nome do processo:"));
					
				case 9:
					break;
					
				default:
					
					JOptionPane.showMessageDialog(null,"Opção inválida, escolha novamente!");
			}
			
		}while(opcao != 9);
	}

}
