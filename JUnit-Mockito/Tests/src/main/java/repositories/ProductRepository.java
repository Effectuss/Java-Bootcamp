package repositories;

import edu.school21.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll(Long id);

    Optional<Product> findById(Long id);

    void update(Product product);

    void save(Product product);

    void delete(Long id);
}
