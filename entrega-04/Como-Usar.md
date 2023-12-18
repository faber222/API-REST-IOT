# IOT Controller API

## Descrição

Esta API é destinada ao gerenciamento de dispositivos de Internet das Coisas (IoT) e grupos associados. Ela fornece endpoints para realizar operações como listar todos os dispositivos IoT, adicionar, atualizar, e excluir dispositivos e grupos.

## Instruções de Uso

Siga as instruções abaixo para usar a API:

### Endpoints Disponíveis

1. **Obter todos os IoTs:**
   - Método: GET
   - URL: `/iot`
   - Retorna uma lista de todos os dispositivos IoT.

2. **Atualizar status de um IoT:**
   - Método: PUT
   - URL: `/iot`
   - Corpo da solicitação (JSON): `{ "id": <id>, "nome": <string>, "status": <string>, "dadosAtual": <dados>, "metodos": ["string"] }`
   - Atualiza o status do dispositivo IoT com o ID especificado.

3. **Adicionar um novo IoT:**
   - Método: POST
   - URL: `/iot`
   - Corpo da solicitação (JSON): `{ "id": <id>, "nome": <string>, "status": <string>, "dadosAtual": <dados>, "metodos": ["string"] }`
   - Adiciona um novo dispositivo IoT.

4. **Obter detalhes de um IoT por ID:**
   - Método: GET
   - URL: `/iot/{id}`
   - Retorna os detalhes do dispositivo IoT com o ID especificado.

5. **Excluir um IoT por ID:**
   - Método: DELETE
   - URL: `/iot/{id}`
   - Exclui o dispositivo IoT com o ID especificado.

6. **Obter todos os Grupos:**
   - Método: GET
   - URL: `/grupo`
   - Retorna uma lista de todos os grupos associados aos dispositivos IoT.

7. **Criar um novo Grupo:**
   - Método: POST
   - URL: `/grupo`
   - Corpo da solicitação (JSON): `{"grupoId": <grupoId>, "nome": <nome>, "produtos": [{ "id": <id>, "nome": <string>, "status": <string>, "dadosAtual": <dados>, "metodos": ["string"] }]}`
   - Cria um novo grupo associado aos dispositivos IoT.

8. **Obter detalhes de um Grupo por ID:**
   - Método: GET
   - URL: `/grupo/{grupoId}`
   - Retorna os detalhes do grupo com o ID especificado.

9. **Atualizar status de todos os IoTs em um Grupo:**
   - Método: PUT
   - URL: `/grupo/{grupoId}`
   - Corpo da solicitação (JSON): `{"grupoId": <grupoId>, "nome": <nome>, "produtos": [{ "id": <id>, "nome": <string>, "status": <string>, "dadosAtual": <dados>, "metodos": ["string"] }]}`
   - Atualiza o status de todos os dispositivos IoT no grupo com o ID especificado.

10. **Excluir um Grupo por ID:**
    - Método: DELETE
    - URL: `/grupo/{grupoId}`
    - Exclui o grupo com o ID especificado, incluindo todos os dispositivos IoT associados.

### Tratamento de Erros

A API trata os seguintes erros:

- **Método Não Permitido:**
  - Retorna 405 Method Not Allowed se um método HTTP não permitido for utilizado.

- **Dispositivo IoT Deletado Não Encontrado:**
  - Retorna 404 Not Found se tentar acessar um dispositivo IoT que foi deletado.

- **Dispositivo IoT Não Encontrado:**
  - Retorna 404 Not Found se tentar acessar um dispositivo IoT que não existe.

- **Grupo Não Encontrado:**
  - Retorna 404 Not Found se tentar acessar um grupo que não existe.

## Modelos

### Schema `prodIOT`

- **id**: Tipo inteiro (formato int64) - Identificador único do dispositivo IoT.
- **nome**: Tipo string - Nome do dispositivo IoT.
- **status**: Tipo string - Status atual do dispositivo IoT.
- **dadosAtual**: Tipo inteiro (formato int32) - Dados atuais do dispositivo IoT.
- **metodos**: Tipo array de strings - Lista de métodos associados ao dispositivo IoT.

### Schema `GroupIOT`

- **grupoId**: Tipo inteiro (formato int64) - Identificador único do grupo de dispositivos IoT.
- **nome**: Tipo string - Nome do grupo de dispositivos IoT.
- **produtos**: Tipo array - Lista de dispositivos IoT associados ao grupo. Cada item é uma referência ao schema `prodIOT`.

## Como usar
* Obter todos os IoT:
```rust
curl -X 'GET' \
  'http://localhost:9090/iot' \
  -H 'accept: */*'
```
* Adicionar um novo IoT:
```rust
curl -X 'POST' \
  'http://localhost:9090/iot' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 0,
  "nome": "teste",
  "status": "desligado",
  "dadosAtual": 0,
  "metodos": [
    "abrir"
  ]
}'
```
* Atualizar status de um IoT:
```rust
curl -X 'PUT' \
  'http://localhost:9090/iot' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 0,
  "nome": "string",
  "status": "string",
  "dadosAtual": 0,
  "metodos": [
    "string"
  ]
}'
```
* Obter detalhes de um IoT por ID:
```rust
curl -X 'GET' \
  'http://localhost:9090/iot/<id>' \
  -H 'accept: */*'
```
* Excluir um IoT por ID:
```rust
curl -X 'DELETE' \
  'http://localhost:9090/iot/<id>' \
  -H 'accept: */*'
```
* Obter todos os Grupos:
```rust
curl -X 'GET' \
  'http://localhost:9090/grupo' \
  -H 'accept: */*'
```
* Criar um novo Grupo:
```rust
curl -X 'POST' \
  'http://localhost:9090/grupo' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "grupoId": 0,
  "nome": "string",
  "produtos": [
    {
      "id": 0,
      "nome": "string",
      "status": "string",
      "dadosAtual": 0,
      "metodos": [
        "string"
      ]
    }
  ]
}'
```
* Obter detalhes de um Grupo por ID:
```rust
curl -X 'GET' \
  'http://localhost:9090/grupo/<1>' \
  -H 'accept: */*'
```
* Atualizar status de todos os IoTs em um Grupo:
```rust
curl -X 'PUT' \
  'http://localhost:9090/grupo' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "grupoId": 0,
  "nome": "string",
  "produtos": [
    {
      "id": 0,
      "nome": "string",
      "status": "string",
      "dadosAtual": 0,
      "metodos": [
        "string"
      ]
    }
  ]
}'
```
* Excluir um Grupo por ID:
```rust
curl -X 'DELETE' \
  'http://localhost:9090/grupo/{grupoId}' \
  -H 'accept: */*'
```
