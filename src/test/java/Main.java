import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		
		//array para salvar os nick
		String[] nick = new String[30];
	
		//array para salvar os cpf
		String[] cpf = new String[30];
		
		//para executar o programa em Si
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\FPsRooT\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		//driver e options e para executar no modo linha sem ter grafico
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		WebDriver driver = new ChromeDriver(options);
		
		//driverr e optionss faz a mesma funçao oque muda e que executa codigos diferentes
		ChromeOptions optionss = new ChromeOptions();
		optionss.setHeadless(true);
		WebDriver driverr = new ChromeDriver(options);
		
		//link para pegar os nicks
		driver.get("https://www.4devs.com.br/gerador_de_nicks");
		
		//link para pegar os cpf
		driverr.get("https://www.4devs.com.br/gerador_de_cpf");
		
		//mudar opeçao dentro do site da funçao RADIO 
		driverr.findElement(By.cssSelector(".radio-label:nth-child(2) > label")).click();
		
		//Seleciona o estado de SP
		driverr.findElement(By.id("cpf_estado")).click();
	    {
	      WebElement dropdown = driverr.findElement(By.id("cpf_estado"));
	      dropdown.findElement(By.xpath("//option[. = 'SP']")).click();
	    }
	    //faz o click para confirma
	    driverr.findElement(By.id("cpf_estado")).click();
	    
	    //fala o tempo que pode "demorar"
	    System.out.println("Demorar 40s - 50s.....");
	    
	    
	    //executa o for para pegar os dados e guarda os dados
	    for (int i = 0; i < 30; i++) {
	    	
	    	//faz o click para gerar cpf
	    	driverr.findElement(By.id("bt_gerar_cpf")).click();
	    	
	    	//faz o click para gerar os nick
	    	driver.findElement(By.id("bt_gerar_nick")).click();
	    	
	    	//delay de 500
	    	Thread.sleep(500);
	    	
	    	//pega o conteúdo dentro do texto
		    cpf[i] = driverr.findElement(By.xpath("//*[@id=\"texto_cpf\"]")).getText();
		    
		    //delay de 500
		    Thread.sleep(500);
		    
			//pega o conteúdo dentro do texto
		    nick[i] = driver.findElement(By.xpath("//*[@id=\"nicks\"]/ul/li[1]/span")).getText();
			
		}

	    //for para imprimir os valores Nick e cpf 
	    for (int n = 0; n < 30; n++) {
	    	System.out.println("Nick : "+nick[n]+"  "+"Cpf : "+cpf[n]);
		}
	    
	    //local a onde vai salvar o arquivo
	    FileWriter arq = new FileWriter("C:\\Users\\FPsRooT\\Desktop\\nickcpf.txt");
	    PrintWriter gravarArq = new PrintWriter(arq);

	    //escreve na primeira linha do arquivo txt
	    gravarArq.printf("+--------------Resultado--------------+%n");
	    
	    //for para salva os dados. ops o "\r\n" serve para pular a linha
	    for (int x = 0; x < 30; x++) {
	    	
	      gravarArq.printf(nick[x]+"; "+cpf[x]+"\r\n");
	    }
	    
	    //salva na ultima linha do arquivo
	    gravarArq.printf("+-------------------------------------+%n");

	    //fecha o arquivo
	    arq.close();
	    
	    //serve para fechar a parte grafica mas prefiro manter aqui :)
	    driver.close();
	    driverr.close();
	    
	    //by FPsRooT :)

	}

}
