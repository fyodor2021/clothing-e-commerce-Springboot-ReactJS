import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import useCartContext from "../hooks/useCartContext";
import { toast, ToastContainer } from "react-toastify";

export default function Product({ product, suggestion = false}) {
  const [images, setImages] = useState([]);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const handleCardClick = () => {
    navigate("/product/details/" + product.productId);
  };
  const {handleAddToCart} = useCartContext();
  useEffect(() => {
    if (product) {
      const renderedImages = product.imageList.map((image, key) => {
        return <img className="product-image" src={image} key={key} />;
      });
      setImages(renderedImages);
    }
  }, []);
  const addToCartClick = (size) => {
    handleAddToCart(product.productId,size)
    toast('item added to cart')
  }
  if (product) {
    return (
      <div className="card-container">
        <div className={"product-img "}>
          <a href="#" data-abc="true" onClick={handleCardClick}>
            {images[0]}
          </a>
          <span className="text-center">
            <i className="fa fa-rupee"></i> ${product.currentPrice}
          </span>
          {!suggestion && <div className="product-action">
            <div className="product-action-style ">
              <div onClick={() => addToCartClick("s")}>
                <button >S</button>
              </div>
              <div onClick={() => addToCartClick("m")}>
                <button >M</button>
              </div>
              <div  onClick={() => addToCartClick("l")}>
                <button>L</button>
              </div>
              <div onClick={() => addToCartClick("xl")}>
                <button >
                  XL
                </button>
              </div>
            </div>
          </div>}
        </div>

      </div>
    );
  }
}
