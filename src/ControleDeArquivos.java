
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ControleDeArquivos {
    
    public static void salvarEmCSV(String nomeDoArquivo, String conteudo){
        try {
            FileWriter fileWriter = new FileWriter(nomeDoArquivo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.close();
            System.out.println("Arquivo salvo com sucesso: " + nomeDoArquivo);
        }
    
        catch(IOException e){
            System.out.println("Erro ao salver o arquivo: " + e.getMessage());
        }
    }
   
}
