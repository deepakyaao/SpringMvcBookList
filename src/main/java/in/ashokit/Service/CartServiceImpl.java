package in.ashokit.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.Entity.Cart;
import in.ashokit.Entity.CartDto;
import in.ashokit.Repo.CartRepo;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Override
    public void addToCart(Integer userId, Integer bookId) {

        Cart cart = new Cart();

        cart.setUserId(userId);
        cart.setBookId(bookId);
      
       waht the fukc is happigne.,oh now i see
           acha  merger practice
        
    }
    
    @Override
    public List<CartDto> getCartItems(Integer userId) {

        List<Cart> cartList = cartRepo.findByUserId(userId);

        List<CartDto> dtoList = new ArrayList<>();

        for (Cart cart : cartList) {

            CartDto dto = new CartDto();

            dto.setCartId(cart.getCartId());
            dto.setBookId(cart.getBookId().getBookId());
            dto.setBookName(cart.getBookId().getBookName());
            dto.setBookPrice(cart.getBookId().getBookPrice());

            dtoList.add(dto);
        }

        return dtoList;
    }
}
