package com.example.demo.service;

import com.example.demo.model.Sale;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.demo.model.Product;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService {

    //  getAllProducts(): Returns a list of all products in the service.
    //  getProductById(int id): Returns the product with the specified ID.
    //  addProduct(Product product): Adds a new product to the service.
    //  updateProduct(int id, Product product): Updates the product with the specified ID.
    //  deleteProduct(int id): Deletes the product with the specified ID.

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Page<Product> getProductsWithPagination(int offset, int pageSize) {
        Page<Product> products = productRepository.findAll(PageRequest.of(offset, pageSize));
        return products;
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }

    public Product addProduct(Product product) {
        Product product1 = Product.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();

        Product product2 = productRepository.save(product1);

        List<Sale> sales = product.getSales();
        for (Sale sale : sales) {
            Sale sale1 = Sale.builder()
                    .productId(product2.getId())
                    .quantity(sale.getQuantity())
                    .saleDate(LocalDate.now())
                    .build();
            saleRepository.save(sale1);
        }
        return productRepository.findById(product2.getId()).get();
    }

    public Product updateProduct(int id, Product product) {
        Product product1 = Product.builder()
                .id(id)
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();

        Product product2 = productRepository.save(product1);

        List<Sale> sales = product.getSales();
        for (Sale sale : sales) {
            Sale sale1 = Sale.builder()
                    .productId(product2.getId())
                    .quantity(sale.getQuantity())
                    .saleDate(sale.getSaleDate())
                    .build();
            saleRepository.save(sale1);
        }
        return productRepository.findById(product2.getId()).get();
    }

    public void deleteProduct(int id) {
         productRepository.deleteById(id);
    }

    public Double getTotalRevenue() {
        return productRepository.getTotalRevenue();
    }

    public double getTotalRevenue(int productId) {
        return productRepository.getRevenueForEachProduct(productId);
    }

    public void addProductToDB(List<Product> product) {
        for(Product p : product) {
            productRepository.save(p);
        }
    }
}
