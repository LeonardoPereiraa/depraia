/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author leonardo
 */
// IMPORTANTE :: ESSE È UMA CLASSE FEITA PARA CARREGAR UM BANCO DE DADOS VAZIO PARA QUE O 
// TESTANDOAPP possa ser utilizado
public class inicialTesteSelenium {
  @Test
  public void iniciadorDeTeste()throws InterruptedException{
      postCadastraUsuario();
      postCriarPraia();
      agendaControllerPost();
      postCadastradasAnbulante();
      cadastroDeProduto();
      cadastrarQuiosque();
  }
  public void postCadastraUsuario() throws InterruptedException {
    String info = "{\"admin\":true,\"cpf\":\"000.845.810-48\",\"email\":\"t@gmail\",\"endereco\":{\"bairro\":\"BA\",\"cep\":\"C1\",\"cidade\":\"CA\",\"rua\":\"RA\"},\"id\":0,\"nome\":\"NA\",\"senha\":\"SA\",\"tipoUsuario\":0}";
      System.setProperty("webdriver.Chrome.driver", "chromedriver");
      WebDriver bro;
      bro = new ChromeDriver();
      bro.navigate().to("http://localhost:8080/swagger-ui.html#/");
      Thread.sleep(3000);
      bro.findElements(By.className("expand-operation")).get(3).click();
      Thread.sleep(3000);
      bro.findElements(By.className("opblock-summary-method")).get(1).click();
      Thread.sleep(3000);
      bro.findElement(By.className("try-out")).click();
      Thread.sleep(3000);
      bro.findElement(By.className("body-param__text")).clear();
      Thread.sleep(2000);
      bro.findElement(By.className("body-param__text")).sendKeys(info);
      Thread.sleep(10000);
      bro.findElement(By.className("execute")).click();
      Thread.sleep(6000);
      bro.quit();
  }
  
  public void postCriarPraia() throws InterruptedException {
      String info = "{\"endereco\":{\"bairro\":\"BA\",\"cep\":\"1\",\"cidade\":\"CA\",\"rua\":\"RA\"},\"nome\":\"NA\"}"; 
      System.setProperty("webdriver.Chrome.driver", "chromedriver");
      WebDriver bro;
      bro = new ChromeDriver();
      bro.navigate().to("http://localhost:8080/swagger-ui.html#/");
      Thread.sleep(3000);
      bro.findElements(By.className("expand-operation")).get(4).click();
      Thread.sleep(3000);
      bro.findElements(By.className("opblock-summary-method")).get(0).click();
      Thread.sleep(3000);
      bro.findElement(By.className("try-out")).click();
      Thread.sleep(3000);
      bro.findElement(By.className("body-param__text")).clear();
      Thread.sleep(2000);
      bro.findElement(By.className("body-param__text")).sendKeys(info);
      Thread.sleep(10000);
      bro.findElement(By.className("execute")).click();
      Thread.sleep(5000);
      bro.quit();
  }
  
  
  public void agendaControllerPost() throws InterruptedException {
      String info = "{\"data\":\"01/01/2000\",\"praia\":{\"id\":3},\"vagas\":1000}";
      System.setProperty("webdriver.Chrome.driver", "chromedriver");
      WebDriver bro;
      bro = new ChromeDriver();
      bro.navigate().to("http://localhost:8080/swagger-ui.html#/");
      Thread.sleep(3000);
      bro.findElements(By.className("expand-operation")).get(0).click();
      Thread.sleep(3000);
      bro.findElements(By.className("opblock-summary-method")).get(0).click();
      Thread.sleep(3000);
      bro.findElement(By.className("try-out")).click();
      Thread.sleep(2000);
      bro.findElement(By.className("body-param__text")).clear();
      Thread.sleep(2000);
      bro.findElement(By.className("body-param__text")).sendKeys(info);
      Thread.sleep(3000);
      bro.findElement(By.className("execute")).click();
      Thread.sleep(5000);
      bro.quit();
  }
   public void postCadastradasAnbulante() throws InterruptedException {
      String info1 = "{\"praia\":{\"id\":3},";
      String info2 = "\"user\":{\"admin\":true,\"cpf\":\"teste\",\"email\":\"teste\",";
      String info3 = "\"endereco\":{\"bairro\":\"teste\",\"cep\":\"teste\",\"cidade\":\"teste\",\"rua\":\"teste\"},";
      String info4 = "\"id\":0,\"nome\":\"teste\",\"senha\":\"teste\",\"tipoUsuario\":0}}";
      String info =info1+info2+info3+info4 ;
      System.setProperty("webdriver.Chrome.driver", "chromedriver");
      WebDriver bro;
      bro = new ChromeDriver();
      bro.navigate().to("http://localhost:8080/swagger-ui.html#/");
      Thread.sleep(3000);
      bro.findElements(By.className("expand-operation")).get(1).click();
      Thread.sleep(3000);
      bro.findElements(By.className("opblock-summary-method")).get(0).click();
      Thread.sleep(3000);
      bro.findElement(By.className("try-out")).click();
      Thread.sleep(2000);
      bro.findElement(By.className("body-param__text")).clear();
      Thread.sleep(2000);
      bro.findElement(By.className("body-param__text")).sendKeys(info);
      bro.findElement(By.className("execute")).click();
      Thread.sleep(10000);
      String elementoTexto = bro.findElements(By.className("response-col_status")).get(1).getText();
      Thread.sleep(3000);
      bro.quit();   
  }
   public void cadastroDeProduto() throws InterruptedException {
      String info = "{\"descricao\":\"P1\",\"nome\":\"P1\",\"preco\":999}";
      System.setProperty("webdriver.Chrome.driver", "chromedriver");
      WebDriver bro;
      bro = new ChromeDriver();
      bro.navigate().to("http://localhost:8080/swagger-ui.html#/");
      Thread.sleep(3000);
      bro.findElements(By.className("expand-operation")).get(5).click();
      Thread.sleep(3000);
      bro.findElements(By.className("opblock-summary-method")).get(0).click();
      Thread.sleep(3000);
      bro.findElement(By.className("try-out")).click();
      Thread.sleep(2000);
      bro.findElement(By.className("body-param__text")).clear();
      Thread.sleep(2000);
      bro.findElement(By.className("body-param__text")).sendKeys(info);
      Thread.sleep(7000);
      bro.findElement(By.className("execute")).click();
      Thread.sleep(10000);
      bro.quit();   
      
  }
    public void cadastrarQuiosque() throws InterruptedException {
      String info = "{\"nome\":\"Verde\",\"praia\":{\"id\":3}}";
      System.setProperty("webdriver.Chrome.driver", "chromedriver");
      WebDriver bro;
      bro = new ChromeDriver();
      bro.navigate().to("http://localhost:8080/swagger-ui.html#/");
      Thread.sleep(3000);
      bro.findElements(By.className("expand-operation")).get(6).click();
      Thread.sleep(3000);
      bro.findElements(By.className("opblock-summary-method")).get(0).click();
      Thread.sleep(3000);
      bro.findElement(By.className("try-out")).click();
      Thread.sleep(3000);
      bro.findElement(By.className("body-param__text")).clear();
      Thread.sleep(2000);
      bro.findElement(By.className("body-param__text")).sendKeys(info);
      Thread.sleep(7000);
      bro.findElement(By.className("execute")).click();
      Thread.sleep(10000);
      bro.quit();    
  }
}
