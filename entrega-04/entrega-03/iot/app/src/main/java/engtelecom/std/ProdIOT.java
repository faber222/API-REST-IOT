package engtelecom.std;

import java.util.List;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        com.rabbitmq.client.Connection connection = null;
        com.rabbitmq.client.Channel channel = null;
        try {
            // Informações sobre a conexão com o sistema de filas
            ConnectionFactory factory = Conexao.getConnectionFactory();
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            // channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // Inicialmente envia a mensagem referente ao registro
            String mensagemRegistro = serializaAtributoParaJson();
            String routingKey = "registro";

            channel.basicPublish(EXCHANGE_NAME, routingKey, null, mensagemRegistro.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + mensagemRegistro + "'");

            // channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // Consumer consumer = new DefaultConsumer(channel) {
            //     @Override
            //     public void handleDelivery(String consumerTag, Envelope envelope,
            //             AMQP.BasicProperties properties,
            //             byte[] body) throws IOException {
            //         String message = new String(body, "UTF-8");
            //         System.out.println(" [x] Received '" + message + "'");
            //     }
            // };
            // channel.basicConsume(QUEUE_NAME, true, consumer);
            // // Se torna o consumidor e fica eternamente consumindo por mensagens
            // System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ignore) {
                }
            }
        }

    }

}
