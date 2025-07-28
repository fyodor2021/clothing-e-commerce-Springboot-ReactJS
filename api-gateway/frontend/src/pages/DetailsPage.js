import { useEffect, useState } from 'react';
import {
  FaCar,
  FaChevronCircleLeft,
  FaChevronCircleRight,
  FaStore,
} from 'react-icons/fa';
import { MdAssignmentReturn } from 'react-icons/md';
import { useParams } from 'react-router-dom';
import useCartContext from '../hooks/useCartContext';
import { useFetchProductDetailsQuery } from '../store';
import { toast, ToastContainer } from 'react-toastify';

export default function DetailsPage() {
  const { productId } = useParams();
  const [slideNumber, setSlideNumber] = useState(0);
  const { data: product, isLoading } = useFetchProductDetailsQuery(productId);

  const { handleAddToCart } = useCartContext();
  useEffect(() => {
    const el = document.getElementById('slide-' + slideNumber);
    if (el) {
      el.scrollIntoView({ block: 'center' });
    }
  }, [slideNumber]);
  if (product) {
    const renderedImages = product.imageList.map((image, key) => {
      if (key === 0) {
        return <img id={'slide-' + key} src={image} key={key} />;
      } else {
        return <img id={'slide-' + key} src={image} key={key} />;
      }
    });
    const updateSlideNumber = (direction) => {
      switch (direction) {
        case 'right':
          if (slideNumber < renderedImages.length - 1) {
            setSlideNumber((prev) => prev + 1);
          }
          break;
        case 'left':
          if (slideNumber > 0) {
            setSlideNumber((prev) => prev - 1);
          }
          break;
        default:
      }
    };

    const handleRightClick = (event) => {
      updateSlideNumber('right');
    };

    const handleLeftClick = (event) => {
      updateSlideNumber('left');
    };
    const addToCartClick = (size) => {
      handleAddToCart(product.productId, size);
      toast('Item added to cart');
    };

    return (
      <div className="bg-black bg-opacity-80  py-10  mt-44 md:mt-36 xl:mt-48 w-full xl:h-[calc(100vh-192px)] w-full flex justify-center items-center ">
        <div className="flex flex-col-reverse xl:flex xl:flex-row justify-center items-center h-full md:w-[75%]">
          <div className="h-full w-3/4 relative">
            <div className="details-slider w-full h-full rounded-none ">
              <button
                className="scroll-button left-scroll-button"
                onClick={handleLeftClick}
              >
                <FaChevronCircleLeft />
              </button>
              {renderedImages}
              <button
                className="scroll-button right-scroll-button"
                onClick={handleRightClick}
              >
                <FaChevronCircleRight />
              </button>
            </div>
          </div>
          <div className="w-full xl:w-3/4 px-8 py-10 bg-gray-100 h-full">
            <div className="h-16 w-16 m-3 border border-black-500 text-black-500 flex justify-center items-center rounded">
              In Store
            </div>
            <div>
              <h2 className="text-5xl m-4">{product?.productName}</h2>
            </div>
            <div>
              <h6 className="text-xl m-4">{product?.description}</h6>
            </div>
            <div className="m-4">
              <h4 className="text-6xl text-red-900">
                $<span>{product.currentPrice}</span>
              </h4>
            </div>
            <div className="m-4">
              <h4>
                Price in points?{' '}
                <span className="text-green-900">
                  {product.currentPrice * 1000}
                </span>{' '}
                JoseDor-points
              </h4>
            </div>
            <div
              className="mb-5 min-w-60 product-action-style"
              style={{ width: '12.7vw' }}
            >
              <div>
                <button onClick={() => addToCartClick('s')}>S</button>
              </div>
              <div>
                <button onClick={() => addToCartClick('m')}>M</button>
              </div>
              <div>
                <button onClick={() => addToCartClick('l')}>L</button>
              </div>
              <div>
                <button onClick={() => addToCartClick('xl')}>XL</button>
              </div>
            </div>
            <div className="flex items-center m-2 m-2 text-1xl">
              <FaCar className="mr-5" />
              Pickup, from 703 Orange Avenue Saint-Hippolyte, QC J8A 0X4
            </div>
            <div className="flex items-center  m-2 text-1xl">
              <FaStore className="mr-5" />
              Made By {product.brand} sold by JoseDor.
            </div>
            <div className="flex items-center m-2 text-1xl">
              <MdAssignmentReturn className="mr-5" />
              Learn more about our return policy.
            </div>
          </div>
        </div>
        <ToastContainer
          position="top-right"
          autoClose={2000}
          hideProgressBar
          newestOnTop={false}
          closeOnClick={false}
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
          theme="dark"
        />
      </div>
    );
  }
}
