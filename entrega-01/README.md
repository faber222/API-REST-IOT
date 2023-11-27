# Entrega-01
Para a entrega:
- Documento detalhando as tecnologias que escolheu para o desenvolvimento de cada
componente que compõe o sistema de automação residencial. Por exemplo, qual tecnologia escolheu para desenvolver o servidor na nuvem, qual tecnologia escolheu para desenvolver
o cliente, qual tecnologia escolheu para desenvolver o dispositivo inteligente, etc.
    - Dependendo das escolhas que fizer, talvez não seja necessário desenvolver o cliente.
- É importante ressaltar como será a comunicação entre cada componente (e.g. como será feita localização, trocas de mensagens, protocolo de comunicação, etc).

## Escolhendo os componentes a serem usados pelo serviços
Para os produtos, a comunicação inicial representada pelo item 1 e 2, é totalmente feita usando o ZeroMq, da fila de mensagens, onde o servidor estará aguardando conexões via thread, e quando um novo produto se cadastrar, ja que terá dentro de si o ip e a porta do servidor, irá enviar através de request e reply, os dados dele.

Exemplo: 
>1. Cliente sobe e tenta estabelecer comunicação com o servidor.
>2. Servidor retorna que a conexão ocorreu com sucesso.
>2. Cliente se conecta e envia os dados de divulgação.
>3. Servidor retorna informando que recebeu os dados.
>4. Cliente fica ativo aguardando consumo da api.

Para representar o dispositivo, será usado um docker-compose, DockerFile, contendo o sistema que vai usar, os dados que vai baixar e a api que vai construir.
Porém, será passado o diretório dos arquivos do programa de cada dispositivo para iniciar corretamente.

## Escolhendo o componente a ser usado pelo servidor
O servidor implementa um código em java, contendo duas partes, uma que usa o zeroMq, para aguardar conexões de novos produtos, e outra que implementa um código que consome dados das apis dos produtos.
O servidor ficará hospedado em um docker local, visto que ficamos limitados ao lab 1, e com o docker local, apesar de ser "nuvem", todas as comunicações e acessos vão ocorrer no ip 127.0.0.1, a porta não foi definida até o momento.
Abaixo está descrito o processo de comunicação do Dispositivo com o Servidor.

Exemplo Servidor e Dispositivo: 
>1. Servidor sobe e aguarda conexões dos dispositivos.
>2. Dispositivo tenta estabelecer conexão.
>2. Servidor processa os dados de conexão.
>3. Servidor retorna informando que recebeu os dados.
>4. Servidor recebe dados de divulgação do cliente.
>5. Servidor cadastra o produto.
>6. Servidor retorna ao cliente que recebeu a comunicação

Abaixo o processo de comunicação do Cliente com o Servidor.

Exemplo Servidor e Cliente
>1. Servidor sobe e aguarda conexões dos clientes.
>2. Cliente tente estabelecer conexão.
>3. Servidor processa os dados de conexão.
>4. Servidor retorna informando que recebeu a conexão.
>5. Servidor fica aguardando solicitações de consumo de api do cliente

## Escolhendo o componente a ser usado pelo cliente consumidor
O cliente implementa um código e java que se conecta ao servidor, tal código usa comunicação padrão de zeroMQ, o código em java proverá uma interface para o usuário:
sendo ela contida por exemplo:

![imagem](assets/menu.png)

Sendo assim, para cada escolha do cliente, ele enviará uma determinada solicitação para o servidor, exemplo, cliente seleciona a opção 1, que refere-se a coleta de dados - GET -, e vai listar os produtos que ele tem disponivel, e vai escolher o dispositivo, e quando selecionar um dispositivo, enviará ao servidor a escolha, e o servidor irá fazer o consumo da api do dispositivo escolhido, e retornará ao cliente a informação desejada.

Porém a lista de dispositos vai ser fornecida para o cliente toda vez que o mesmo se conectar ao servidor, usando o REQ e REP do ZeroMQ, e também fornecendo as funções disponiveis de cada dispositivo.

E a criação do grupo de dispositivos será feita na aplicação do cliente.

Abaixo o processo de comunicação do Cliente com o Servidor.

Exemplo Cliente e Servidor
>1. Cliente sobe e testa se conectar ao servidor.
>2. Cliente recebe retorno de conexão do servidor.
>3. Cliente processa os dados de conexão.
>4. Cliente envia dados da aplicação para o servidor.
>5. Cliente aguarda retorno da solicitação feita ao servidor.

# Ferramentas a serem usadas
>1. Docker - [Servidor, Dispositivos]
>2. ZeroMQ - [Servidor, Dispositivos, Clientes]
>3. Java - [Servidor, Dispositivos, Clientes]
>4. ApiRest - [Dispositivos]