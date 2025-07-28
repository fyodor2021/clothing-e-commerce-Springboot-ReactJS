import { useRef, useState, useEffect } from 'react';
import CartProductList from '../components/CartProductList';
import { useLocation, useNavigate } from 'react-router-dom';
import { useEmptyCartMutation } from '../store/apis/cartApi';
import { useAddPaymentMethodMutation, usePlaceOrderMutation } from '../store';
import { useDispatch, useSelector } from 'react-redux';
import {
  useFetchCardsInfoQuery,
  setFname,
  setLname,
  setSecurityCode,
  setCardNumber,
  setExpire,
} from '../store/index';
import Input from '../components/Input.js';

export default function CheckoutPage() {

  const [emptyCart, emptyCartResults] = useEmptyCartMutation();
  const [placeOrder, placeOrderResults] = usePlaceOrderMutation();
  const [subTotal, setSubTotal] = useState(0.0);
  const [addPayment, setAddPayment] = useState(false);
  const dispatch = useDispatch();
  const [tax, setTax] = useState(0.0);
  const [pointsGainedByPurchase, setPointsGainedByPurchase] = useState(0);
  const [valMessage, setValMessage] = useState();
  const loggedUser = useSelector((state) => {
    return state.user;
  });
  const { data: cardsInfo } = useFetchCardsInfoQuery(loggedUser.email);
  const [paymentCard, setPaymentCard] = useState();
  const [payWithPoints, setPayWithPoints] = useState(false);
  const [addPaymentMethod] = useAddPaymentMethodMutation();
  const navigate = useNavigate(0)
  const location = useLocation();
    const { cartItems } = useSelector((state) => {
    return state.cartSlice;
  });
  const {
    fname,
    fnameVal,
    fnameValMes,
    lname,
    lnameVal,
    lnameValMes,
    cardNumber,
    cardNumberVal,
    cardNumberValMes,
    securityCode,
    securityCodeVal,
    securityCodeValMes,
    expire,
    expireVal,
    expireValMes,
  } = useSelector((state) => {
    return state.input;
  });

  useEffect(() => {
    setPaymentCard(
      cardsInfo && cardsInfo.length > 0 ? cardsInfo[0].lastFourDigit : ''
    );
  }, [cartItems]);
  useEffect(() => {
    console.log("placeOrderResults",placeOrderResults)
    if(placeOrderResults.isSuccess){
      emptyCart(loggedUser.email);
    }
  },[placeOrderResults])

    useEffect(() => {
    if(emptyCartResults.isSuccess){
      window.localStorage.removeItem('cart')
      navigate(0)
    }
  },[emptyCartResults])
  useEffect(() => {
    const data = location.state.products;
    if (data) {
      const calSubTotal = data.reduce((acc, product) => {
        return acc + product.price * product.quantity;
      }, 0);
      setSubTotal(calSubTotal);

      const calPointsGainedByPurchase = data.reduce((acc, product) => {
        return acc + parseInt(product.points) * product.quantity;
      }, 0);
      setPointsGainedByPurchase(calPointsGainedByPurchase);

      const calTax = data.reduce((acc, item) => {
        return acc + item.price * 0.13 * item.quantity;
      }, 0);
      setTax(calTax);
    }
  }, []);

  const total = subTotal + tax;

  let renderedCards;
  if (cardsInfo) {
    renderedCards = cardsInfo.map((card, key) => {
      return (
        <option
          onClick={() => setPaymentCard(card.lastFourDigit)}
          key={key}
          value={card.lastFourDigit}
          className="flex justify-between bg-white items-center px-6 font-semibold"
        >
          {card.brand.toLowerCase()} ending with {card.lastFourDigit}
        </option>
      );
    });
  }

  const handlePlaceOrder = async () => {
    if (!paymentCard) {
      console.log(paymentCard)
      console.log('Please select a payment method');
      setValMessage('Please select a payment method');
    }

    if (paymentCard) {
      const orderRequest = {
        userEmail: loggedUser.email,
        orderTotal: total,
        products:cartItems,
        cardBrand: paymentCard,
        pointsToAdd: pointsGainedByPurchase,
        pointsToPay: payWithPoints ? loggedUser.points : 0,
        orderTax: tax,
        moneyToPay: payWithPoints ? total - loggedUser.points / 1000 : total,
      };
      placeOrder(orderRequest);
      setValMessage('Order was successfully placed');

    }
  };
  const handleAddPaymentSubmit = (event) => {
    event.preventDefault();
    const storedCart = window.localStorage.getItem('cart');
    const storedCartItems = storedCart ? JSON.parse(storedCart) : [];
    const addPaymentRequest = {
      cardHolderFirstName: fname,
      cardHolderLastName: lname,
      cardNumber,
      cvv: securityCode,
      expiryDate: expire,
      userEmail: loggedUser.email,
    };
    addPaymentMethod(addPaymentRequest);
    dispatch(setFname(''));
    dispatch(setLname(''));
    dispatch(setSecurityCode(''));
    dispatch(setCardNumber(''));
    dispatch(setExpire(''));
    setAddPayment(false);
  };

  const inputFieldStyle =
    'input-field h-[35px] text-[.95rem] w-full rounded-[0] font-semibold';
  const inputLabelStyle =
    'input-label text-[.85rem] m-0  text-black font-semibold';
  const button = 'button p-[.5rem] m-[.15rem] rounded-[0] font-semibold';
  return (
    <div
      className="bg-black bg-opacity-80  py-10  mt-44 md:mt-36 l:w-full xl:mt-48 xl:h-[calc(100vh-192px)] 
   w-full flex justify-center items-center "
    >
      <div className="flex flex-col-reverse  lg:w-full xl:flex xl:flex-row justify-center items-center h-full xl:w-[75%]">
        <div className="w-full lg:w-3/4 px-10 py-10">
          <div className="flex justify-between border-b pb-8">
            <h1 className="font-semibold text-2xl text-white">Shopping Cart</h1>
          </div>
          <div className=" h-[calc(100vh-300px)] overflow-y-auto">
            <CartProductList checkout={true} products={location.state.products} />
          </div>
        </div>
        <div
          id="summary"
          className="w-full lg:w-3/4 px-8 py-10 bg-gray-100 h-full"
        >
          {valMessage === 'Please select a pickup location' ||
          valMessage === 'Please select a payment method' ? (
            valMessage ? (
              <div
                style={{
                  backgroundColor: 'rgba(255, 0, 0, 0.3)',
                  color: 'rgba(255, 54, 54, 0.98)',
                  fontWeight: 'normal',

                }}
              >
                {valMessage}
              </div>
            ) : (
              <></>
            )
          ) : valMessage ? (
            <div
              style={{
                backgroundColor: 'rgba(43, 255, 0, 0.37)',
                color: 'green',
                fontWeight: 'bold',
              }}
            >
              {valMessage}
            </div>
          ) : (
            <></>
          )}
          <h1 className="font-semibold text-2xl border-b pb-8">
            Order Summary
          </h1>
          <div className="flex justify-between mt-10 mb-5">
            <span className="font-semibold text-sm uppercase">
              {cartItems.length} items
            </span>
            <span className="font-semibold text-sm">
              ${subTotal.toFixed(2)}
            </span>
          </div>
          <div className="flex w-full justify-between">
            <label className="font-medium inline-block mb-3 text-sm uppercase">
              Shipping
            </label>
            <span className="font-semibold">Standard shipping - FREE</span>
          </div>
          <div>
            <div>
              <div className="flex justify-between items-center font-semibold">
                <h1>Select payment method:</h1>
                <div
                  className="text-gray-500"
                  onClick={() => setAddPayment(!addPayment)}
                >
                  Add a payment method
                </div>
              </div>

              <div>
                {addPayment && (
                  <div className="flex justify-center flex-col items-center w-full">
                    <h3 className="text-xl bg-black text-white p-2 w-full">
                      Add payment method
                    </h3>
                    <form onSubmit={handleAddPaymentSubmit} className="w-full">
                      <div className="flex-row">
                        <Input
                          setState={setFname}
                          state={fname}
                          val={fnameVal}
                          valMes={fnameValMes}
                          htmlFor="fname"
                          fieldClassName={inputFieldStyle}
                          labelClassName={inputLabelStyle}
                          labelContent="First Name: "
                          required={true}
                          type="text"
                        />
                        <Input
                          setState={setLname}
                          state={lname}
                          val={lnameVal}
                          valMes={lnameValMes}
                          htmlFor="lname"
                          fieldClassName={inputFieldStyle}
                          labelClassName={inputLabelStyle}
                          labelContent="Last Name: "
                          required={true}
                          type="text"
                        />
                      </div>
                      <Input
                        setState={setCardNumber}
                        state={cardNumber}
                        val={cardNumberVal}
                        valMes={cardNumberValMes}
                        htmlFor="card-number"
                        fieldClassName={inputFieldStyle}
                        labelClassName={inputLabelStyle}
                        labelContent="Card Number: "
                        required={true}
                        type="text"
                      />
                      <div className="flex-row">
                        <Input
                          setState={setSecurityCode}
                          state={securityCode}
                          val={securityCodeVal}
                          valMes={securityCodeValMes}
                          htmlFor="security-code"
                          fieldClassName={inputFieldStyle}
                          labelClassName={inputLabelStyle}
                          labelContent="Security Code: "
                          required={true}
                          type="text"
                        />
                        <Input
                          setState={setExpire}
                          state={expire}
                          val={expireVal}
                          valMes={expireValMes}
                          htmlFor="expire"
                          fieldClassName={inputFieldStyle}
                          labelClassName={inputLabelStyle}
                          labelContent="Expiration Date: "
                          required={true}
                          type="text"
                        />
                      </div>
                      <div className="flex w-full justify-end">
                        <button className={button}>Submit</button>
                        <button
                          className={button}
                          onClick={() => setAddPayment(false)}
                        >
                          Cancel
                        </button>
                      </div>
                    </form>
                  </div>
                )}
              </div>
              {cardsInfo && cardsInfo.length > 0 && (
                <select
                  className="w-full bg-gray-400 p-2 mt-2"
                  onChange={(e) => {
                    setPaymentCard(e.target.value);
                  }}
                >
                  {renderedCards}
                </select>
              )}
              {loggedUser && loggedUser.points > 0 ? (
                <div
                  onClick={() => setPayWithPoints(!payWithPoints)}
                  className="checkout-payment-method"
                >
                  <div>Pay with points? {loggedUser.points}</div>
                  {payWithPoints ? (
                    <div className="checkout-payment-method-layer"></div>
                  ) : (
                    <></>
                  )}
                </div>
              ) : (
                <></>
              )}
            </div>
          </div>
          <div className="border-t mt-8">
            <div className="flex font-semibold justify-between py-6 text-sm uppercase">
              <span>Total cost</span>
              <span>${total.toFixed(2)}</span>
            </div>
            <button className="button w-full m-0" onClick={handlePlaceOrder}>
              Place order
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
