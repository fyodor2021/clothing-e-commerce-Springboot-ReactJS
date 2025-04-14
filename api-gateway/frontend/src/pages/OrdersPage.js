import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { useGetLoggedUserOrdersQuery } from "../store/index.js";

import OrderSkeleton from "../components/OrderSkeleton.js";
import CartPage from "./CartPage.js";
import ProductSuggestion from "../components/ProductSuggestion.js";
export default function OrdersPage() {
  const navigate = useNavigate();
  const loggedUser = useSelector((state) => {
    return state.user;
  });
  let isFetching = false;
  const { data: orders, isLoading } = useGetLoggedUserOrdersQuery(loggedUser);
  const { value: token } = useSelector((state) => {
    return state.token;
  });
  let renderedOrders;
  useEffect(() => {
    if (!token) {
      navigate("/login");
    }
  }, []);

  const handleGoHome = () => {
    navigate("/");
  };
  let sortedData;

  if (orders && orders.length > 0) {
    sortedData = [...orders].sort((a) => {
      if (a.status == "placed") {
        return -1;
      } else {
        return 1;
      }
    });
    renderedOrders = sortedData.map((order, key) => {
      const renderedProducts = order.products.map((product, index) => {
        console.log(product);
        return (
          <div
            key={index}
            className="flex-row order-product-wrapper bg-black bg-opacity-30 m-1"
          >
            <div>
              <img className="order-product-image" src={product.imageUrl} />
            </div>
            <div className="flex-col">
              <div>
                <div>{product.productName}</div>
              </div>
              <div>
                <div>{product.description}</div>
              </div>
              <div>
                <div className="text-2xl text-gray-600">
                  {product.size.toUpperCase()} <span>x</span> {product.quantity}
                </div>
              </div>
            </div>
          </div>
        );
      });
      console.log(order);
      let total = order.orderTotal.toFixed(2);
      let subTotal = total - order.orderTax;
      const orderDetailItem = "p-1 words-break";
      return (
        <div
          className="bg-white border border-gray-200 flex flex-col mb-1"
          key={key}
        >
          <div>
            <div className="flex w-full justify-between p-2 bg-[#e9e9e9] flex-wrap">
              <div className="flex w-[50%] flex-wrap justify-around">
                <div className={orderDetailItem}>
                  <div>Order Placed</div>
                  <div>{order.datePlaced}</div>
                </div>
                <div className={orderDetailItem}>
                  <div>Order ID:</div>
                  <div>{order.orderId}</div>
                </div>
              </div>
              <div className="flex w-[50%] flex-wrap justify-around">
                <div className={orderDetailItem}>
                  <div>Order#</div>
                  <div>{order.orderNumber}</div>
                </div>
                <div className={orderDetailItem}>
                  <div>status</div>
                  <div style={{ fontSize: "1rem" }}>
                    {order.status === "placed" ? (
                      <span style={{ color: "green" }}>Active</span>
                    ) : order.status === "cancelled" ? (
                      <span style={{ color: "red" }}>{order.status}</span>
                    ) : (
                      <span>{order.status}</span>
                    )}
                  </div>
                </div>
              </div>
            </div>
            <div className="overflow-y-scroll h-72 p-2 border-t border-[#e9e9e9]">
              {renderedProducts}
            </div>
          </div>
          <div className="p-3">
            <div className="order-summary order-history-summary w-full flex justify-end border-t border-[#e9e9e9]">
              <button
                onClick={() => navigate("/order/details/" + order.orderId)}
                className="button bg-white outline-1 text-black shadow-none rounded-[0] active:bg-gray-200 "
              >
                View Order
              </button>
            </div>
          </div>
        </div>
      );
    });
    return (
      <div className="mt-44 md:mt-36 xl:mt-48 w-full  h-screen bg-black bg-opacity-80 font-semibold flex flex-col justify-center items-center xl:h-[calc(100vh-192px)]">
        {isFetching ? (
          <div className=" overflow-y-hidden xl:h-[calc(100vh-192px)] mt-10 w-3/4">
            <OrderSkeleton />
            <OrderSkeleton />
            <OrderSkeleton />
            <OrderSkeleton />
            <OrderSkeleton />
          </div>
        ) : (
          <div className=" overflow-y-auto xl:h-[calc(100vh-192px)] mt-10 w-3/4">
            {renderedOrders}
          </div>
        )}
      </div>
    );
  } else {
    return (
      <div className="mt-44 md:mt-36 xl:mt-48 w-full h-screen bg-black bg-opacity-80 overflow-x-hidden font-semibold flex justify-center items-center xl:h-[calc(100vh-192px)]">
        <div className="xl:w-[75%] h-full overflow-x-hidden transform translate-x-0 transition ease-in-out duration-700">
          <div
            className="flex  md:flex-row xl:flex-row justify-end flex-col-reverse h-full  py-10 "
            id="cart"
          >
            <div className=" w-full bg-gray-100 h-full px-5 py-5">
              <p className="text-2xl font-black leading-10  w-full text-gray-500 ">
                  Looks like your orders took a rain check!
              </p>
              <ProductSuggestion />
            </div>
          </div>
        </div>
      </div>
    );
  }
}
