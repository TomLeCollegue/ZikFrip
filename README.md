<img src="https://github.com/TomLeCollegue/GoalLifeApp/blob/main/forReadme/calendar.png?raw=true" alt="drawing" align="center" />

## `Zik'Frip` 

### TP d'INFO802
#### Ce tp est constitué d'une application en Android Native Kotlin, et d'un serveur GlassFish
###   Download APK : [![APK](https://img.shields.io/static/v1?label=Zik'Frip:&message=v1.0&color=blue)](https://github.com/TomLeCollegue/GoalLifeApp/releases/download/v0.2/GoalLifeHelper.v0.2.apk)
###   Repository du server : [![URL](https://img.shields.io/static/v1?label=TpInfo802-Server:&message=v0.2&color=blue)](https://github.com/TomLeCollegue/GoalLifeApp/releases/download/v0.2/GoalLifeHelper.v0.2.apk)

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

L'application fera une requete POST à l'api avec le JSON :
```json
{
    "weight":"2",
    "distance":"5"
}
```
et recevra : 
```json
{ "result" : "1.0"}
```

## `Neo4j : GraphQL`
Neo4j est une base de donnée sous forme de graph, normalement on communique avec la bdd sous forme de requete cypher, mais il existe la possibilité d'installer un plugin GraphQL.

Les requetes sont faites a l'adresse :
http://serveur:7474/graphqh
Et le plugin sont donne la possibilité de faire nos schema a l'adresse : 
http://serveur:7474/graphqh/idl

Sur notre aplication on vend des Instruments de Musique donc notre base est composé de Produit (nom, prix, description, imageURL) qui sont relié a des categorié ( nom ).

<img src="http://89.87.13.28:8800/Fac/info802/neo4j.png" alt="drawing" align="center" />

Dans notre application on peut pouvoir :
* Recuperer les produits par categorie
* Recuperer un certain nombre de produit

On complete notre schema pour que cela soit possible :
```graphql
type Category {
    name: String
    products: [Product] @relation(name:"CATEGORY", direction:IN)
}

type Product {
    name: String
    price: Int
    description: String
    imageURL:String
    category: Category @relation(name:"CATEGORY", direction:OUT)
}
```

Si on veut recuperer les produits de la categorie "Guitares" on fait la requete POST: 
```json
{
    "query":"{Category (name:\"Guitares\"){name products{name price description imageURL}}}"
}
```
Cela nous donne le JSON : 
```json
{
    "data": {
        "Category": [
            {
                "name": "Guitares",
                "products": [
                    {
                        "name": "Fender Eric Clapton Signature",
                        "price": 800,
                        "description": "Construite selon les spécifications de Clapton lui-même, la guitare Custom Shop Eric Clapton Signature Stratocaster® se caractérise par un corps en aulne, un manche spécial en érable doux en forme de V, trois micros Vintage Noiseless™, un trémolo bloqué et la signature de Clapton sur la tête.",
                        "imageURL": "https://www.woodbrass.com/images/woodbrass/FMIC+0150082806.JPG"
                    },
                    {
                        "name": "Cort SFX-MEM",
                        "price": 229,
                        "description": "table acajou, fond & éclisses acajou, manche acajou (43 mm au sillet), touche & chevalet palissandre",
                        "imageURL": "https://www.woodbrass.com/images/woodbrass/CORT+SFX+MEMOP-1.JPG"
                    },
                    {
                        "name": "Taylor 224CE-K",
                        "price": 1699,
                        "description": "Les fans de koa hawaïen vont adorer l'aspect, la sensation et le son de cette Grand Auditorium entièrement koa. Une finition ombragée sur les bords fait ressortir l'attrait vintage de la table en koa massif, qui est associée à un dos et à des éclisses en koa stratifiés, tandis qu'une finition brillante habille le corps de reflets brillants. La polyvalence musicale de la Grand Auditorium en fait une guitare polyvalente idéale pour le jeu à la fois pour le strumming et le fingerpicking, et la table d'harmonie en koa massif égalisera une attaque vive pour produire une attaque précise et linéaire. Les caractéristiques modernes et confortables pour le joueur comprennent un pan coupé et l'électronique acoustique ES2 de Taylor. Parmi les éléments distinctifs, on trouve une rosace en fausses nacres à un seul anneau avec un motif en arête de poisson, un filet noir, des incrustations de petits diamants en acrylique italienne et des mécaniques dorées. La guitare est livrée dans un étui de luxe Taylor.",
                        "imageURL": "https://www.woodbrass.com/images/woodbrass/TAYLOR-224CE-K-DLX-2019.JPG"
                    }
                ]
            }
        ]
    },
    "extensions": {
        "type": "READ_ONLY"
    }
}
```

Si on veut recuperer les 2 premiers produits on fait la requete POST: 
```json
{
    "query":"{Product(first:2){name price description imageURL}}"
}
```
Cela nous donne le JSON
```json
{
    "data": {
        "Product": [
            {
                "name": "Korg LP-380U",
                "price": 749,
                "description": "Le LP-380 est un piano numérique conçu pour s’intégrer aisément à votre décor intérieur d’autant qu’il est proposé en quatre couleurs : noir, blanc, palissandre et palissandre foncé. De plus, avec seulement 26 cm de profondeur, il se montre assez mince pour s’installer pratiquement n'importe où. Le couvre-clavier, en bois, accroît ce design élégant quand vous ne jouez pas l’instrument.",
                "imageURL": "https://www.woodbrass.com/images/woodbrass/KORG+LP380U+BK.JPG"
            },
            {
                "name": "Cort SFX-MEM",
                "price": 229,
                "description": "table acajou, fond & éclisses acajou, manche acajou (43 mm au sillet), touche & chevalet palissandre",
                "imageURL": "https://www.woodbrass.com/images/woodbrass/CORT+SFX+MEMOP-1.JPG"
            }
        ]
    },
    "extensions": {
        "type": "READ_ONLY"
    }
}
```

