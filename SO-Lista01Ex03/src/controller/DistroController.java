package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class DistroController {

	public DistroController() {
		super();
	}
	
	private String os() {
		String nomeOs = System.getProperty("os.name");
		return nomeOs;
	}
	
	public void exibeDistro() {
		String os = os();
		
		//A partir do SO detectado as variaveis mudam para localizar as informações necessárias
		if(os.contains("Linux")) {
			try {
				
				//Receber a saída do processo e alocar em um buffer para leitura
				Process processo = Runtime.getRuntime().exec("cat /etc/os-release");
				InputStream fluxoDados = processo.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxoDados);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				String saida = "";
				
				
				while (linha != null) {
					
					if(linha.contains("ID=")) {
						
						String[] splitLinha = linha.split("=");
						saida = "Distro: " + splitLinha[1];
					}
						
					linha = buffer.readLine();
					
					
				}
				
				JOptionPane.showMessageDialog(null, saida);
				
				//Fechamento dos buffers
				buffer.close();
				leitor.close();
				fluxoDados.close();
				
				
			} catch (Exception e) {
				
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "Sistema inválido");
		}
		
	}

}
