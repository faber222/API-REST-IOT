# Comentários

## Entrega 01

- Como o servidor deverá ficar em uma nuvem (a ideia e não necessariamente a implementação desse projeto), então não seria adequado ZeroMQ. Assim, opte pelo RabbitMQ.
- Não tem como o dispositivo IoT conhecer o IP do servidor na nuvem, ciente que o IP pode ser trocado e isso implicaria em problemas para os dispositivos
- As comunicações não podem ocorrer na localhost (127.0.0.1). Cada dispositivo, servidor e cliente, estarão em máquinas separadas (na implementação cada máquina será na verdade um contêiner docker)
- Será necessário repensar em grande parte dessa atividade. 
- Faça as correções e entregue juntamente com a entrega 02

## Entrega 02

- API não segue o conceito de RESTful. 
  - Não deve ter verbos na URI (e.g. `/iot/status` ou `/iot/associar`)
- Um dispositivo pode participar de 0 ou N ambientes. Assim, um disposito NÃO DEVE conter 0 ou N ambientes.
- Faltou colocar a descrição detalhada de cada recurso (documentação)
- Faça as correções para a entrega final do projeto


## Entrega 03

- OK. 
- Lembre-se que o diretório `entrega 04` deverá conter a solução completa, com Docker, código fonte Java, etc.

## Entrega 04

- Documentação insuficiente para executar testes na aplicação, conforme solicitado na descrição do projeto
- Não era necessário explicar como executar docker, mas sim como subir sua aplicação, etc.
- Consegui subir individualmente cada contêiner e foi possível ver a interação entre eles, contudo ao interagir com a API para, por exemplo, abrir uma cortina, não foi possível ver a mensagem chegar na cortina (pelo menos não imprimiu no console da cortina)
- Não deveria ter entregar um `jar` com a aplicação. Isso deveria ser gerado ao executar o contêiner

## Conceito final

- Entrega 1: 10
- Entrega 2: 8
- Entrega 3: 10
- Entrega 4: 6
- Nota final: 7,2
