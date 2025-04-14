import {useEffect, useRef} from "react";
import Footer from "../components/Footer";
import {useNavigate} from "react-router-dom";
import CartProductList from "../components/CartProductList";
import {
    setCartItems,
    useFetchProductsQuery,
    useLazyFetchCartProductsQuery,
} from "../store/index";
import {useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import ProductList from "../components/ProductList";
import ProductSuggestion from "../components/ProductSuggestion";

export default function CartPage() {
    const [fetchCartProducts, fetchCartProductsResult] =
        useLazyFetchCartProductsQuery();
    const [subTotal, setSubTotal] = useState(0.0);
    const [tax, setTax] = useState();
    const [products, setProducts] = useState();
    const dispatch = useDispatch();
    const [pointsGainedByPurchase, setPointsGainedByPurchase] = useState(0);
    const {value: token} = useSelector((state) => {
        return state.token;
    });
    const {cartItems} = useSelector((state) => {
        return state.cartSlice;
    });
    const loggedUser = useSelector((state) => state.user);
    const navigate = useNavigate();

    useEffect(() => {
        const storedCart = window.localStorage.getItem("cart");
        const storedCartItems = storedCart ? JSON.parse(storedCart) : [];

        const storedCartItemsCount = storedCartItems.length > 0 && storedCartItems.reduce((acc, item) => {
            return acc + item.quantity;
        }, 0);

        const cartItemsCount = cartItems.length > 0 && cartItems.reduce((acc, item) => {
            return acc + item.quantity;
        }, 0);

        if (storedCartItemsCount !== cartItemsCount) {
            const cartProductReq = {
                cartProducts: storedCartItems,
                userEmail: loggedUser ? loggedUser.email : "",
            };
            fetchCartProducts(cartProductReq);
        }

    }, []);

    useEffect(() => {
        const data = [];
        for (let item of cartItems) {
            data.push(item)
        }
        if (fetchCartProductsResult.status === 'fulfilled' && fetchCartProductsResult.data) {
            if (fetchCartProductsResult.data) {
                for (let [dataItem, index] of data.entries()) {
                    for (let item of fetchCartProductsResult.data) {
                        if (dataItem.productId === item.productId && dataItem.size === item.size) {
                            let temp = dataItem
                            console.log(temp)
                            temp.quantity += item.quantity
                            console.log(temp)

                            data.slice(index, 1)
                            data.push(temp)
                        }
                    }
                }
            }
        }
        setProducts(data)
        if (data) {

            const calSubTotal = data.reduce((acc, product) => {
                return acc + product.price * product.quantity
            }, 0);

            setSubTotal(calSubTotal);

            const calPointsGainedByPurchase = data.reduce((acc, product) => {
                return acc + parseInt(product.points) * product.quantity
            }, 0);

            setPointsGainedByPurchase(calPointsGainedByPurchase);

            const calTax = data.reduce((acc, item) => {
                return acc + item.price * 0.13 * item.quantity;
            }, 0);

            setTax(calTax);
        }
    }, [fetchCartProductsResult.status]);


    const handleCheckout = () => {
        if (token) {
            navigate("/checkout");
        } else {
            navigate("/login");
        }
    };

    const handleGoHome = () => {
        navigate("/");
    };
    return (
        <div>
            {products && subTotal > 0 ? (
                <div
                    className="mt-44 md:mt-36 xl:mt-48 w-full h-screen bg-black bg-opacity-80 overflow-x-hidden font-semibold flex justify-center items-center xl:h-[calc(100vh-192px)]">
                    <div
                        className="xl:w-[75%] h-full overflow-x-hidden transform translate-x-0 transition ease-in-out duration-700">
                        <div
                            className="flex  md:flex-row xl:flex-row justify-end flex-col-reverse h-full  py-10 "
                            id="cart"
                        >
                            <div className="flex flex-col">
                                <p className="text-5xl font-black leading-10  p-3 w-full text-white">
                                    Bag
                                </p>
                                <div className=" xl:h-[calc(100vh-300px)] overflow-y-auto">
                                    <CartProductList checkout={false} products={products}/>
                                </div>
                            </div>
                            <div className=" md:w-1/2  w-full bg-gray-100 h-full">
                                <div className="flex flex-col px-14 py-20 justify-between h-full ">
                                    <div>
                                        <p className="text-4xl font-black leading-9 ">Summary</p>
                                        <div className="flex items-center justify-between pt-16">
                                            <p className="text-base leading-none ">Subtotal</p>
                                            <p className="text-base leading-none ">
                                                ${subTotal ? subTotal.toFixed(2) : ""}
                                            </p>
                                        </div>
                                        <div className="flex items-center justify-between pt-5">
                                            <p className="text-base leading-none ">Shipping</p>
                                            <p className="text-base leading-none ">FREE</p>
                                        </div>
                                        <div className="flex items-center justify-between pt-5">
                                            <p className="text-base leading-none ">Tax</p>
                                            <p className="text-base leading-none ">
                                                ${tax ? tax.toFixed(2) : ""}
                                            </p>
                                        </div>
                                    </div>
                                    <div>
                                        <div className="flex items-center pb-6 justify-between lg:pt-5 pt-20">
                                            <p className="text-2xl leading-normal ">Total</p>
                                            <p className="text-2xl font-bold leading-normal text-right ">
                                                ${subTotal && tax ? (subTotal + tax).toFixed(2) : ""}
                                            </p>
                                        </div>
                                        <button
                                            onClick={handleCheckout}
                                            className="button w-full m-0"
                                        >
                                            Checkout
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            ) : (
                <div
                    className="mt-44 md:mt-36 xl:mt-48 w-full h-screen bg-black bg-opacity-80 overflow-x-hidden font-semibold flex justify-center items-center xl:h-[calc(100vh-192px)]">
                    <div
                        className="xl:w-[75%] h-full overflow-x-hidden transform translate-x-0 transition ease-in-out duration-700">
                        <div
                            className="flex  md:flex-row xl:flex-row justify-end flex-col-reverse h-full  py-10 "
                            id="cart"
                        >
                            <div className=" w-full bg-gray-100 h-full px-5 py-5">
                                <p className="text-2xl font-black leading-10  w-full text-gray-500 ">
                                    Oops, your cart’s on a shopping diet
                                </p>
                                <ProductSuggestion/>
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}
