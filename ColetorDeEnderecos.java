/*coletar os enderecos IP das interfaces de rede da maquina e identificar o tipo (IPv4 ou IPv6) de cada um*/
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
public class ColetorDeEnderecos {

    public void coletarEnderecosIp() {
        System.out.println("\n=== Endereços IP e tipo (IPv4/IPv6) ===\n");

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            /* percorre todas as interfaces de rede da máquina*/
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                Enumeration<InetAddress> enderecos = iface.getInetAddresses();

                /* percorre os endereços IP daquela interface*/
                while (enderecos.hasMoreElements()) {
                    InetAddress endereco = enderecos.nextElement();
                    imprimirEnderecoComTipo(iface.getName(), endereco);
                }
            }

        } catch (Exception erro) {
            System.out.println("Erro ao obter interfaces de rede: " + erro.getMessage());
        }
    }

    /* verifica o tipo do endereço (IPv4 ou IPv6) e imprime a linha correspondente */
    private void imprimirEnderecoComTipo(String nomeInterface, InetAddress endereco) {
        if (endereco instanceof Inet4Address) {
            System.out.printf("Interface: %-10s | Tipo: IPv4 | Endereco: %s%n",
                    nomeInterface, endereco.getHostAddress());

        } else if (endereco instanceof Inet6Address) {
            System.out.printf("Interface: %-10s | Tipo: IPv6 | Endereco: %s%n",
                    nomeInterface, endereco.getHostAddress());
        }
    }
}
