package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class RedesController {

	public RedesController() {
		super();
	}
	
	//Método que retorna o nome do sistema operacional utilizado
	private String os() {
		String nomeOs = System.getProperty("os.name");
		return nomeOs;
	}
	
	//Método que retorna os adaptadores de rede e os endereços IPV4
	public void ip() {
		String os = os();
		String nomeProcesso = "", separadorAdaptador = "", separadorIpv4 = "", indicadorAdaptador = "", indicadorIpv4 = "";
		int numSplitAdaptador = 0, numSplitIpv4 = 0;
		
		//A partir do SO detectado as variaveis mudam para localizar as informações necessárias
		if(os.contains("Windows")) {
			nomeProcesso = "ipconfig";
			indicadorAdaptador = "Adaptador";
			separadorAdaptador = ":";
			numSplitAdaptador = 0;
			indicadorIpv4 = "IPv4";
			separadorIpv4 = ":";
			numSplitIpv4 = 1;
			
		}else if(os.contains("Linux")) {
			nomeProcesso = "ip addr";
			indicadorAdaptador = "mtu";
			separadorAdaptador = " ";
			numSplitAdaptador = 1;
			indicadorIpv4 = "inet ";
			separadorIpv4 = " ";
			numSplitIpv4 = 5;
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
			
			StringBuffer saidaBuffer = new StringBuffer();
			String nomeAdaptador = "";
			
			while (linha != null) {
				
				if(linha.contains(indicadorAdaptador)) {
					
					String[] splitLinha = linha.split(separadorAdaptador);
					nomeAdaptador = splitLinha[numSplitAdaptador];
					
				}else if(linha.contains(indicadorIpv4)) {
					
					String[] splitLinha = linha.split(separadorIpv4);
					saidaBuffer.append(nomeAdaptador + " \t" + splitLinha[numSplitIpv4] + "\n");
					
				}
				
				linha = buffer.readLine();
			}
			
			//Fechamento dos buffers
			buffer.close();
			leitor.close();
			fluxoDados.close();
			
			JOptionPane.showMessageDialog(null, saidaBuffer.toString());
			
		} catch (Exception e) {
			
		}
	}

	//Método que retorna o tempo médio do ping até a Google
	public void ping() {
		String os = os();
		String nomeProcesso = "", separadorMedia = "", indicadorMedia = "", fimTexto = "";
		int numSplitMedia = 0;
		
		//A partir do SO detectado as variaveis mudam para localizar as informações necessárias
		if(os.contains("Windows")) {
			nomeProcesso = "ping -4 -n 10 www.google.com.br";
			indicadorMedia = "dia";
			separadorMedia = "=";
			numSplitMedia = 3;
			
		}else if(os.contains("Linux")) {
			nomeProcesso = "ping -4 -c 10 www.google.com.br";
			indicadorMedia = "avg";
			separadorMedia = "/";
			numSplitMedia = 5;
			fimTexto = "ms";
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
			
			StringBuffer saidaBuffer = new StringBuffer();
			
			while (linha != null) {
				
				if(linha.contains(indicadorMedia)) {
					
					String[] splitLinha = linha.split(separadorMedia);
					int tempo = (int) Double.parseDouble(splitLinha[numSplitMedia]);
					saidaBuffer.append("Tempo médio:" + tempo + fimTexto);
					
				}
				
				linha = buffer.readLine();
			}
			
			//Fechamento dos buffers
			buffer.close();
			leitor.close();
			fluxoDados.close();
			
			JOptionPane.showMessageDialog(null, saidaBuffer.toString());
			
		} catch (Exception e) {
			
		}
	}
}
