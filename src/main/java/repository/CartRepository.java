package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
