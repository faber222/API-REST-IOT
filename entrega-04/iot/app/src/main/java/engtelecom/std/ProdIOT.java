package engtelecom.std;

import java.util.List;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ProdIOT {
    private long id;
    private String nome;
    private String status;
    private int dadosAtual;
    private List<String> metodos;

    public ProdIOT(long id, String nome, String status, int dadosAtual, List<String> metodos) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.dadosAtual = dadosAtual;
        this.metodos = metodos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDadosAtual() {
        return dadosAtual;
    }

    public void setDadosAtual(int dadosAtual) {
        this.dadosAtual = dadosAtual;
    }

    public List<String> getMetodos() {
        return metodos;
    }

    public void setMetodos(List<String> metodos) {
        this.metodos = metodos;
    }

    public String serializaAtributoParaJson() {
        ProdIOT prodIOT = new ProdIOT(getId(), getNome(), getStatus(), getDadosAtual(), getMetodos());
        // Criar um ObjectMapper (Jackson)
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Converter o objeto para JSON
            return objectMapper.writeValueAsString(prodIOT);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void registraDispositivo() throws java.io.IOException, java.lang.InterruptedException, TimeoutException {
        String EXCHANGE_NAME = "topic_logs";
        ConnectionFactory factory = Conexao.getConnectionFactory();

        int maxRetries = 10;
        int retryDelayMillis = 1000; // 1 segundo (pode ajustar conforme necessário)

        int retryCount = 0;
        boolean connected = false;

        while (!connected && retryCount < maxRetries) {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            String mensagemRegistro = serializaAtributoParaJson();
            String routingKey = "registro";

            channel.basicPublish(EXCHANGE_NAME, routingKey, null, mensagemRegistro.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + mensagemRegistro + "'");

            connected = true; // Se chegou aqui sem lançar exceções, a conexão foi bem-sucedida

            // } catch (Exception e) {
            // e.printStackTrace();
            retryCount++;
            // Thread.sleep(retryDelayMillis * retryCount);
            System.out.println("Retentativa de conexão: " + retryCount);
            // }
        }

        if (!connected)

        {
            System.out.println("Falha ao conectar após " + maxRetries + " tentativas.");
        }

    }

}
