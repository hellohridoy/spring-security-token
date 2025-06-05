package Com.example.e_commerce.E_commerce.Project.Backend.Java.service.cart;


import Com.example.e_commerce.E_commerce.Project.Backend.Java.model.CartItem;

public interface ICartItemService {
    void addItemToCart(Long cartId, Long productId, int quantity);

    void removeItemFromCart(Long cartId, Long productId);

    void updateItemQuantity(Long cartId, Long productId, int quantity);

    CartItem getCartItem(Long cartId, Long productId);
}
