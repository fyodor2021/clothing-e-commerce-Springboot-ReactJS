import Footer from "../components/Footer"
import coverPic from "../statics/josedor-about.png"

export default function AboutPage() {

    return (
        <div className="about-page page-padding-top">
                <img className="image-cover" src={coverPic}></img>
            <div className="about-header">
                <p className="about-title">JoseDor</p>
            </div>
            <div className="about-header">
                <p className="about-title">The Essence of Fashion meets Elegance</p>
            </div>
            <div className="about-paragraph-conatiner">

                <div className="three-paragraph">
                    <p className="para-header">Origins and Inspiration:</p>
                    <p>Founded in the heart of Barcelona, Josedor emerged from a rich tapestry of culture and artistic expression.
                        Inspired by the region's vibrant colors, historical architecture, and passionate lifestyle,
                        Josedor began with a vision to embody the essence of sophistication in every stitch.</p>
                </div>
                <div className="three-paragraph">
                    <p className="para-header">Signature Style:</p>
                    <p>
                        At Josedor, the hallmark of each collection lies in its seamless fusion of tradition and modernity.
                        From flowing silhouettes that echo the graceful movements of waves to bold prints reminiscent of sun-kissed landscapes,
                        every garment exudes a sense of timeless allure. The brand is renowned for its use of luxurious fabrics sourced from Italy and Spain,
                        ensuring both quality and comfort in every piece.
                    </p>
                </div>
                <div className="three-paragraph">
                    <p className="para-header">Seasonal Collections:</p>
                    <p>
                        Josedor unveils seasonal collections that captivate fashion enthusiasts with their thematic storytelling.
                        Each collection draws inspiration from different facets of Mediterranean life —
                        from the vibrant festivals of Spain to the serene landscapes of the Greek islands.
                        Whether it's a spring collection evoking the blossoming flora of Provence or a
                        winter line inspired by the cozy elegance of coastal villas, Josedor ensures that every piece
                        resonates with its cultural heritage.
                    </p>
                </div>
                <div className="three-paragraph">
                    <p className="para-header">Craftsmanship:</p>
                    <p>
                        Crafted by skilled artisans with a dedication to precision,
                        Josedor garments are imbued with meticulous attention to detail.
                        Intricate embroidery, hand-sewn embellishments, and tailored cuts elevate each creation to a work of art.
                        This commitment to craftsmanship ensures that wearing Josedor is not merely about fashion,
                        but a celebration of impeccable design and superior quality.
                    </p>
                </div>
                <div className="three-paragraph">
                    <p className="para-header">Global Appeal:</p>
                    <p>
                        From the cobblestone streets of Rome to the bustling markets of Marrakech,
                        Josedor has garnered a global following among discerning fashion enthusiasts.
                        Its collections resonate with individuals who appreciate the allure of understated elegance
                        and seek to make a statement with refined taste. Whether gracing red carpets or adorning stylish travelers,
                        Josedor epitomizes cosmopolitan chic with a Mediterranean soul.
                    </p></div>


                <div className="three-paragraph">
                    <p className="para-header">Ethical Values:</p>
                    <p>
                        Beyond aesthetics, Josedor is committed to ethical practices and sustainability.
                        Partnering with local artisans and prioritizing eco-friendly materials,
                        the brand strives to minimize its environmental footprint while supporting communities.
                        Each purchase is not just a fashion statement, but a step towards a more conscious and compassionate future.
                    </p>
                </div>
            </div>
        </div>)
}