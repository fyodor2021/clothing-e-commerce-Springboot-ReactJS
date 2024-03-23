import { Link, useLocation, useParams } from "react-router-dom"
import { useState, useEffect } from 'react'
import useProductContext from "../hooks/useProductContext";
import { render } from "@testing-library/react";
import Footer from '../components/Footer'
import { FaCar } from "react-icons/fa";
import { FaStore } from "react-icons/fa";
import { MdAssignmentReturn } from "react-icons/md";
import userAvatar from "../statics/user-avatar.png"
import { FaChevronCircleRight } from "react-icons/fa";
import { FaChevronCircleLeft } from "react-icons/fa";
import useReivewContext from "../hooks/useReviewContext";
export default function DetailsPage() {
    const { productId } = useParams();
    const { fetchProductDetails, product, fetchProductReviews, reviews, isLoading } = useProductContext();
    const { addReview } = useReivewContext();
    const [slideNumber, setSlideNumber] = useState(0)
    const [reviewTab, setReviewTab] = useState(false)
    const handleReviewTabExtend = () => {
        setReviewTab(true)
    }
    const filterPanel = document.getElementsByClassName('details-content-container');
    const handleScrollEvent = () => {
        if(filterPanel[0]){
            if (window.scrollY > 100) {
                filterPanel[0].classList.add('fix-detail-panel');
            } else {
                filterPanel[0].classList.remove('fix-detail-panel');
            }
        }
    }

    useEffect(() => {
        if (product) {
            if (!isLoading) {

                window.addEventListener('scroll', handleScrollEvent);
            }
        }
        return () => {
            window.removeEventListener('scroll', handleScrollEvent);
        };

    }, [isLoading, product]);


    useEffect(() => {
        fetchProductDetails(productId)
        fetchProductReviews(productId)
    }, [])
    useEffect(() => {
        const el = document.getElementById('slide-' + slideNumber)

        if (el) {
            el.scrollIntoView({ block: 'center' });
        }
    }, [slideNumber]);
    if (product) {
        const renderedImages = product.imageList.map((image, key) => {
            if (key === 0) {
                return <img id={'slide-' + key} src={'data:image/jpeg;base64,' + image} key={key} />
            } else {
                return <img id={'slide-' + key} src={'data:image/jpeg;base64,' + image} key={key} />
            }
        })
        let renderedReviews;
        if (reviews) {
            renderedReviews = reviews.map((review, key) => {
                return <div className="review-wrapper" key={review.reviewId}>
                    <div className="review-avatar">
                        <img src={userAvatar} />
                    </div>
                    <div className="review-content">
                        <div className="text-2xl font-bold">{review.userFname} {review.userLname}</div>
                        <div className="text-3xl">{review.reviewBody}</div>
                    </div>
                </div>
            })
        }
        const verticalImages = product.imageList.map((image, key) => {
            return <img className='vertical-image' src={'data:image/jpeg;base64,' + image} key={key} />
        })


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
            }
        };

        const handleRightClick = (event) => {
            updateSlideNumber('right');
        };

        const handleLeftClick = (event) => {
            updateSlideNumber('left');
        };

        const handleReivewAddFormSubmit = (event) => {
            event.preventDefault()
            addReview(
                {
                    productId,
                    reviewBody: event.target[0].value
                }
            )
        }
        return <div className="details-page-content">
            <div className="details-body-container">
                <div className="vertical-image-container">
                    {verticalImages}
                </div>
                <div className="details-slider-container">
                    <div className="details-slider-wrapper">
                        <button className='scroll-button left-scroll-button' onClick={handleLeftClick}>
                            {/* <a href={'#slide-' + slideNumber} className="text-4xl">&lt;</a> */}
                            <FaChevronCircleLeft />
                        </button>
                        <div className="details-slider">
                            {renderedImages}
                        </div>
                        <button className='scroll-button right-scroll-button' onClick={handleRightClick}>
                            {/* <a href={'#slide-' + slideNumber} className="text-4xl">&gt;</a> */}
                            <FaChevronCircleRight />
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
                    <div className="flex items-center m-2 m-2 text-1xl">
                        <FaCar className="mr-5" />
                        Pickup, from 1909 Lawrence Ave E, Scarborough, ON M1R 2Y6
                    </div>
                    <div className="flex items-center  m-2 text-1xl">
                        <FaStore className="mr-5" />
                        Made By {product.brand} sold by ARZ Fine Foods.
                    </div>
                    <div className="flex items-center m-2 text-1xl">
                        <MdAssignmentReturn className="mr-5" />
                        Learn more about our return policy.
                    </div>
                </div>
            </div>
            <div className="details-review-container">
                <div>
                    <div className="review-container">
                        {renderedReviews}
                    </div>
                    <div className="details-review-content-container">
                        {
                            reviewTab ? (<div className="add-review-container">
                                <div>
                                    Tell us How we did?
                                </div>
                                <form className="flex-col" onSubmit={handleReivewAddFormSubmit}>
                                    <textarea className="review-box" rows='5' cols='60' />
                                    <button className="button" type="submit">Review</button>
                                </form>
                            </div>) : (<div className="review-content-container">

                                <h1>
                                    Tell us How we Did:
                                </h1>
                                <h1>
                                    Review Product:
                                </h1>
                                <h1>
                                    We value your feedback, share your thoughts with other customers!
                                </h1>
                                <div className="write-review-button">
                                    <button className="button details-review-button" onClick={handleReviewTabExtend}>Write a Customer Review</button>
                                </div>

                            </div>
                            )
                        }

                    </div>

                </div>

                <div className="details-content-container" style={{ visibility: 'hidden' }}>
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
                    <div className="flex items-center m-2 m-2 text-1xl">
                        <FaCar className="mr-5" />
                        Pickup, from 1909 Lawrence Ave E, Scarborough, ON M1R 2Y6
                    </div>
                    <div className="flex items-center  m-2 text-1xl">
                        <FaStore className="mr-5" />
                        Made By {product.brand} sold by ARZ Fine Foods.
                    </div>
                    <div className="flex items-center m-2 text-1xl">
                        <MdAssignmentReturn className="mr-5" />
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