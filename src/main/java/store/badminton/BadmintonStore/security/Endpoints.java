package store.badminton.BadmintonStore.security;

public class Endpoints {
    public static final String front_end_host = "http://localhost:3000";
    public static final String[] PUBLIC_GET_ENDPOINTS = {
            "/api/products", "/api/products/image/*", "/api/products/*",
            "/api/users/existsByEmail", "/api/users/existsByPhone", "/api/users/existsByUsername", "/api/users/getByUsername/*",


    };

    public static final String[] GET_USER_ENDPOINTS = {
            "/c/getItemByUser/*", "/api/carts/getCountByUserId/*", "/api/carts/getAmountByUserId/*", "/api/address", "/api/orders/*", "/api/orderdetails/*"
    };
    public static final String[] DELETE_USER_ENDPOINTS = {
            "/api/carts/removeCartItemByUserId/*/*"
    };
    public static final String[] POST_USER_ENDPOINTS = {
            "/api/carts/addCart/*/*", "/api/address", "/api/orders"
    };
    public static final String[] PUT_USER_ENDPOINTS = {
            "/api/carts/handleDecrementById/*/*", "/api/carts/handleIncrementById/*/*"
    };


    public static final String[] PUBLIC_POST_ENDPOINTS = {
            "/api/users",
            "/api/users/loginAccount",
    };

    public static final String[] PRIVATE_GET_ENDPOINTS = {
            "/api/categories", "/api/users", "/api/orders", "/api/orders/getById/*","/api/orders/changeStatus/*"

    };
    public static final String[] PRIVATE_POST_ENDPOINTS = {
            "/api/products/*/add", "/api/products/image/upload/*", "/api/categories"
    };


}
