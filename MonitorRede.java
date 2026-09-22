/*Main*/
public class MonitorRede {
    static final String NUMERO_GRUPO = "1";

    public static void main(String[] args) {
        ColetorDeEnderecos coletor = new ColetorDeEnderecos();
        TestadorDeRede testador = new TestadorDeRede();
        Menu menu = new Menu();

        boolean continuar = true;

        System.out.printf("Estas são as informações do monitor de rede do grupo '%s':%n", NUMERO_GRUPO);

        while (continuar) {
            int opcao = menu.exibirMenu();

            if (opcao == 1) {
                coletor.coletarEnderecosIp();

            } else if (opcao == 2) {
                String host = menu.solicitarHost();
                testador.medirLatencia(host);

            } else if (opcao == 3) {
                String host = menu.solicitarHost();
                testador.calcularTaxaPerda(host);

            } else if (opcao == 4) {
                coletor.coletarEnderecosIp();
                String host = menu.solicitarHost();
                testador.medirLatencia(host);
                testador.calcularTaxaPerda(host);

            } else if (opcao == 5) {
                continuar = false;

            } else {
                System.out.println("\nOpção inválida. Tente novamente.");
            }
        }

        System.out.println("\nEncerrando o monitor de rede.");
    }
}
