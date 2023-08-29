package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		int opcao = 0;
		
		RedesController ctrl = new RedesController();
		
		do {
			opcao = Integer.parseInt(JOptionPane.showInputDialog(	"Selecione o processo desejado: \n"
																		+ "1- Configuração de IP\n"
																		+ "2- Ping com o Google(10 segundos)\n"
																		+ "9- Sair"));
			
			switch(opcao) {
				case 1:
					ctrl.ip();
					break;
					
				case 2:
					ctrl.ping();
					break;
					
				case 9:
					break;
					
				default:
					
					JOptionPane.showMessageDialog(null,"Opção inválida, escolha novamente!");
			}
			
		}while(opcao != 9);
	}

}
