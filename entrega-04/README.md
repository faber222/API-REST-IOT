# Entrega-03

Este projeto utiliza Docker para criar e gerenciar contêineres para os serviços IoT (tv, som, cortina, lampada, ar) e um servidor de API.

Mas atualmente a aplicação apenas consegue funcionar sendo executada em maquina local, sem o uso do docker para subir os produtos IOT.
Onde é necessário subir o docker apenas para manter o rabbit online, visto que, o código implementado não está operando devido a erro de rede.

O problema ocorre na hora de subir no docker, onde a api-server ou o iot, não foi descoberto ainda, não consegue se vincular ao serviço do rabbitmq, e por conta disso, a mensagem que é enviada do iot para o servidor, não chega, ou o servidor não recebe.

Mas todo o funcionamento da api está 100%.

## Pré-requisitos

- Docker instalado: [Instalação do Docker](https://docs.docker.com/get-docker/)
- JDK 17 ou posterior para construir os JARs

## Instruções de Uso

### Construir as Imagens Docker

Certifique-se de estar no diretório onde o `docker-compose.yaml` está localizado.

Executar os Contêineres
```bash
docker compose up -d &
```

Os contêineres serão iniciados em segundo plano (-d).

Parar os Contêineres
```bash
docker compose down
```

Ou caso use outro docker-compose

Executar os Contêineres
```bash
docker-compose up -d &
```

Os contêineres serão iniciados em segundo plano (-d).

Parar os Contêineres
```bash
docker-compose down
```

## Remover todas as imagens docker
```bash
docker rmi -f $(docker images -q)
``

## Visualizar os dockers

Para ver se gerou as imagens
```bash
docker images
```

Para ver se gerou o compose
```bash
docker compose ps
```

Veja como usar aqui [Como Usar](./Como-Usar.md)
