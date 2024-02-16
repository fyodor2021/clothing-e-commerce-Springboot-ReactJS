import productPic from "../statics/Bread-Selection.jpg"
export default function CartPage() {
    return <div>
        <div className="cart-main-container">

            {/* left container */}
            <div className="left-container">
            <p>left </p>

            {/* Labels  */}
            <div className="cart-labels-container">
                <div className="cart-labels-fields"><p>PRODUCT</p></div>
                <div className="cart-labels-fields"><p>PRICE</p></div>
                <div className="cart-labels-fields"><p>QTY</p></div>
                <div className="cart-labels-fields"> <p>TOTAL</p></div>
            </div>

            {/* Cards to display  */}
            <div className="cart-product-card">
                <div className="cart-product-fields">

                    {/* product image and details */}
                    <div className="cart-product-image-container">
                        <img  className="cart-product-image" src={productPic}></img>
                        <p>Arz White Pita Bread</p>
                        <p>250g</p>
                        {/* <p>Product size</p> */}

                    </div>
                </div>
                {/* price field */}
                <div className="cart-product-fields" ><p>$109.00</p></div>

                {/* QTY */}
                <div className="cart-product-fields">
                <div className="qty-field">
                    <p>-</p>
                    <p>4</p>
                    <p>+</p>
                </div>
                </div>

                {/* total */}
                <div className="cart-product-fields"><p>$400</p></div>

            </div>
            
            {/* SECOND CARD TO DISPLAY */}
             {/* Cards to display  */}
             <div className="cart-product-card">
                <div className="cart-product-fields">

                    {/* product image and details */}
                    <div className="cart-product-image-container">
                        <img  className="cart-product-image" src={productPic}></img>
                        <p>Arz White Pita Bread</p>
                        <p>250g</p>
                        {/* <p>Product size</p> */}

                    </div>
                </div>
                {/* price field */}
                <div className="cart-product-fields" ><p>$109.00</p></div>

                {/* QTY */}
                <div className="cart-product-fields">
                <div className="qty-field">
                    <p>-</p>
                    <p>4</p>
                    <p>+</p>
                </div>
                </div>

                {/* total */}
                <div className="cart-product-fields"><p>$400</p></div>

            </div>
            {/* THIRD CARD TO DISPLAY */}
             {/* Cards to display  */}
             <div className="cart-product-card">
                <div className="cart-product-fields">

                    {/* product image and details */}
                    <div className="cart-product-image-container">
                        <img  className="cart-product-image" src={productPic}></img>
                        <p>Arz White Pita Bread</p>
                        <p>250g</p>
                        {/* <p>Product size</p> */}

                    </div>
                </div>
                {/* price field */}
                <div className="cart-product-fields" ><p>$109.00</p></div>

                {/* QTY */}
                <div className="cart-product-fields">
                <div className="qty-field">
                    <p>-</p>
                    <p>4</p>
                    <p>+</p>
                </div>
                </div>

                {/* total */}
                <div className="cart-product-fields"><p>$400</p></div>

            </div>



            </div>
            {/* right container */}
            <div className="right-container">
            <p>right</p>    
                {/* Right checkout container */}
                <div className="cart-checkout-container">

                    {/* Button */}
                    <div>
                        <button className="button">Checkout</button>
                    </div>

                    {/* Subttal container */}
                    <div className="cart-checkout-small-containers">
                        <p>Subtotal: </p>
                        <p>$1300</p>
                    </div>

                    {/* Tax amount */}
                    <div className="cart-checkout-small-containers">
                        <p>Tax: </p>
                        <p>$130</p>
                    </div>

                    {/* Total */}
                    <div className="cart-checkout-small-containers">
                        <p>Subtotal: </p>
                        <p>$1430</p>
                    </div>

                </div>
            </div>
        
        </div>
    </div>
}