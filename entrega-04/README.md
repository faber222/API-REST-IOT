# Entrega-03

Este projeto utiliza Docker para criar e gerenciar contêineres para os serviços IoT (tv, som, cortina, lampada, ar) e um servidor de API.

## Pré-requisitos

- Docker instalado: [Instalação do Docker](https://docs.docker.com/get-docker/)
- JDK 17 ou posterior para construir os JARs

## Instruções de Uso

### Construir as Imagens Docker

Certifique-se de estar no diretório onde o `docker-compose.yaml` está localizado.

Executar os Contêineres
```bash
docker compose up -d rabbitmq &
docker compose up -d api-server &
docker compose up -d tv &
docker compose up -d som &
docker compose up -d cortina &
docker compose up -d lampada &
docker compose up -d ar &
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
