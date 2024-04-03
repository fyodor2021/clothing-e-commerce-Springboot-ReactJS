import useOrderContext from "../hooks/useOrderContext"
import { useEffect, useState } from "react";
import axios from 'axios'
import AuthContext from "../context/AuthContext";
import useAuthContext from "../hooks/useAuthContext";
import { FaLeaf } from "react-icons/fa";
import Footer from "../components/Footer";
import { useNavigate } from "react-router-dom";
export default function OrdersPage() {
    const { orders, cancelOrder } = useOrderContext();
    const navigate = useNavigate();
    let renderedOrders;
    console.log(orders)
    const handleCancelOrder = (orderId) => {
        cancelOrder(orderId)
    }
    const handleGoHome = () => {
        navigate('/')
    }
    console.log(orders)
    if (orders && orders.length > 0) {
        orders.sort((a) => {
            if (a.status == 'placed') {
                return -1
            } else {
                return 1
            }
            return 0
        })
        renderedOrders = orders.map((order, key) => {
            const renderedProducts = order.products.map((product, key) => {
                return <div key={product.productId}>
                    <div className="flex-row order-product-wrapper">
                        <div>
                            <img className="order-product-image" src={'data:image/jpeg;base64,' + product.imageList[0]} />
                        </div>
                        <div className="flex-col">
                            <div>
                                <div>{product.productName}</div>
                            </div>
                            <div>
                                <div>{product.description}</div>
                            </div>

                        </div>
                    </div>
                </div>
            })
            let total = order.orderTotal.toFixed(2);
            let subTotal = ((order.orderTotal.toFixed(2)) - (order.orderTotal * .13)).toFixed(2);
            let tax = (total - subTotal).toFixed(2);
            return <div className="order-container" key={key}>
                <div className="order-wrapper">
                    <div>
                        <div className="order-header">
                            <div >
                                <div>Order Placed</div>
                                <div>{order.datePlaced}</div>
                            </div>
                            <div>
                                <div>Order ID:</div>
                                <div>{order.orderId}</div>
                            </div>
                            <div>
                                <div>Order#</div>
                                <div>{order.orderNumber}</div>
                            </div>
                            <div>
                                <div>status</div>
                                <div style={{ fontSize: '1rem' }}>
                                    {order.status === "placed" ?
                                        <span style={{ color: 'green' }}>Active</span> :
                                        order.status === "cancelled" ?
                                            <span style={{ color: 'red' }}>{order.status}</span> : <span>{order.status}</span>}
                                </div>
                            </div>
                        </div>
                        <div>{renderedProducts}</div>
                    </div>
                    <div>
                        <div className="order-summary order-history-summary">
                            <div>
                                <div style={{ color: 'green' }}>
                                    <p>Total paid in points:</p>
                                    <p>{order.totalPaidInPoints} Points</p>
                                </div>
                                <div>
                                    <p>Total paid on card:</p>
                                    <p>${order.totalPaidOnCard.toFixed(2)}</p>
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
                                        {subTotal}
                                    </p>
                                </div>
                                <div>
                                    <p>
                                        Estimated GST/HST:
                                    </p>
                                    <p>
                                        {tax}
                                    </p>
                                </div>
                            </div>
                            <div>
                                <h1>Order Total: </h1>
                                <h1>{total} </h1>
                            </div >
                            {order.status === "placed" ?
                                <div width="100%">
                                    <button onClick={() => handleCancelOrder(order.orderId)} className="button order-cancel-button">
                                        Cancel order
                                    </button>
                                </div> : <></>}

                        </div>
                    </div>
                </div>

            </div>
        })
        return <div>
            <div>{renderedOrders}</div>
            <Footer />
        </div>

    } else {
        return<div>
            <div className="no-active-orders">
                <div>
                    No active orders can be found
                </div>
                <div onClick={handleGoHome}>
                    Continue shopping
                </div>
            </div>
            <Footer />
        </div>


    }


}