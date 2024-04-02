import { useEffect, useState } from "react";
import logo from '../statics/logo.png'
import { useNavigate } from 'react-router-dom'
import useCartContext from '../hooks/useCartContext.js'
export default function Product({ product }) {
    const [images, setImages] = useState([]);
    const { addToCart } = useCartContext();
    const navigate = useNavigate()
    const handleCardClick = () => {
        navigate('/details/' + product.productId)
    }
    useEffect(() => {
        if (product) {
            const renderedImages = product.imageList.map((image, key) => {
                return <img className="product-image" src={'data:image/jpeg;base64,' + image} key={key} width="100px" height="100px" />
            })
            setImages(renderedImages)
        }
    }, [])
    const handleAddToCart = (product) => {
        const addedProduct = { ...product, quantity: 1 }
        addToCart(addedProduct)
    }
    console.log()
    if (product) {
        return <div className="card-container">
            <div onClick={handleCardClick}>
                <div className="card-image-container">
                    {images[0]}
                    {product.brand === 'Arz' ? <img style={{ position: 'absolute' }} src={logo} width="100px" height="100px" /> : ''}
                </div>
                <div className="card-ccd">
                    <div className="product-name">
                        {product.productName}
                    </div>
                    <div className="price-container">
                        <h4 className="product-price">
                            ${product.price}
                        </h4>
                        <h4 className="product-current-price">
                            ${product.currentPrice}
                        </h4>
                    </div>
                    <div className="product-weight">
                        {product.size + 'g'}
                    </div>
                    {
                        product.brand === 'Arz' ?
                            <div className="flex-row branding-container">
                                <div className="product-brand">
                                    {product.brand}
                                </div>
                                <div className="product-vendor">
                                    {product.vendor}
                                </div>
                            </div> :
                            <div className="flex-row branding-container">
                                <div className="product-vendor">
                                    {product.vendor}
                                </div>
                            </div>
                    }
                </div>
            </div>
            <div>
                <button className="button" onClick={() => handleAddToCart(product)}>
                    add to cart
                </button>
            </div>

        </div>
    }
}