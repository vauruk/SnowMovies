------------------------------------------------------------------------------
---- Descrição de implementação do SnowMovies                           ------
------------------------------------------------------------------------------
- Ambiente desenvolvido e validado conforme tempo permitido

----
- VM - Genymotion - Google Nexus 5 - Android 6.0.0 - API 23 resolução - 1080x1920
----

-- Utilizado Android Studio para o desenvolvimento
- Criado projeto com guia de botoes de navegação fixa no rodapé da aplicação

- Bibliotecas utilizadas:
- Volley para acesso a webservices RESTFull
    - Utilizado a lib do TheMovieDb para listagem de filmes:
    - API - consumida
        Filmes Top
        - http://api.themoviedb.org/3/movie/top_rated?api_key=b5127d1016c968d30de8d0d6cc725a73
        Filmes pupulares
        - http://api.themoviedb.org/3/movie/popular?api_key=b5127d1016c968d30de8d0d6cc725a73
- Utiliso a API GSON para converter JSON para objetos java
- Utilizado a biblioteca Picasso para mostrar os posters dos filmes nos itens da linha e no dialog para mosntrar os detalhes


- Utilizado framework de persistencia no SQLLite desenvolvida por min (esta em fase de ajustes)
    - Arquitetura
        - DAO - implementado interface que mapeia os metodos que devem ser implementados, nas classe como exemplo MovieDAO
        - DAOFactory - interface que mapeia para a factory quais DAOs devem ser instanciados
        - DAOFactorySQLite - implementa de qual forma a factory deve utilizar para acessar o banco em SQLLite, esse formado pode ser implemetando apra acesso a diversos bancos, ou frameworks
        - DAOGeneric - implementa o DAO e de forma generica  metodos são implementados para atender a todas as situações de CRUD - Insert , Update, Remove, List, os métodos recebem uma instância do objeto a ser persistido e usando reflection, é feito get ou set  dos dados o BO (Business Object) os dados e faço a maunipulação necessária,
        - MovieDAO - estende  DAOGenerico e faz as implemetações especificas como carregar um movie com filtro especifico
        - MavieBO - é o objeto de negocio toda a informação é encapsulada nesse BO e trafegado em toda a aplicação, esse objeto estende de uma classe EntityApp isso me possibilita utilizar polimorfismo para todas as iterações com esse objeto.
        - Tenho um pacote Util que provê tudo que pode ser reutilizado de forma estatica como a montagem das URLs de acesso externo, a conversão dos dados JSON para o BO, poderia ter utilizado a Biblioteca GSON para fazer isso, mas o tempo não permitiu



- Utilizando Fragment para as telas, todas as telas tem caracteristicas iguais listar filmes e mostar de maneira igual  o item o que muda eh o filtro de Top , Popular que vem da web os dados e os favoritos são gravados localmente da forma que estao sendo exibidos tanto em Top, Favorito e Popular, eu reutilizo item das listas em todas e os adapter e os framents são customizados, o fragment de cada tipo é inflado no main

- Liberado acesso a internet, leitura e gravação no branco atraves do manifest

- O Dialogo que foi criado para os detalhes foi feito de forma cusmizada e reutilizada em todas as situações
- Criado actionBar customizada para mostrar o titulo da pagina e para fazer a  paginação, para fazer a ação foi criado listners em cada Fragment e injetado conforme as telas são inflados e modificados em tempo de exacução



-- Features Desejadas
 -- Tela de login no the movie db
 -- Utilizar a API para opinar para os filmes, fazer votação de preferencia, etc
 -- Colocar numeros na barra de ação na navegação para demonstrar a pagina que esta


