import useCartContext from '../hooks/useCartContext';

export default function CartProduct({ product, checkout }) {
  const { handleAddToCart } = useCartContext();
  const productCountDecrement = (product) => {
    console.log('im hrer')
    handleAddToCart(product.productId, product.size, -1);
  };
  const productCountIncrement = (product) => {
    handleAddToCart(product.productId, product.size, 1);
  };
  const incDecRequest = {};
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
                  onClick={() => productCountDecrement(product)}
                  style={{ cursor: 'pointer' }}
                >
                  -
                </p>
                <p>{product.quantity}</p>
                <p
                  onClick={() => productCountIncrement(product)}
                  style={{ cursor: 'pointer' }}
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