## `MangoPAY : REST`
Pour assurer le systeme de paiement de notre application nous utilisons l'api MangoPay
Dans un premier temps on crée un User Vendeur, celui ci aura un "Wallet" et un Compte en Banque.
Dans notre cas le vendeur est Zik Seller.

A chaque fois que quelqu'un veut faire un achat, on lui crée un compte utilisateur car cela est obligatoire.
Apres cela on recupère l'ID de l'utilisateur, et on demande a MangoPAY de nous generer une page web de paiement direct, le paiement sera fait directement de la carte de l'utilisateur au "Wallet" de Zik Seller.

```json
{
    "AuthorId": "101971831", // l'ID de l'utilisateur
    "DebitedFunds": {
    "Currency": "EUR",
    "Amount": 1200  // le prix de l'objet en centimes
    },
    "Fees": {
    "Currency": "EUR",
    "Amount": 0
    },
    "ReturnURL": "http://google.com",
    "CardType": "CB_VISA_MASTERCARD",
    "CreditedWalletId": "101937634",   // l'ID du wallet de Zik Seller
    "Culture": "FR"
}
```

## `Kotlin : L'application`
Etant ammené à faire du Kotlin en Stage, j'ai pris la decision de faire ce TP en Android Kotlin.
Pour faire les requetes HTTP, j'utilise la librairie Volley developpée par google.
Pour Afficher les images des produits, j'utilise la librairie GLIDE qui permet de recuperer une image à partir d'une URL

<img src="http://89.87.13.28:8800/Fac/info802/homepage.png" alt="drawing" width="200" />    <img src="http://89.87.13.28:8800/Fac/info802/guitares.png" alt="drawing"   width="200"/>    <img src="http://89.87.13.28:8800/Fac/info802/Product.png" alt="drawing"   width="200" />    <img src="http://89.87.13.28:8800/Fac/info802/MangoPay.png" alt="drawing" width="200"/>



### La partie qui gère les requetes HTTP de graphQL et des frais de port se trouve dans le fichier [![URL](https://img.shields.io/static/v1?label=ProductStorage:&message=.kt&color=green)](https://github.com/TomLeCollegue/ZikFrip/blob/master/app/src/main/java/com/entreprisecorp/zikfrip/storage/ProductStorage.kt)

On retrouve 3 fonctions utilisée :
* Une fonction `CalculateDeliferyFee()` appelée a chaque affichage d'article qui recupère les frais de ports
* Une fonction `getProductbyCategoy()` appelée quand on clique sur une categorie qui permet de recuperer tous les produits d'une catégorie
* Une fonction `getFirstsproduct()` appelée pour avoir les produits "A la une" qui permet de recuperer les premiers produits de la base neo4j

Exemple avec `getProductByCategory()`:
```kotlin
fun getProductbyCategoy(category : String, listProduct:ArrayList<Product>, adapter: ProductAdapter, activity: MainActivity){

    val jsonParams = JSONObject()
    jsonParams.put("query", "{Category(name:\"$category\"){products{name price description imageURL}}}" )
    val queue = Volley.newRequestQueue(activity)
    val url = "http://89.87.13.28:8413/graphql/"
    val request = JsonObjectRequest(Request.Method.POST, url, jsonParams, Response.Listener { response ->
        try {
            listProduct.clear()
            val jsonData = response.getJSONObject("data")
            val jsonArray = jsonData.getJSONArray("Category")
            val products = jsonArray.getJSONObject(0)
            val productsArray = products.getJSONArray("products")
            val arrayLenght = productsArray.length() -1

            for (i in 0..arrayLenght) {
                val productJson = productsArray.getJSONObject(i)
                val name = productJson.getString("name")
                val price = productJson.getInt("price")
                val description = productJson.getString("description")
                val imageURL = productJson.getString("imageURL")
                val product = Product(i, name, description, imageURL, price, category, 0.0)
                listProduct.add(product)
                adapter.notifyDataSetChanged()
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }, Response.ErrorListener { error -> error.printStackTrace() })
    queue.add(request)
}
```
### La partie qui gère les requetes HTTP de MangoPay se trouve dans le fichier [![URL](https://img.shields.io/static/v1?label=Payments:&message=.kt&color=green)](https://github.com/TomLeCollegue/ZikFrip/blob/master/app/src/main/java/com/entreprisecorp/zikfrip/payments/Payments.kt)

A chaque fois qu'on clique sur `Acheter` on les choses suivantes : 
* On cree un utilisateur avec le nom et le prénom de la personne avec la fonction `createNaturalClient()` 
* Avec L'id recupéré on execute la fonction `Pay()` qui nous donne une adresse URL de paiement web
* Avec `getUrlFromIntent()` on ouvre le navigateur avec l'adresse recupérée

Si vous voulez voir le resultat d'un paiement dans la sandbox, 
les identifiants sont :
    * tomkubasik
    * tomkubasik74200@gmail.com
    * X4uDHVCDZzne8!w













