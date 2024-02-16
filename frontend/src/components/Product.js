import { useEffect, useState } from "react";
import logo from '../statics/logo.png'
import {useNavigate} from 'react-router-dom'
export default function Product({ product}) {
    const [images, setImages] = useState([]);
    const navigate = useNavigate()
    const handleCardClick = () => {
        navigate('/details/'+product.productId)
    }

    useEffect(() => {
        const renderedImages = product.imageList.map((image, key) => {
            return <img className="product-image" src={'data:image/jpeg;base64,' + image} key={key} width="100px" height="100px" />
        })
        setImages(renderedImages)
    }, [])

    return <div className="card-container" onClick={handleCardClick}>
        <div className="card-image-container">
            {images[0]}
            {product.brand === 'Arz' ? <img style={{ position: 'absolute' }} src={logo} width="100px" height="100px" /> : ''}
        </div>
        <div className="card-content">

            <h4>
                ${product.currentPrice}
            </h4>
            <div>
                {product.productName + ',' + product.description + ',' + product.size + 'g'}
            </div>
            <div>
                {product.vendor}
            </div>
        </div>
        <div>
            <button className="button">
                add to cart
            </button>
        </div>

    </div>
}