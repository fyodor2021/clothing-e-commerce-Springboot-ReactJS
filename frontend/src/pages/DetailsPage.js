import { useLocation, useParams } from "react-router-dom"
import { useState, useEffect } from 'react'
import useProductContext from "../hooks/useProductContext";
import { render } from "@testing-library/react";
import Footer from '../components/Footer'
import { FaCar } from "react-icons/fa";
import { FaStore } from "react-icons/fa";
import { MdAssignmentReturn } from "react-icons/md";
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
        const verticalImages = product.imageList.map((image, key) => {
            return <img className='vertical-image' src={'data:image/jpeg;base64,' + image} key={key} />
        })
        const handleltClick = (event) => {
            if (slideNumber < renderedImages.length - 1) {
                setSlideNumber(slideNumber + 1)
            }
        }
        const handlegtClick = (event) => {
            if (slideNumber > 0) {
                setSlideNumber(slideNumber - 1)
            }
        }
        console.log(product)
        return <div className="details-page-content">
            <div className="details-body-container">
                <div className="vertical-image-container">
                    {verticalImages}
                </div>
                <div className="details-slider-container">
                    <div className="details-slider-wrapper">
                        <button className='scroll-button left-scroll-button' onClick={handlegtClick}>
                            <a href={'#slide-' + slideNumber} className="text-4xl">&lt;</a>
                        </button>
                        <div className="details-slider">
                            {renderedImages}
                        </div>
                        <button className='scroll-button right-scroll-button' onClick={handleltClick}>
                            <a href={'#slide-' + slideNumber} className="text-4xl">&gt;</a>
                        </button>
                    </div>
                </div>
                <div className="details-content-container">
                        <div className="min-h-16 w-24 w-1 border border-blue-500 text-blue-500 flex justify-center items-center rounded">
                            In Store
                        </div>
                        <div>
                            <h2 className="text-5xl m-4">{product?.productName}</h2>
                        </div>
                        <div className="m-4">
                            <h4 className="text-6xl">${product.currentPrice}</h4>
                        </div>
                        <div className="m-4">
                            <h4>Price in points? {product.currentPrice * 1000} Arz-points</h4>
                        </div>
                        <div className="mb-5 ">
                            <button className="button">
                                Add to Cart
                            </button>
                        </div>
                        <div className="flex items-center m-2 m-2 text-2xl">
                        <FaCar className="mr-5"/>
                        Pickup, from 1909 Lawrence Ave E, Scarborough, ON M1R 2Y6
                        </div>
                        <div className="flex items-center  m-2 text-2xl">
                        <FaStore className="mr-5"/>
                            Made By {product.brand} sold by ARZ Fine Foods.
                        </div>
                        <div className="flex items-center m-2 text-2xl">
                        <MdAssignmentReturn className="mr-5"/>
                            Learn more about our return policy.
                        </div>
                </div>
            </div>
            <div>
                <Footer />
            </div>
        </div>
    }

}