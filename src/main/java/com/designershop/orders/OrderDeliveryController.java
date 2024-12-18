package com.designershop.orders;

import com.designershop.exceptions.*;
import com.designershop.orders.models.CreateOrderDeliveryRequestModel;
import com.designershop.orders.models.ReadOrderDeliveryResponseModel;
import com.designershop.orders.models.UpdateOrderDeliveryRequestModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order_delivery")
@RequiredArgsConstructor
public class OrderDeliveryController {

    private final OrderDeliveryService orderDeliveryService;

    @PostMapping
    public ResponseEntity<String> createOrderDelivery(@RequestBody @Valid CreateOrderDeliveryRequestModel request)
            throws EmptyException, UserException, OrderException {
        String address = orderDeliveryService.createOrderDelivery(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReadOrderDeliveryResponseModel>> readAllOrderDelivery() throws UserException {
        List<ReadOrderDeliveryResponseModel> response = orderDeliveryService.readAllOrderDelivery();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadOrderDeliveryResponseModel> readOrderDelivery(@PathVariable String id)
            throws UserException, OrderException {
        ReadOrderDeliveryResponseModel response = orderDeliveryService.readOrderDelivery(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<ReadOrderDeliveryResponseModel> readOrderDeliveryByIsDefault()
            throws UserException, OrderException {
        ReadOrderDeliveryResponseModel response = orderDeliveryService.readOrderDeliveryByIsDefault();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrderDelivery(@PathVariable String id, @RequestBody @Valid UpdateOrderDeliveryRequestModel request)
            throws EmptyException, UserException, OrderException {
        String address = orderDeliveryService.updateOrderDelivery(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderDelivery(@PathVariable String id) throws UserException, OrderException {
        String userId = orderDeliveryService.deleteOrderDelivery(id);
        return ResponseEntity.status(HttpStatus.OK).body(userId);
    }
}
