import { useEffect, useState, useSyncExternalStore } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import {
  useCancelOrderMutation,
  useLazyGetOrderByOrderIdQuery,
} from '../store';
import { render } from '@testing-library/react';
import { useSelector } from 'react-redux';
import { AiOutlineLoading } from 'react-icons/ai';
import { IoMailSharp } from 'react-icons/io5';
export default function OrderDetailsPage() {
  const { orderId } = useParams();
  const [getOrderDetails, orderDetailsResult] = useLazyGetOrderByOrderIdQuery();
  const [
    cancelOrder,
    { isSuccess: cancelationSuccess, isLoading: cancelLoading },
  ] = useCancelOrderMutation();
  const [orderDetails, setOrderDetails] = useState();
  const navigate = useNavigate();
  const loggedUser = useSelector((state) => {
    return state.user;
  });
  let isFetching = false;
  const [renderedProducts, setRenderedProducts] = useState();
  const handleCancelOrder = (orderId) => {
    cancelOrder(orderId);
  };
  useEffect(() => {
    if (cancelationSuccess) {
      navigate(0);
    }
  }, [cancelationSuccess]);
  useEffect(() => {
    getOrderDetails(orderId);
  }, []);
  useEffect(() => {
    if (orderDetailsResult.isSuccess && orderDetailsResult.data) {
      console.log(orderDetailsResult.data);
      setOrderDetails(orderDetailsResult.data);
      setRenderedProducts(
        orderDetailsResult.data.products.map((product, index) => {
          return (
            <div
              key={index}
              className="flex-row order-product-wrapper  bg-black bg-opacity-30 m-1"
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
                  <div className="text-2xl text-red-900">
                    {product.size.toUpperCase()} <span>x</span>{' '}
                    {product.quantity}
                  </div>
                </div>
              </div>
            </div>
          );
        })
      );
    }
  }, [orderDetailsResult]);
  return (
    orderDetails && (
      <div className="mt-44 md:mt-36 xl:mt-48  py-10   w-full bg-black bg-opacity-80 font-semibold flex justify-center items-center xl:h-[calc(100vh-192px)]">
        <div className=" flex flex-col xl:flex-row jusitfy-center items-stretch w-3/4 xl:space-x-8 space-y-4 md:space-y-6 xl:space-y-0 ">
          <div className="flex flex-col justify-start items-start w-full space-y-4 md:space-y-6 xl:space-y-8">
            <div className="flex flex-col justify-start items-start dark:bg-gray-800 bg-gray-50 px-4 py-4 md:py-6  w-full">
              <div className="flex justify-between w-full items-start">
                <div>
                  <p className="text-lg md:text-xl dark:text-white font-semibold leading-6 xl:leading-5 text-gray-800">
                    Order products
                  </p>
                  <div style={{ fontSize: '1rem' }}>
                    {orderDetails.status === 'placed' ? (
                      <span style={{ color: 'green' }}>Active</span>
                    ) : orderDetails.status === 'cancelled' ? (
                      <span style={{ color: 'red' }}>
                        {orderDetails.status}
                      </span>
                    ) : (
                      <span>{orderDetails.status}</span>
                    )}
                  </div>
                </div>
                <div className="flex justify-start item-start space-y-2 flex-col">
                  <p className="text-base dark:text-gray-300 font-medium leading-6 text-gray-600">
                    {orderDetails.datePlaced}
                  </p>
                  <h1 className=" dark:text-white text-sm font-semibold text-gray-400">
                    Order #{orderDetails.orderNumber.slice(0, 10)}
                  </h1>
                </div>
              </div>
              <div className="overflow-y-auto h-72  border border-gray-300 w-full">
                {renderedProducts}
              </div>
            </div>
            <div className="flex justify-center flex-col md:flex-row flex-col items-stretch w-full space-y-4 md:space-y-0 md:space-x-6 xl:space-x-8">
              <div className="flex flex-col px-4 py-6 md:p-6 xl:p-8 w-full bg-gray-50 dark:bg-gray-800 space-y-6">
                <h3 className="text-xl dark:text-white font-semibold leading-5 text-gray-800">
                  Summary
                </h3>
                <div className="flex justify-center items-center w-full space-y-4 flex-col border-gray-200 border-b pb-4">
                  <div className="flex justify-between items-center w-full">
                    <p className="text-base dark:text-white leading-4 text-gray-800">
                      Subtotal
                    </p>
                    <p className="text-base dark:text-gray-300 leading-4 text-gray-600">
                      {(
                        orderDetails.status === 'placed' ? orderDetails.orderTotal - orderDetails.orderTax : orderDetails.totalPaidOnCard
                      ).toFixed(2)}
                    </p>
                  </div>
                  <div className="flex justify-between items-center w-full">
                    <p className="text-base dark:text-white leading-4 text-gray-800">
                      Shipping
                    </p>
                    <p className="text-base dark:text-gray-300 leading-4 text-gray-600">
                      FREE
                    </p>
                  </div>
                  <div className="flex justify-between items-center w-full">
                    <p className="text-base dark:text-white leading-4 text-gray-800">
                      Tax
                    </p>
                    <p className="text-base dark:text-gray-300 leading-4 text-gray-600">
                      {orderDetails.orderTax.toFixed(2)}
                    </p>
                  </div>
                  <div className="flex justify-between items-center w-full">
                    <p className="text-base dark:text-white leading-4 text-gray-800">
                      Points paid
                    </p>
                    <p className="text-base dark:text-gray-300 leading-4 text-gray-600">
                      {orderDetails.totalPaidInPoints} Points
                    </p>
                  </div>
                </div>
                <div className="flex justify-between items-center w-full">
                  <p className="text-base dark:text-white leading-4 text-gray-800">
                    Order total
                  </p>
                  <p className="text-base dark:text-gray-300 leading-4 text-gray-600">
                    ${(orderDetails.status === 'placed' ? 
                      orderDetails.totalPaidOnCard: orderDetails.orderTotal - orderDetails.orderTax
                    ).toFixed(2)}
                  </p>
                </div>
              </div>
            </div>
          </div>
          <div className="bg-gray-50 dark:bg-gray-800 w-full xl:w-96 flex justify-between  items-center md:items-start px-4 py-6 md:p-6 xl:p-8 flex-col">
            <h3 className="text-xl dark:text-white font-semibold leading-5 text-gray-800">
              Customer
            </h3>
            <div className="flex flex-col md:flex-row xl:flex-col justify-start items-stretch h-full w-full md:space-x-6 lg:space-x-8 xl:space-x-0">
              <div className="flex flex-col justify-start items-start flex-shrink-0">
                <div className="flex justify-center w-full md:justify-start items-center space-x-4 py-8 border-b border-gray-200">
                  <div className="flex justify-start items-start flex-col space-y-2">
                    <p className="text-base dark:text-white font-semibold leading-4 text-left text-gray-800">
                      {loggedUser && loggedUser.fname}{' '}
                      {loggedUser && loggedUser.lname}
                    </p>
                  </div>
                </div>
                <div className="flex justify-center text-gray-800 dark:text-white md:justify-start items-center space-x-4 py-4 border-b border-gray-200 w-full">
                  <p className="text-sm leading-5 ">
                    {loggedUser && loggedUser.email}
                  </p>
                </div>
              </div>
              <div className="flex justify-between xl:h-full items-stretch w-full flex-col mt-6 md:mt-0">
                <div className="flex justify-center md:justify-start xl:flex-col flex-col md:space-x-6 lg:space-x-8 xl:space-x-0 space-y-4 xl:space-y-12 md:space-y-0 md:flex-row items-center md:items-start">
                  <div className="flex justify-center md:justify-start items-center md:items-start flex-col space-y-4 xl:mt-8">
                    <p className="text-base dark:text-white font-semibold leading-4 text-center md:text-left text-gray-800">
                      Shipping Address
                    </p>
                    <p className="w-48 lg:w-full dark:text-gray-300 xl:w-48 text-center md:text-left text-sm leading-5 text-gray-600">
                      {loggedUser && loggedUser.address}
                    </p>
                  </div>
                  <div className="flex justify-center md:justify-start items-center md:items-start flex-col space-y-4">
                    <p className="text-base dark:text-white font-semibold leading-4 text-center md:text-left text-gray-800">
                      Billing Address
                    </p>
                    <p className="w-48 lg:w-full dark:text-gray-300 xl:w-48 text-center md:text-left text-sm leading-5 text-gray-600">
                      {loggedUser && loggedUser.address}
                    </p>
                  </div>
                </div>
                <div className="flex w-full flex-col justify-center items-center md:justify-start md:items-start">
                  {orderDetails.status === 'placed' ? (
                    <button
                      onClick={() => handleCancelOrder(orderDetails.orderId)}
                      className="button w-full rounded-none shadow-none m-0 bg-white text-black"
                    >
                      {cancelLoading || isFetching ? (
                          <AiOutlineLoading className="animate-spin w-full"/>
                      ) : (
                        'Cancel order'
                      )}
                    </button>
                  ) : (
                    <></>
                  )}
                  <button
                    onClick={() => navigate('/orders')}
                    className="button w-full rounded-none shadow-none m-0 mt-1 bg-white text-black"
                  >
                    My orders
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  );
}
