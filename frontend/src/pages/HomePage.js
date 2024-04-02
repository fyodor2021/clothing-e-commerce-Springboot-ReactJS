import ProductList from "../components/ProductList";
import useProductContext from "../hooks/useProductContext"
import slide1 from '../statics/slide1.jpg'
import slide2 from '../statics/slide2.jpg'
import slide3 from '../statics/slide3.jpg'
import slide4 from '../statics/slide4.jpg'
import Footer from '../components/Footer';
import FilterPanel from '../components/FilterPanel';
import Skeleton from '../components/Skeleton'
import { useEffect, useState } from "react";
import FilterSkeleton from "../components/FilterSkeleton";
import useCartContext from "../hooks/useCartContext";
import Product from "../components/Product";
export default function HomePage({ category }) {
  const { products, isLoading, fetchProducts } = useProductContext();

  const { createCart } = useCartContext();
  const [filter, setFilter] = useState('');
  const filterPanel = document.getElementsByClassName('filter-panel-container');
  const handleScrollEvent = () => {
    if (window.scrollY > 580) {
      filterPanel[0].classList.add('fix-panel');
    } else {
      filterPanel[0].classList.remove('fix-panel');
    }
  }
  let counter = 1;
  useEffect(() => {
    let interval;
    interval = setInterval(() => {
      document.getElementById('radio' + counter).checked = true;
      counter++;
      if (counter > 4) {
        counter = 1;
      }
    }, 2500);
    if (!isLoading && products.length > 10) {

      window.addEventListener('scroll', handleScrollEvent);
    }

    return () => {
      clearInterval(interval);
      window.removeEventListener('scroll', handleScrollEvent);
    };
  }, [isLoading, window.location.pathname]);
  let skeletons = [];
  for (let i = 0; i < 20; i++) {
    skeletons.push(<div key={i} className="m-5">
      <Skeleton />
     </div>)
  }
  return (
    <div>
      <div className="home-page-container">
        <div className="slider-container">
          <div className="slider">
            <div className="slides">
              <input type="radio" name="radio-btn" id="radio1" />
              <input type="radio" name="radio-btn" id="radio2" />
              <input type="radio" name="radio-btn" id="radio3" />
              <input type="radio" name="radio-btn" id="radio4" />

              <div className="slide first">
                <img src={slide1} alt="Slide 1" />
              </div>
              <div className="slide">
                <img src={slide2} alt="Slide 2" />
              </div>
              <div className="slide">
                <img src={slide3} alt="Slide 3" />
              </div>
              <div className="slide">
                <img src={slide4} alt="Slide 4" />
              </div>
            </div>
          </div>
        </div>
        {isLoading ? (
          <div className="flex flex-row justify-center w-screen flex-wrap">
            <div className="m-5">
              <FilterSkeleton />
            </div>
            <div className="flex flex-row w-3/5 justify-center flex-wrap">
              {skeletons}


            </div>
          </div>
        ) : (
          <div>
            <div className="home-page-cards-filter-container">
              <div>
                <FilterPanel/>
                <FilterPanel hidden={true} className={'hidden-filter-panel'} />
              </div>
              <div>
                <ProductList products={products} />
              </div>
            </div>
          </div>
        )}
      </div>
      <div>
        <Footer />
      </div>
    </div>

  );

}
