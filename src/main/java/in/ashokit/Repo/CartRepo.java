package in.ashokit.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.Entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer> {

    List<Cart> findByUserId(Integer userId);

}