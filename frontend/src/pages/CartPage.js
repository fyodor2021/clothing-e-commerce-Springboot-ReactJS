import { useEffect } from "react"
import useCartContext from "../hooks/useCartContext"
import CartProduct from "../components/CartProduct";
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
        return <CartProduct key={key} product={product}/>
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