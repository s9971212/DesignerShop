package com.designershop.coupons;

import com.designershop.coupons.models.CreateCouponRequestModel;
import com.designershop.coupons.models.ReadCouponResponseModel;
import com.designershop.coupons.models.UpdateCouponRequestModel;
import com.designershop.exceptions.CouponException;
import com.designershop.exceptions.EmptyException;
import com.designershop.exceptions.UserException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller/coupon")
@RequiredArgsConstructor
public class SellerCouponController {

    private final SellerCouponService sellerCouponService;

    @PostMapping
    public ResponseEntity<String> createCoupon(@RequestBody @Valid CreateCouponRequestModel request) throws EmptyException, UserException,CouponException {
        String code = sellerCouponService.createCoupon(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(code);
    }

    @GetMapping
    public ResponseEntity<List<ReadCouponResponseModel>> readAllCoupon() throws UserException{
        List<ReadCouponResponseModel> response = sellerCouponService.readAllCoupon();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadCouponResponseModel> readCoupon(@PathVariable String id) throws UserException,CouponException {
        ReadCouponResponseModel response = sellerCouponService.readCoupon(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCoupon(@PathVariable String id, @RequestBody @Valid UpdateCouponRequestModel request)
            throws EmptyException,UserException, CouponException {
        String code = sellerCouponService.updateCoupon(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(code);
    }
}
