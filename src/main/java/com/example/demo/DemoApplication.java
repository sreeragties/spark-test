package com.example.demo;

import com.example.demo.model.Product;
import com.example.demo.model.Sale;
import com.example.demo.service.ProductService;
import com.example.demo.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.List;

import java.time.LocalDate;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private ProductService productService;

	@Autowired
	private SaleService saleService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

	@Bean
	public CommandLineRunner CommandLineRunnerBean() {
		return (args) -> {
			System.out.println("In CommandLineRunnerImpl ");

			Product product1 = Product.builder()
					.name("Apple")
					.description("Red")
					.price(100.0)
					.quantity(10)
					.build();

			Product product2 = Product.builder()
					.name("Orange")
					.description("Orange")
					.price(100.0)
					.quantity(10)
					.build();

			productService.addProductToDB(List.of(product1, product2));

			Sale sale1 = Sale.builder()
					.productId(product1.getId())
					.quantity(5)
					.saleDate(LocalDate.now())
					.build();

			Sale sale2 = Sale.builder()
					.productId(product2.getId())
					.quantity(5)
					.saleDate(LocalDate.now())
					.build();

			Sale sale3 = Sale.builder()
					.productId(product2.getId())
					.quantity(2)
					.saleDate(LocalDate.now())
					.build();

			saleService.addSalesToDB(new Sale[]{sale1, sale2, sale3});
		};
	}

}
