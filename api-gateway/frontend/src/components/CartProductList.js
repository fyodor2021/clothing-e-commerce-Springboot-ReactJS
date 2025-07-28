import CartProduct from "./CartProduct";
export default function CartProductList({checkout, products}){
    console.log("cartlist", products)
    const renderedProducts = products.map((product,index) => {
        return <CartProduct key={index} checkout={checkout} product={product} />
    })
    return <div>
        {renderedProducts}
    </div>
}