package engtelecom.std.config;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import engtelecom.std.entities.ProdIOT;
import engtelecom.std.service.IotService;

@Component
public class Consumidor {
    @Autowired
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(IotService.class);

    private static final String EXCHANGE_NAME = "topic_logs";

    public Consumidor() {
        this.restTemplate = new RestTemplate();
    }

    public ProdIOT registraNovoIot(String jsonMensagem) {
        System.out.println("payload:" + jsonMensagem);
        logger.info("Recebendo mensagem: {}", jsonMensagem);
        // Criar um ObjectMapper (Jackson)
        ObjectMapper objectMapper = new ObjectMapper();
        ProdIOT novoIot = null;
        try {
            novoIot = objectMapper.readValue(jsonMensagem, ProdIOT.class);
            return novoIot;
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Registro concluído.");
        return null;
    }

    // public void startConsumidor() throws java.io.IOException,
    //         java.lang.InterruptedException, TimeoutException {
    //     // Informações sobre a conexão com o sistema de filas
    //     ConnectionFactory factory = Conexao.getConnectionFactory();

    //     Connection connection = factory.newConnection();
    //     Channel channel = connection.createChannel();

    //     channel.exchangeDeclare(EXCHANGE_NAME, "topic");
    //     String queueName = "registro";

    //     channel.queueDeclare(queueName, false, false, false, null);
    //     String bindingKey = "registro";

    //     channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
    //     System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    //     Consumer consumer = new DefaultConsumer(channel) {
    //         @Override
    //         public void handleDelivery(String consumerTag, Envelope envelope,
    //                 AMQP.BasicProperties properties,
    //                 byte[] body)
    //                 throws IOException {
    //             String message = new String(body, "UTF-8");
    //             System.out.println(" [x] Received '" + message + "'");
    //             // Chama o endpoint da API para processar a mensagem
    //             chamarEndpointDaApi(registraNovoIot(message));
    //         }
    //     };
    //     channel.basicConsume(queueName, true, consumer);
    // }

    public void startConsumidor() throws IOException, InterruptedException, TimeoutException {
        ConnectionFactory factory = Conexao.getConnectionFactory();

        int maxRetries = 10;
        int retryDelayMillis = 1000; // 1 segundo (pode ajustar conforme necessário)

        int retryCount = 0;
        boolean connected = false;

        while (!connected && retryCount < maxRetries) {

            com.rabbitmq.client.Connection connection = factory.newConnection();
            com.rabbitmq.client.Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            String queueName = "registro";

            channel.queueDeclare(queueName, false, false, false, null);
            String bindingKey = "registro";

            channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            DefaultConsumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                        byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");
                    chamarEndpointDaApi(registraNovoIot(message));
                }
            };
            channel.basicConsume(queueName, true, consumer);

            connected = true; // Se chegou aqui sem lançar exceções, a conexão foi bem-sucedida

            // } catch (Exception e) {
            // e.printStackTrace();
            retryCount++;
            // Thread.sleep(retryDelayMillis * retryCount);
            System.out.println("Retentativa de conexão: " + retryCount);
            // }
        }

        if (!connected) {
            System.out.println("Falha ao conectar após " + maxRetries + " tentativas.");
        }
    }

    private void chamarEndpointDaApi(ProdIOT prodIOT) {
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProdIOT> request = new HttpEntity<>(prodIOT, headers);
        restTemplate.postForObject("http://localhost:9090/iot", request, Void.class);
    }

}