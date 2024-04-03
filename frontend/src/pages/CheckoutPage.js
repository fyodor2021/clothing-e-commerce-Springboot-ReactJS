import { useRef, useState, useEffect } from 'react';
import CartProductList from '../components/CartProductList';
import useCartContext from '../hooks/useCartContext'
import usePaymentContext from '../hooks/usePaymentContext';
import useAuthContext from '../hooks/useAuthContext';
import { useNavigate } from 'react-router-dom';
import useOrderContext from '../hooks/useOrderContext';

export default function CheckoutPage() {
    const { getCartProducts, cartProducts } = useCartContext();
    const { emptyCart } = useCartContext();
    const { placeOrder } = useOrderContext();
    const { userCardsInfo } = usePaymentContext();
    const [valMessage, setValMessage] = useState();
    const { loggedUser, getLoggedUser } = useAuthContext();
    const [pickupLocation, setPickupLocation] = useState();
    const [paymentCard, setPaymentCard] = useState();
    const [payWithPoints, setPayWithPoints] = useState(false);
    const delay = ms => new Promise(res => setTimeout(res, ms));
    const navigate = useNavigate();
    const summaryPanelRef = useRef();
    const handleScrollEvent = () => {
        if (subTotal != 0) {
            if (window.scrollY > 80) {
                summaryPanelRef.current.classList.add('fix-summary-panel')
            } else {
                summaryPanelRef.current.classList.remove('fix-summary-panel')
            }
        }
    }
    useEffect(() => {

        if (userCardsInfo && userCardsInfo.length == 1) {
            setPaymentCard(userCardsInfo[0].brand)
        }
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
    let renderedCards;
    console.log(userCardsInfo)
    if (userCardsInfo) {
        renderedCards = userCardsInfo.map((card, key) => {
            return (
                <div onClick={() => setPaymentCard(card.brand)} key={key} className='checkout-payment-method'>
                    <img src="data:image/jpg;base64,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" width="50" height="50" />
                    <div>
                        <div>
                            Payment will be Charged to {card.brand} ending with {card.lastFourDigit}
                        </div>

                    </div>
                    {paymentCard === card.brand ? <div className='checkout-payment-method-layer'></div> : <></>}
                </div>
            )
        })
    }
    //we need a list of products
    //points gained
    //points they want to pay with
    //
    const pointsGainedByPurchase = cartProducts.reduce((acc, item) => {
        return acc = parseInt(acc) + parseInt(item.points * item.quantity)
    }, [0])
    const handlePlaceOrder = async () => {
        if (!pickupLocation) {
            setValMessage("Please select a pickup location")
        }
        if (!pickupLocation) {
            setValMessage("Please select a payment method")
        }
        let productIds = []
        for (let i = 0; i < cartProducts.length; i++) {
            productIds.push(cartProducts[i].productId)
        }
        if (pickupLocation && paymentCard) {
            const orderRequest = {
                userEmail: loggedUser.email,
                orderTotal: total,
                productIds,
                cardBrand: paymentCard,
                pointsToAdd: pointsGainedByPurchase,
                pointsToPay: payWithPoints ? loggedUser.points : 0,
                moneyToPay: payWithPoints ? total - (loggedUser.points / 1000) : total
            }
            await placeOrder(orderRequest)
            await emptyCart()
            setValMessage('Order was successfully placed')
            await delay(2000)
            window.location.reload();
        }

    }
    const handlePaymentMethodAdd = () => {
        navigate('/account',{state: "add payment"})
    }
    console.log(cartProducts)
    if (cartProducts.length != 0) {
        return <div className='checkout-page'>

            <div className='checkout-requirments'>
                <div>
                    <h1>
                        Select pickup location:
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
                            <div onClick={() => setPickupLocation('mississauga')}>
                                720 Bristol Rd W Unit 1, Mississauga, ON L5R 4A5
                            </div>
                            {pickupLocation === 'mississauga' ?
                                <div className='location-selected-layer'></div> : <></>}
                        </div>
                    </div>
                </div>
                <div>
                    <h1>
                        Select payment method:
                    </h1>
                    <div >
                        <div>
                        <div>
                            {renderedCards}
                        </div>
                        <div style={{color: 'blue', textDecoration: 'solid'}}>
                            <span onClick={handlePaymentMethodAdd}  style={{cursor:'pointer'}}>
                            Add a payment method
                            </span>
                        </div>
                        </div>
                        {loggedUser && loggedUser.points > 0 ? <div onClick={() => setPayWithPoints(!payWithPoints)} className='checkout-payment-method'>
                            <div>
                                Pay with points? {loggedUser.points}
                            </div>
                            {payWithPoints ? <div className='checkout-payment-method-layer'></div> : <></>}
                        </div> : <></>}

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
            <div>
                {valMessage === 'Please select a pickup location' || valMessage === 'Please select a payment method' ?
                    valMessage ? <div style={{ backgroundColor: 'rgba(255, 0, 0, 0.37)', color: 'red', fontWeight: 'bold' }}>
                        {valMessage}
                    </div> : <></>
                    :
                    valMessage ? <div style={{ backgroundColor: 'rgba(43, 255, 0, 0.37)', color: 'green', fontWeight: 'bold' }}>
                        {valMessage}
                    </div> : <></>
                }

                <div ref={summaryPanelRef} className="order-summary">

                    <div>
                        <div>
                            <p>Items:</p>
                            <p>{subTotal.toFixed(2)}</p>
                        </div>
                        <div style={{ color: 'green' }}>
                            <p>Points gained:</p>
                            <p>{pointsGainedByPurchase} Points</p>
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
                        <button onClick={handlePlaceOrder} className="button">
                            Place Order
                        </button>
                        <p>
                            By Placing your order, you agree to our return
                            policy and conditions of use.
                        </p>
                    </div>
                </div>
                <div className="order-summary" style={{ visibility: "hidden" }}>
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


        </div>
    } else {
        navigate('/')
    }

}