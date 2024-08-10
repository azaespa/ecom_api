package xaltius.azanespaul.ecom_api.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xaltius.azanespaul.ecom_api.cart.Cart;
import xaltius.azanespaul.ecom_api.cart.CartService;
import xaltius.azanespaul.ecom_api.cart.dto.CartDto;
import xaltius.azanespaul.ecom_api.order.converter.OrdersToOrdersDtoConverter;
import xaltius.azanespaul.ecom_api.order.dto.OrdersDto;
import xaltius.azanespaul.ecom_api.system.Result;

import java.time.LocalDate;
import java.util.List;

@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    private final OrdersToOrdersDtoConverter ordersToOrdersDtoConverter;

    public OrdersController(OrdersToOrdersDtoConverter ordersToOrdersDtoConverter) {
        this.ordersToOrdersDtoConverter = ordersToOrdersDtoConverter;
    }

    @PostMapping("/order/place")
    private Result saveOrders(@RequestBody List<CartDto> cartDtoList) {
        List<Orders> ordersList = ordersService.saveAllOrders(cartDtoList);
        List<OrdersDto> ordersDtoList = ordersList.stream().map(ordersToOrdersDtoConverter::convert).toList();

        return new Result("Save All Success", HttpStatus.OK.value(), ordersDtoList);
    }

    @GetMapping("/orders")
    private Result getAllOrders() {
        List<Orders> ordersList = ordersService.findAllOrdersOfLoggedInUser();
        List<OrdersDto> ordersDtoList = ordersList.stream().map(ordersToOrdersDtoConverter::convert).toList();

        return new Result("Find All Success", HttpStatus.OK.value(), ordersDtoList);
    }

    @GetMapping("/orders/by/{date}")
    private Result getAllOrdersByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<Orders> ordersList = ordersService.findAllOrdersOfLoggedInUserByDate(String.valueOf(date));
        List<OrdersDto> ordersDtoList = ordersList.stream().map(ordersToOrdersDtoConverter::convert).toList();

        return new Result("Find All By Date Success", HttpStatus.OK.value(), ordersDtoList);
    }

    @GetMapping("/orders/{ordersId}")
    private Result getAllOrdersById(@PathVariable int ordersId) {
        List<Orders> ordersList = ordersService.findAllOrdersOfLoggedInUserById(ordersId);
        List<OrdersDto> ordersDtoList = ordersList.stream().map(ordersToOrdersDtoConverter::convert).toList();

        return new Result("Find All By Id Success", HttpStatus.OK.value(), ordersDtoList);
    }

    @PutMapping("/orders/{ordersId}")
    private Result updateOrdersById(@PathVariable int ordersId, @RequestBody Orders o) {
        Orders orders = ordersService.updateOrderById(ordersId, o);
        OrdersDto ordersDto = ordersToOrdersDtoConverter.convert(orders);

        return new Result("Update One Success", HttpStatus.OK.value(), ordersDto);
    }

    @DeleteMapping("/orders/{ordersId}")
    private Result deleteOrdersById(@PathVariable int ordersId) {
        ordersService.deleteOrderById(ordersId);

        return new Result("Delete One Success", HttpStatus.OK.value(), null);
    }
}
