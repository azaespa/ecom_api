package xaltius.azanespaul.ecom_api.order.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import xaltius.azanespaul.ecom_api.order.Orders;
import xaltius.azanespaul.ecom_api.order.dto.OrdersDto;

@Component
public class OrdersToOrdersDtoConverter implements Converter<Orders, OrdersDto> {
    @Override
    public OrdersDto convert(Orders source) {

        final OrdersDto ordersDto = new OrdersDto(
                source.getOrdersId(),
                source.getTransactionId(),
                source.getDateOrdered(),
                source.getCart().getCustomer().getUsers().getName(),
                source.getCart().getCustomer().getUsers().getAddress(),
                source.getCart().getCustomer().getUsers().getEmail(),
                source.getCart().getCustomer().getUsers().getMobile(),
                source.getCart().getProduct().getName(),
                source.getCart().getProduct().getImageUrl(),
                source.getCart().getProduct().getSeller().getUsers().getName()
        );

        return ordersDto;
    }
}
