import { useCookies } from "react-cookie";
import {
  useProductCountDecrementMutation,
  useProductCountIncrementMutation,
} from "../store/apis/cartApi";
export default function CartProduct({ product, checkout }) {
  const [cookie] = useCookies(["SESSION"]);
  const [productCountDecrement] = useProductCountDecrementMutation();
  const [productCountIncrement] = useProductCountIncrementMutation();

  const incDecRequest = {
    productId: product.productId,
    productSize: product.size,
    headerValue: window.localStorage.getItem(
      process.env.REACT_APP_AUTH_TOKEN_LOCAL
    )
      ? window.localStorage.getItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL)
      : cookie["SESSION"],
  };
  return (
    <div className="md:flex items-center my-3 border-t border-gray-200">
      <div className="w-1/4">
        <img
          src={product.imageUrl}
          className="w-full h-full object-center object-cover"
        />
      </div>
      <div className="md:pl-3 md:w-3/4">
        <p className="text-xs leading-3 text-gray-400 md:pt-0 pt-4">
          {product.upcCode}
        </p>
        <div className="flex items-center justify-between w-full pt-1">
          <p className="text-base font-black leading-none text-gray-400">
            {product.productName}
          </p>
          {!checkout ? (
            <div className="flex flex-col justify-center items-center px-8">
              <div className="qty-field">
                <p
                  onClick={() => productCountDecrement(incDecRequest)}
                  style={{ cursor: "pointer" }}
                >
                  -
                </p>
                <p>{product.quantity}</p>
                <p
                  onClick={() => productCountIncrement(incDecRequest)}
                  style={{ cursor: "pointer" }}
                >
                  +
                </p>
              </div>
              <div>
                <div className="size-display-cart-product">
                  {product.size.toUpperCase()}
                </div>
              </div>
            </div>
          ) : (
            <>
              <div className="flex">
                <div className="size-display-cart-product mr-1">
                <p>{product.quantity}</p>
                </div>
                <div className="size-display-cart-product">
                  {product.size.toUpperCase()}
                </div>
              </div>
            </>
          )}
        </div>

        <p className="w-96 text-xs leading-3 text-gray-200">
          Description: {product.description}
        </p>
        <div className="flex items-center justify-between pt-5 pr-6">
          <p className="text-base font-black leading-none text-gray-500">
            ${product.price}
          </p>
        </div>
      </div>
    </div>
  );

}
