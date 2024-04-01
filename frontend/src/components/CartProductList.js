import CartProduct from "./CartProduct";
export default function CartProductList({checkout, products}){
    const renderedProducts = products.map((product, key) => {
        return <CartProduct key={key} checkout={checkout} product={product} />
    })
    return renderedProducts;
}