import { useLocation, useParams } from "react-router-dom"
import { useState, useEffect } from 'react'
import useProductContext from "../hooks/useProductContext";
import { render } from "@testing-library/react";
export default function DetailsPage() {
    const { productId } = useParams();
    const { fetchProductDetails, product } = useProductContext();
    const [slideNumber, setSlideNumber] = useState(0)
    useEffect(() => {
        fetchProductDetails(productId)
    }, [])
    if (product) {
        const renderedImages = product.imageList.map((image, key) => {
            if (key === 0) {
                return <img id={'slide-' + key} src={'data:image/jpeg;base64,' + image} key={key} />
            } else {
                return <img id={'slide-' + key} src={'data:image/jpeg;base64,' + image} key={key} />
            }
        })
        const handleltClick = () => {
            if (slideNumber < renderedImages.length - 1) {

                setSlideNumber(slideNumber + 1)
            }
        }
        const handlegtClick = () => {
            if (slideNumber > 0) {
                setSlideNumber(slideNumber - 1)
            }
        }
        return <div>
            <div className="details-slider-container">
                <div className="details-slider-wrapper">
                    <button className='scroll-button left-scroll-button' onClick={handlegtClick}>
                        <a href={'#slide-' + slideNumber}>&lt;</a>
                    </button>
                    <div className="details-slider">
                        {renderedImages}
                    </div>

                    <button className='scroll-button right-scroll-button' onClick={handleltClick}>
                        <a href={'#slide-' + slideNumber}>&gt;</a>
                    </button>

                </div>
            </div>
        </div>
    }

}