package ma.enset.tp6_bach_processing;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

// ProductItemProcessor class
public class OrderItemProcessor implements ItemProcessor<Order, Order> {

    private static final Logger log = LoggerFactory.getLogger(OrderItemProcessor.class);

    @Override
    public Order process(final Order order) {
        final String name = order.name().toUpperCase();
        final Double montant = order.montant() * 1.2; // Applique une augmentation de 20% sur le prix

        final Order transformedProduct = new Order(order.orderId(),name,montant);

        log.info("Converting (" + order + ") into (" + transformedProduct + ")");
        return transformedProduct;
    }
}

