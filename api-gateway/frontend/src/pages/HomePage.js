import React, { useState, useEffect, useRef } from "react";
import slide1 from '../statics/slide1.jpg'
import slide2 from '../statics/slide2.jpg'
import slide3 from '../statics/slide3.jpg'
import slide4 from '../statics/slide4.jpg'
import { FaArrowRight } from "react-icons/fa";
import { FaArrowLeft } from "react-icons/fa";
import { toast, ToastContainer } from "react-toastify";
export default function HomePage() {
  const anchorRef1 = useRef(null)
  const anchorRef2 = useRef(null)
  const anchorRef3 = useRef(null)
  const anchorRef4 = useRef(null)
  const slideAnchors = [null, anchorRef1,anchorRef2,anchorRef3,anchorRef4]
  const slider = useRef(null)
  const [currentSlide, setCurrentSlide] = useState(1);
  const leftClickHandle = () => {
    setCurrentSlide(currentSlide - 1 < 1 ? 4 : currentSlide - 1);
  }
  const RightClickHandle = () => {
    setCurrentSlide(currentSlide + 1 > 4 ? 1 : currentSlide + 1);
  }
  useEffect(() => {
    if(slideAnchors[currentSlide]){
      slideAnchors[currentSlide].current.click()
    }
  }, [currentSlide])
  useEffect(() => {
    const interval = setInterval(() => {
      RightClickHandle()
    }, 5000)
    return () => {
      clearInterval(interval);
    };
  },[currentSlide])  


  return (
    <section>
      <div className="home-page-container w-screen h-screen  overflow-y-hidden">
        <div className="slider-wrapper w-full h-full">
          <div className="slider min-h-screen w-screen" ref={slider}>
            <img src={slide1} id="slide-1" />
            <img src={slide2} id="slide-2" />
            <img src={slide3} id="slide-3" />
            <img src={slide4} id="slide-4" />
          </div>
        </div>
      </div>
      <div className="slider-nav">
        <a href="#slide-1" ref={anchorRef1}></a>
        <a href="#slide-2" ref={anchorRef2}></a>
        <a href="#slide-3" ref={anchorRef3}></a>
        <a href="#slide-4" ref={anchorRef4}></a>
      </div>
      <div className="slider-nav-arrow-container">
        <div><FaArrowLeft onClick={leftClickHandle} /></div>
        <div><FaArrowRight onClick={RightClickHandle} /></div>
      </div>
      
    </section>

  );

}
