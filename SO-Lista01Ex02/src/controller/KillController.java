package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class KillController {

	public KillController() {
		super();
	}
	
	//Método que retorna o nome do sistema operacional utilizado
	private String os() {
		String nomeOs = System.getProperty("os.name");
		return nomeOs;
	}
	
	public void listaProcessos() {
		String os = os();
		String nomeProcesso = "";
		
		//A partir do SO detectado as variaveis mudam para localizar as informações necessárias
		if(os.contains("Windows")) {
			nomeProcesso = "TASKLIST /FO TABLE";
			
		}else if(os.contains("Linux")) {
			nomeProcesso = "ps -ef";
		}else {
			JOptionPane.showMessageDialog(null, "Sistema inválido");
		}
		
		try {
			
			//Receber a saída do processo e alocar em um buffer para leitura
			Process processo = Runtime.getRuntime().exec(nomeProcesso);
			InputStream fluxoDados = processo.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxoDados);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			
			while (linha != null) {
				
				System.out.println(linha);
				linha = buffer.readLine();
			}
			
			//Fechamento dos buffers
			buffer.close();
			leitor.close();
			fluxoDados.close();
			
			
		} catch (Exception e) {
			
		}
	}

	public void mataPid(String pid) {
		String os = os();
		String nomeProcesso = "";
		
		//A partir do SO detectado as variaveis mudam para localizar as informações necessárias
		if(os.contains("Windows")) {
			nomeProcesso = "TASKKILL /PID " + pid;
			
		}else if(os.contains("Linux")) {
			nomeProcesso = "kill -9 " + pid;
		}else {
			JOptionPane.showMessageDialog(null, "Sistema inválido");
		}
		
		try {
			Runtime.getRuntime().exec(nomeProcesso);
		
		} catch (Exception e) {
			
		}
	}
	
	public void mataNome(String nome) {
		String os = os();
		String nomeProcesso = "";
		
		//A partir do SO detectado as variaveis mudam para localizar as informações necessárias
		if(os.contains("Windows")) {
			nomeProcesso = "TASKKILL /IM " + nome;
			
		}else if(os.contains("Linux")) {
			nomeProcesso = "pkill -f " + nome;
		}else {
			JOptionPane.showMessageDialog(null, "Sistema inválido");
		}
		
		try {
			Runtime.getRuntime().exec(nomeProcesso);
		
		} catch (Exception e) {
			
		}
	}
}
