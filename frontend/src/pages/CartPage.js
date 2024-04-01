import { useEffect } from "react"
import useCartContext from "../hooks/useCartContext"
import CartProduct from "../components/CartProduct";
import Footer from "../components/Footer";
import emptyCartImage from '../statics/emptyCart.png'
import { useNavigate } from "react-router-dom";
import CartProductList from "../components/CartProductList";
export default function CartPage() {
    const { getCartProducts, cartProducts } = useCartContext();
    const checkoutPanel = document.getElementsByClassName('right-container');
    const navigate = useNavigate();
    const subTotal = cartProducts.reduce((acc, product) => {
        return acc + (product.currentPrice * product.quantity)
    }, 0)
    const handleScrollEvent = () => {
        if (subTotal != 0) {
            if (window.scrollY > 100) {
                checkoutPanel[0].classList.add('fix-checkout-panel')
            } else {
                checkoutPanel[0].classList.remove('fix-checkout-panel')
            }
        }
    }
    useEffect(() => {
        getCartProducts()
        window.addEventListener('scroll', handleScrollEvent);

        return () => {
            window.removeEventListener('scroll', handleScrollEvent);
        };
    }, [])
    useEffect(() => {
        return () => {
            window.removeEventListener('scroll', handleScrollEvent);
        };
    }, [subTotal])
    const tax = subTotal * .13
    const total = (subTotal + tax)
    const handleCheckout = () => {
        navigate('/checkout')
    }
    const pointsGainedByPurchase = cartProducts.reduce((acc,item) => {
        return acc = parseInt(acc) + parseInt(item.points * item.quantity)
    },[0])
    return <div>
        {subTotal != 0 ?
            <div className="cart-main-container">
                <div>
                    <div className="cart-product-cards">
                        <CartProductList checkout={false} products={cartProducts} />
                    </div>
                </div>
                <div>
                    <div className="right-container">
                        <div className="cart-checkout-container">
                            <div className="cart-checkout-small-containers">
                                <p>Total before tax: </p>
                                <p>${subTotal.toFixed(2)}</p>
                            </div>
                            <div className="cart-checkout-small-containers">
                                <p>Estimated tax: </p>
                                <p>${tax.toFixed(2)}</p>
                            </div>
                            <div className="cart-checkout-small-containers" style={{color:'green'}}>
                                <p>Points gained: </p>
                                <p>{pointsGainedByPurchase} Points</p>
                            </div>
                            <div className="cart-checkout-small-containers">
                                <p>Estimated total: </p>
                                <p>${total.toFixed(2)}</p>
                            </div>
                            <div>
                                <button onClick={handleCheckout} className="button">Checkout</button>
                            </div>
                        </div>
                    </div>
                    <div className="right-container" style={{ visibility: 'hidden' }}>
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
            </div> :
            <div className="empty-cart-image">
                <div>
                    <img src={emptyCartImage} width="800" />
                </div>
                <div>
                    <h1>Your Cart is Empty!</h1>
                    <h1>Items you add will appear here!</h1>
                </div>
            </div>
        }
        <Footer />
    </div>
}