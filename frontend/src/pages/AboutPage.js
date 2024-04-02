import Footer from "../components/Footer"
import coverPic from "../statics/arz-about.jpg"
import aboutPagePic from "../statics/about-page.jpg"
import breadPic from "../statics/Bread-Selection.jpg"
import missiPic from "../statics/missi.jpg"
import freshPic from "../statics/fresh.jpg"

export default function AboutPage() {

    return <div >
        <div  className="about-page">
            <div className="flex items-center justify-center"  >
                <img className="image-cover" src={coverPic}></img>
            </div>
            <div >
                <div className="about-header">
                    <p className="about-text">Arz Fine Foods</p>
                </div>
                <div className="about-header">
                    <p className="about-text">The Essence of Mediterranean Gourmet</p>
                </div>
                <div className="about-container">
                    <div className="about-two-paragraph-container">
                        <div className="two-paragraph">
                            <p>The Essence of Mediterranean Gourmet. This is where the exotic and traditional Middle East meets
                                contemporary and curious West. We are purveyors of exotic Middle Eastern and Mediterranean cuisine;
                                Everything you need to experience the finest foods. Whether you are looking for the freshest ingredients
                                to create your own recipes or select from our vast variety of prepared foods, Arz is the destination of choice.</p></div>
                        <div className="two-paragraph">
                            <p>
                                Founded in 1989 by the Boyadjian brothers, Arz Fine Foods a family owned and operated company
                                started a quality Middle Eastern food shop with an extensive bakery
                                featuring some of the very best in Lebanese pastry specialties.
                            </p>
                        </div>
                    </div>
                </div>
                <div className="image-container">
                    <img className="image-about" src={aboutPagePic}></img>
                </div>
                <div className="about-container">
                    <div className="about-three-paragraph-container">
                        <div className="three-paragraph">
                        <p className="label">Savoring Success in Toronto and Beyond</p>
                        <p>
                            Arz Fine Foods has kept pace with the rapid growth of Toronto as a World Class c
                            ity and expanding to a second store in the city of Mississauga by providing authentic fresh,
                            frozen and prepared Middle Eastern and Mediterranean produce, groceries, deli, fresh and frozen prepared foods and baked goods.
                        </p>
                        <img src={missiPic}></img>
                        </div>
                        <div className="three-paragraph">
                        <img src={breadPic}></img>
                        <p className="label">Indulge in Authentic Delights: Arz Fine Foods Bakery</p>
                        <p>
                            Our bakery is second to none, offering our own freshly baked pita breads (baked and served daily), baklawas, European cakes and tarts,
                            Armenian Tahini Bread, a huge selection of cookies, chocolates, and so much more.
                            Our staff will be pleased to assist you in exploring our delectable, authentic and traditional Mediterranean sweets.
                        </p></div>

                        <div className="three-paragraph">
                        <p className="label">Arz Fine Foods Invites You to Taste Freshness</p>
                        <p>
                            The freshest ingredients are at the core of everything we sell and produce.
                            We strive to create the tastiest and most authentic cuisine possible. From fresh garlic to flavourful hummus to hand-rolled stuffed grape leaves,
                            Arz welcomes you to experience all that the Mediterranean has to offer
                        </p> 
                        <img src={freshPic}></img>
                        </div>
                    </div>
                </div>
            </div>
            <Footer />

        </div>

    </div>
}