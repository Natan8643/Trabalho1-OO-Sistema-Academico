import java.util.Scanner;

public class App {

    private final Menu menu = new Menu();
    public static void main(String[] args) throws Exception {
        App app = new App();
        System.out.println("\n---------------------------------------");
        System.out.println("Olá usuário!\n\nAo usar esse programa não utilize caracteres especiais como \"ç\" ou acento nas letras\n\nTecle enter para começar");
        System.out.println("---------------------------------------");
        
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        app.menu.menuInicial();
    }

    

    

    

  
    
}
