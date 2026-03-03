package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import id.ac.ui.cs.advprog.eshop.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final Repository<Product, String> productRepository;

    @Autowired
    public ProductServiceImpl(Repository<Product, String> productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        if (product.getId() == null) {
            product.setId(UUID.randomUUID().toString());
        }

        productRepository.create(product);
        return product;
    }

    @Override
    public Product update(Product product) {
        productRepository.update(product.getId(), product);
        return product;
    }

    @Override
    public boolean delete(String id) {
        return productRepository.delete(id);
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> iterator = productRepository.findAll();
        List<Product> list = new ArrayList<>();

        while (iterator.hasNext()) {
            list.add(iterator.next());
        }

        return list;
    }
}
