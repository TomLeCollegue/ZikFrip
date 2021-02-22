<img src="https://github.com/TomLeCollegue/GoalLifeApp/blob/main/forReadme/calendar.png?raw=true" alt="drawing" align="center" />

## `Zik'Frip` 

### TP d'INFO802
#### Ce tp est constitué d'une application en Android Native Kotlin, et d'un serveur GlassFish
###   Download APK : [![APK](https://img.shields.io/static/v1?label=Zik'Frip:&message=v1.0&color=blue)](https://github.com/TomLeCollegue/GoalLifeApp/releases/download/v0.2/GoalLifeHelper.v0.2.apk)
###   Repo du server : [![URL](https://img.shields.io/static/v1?label=TpInfo802-Server:&message=v0.2&color=blue)](https://github.com/TomLeCollegue/GoalLifeApp/releases/download/v0.2/GoalLifeHelper.v0.2.apk)

## `Hebergement`

Le projet est hébergé sur mon NAS avec Docker, 
nous avons deux images :
* Une image Glassfish
* Une image neo4j avec le plugin GraphQL

<img src="http://89.87.13.28:8800/Fac/info802/docker.png" alt="drawing" align="center" />

## `GlassFish : SOAP et REST`
Sachant que le client est une application en kotlin, il fallait mieu que celle-ci communique avec une API REST plutot qu'une API SOAP.
Nous avons donc deux applications dans le glassFish :
* Un SOAP
* Une API REST communiquant avec le SOAP.

### SOAP :
On a seulement une fonction qui prend un poigt et une distance et qui renvoit le resultat des frais de port

```java
@WebService
public class SOAPServer {
    @WebMethod
    public double calculDeliveryFee(double weight, double distance) {

        double price = weight * distance * 0.1;
        return price;

    }
}
```

### REST : 
Grace a l'api rest, l'application android pourra communiquer avec le soap

```java
@Path("/soap")
public class Soap {

    @POST
    @Path("/calculDeliveryFee")
    @Consumes(MediaType.APPLICATION_JSON)
    public String calculDeliveryFee(JsonObject info){


        double weight = Double.parseDouble(info.getString("weight"));
        double distance = Double.parseDouble(info.getString("distance"));
        SOAPServer service = new SOAPServerService().getSOAPServerPort();
        double result = service.calculDeliveryFee(weight, distance);
        System.out.println(result);


        return "{ \"result\" : \"" + result +  "\"}";
    }
}
```
