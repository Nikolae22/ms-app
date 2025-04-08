package com.productservice.service;

import com.productservice.dto.ProductPurchaseRequest;
import com.productservice.dto.ProductPurchaseResponse;
import com.productservice.dto.ProductRequest;
import com.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    Integer createProduct(ProductRequest request);

    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request);

    ProductResponse findById(Integer productId);

    List<ProductResponse> findAll();
}
