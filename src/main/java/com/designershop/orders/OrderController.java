package com.designershop.orders;

import com.designershop.exceptions.*;
import com.designershop.orders.models.CreateOrderRequestModel;
import com.designershop.orders.models.ReadOrderResponseModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{deliveryId}")
    public ResponseEntity<String> createOrder(@PathVariable String deliveryId, @RequestBody @Valid CreateOrderRequestModel request)
            throws EmptyException, UserException, ProductException, CartException, OrderException {
        String form = orderService.createOrder(deliveryId,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(form);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReadOrderResponseModel>> readAllOrder() throws UserException {
        List<ReadOrderResponseModel> response = orderService.readAllOrder();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
