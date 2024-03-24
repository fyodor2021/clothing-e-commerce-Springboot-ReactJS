import { useEffect } from "react"
import useCartContext from "../hooks/useCartContext"
export default function CartPage() {
    const { getCartProducts, cartProducts } = useCartContext();
    const checkoutPanel = document.getElementsByClassName('right-container');
    const handleScrollEvent = () => {
        if(window.scrollY > 100){
            checkoutPanel[0].classList.add('fix-checkout-panel')
        }else{
            checkoutPanel[0].classList.remove('fix-checkout-panel')
        }
    }

    useEffect(() => {
        getCartProducts()
        window.addEventListener('scroll', handleScrollEvent);
        return () => {
            window.removeEventListener('scroll', handleScrollEvent);
        };
    }, [])

    const subTotal = cartProducts.reduce((acc, product) => {
        return acc + (product.currentPrice * product.quantity)
    }, 0)
    const tax = subTotal * .13
    const total = (subTotal + tax)
    console.log(total)

    const renderedProducts = cartProducts.map((product, key) => {
        console.log(cartProducts)

        return <div key={key}>
            <div className="cart-product-fields">
                <img className="cart-product-image" src={'data:image/jpeg;base64,' + product.imageList[0]} />
            </div>
            <div>
                <p>{product.description}</p>
                <p>{product.size} {product.unit}</p>
                <p>${product.currentPrice}</p>
                <div className="qty-field">
                    <p>-</p>
                    <p>{product.quantity}</p>
                    <p>+</p>
                </div>
            </div>

        </div>
    })

    console.log(subTotal)
    return <div>
        <div className="cart-main-container">
            <div className="cart-product-cards">
                {renderedProducts}
            </div>
            <div>
                <div className="right-container">
                    <div className="cart-checkout-container">
                        <div className="cart-checkout-small-containers">
                            <p>Subtotal: </p>
                            <p>${subTotal}</p>
                        </div>
                        <div className="cart-checkout-small-containers">
                            <p>Tax: </p>
                            <p>${tax.toFixed(2)}</p>
                        </div>
                        <div className="cart-checkout-small-containers">
                            <p>Total: </p>
                            <p>${total.toFixed(2)}</p>
                        </div>
                        <div>
                            <button className="button">Checkout</button>
                        </div>
                    </div>
                </div>
                <div className="right-container hidden">
                    <div className="cart-checkout-container">
                        <div className="cart-checkout-small-containers">
                            <p>Subtotal: </p>
                            <p>${subTotal}</p>
                        </div>
                        <div className="cart-checkout-small-containers">
                            <p>Tax: </p>
                            <p>${tax.toFixed(2)}</p>
                        </div>
                        <div className="cart-checkout-small-containers">
                            <p>Total: </p>
                            <p>${total.toFixed(2)}</p>
                        </div>
                        <div>
                            <button className="button">Checkout</button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
}