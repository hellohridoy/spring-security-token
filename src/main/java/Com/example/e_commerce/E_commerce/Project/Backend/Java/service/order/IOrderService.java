package Com.example.e_commerce.E_commerce.Project.Backend.Java.service.order;


import Com.example.e_commerce.E_commerce.Project.Backend.Java.dto.OrderDto;
import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);

    OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);
}
