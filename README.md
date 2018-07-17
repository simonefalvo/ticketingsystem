# Logging Component

Questo componente permette al programmatore di tenere traccia dell’invocazione di metodi e classi.

Per utilizzare questo componente è sufficiente utilizzare le due annotazioni @LogClass e @LogOperation.

Sono supportati database relazionali e non relazionali.

NB. per tenere traccia dell’utente che compie le operazioni questo componente si appoggia a SpringSecurity. 
Per permettere l'utilizzo del componente anche in assenza di SpringSecutiry, nella classe LogAspect il codice relativo al logging dell'autore è commentato. Se il tuo progetto utilizza SpringSecurity, puoi decommentarlo per ottenere il logging dell'autore.

## Dipendenze

* Lombok, JPA, Spring Security

* Spring AOP:

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```
* Apache Reflection:
```
<dependency>
     <groupId>org.apache.commons</groupId>
     <artifactId>commons-lang3</artifactId>
     <version>3.4</version>
</dependency>
```
* Mongo:
```
<dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

* JPA:
```
<dependency>
     <groupId>org.springframework.data</groupId>
     <artifactId>spring-data-jpa</artifactId>
     <version>2.0.8.RELEASE</version>
</dependency>
```

## Annotazioni

Utilizzare questa annotazione per tutti i metodi di cui si vuole registrare l’invocazione. è possibile inoltre tener traccia degli oggetti di input e return. 

*@LogOperation:*

* String[] inputArgs() : per specificare eventuali input da registrare. Se non si vuole registrare alcun input, non inserire l’attributo.

* boolean returnObject() : true se si vuole registrare l’oggetto di ritorno, false (default) altrimenti.

* String opName() : nome dell’operazione. se omesso, viene utilizzato il nome del metodo.

* String tag() : eventuale tag a discrezione del programmatore.

Utilizzare questa classe per annotare le classi di cui si vuole tener traccia. Per il corretto funzionamento del componente è necessario specificare sempre i campi identificatori della classe.

*@LogClass:*

* String[] logAttrs() : specificare gli attributi d’interesse. se omesso, vengono registrati tutti i campi della classe.

* String[] idAttrs() : specificare i campi identificatori della classe.


## Properties

Specificare nel file application.properties la tipologia di database. Se nel tuo progetto sono definite entrambe le tipologie, specifica in quale dei due vuoi le tabelle di logging.

```

spring.profiles.active=jpa

```

```

spring.profiles.active=mongo

```

##Tabelle

* JPA:
Tramite Hibernate vengono instanziate due tabelle, Record e Payload.
Nella tabella Record viene registrata l’invocazione dei metodi come segue:

```

||    id    ||    author    ||  operation name  ||    tag    ||    timestamp    ||

```
    

Nella tabella Payload vengono registrati gli input e output dei metodi. Il campo type differenzia payload input da payload output.

```
||    id    ||    class_type    ||  json_text  ||    object_id    ||    type    ||    record_id    ||
```

* Mongo:
In una unica Collection vengono registrati i Record contenenti i relativi payload. Gli attributi sono elencati nel punto precedente.

## Interfaccia

la classe RecordReader permette al programmatore di interfacciarsi con il componente ed interrogare il database. Per il momento, queste sono le seguenti query:

JPA

* deleteRecord
* getAllRecords
* getRecordsByTag
* getRecordsByAuthors
* getRecordsByObjectId
* getRecordsByOperation
* getNumberOfOpNameEvents
* getNumberOfTaggedEvents
* getNumberOfOpNameEventsBetween
* getNumberOfTaggedEventsBetween

Mongo

* getAllRecords
* getRecordsByTag
* getRecordsByAuthor
* getRecordsByOperationName


## Esempio

```
@Entity
@LogClass( logAttrs = {“attributeOne”,”attributeTwo”}, idAttrs = {“id”})
public class ClasseProva {

	private Integer id;
	private String attributeOne;
	private String attributeTwo;
	private String attributeThree;
}
```
```
@LogOperation( inputArgs = {“classe”}, returnObject = true, tag = “mytag”, opName = “My Method”)
public ClasseProva myMethod(String str, ClasseProva classe){

	.....

	return classe;

}
```
dopo l’invocazione del metodo:

Tabella Record
```
||    id    ||    author    ||  operation name  ||       tag       ||         timestamp         ||  
      10           user            My Method            mytag           2018-06-21 23:06:00.37
```

Tabella Payload
```
||    id    ||       class_type       ||         json_text           ||    object_id    ||    type    ||    record_id    ||
      11            ClasseProva              { attributeOne: ..            {id:”id”}          input              10
      12            ClasseProva              { attributeOne: ..            {id:”id”}          return             10
```
