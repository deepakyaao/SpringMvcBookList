package in.ashokit.Service;

public interface CartService {

    void addToCart(Integer userId, Integer bookId);
    List<Cart> getCartItems(Integer userId);

}