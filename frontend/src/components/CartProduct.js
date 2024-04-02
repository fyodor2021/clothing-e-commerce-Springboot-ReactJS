import useCartContext from "../hooks/useCartContext";
import { useCookies } from "react-cookie";
export default function CartProduct(
    { key, product, checkout }
) { 
    const { productCountIncrement, productCountDecrement } = useCartContext();
    const [cookie, setCookie, removeCookie] = useCookies(['SESSION'])
    const incDecRequest = {
        productId: product.productId,
        headerValue: window.localStorage.getItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL)
            ? window.localStorage.getItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL)
            : cookie['SESSION']
    }

    return product.inStock ? <div key={product.productId} className="cart-product-container">
        <div className="cart-product-fields">
            <img className="cart-product-image" src={'data:image/jpeg;base64,' + product.imageList[0]} />
        </div>
        <div>
            <p>{product.description}</p>
            <p>{product.size} {product.unit}</p>
            <p>${product.currentPrice}</p>
            {!checkout ?
                <div className="qty-field">
                    <p onClick={() => productCountDecrement(incDecRequest)} style={{ cursor: 'pointer' }}>-</p>
                    <p>{product.quantity}</p>
                    <p onClick={() => productCountIncrement(incDecRequest)} style={{ cursor: 'pointer' }}>+</p>
                </div> : <></>}
        </div>
    </div> : <div key={key} className="not-in-stock-cart-item">
        <div className="cart-product-fields">
            <img className="cart-product-image" src={'data:image/jpeg;base64,' + product.imageList[0]} />
        </div>
        <div>
            <p>{product.description}</p>
            <p>{product.size} {product.unit}</p>
            <p>${product.currentPrice}</p>
            <div className="qty-field">
                <p onClick={() => productCountDecrement(incDecRequest)} style={{ cursor: 'pointer' }}>-</p>
                <p>{product.quantity}</p>
                <p onClick={() => productCountIncrement(incDecRequest)} style={{ cursor: 'pointer' }}>+</p>
            </div>
            <div className="transparent-layer">
                <div>
                    Out of Stock
                </div>
                <div className="qty-field-transparent-layer">
                    <p onClick={() => productCountDecrement(incDecRequest)} style={{ cursor: 'pointer' }}>-</p>
                    <p>{product.quantity}</p>
                    <p onClick={() => productCountIncrement(incDecRequest)} style={{ cursor: 'pointer' }}>+</p>
                </div>
            </div>
        </div>

    </div>
}