import { useState } from 'react';
import CartProductList from '../components/CartProductList';
import useCartContext from '../hooks/useCartContext'

export default function CheckoutPage() {
    const { getCartProducts, cartProducts } = useCartContext();
    const [pickupLocation, setPickupLocation] = useState();
    const subTotal = cartProducts.reduce((acc, product) => {
        return acc + (product.currentPrice * product.quantity)
    }, 0)
    const tax = subTotal * .13
    const total = (subTotal + tax + 2)
    return <div className='checkout-page'>
        <div className='checkout-requirments'>
            <div>
                <h1>
                    Pickup Location:
                </h1>
                <div>
                    <div onClick={() => setPickupLocation('lawerance')}>
                        <div>
                            1909 Lawrence Ave E, Scarborough, ON M1R 2Y6
                        </div>
                        {pickupLocation === 'lawerance' ? 
                        <div className='location-selected-layer'></div> : <></>}
                    </div>
                    <div>
                        <div  onClick={() => setPickupLocation('mississauga')}>
                            720 Bristol Rd W Unit 1, Mississauga, ON L5R 4A5
                        </div>
                        {pickupLocation === 'mississauga' ? 
                        <div className='location-selected-layer'></div> : <></>}
                    </div>
                </div>
            </div>
            <div>
                <h1>
                    Payment method
                </h1>
                <div>

                </div>
            </div>
            <div>
                <h1>
                    Review cart items
                </h1>
                <div className="cart-product-cards">
                    <CartProductList checkout={true} products={cartProducts} />
                </div>
            </div>

        </div>







































        <div className="order-summary">
            <div>
                <div>
                    <p>Items:</p>
                    <p>{subTotal.toFixed(2)}</p>
                </div>
                <div>
                    <p>Shipping and Handling:</p>
                    <p>$0.00</p>
                </div>
            </div>
            <div>
                <div>
                    <p>
                        Total before Taxes:
                    </p>
                    <p>
                        {subTotal.toFixed(2)}
                    </p>
                </div>
                <div>
                    <p>
                        Estimated GST/HST:
                    </p>
                    <p>
                        {tax.toFixed(2)}
                    </p>
                </div>
            </div>
            <div>
                <h1>Order Total: </h1>
                <h1>{total.toFixed(2)} </h1>
            </div>
            <div>
                <button className="button">
                    Place Order
                </button>
                <p>
                    By Placing your order, you agree to our return
                    policy and conditions of use.
                </p>
            </div>
        </div>

    </div>
}