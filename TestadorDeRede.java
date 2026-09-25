/* Mede a latência de rede e calcula a taxa de pacotes perdidos.
 * O programa identifica automaticamente se esta rodando no Windows ou no Linux.
 * Windows: ping -n 1 -w 2000 host
 * Linux:   ping -c 1 -W 2 host
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestadorDeRede {

    private static final int QUANTIDADE_PINGS = 4; // pings enviados por teste

    /* Medição de latência */
    public void medirLatencia(String host) {
        float somaLatencias = 0;
        int pacotesRecebidos = 0;

        System.out.printf("%n=== Latencia de rede para '%s' ===%n%n", host);

        for (int i = 1; i <= QUANTIDADE_PINGS; i++) {
            float latencia = enviarUmPing(host);

            if (latencia >= 0) {
                System.out.printf("Ping %d/%d -> resposta recebida em %.3f ms%n",
                        i, QUANTIDADE_PINGS, latencia);
                somaLatencias += latencia;
                pacotesRecebidos++;
            } else {
                System.out.printf("Ping %d/%d -> pacote perdido%n", i, QUANTIDADE_PINGS);
            }
        }

        System.out.println("\n--- Resultado ---");
        if (pacotesRecebidos > 0) {
            System.out.printf("Latência média: %.3f ms%n", somaLatencias / pacotesRecebidos);
        } else {
            System.out.println("Latência média: não foi possivel calcular (nenhuma resposta)");
        }
    }

    /* Taxa de pacotes perdidos */
    public void calcularTaxaPerda(String host) {
        int pacotesRecebidos = 0;

        System.out.printf("%n=== Taxa de pacotes perdidos para '%s' ===%n%n", host);

        for (int i = 1; i <= QUANTIDADE_PINGS; i++) {
            float latencia = enviarUmPing(host);

            if (latencia >= 0) {
                System.out.printf("Ping %d/%d -> resposta recebida%n", i, QUANTIDADE_PINGS);
                pacotesRecebidos++;
            } else {
                System.out.printf("Ping %d/%d -> pacote perdido%n", i, QUANTIDADE_PINGS);
            }
        }

        float taxaPerda = ((float) (QUANTIDADE_PINGS - pacotesRecebidos) / QUANTIDADE_PINGS) * 100.0f;

        System.out.println("\n--- Resultado ---");
        System.out.printf("Pacotes enviados        : %d%n", QUANTIDADE_PINGS);
        System.out.printf("Pacotes recebidos       : %d%n", pacotesRecebidos);
        System.out.printf("Taxa de pacotes perdidos: %.2f %%%n", taxaPerda);
    }

    /* Envia UM ping para o host e devolve a latencia em ms.
     * Retorna -1 quando não houver resposta. */
    private float enviarUmPing(String host) {
        try {
            String sistemaOperacional = System.getProperty("os.name").toLowerCase();
            ProcessBuilder construtor;

            if (sistemaOperacional.contains("win")) {
                // Windows: -n = quantidade de pings | -w = timeout em milissegundos
                construtor = new ProcessBuilder("ping", "-n", "1", "-w", "2000", host);
            } else {
                // Linux: -c = quantidade de pings | -W = timeout em segundos
                construtor = new ProcessBuilder("ping", "-c", "1", "-W", "2", host);
            }

            construtor.redirectErrorStream(true);
            Process processo = construtor.start();

            BufferedReader leitorSaida =
                    new BufferedReader(new InputStreamReader(processo.getInputStream()));

            float latenciaEncontrada = -1;
            String linha;

            while ((linha = leitorSaida.readLine()) != null) {
                float tempo = extrairTempoMs(linha);
                if (tempo >= 0) {
                    latenciaEncontrada = tempo;
                }
            }

            processo.waitFor();
            return latenciaEncontrada;

        } catch (Exception erro) {
            return -1;
        }
    }

    /* Procura o tempo retornado pelo ping.
     * Aceita exemplos como:
     * Linux:   time=23.4 ms ou tempo=23.4 ms
     * Windows: time=23ms, tempo=23ms, time<1ms ou tempo<1ms
     */
    private float extrairTempoMs(String linha) {
        Pattern padrao = Pattern.compile("(?:time|tempo)\\s*([=<])\\s*(\\d+(?:[\\.,]\\d+)?)",
                Pattern.CASE_INSENSITIVE);
        Matcher resultado = padrao.matcher(linha);

        if (!resultado.find()) {
            return -1;
        }

        try {
            float tempo = Float.parseFloat(resultado.group(2).replace(',', '.'));

            if (resultado.group(1).equals("<") && tempo == 1.0f) {
                return 0.5f;
            }

            return tempo;
        } catch (NumberFormatException erro) {
            return -1;
        }
    }
}
