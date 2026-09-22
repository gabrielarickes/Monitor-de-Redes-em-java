/*Mostra o menu com as opções de escolha, e pede ao usuário que informe um ip para o teste*/
import java.util.Scanner;
public class Menu {

    Scanner leitor = new Scanner(System.in);

    /* mostra as opções do menu e le a escolha do usuario */
    public int exibirMenu() {
        System.out.println("\n=== MENU DE OPÇÕES ===");
        System.out.println("1. Coletar endereços IP e verificar o tipo (IPv4/IPv6)");
        System.out.println("2. Medir a latência de rede");
        System.out.println("3. Calcular a taxa de pacotes perdidos");
        System.out.println("4. Executar tudo (opções 1, 2 e 3)");
        System.out.println("5. Sair");
        System.out.print("\nEscolha uma opção: ");
        return leitor.nextInt();
    }

    /* pede ao usuario o host que sera testado */
    public String solicitarHost() {
        System.out.print("Digite o endereço ou nome do host para testar (ex: 8.8.8.8): ");
        return leitor.next();
    }



}

