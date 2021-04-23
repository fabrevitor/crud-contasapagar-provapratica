# crud-contasapagar-provapratica
CRUD de Contas a Pagar desenvolvido para uma prova prática na Linguagem Java utilizando JavaFX para as telas.

Desenvolvido com apoio do material do Curso de Java da UDEMY do Nélio Alves.

OpenJDK 11 (LTS) HotSpot: https://adoptopenjdk.net/

Eclipse: https://www.eclipse.org/downloads/

MySQL: https://dev.mysql.com/downloads/installer/

JavaFX: Site Gluon -> JavaFX11

------------------------------------------------------------------------------------------------------------------------------------------------
Arquivo de Criação do Banco -> contasdb.sql

Gerar o Executável:

1) Exportar o  Java/Runnable JAR file do Projeto selecionando a classe Main.
2) Colocar o dbproperties/MySQL Connector/JavaFX SDK/Java SDK na pasta.
Foi colocado um exemplo no Projeto do Git do que precisa para rodar em um "cliente". Pasta: dist.

No db.properties colocar os dados de acesso ao seu banco de dados mySQL. No meu caso utilizei login e senha "root".
O banco de dados é criado com o arquivo contasdb.sql

--------------------------------------------------------------------------------------------------------------------------------------------------
Para rodar o Executável:
1) Conferir/configurar o JAVA_HOME e o PATH_TO_FX para as versões do JavaSDK e JavaFX SDK.
2) Colocar numa pasta no C:\. Exemplo: "C:\crudContas" os arquivos crudContas.jar e db.properties.
3) Na pasta do JavaFX SDK (que seu computador estiver configurado no PATH_TO_FX) colocar o drive do MySQLConnector na subpasta lib.
4) Abrir o cmd, ir até a pasta C:\crudContas, por exemplo.
5) Verificar que o MySQL está rodando e com a base de dados criada.
6) Rodar no CMD: "java --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml -cp crudContas.jar application.Main" sem as aspas e estando dentro da pasta que estão os arquivos crudContas.jar e db.properties.
7) Como gerar o BAT: criar um arquivo .bat na mesma pasta C:\crudContas, editar ele e colocar o mesmo comando do item 6.
8) Como criar o atalho. Botão direito na pasta que desejar > Novo > Atalho > Selecionar o arquivo crudContas.bat criado > Digitar o nome que você quer Ex: "crudContas - Atalho" 
Caso queira mudar o ícone -> Botão direito em cima do atalho criado > Trocar o destino para o comando do item 6.


Colocado exemplo da Pasta crudContas.jar, crudContas.bat, e crudContas - Atalho no Projeto do Git. Pasta: crudContas

Principais Telas do Sistema:

Tela Crud Filial

![TelaCrudFilial](https://user-images.githubusercontent.com/39971000/115875050-35494a80-a41b-11eb-8d67-7ede0bd8b79a.PNG)

Tela Crud Contas

![TelaCrudContas](https://user-images.githubusercontent.com/39971000/115875060-37130e00-a41b-11eb-8583-d0a8c43fc523.PNG)

Tela Saldo

![TelaSaldo](https://user-images.githubusercontent.com/39971000/115875088-3b3f2b80-a41b-11eb-876b-f92bcb02d153.PNG)

Tela Sobre

![TelaSobre](https://user-images.githubusercontent.com/39971000/115875093-3c705880-a41b-11eb-9883-b9cbc3f9a209.PNG)

