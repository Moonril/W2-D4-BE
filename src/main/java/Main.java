import ch.qos.logback.core.net.server.Client;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        Product p1 = new Product(1L, "Il signore degli Anelli", "Books", 10.0);
        Product p2 = new Product(2L, "Il nome della rosa", "Books", 101.0);
        Product p3 = new Product(3L, "pannolini", "Baby", 4.0);
        Product p4 = new Product(4L, "maglietta", "Baby", 7.0);
        Product p5 = new Product(5L, "carrozzina", "Baby", 70.0);
        Product p6 = new Product(6L, "scarpe", "Boys", 60.0);
        Product p7 = new Product(7L, "pantalone", "Boys", 30.0);
        Product p8 = new Product(8L, "felpa", "Boys", 30.0);

        List<Product> prodotti = List.of(p1, p2,p3,p4,p5,p6,p7,p8);

        Customer c1 = new Customer(1L, "Sara", 1);
        Customer c2 = new Customer(2L, "Roberto", 2);
        Customer c3 = new Customer(3L, "Carlo", 2);

        Order o1 = new Order(1L, "ordinato", LocalDate.of(2021, 1,1), LocalDate.of(2021, 2,1), c1);
        Order o2 = new Order(2L, "ordinato", LocalDate.of(2021, 3,1), LocalDate.of(2021, 3,2), c2);
        Order o3 = new Order(3L, "ordinato", LocalDate.of(2021, 3,12), LocalDate.of(2021, 3,13), c3);

        List<Order> listaOrdini = List.of(o1,o2,o3);

        // Esercizio #1
        //Raggruppare gli ordini per cliente utilizzando Stream e Lambda Expressions. Crea una mappa in cui la chiave è il cliente e il valore è una lista di ordini effettuati da quel cliente
        Map<Customer, List<Order>> ordiniPerCliente = listaOrdini.stream().collect(Collectors.groupingBy(Order::getCustomer));
        System.out.println(ordiniPerCliente);

        // Esercizio #2
        //Dato un elenco di ordini, calcola il totale delle vendite per ogni cliente utilizzando Stream e Lambda Expressions.
        // Crea una mappa in cui la chiave è il cliente e il valore è l'importo totale dei suoi acquisti

//        Map<Customer, Double> priceOrdini = listaOrdini.stream().collect(Collectors.groupingBy(Order::getCustomer, Collectors
//                .summingDouble(ordine ->ordine.getProducts().stream().mapToDouble(Product::getPrice).sum())));

        //System.out.println(priceOrdini);

        //Esercizio #3
        //Dato un elenco di prodotti, trova i prodotti più costosi utilizzando Stream e Lambda Expressions
        System.out.println("prodotto più costoso: " + prodotti.stream().max(Comparator.comparing(Product::getPrice)));
                          // giulia
        DoubleSummaryStatistics pricey =  prodotti.stream().collect(Collectors.summarizingDouble(Product::getPrice));
        System.out.println("prezzo più alto: "+ pricey.getMax());
        //Esercizio #4
        //Dato un elenco di ordini, calcola la media degli importi degli ordini utilizzando Stream e Lambda Expressions
        System.out.println("media prezzi: " + pricey.getAverage());
                         //oppure
        //listaOrdini.stream().mapToDouble(order -> order.getProducts().stream())
        //Esercizio #5
        //Dato un elenco di prodotti, raggruppa i prodotti per categoria e calcola la somma degli importi per ogni categoria utilizzando Stream e Lambda Expressions.
        Map<String, Double> perCategoria = prodotti.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.summingDouble(Product::getPrice)));
        System.out.println("totale prodotti per categoria: " + perCategoria);

        //[EXTRA] Esercizio #6
        //Usando la classe Apache Commons IO FileUtils implementare un metodo salvaProdottiSuDisco che salvi su disco un file contenente la lista dei prodotti.
        //Utilizzare un formato simile al seguente per storicizzare i dati su file:
        //nomeprodotto1@categoriaprodotto1@prezzoprodotto1#nomeprodotto2@categoriaprodotto2@prezzoprodotto2


        //[EXTRA] Esercizio #7
        //Sempre usando la classe Apache Commons IO FileUtils implementare un metodo leggiProdottiDaDisco che riempia un ArrayList con il contenuto del file salvato al punto 6

    }
}
