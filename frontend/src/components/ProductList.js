import Product from '../components/Product'
export default function ProductList({products}){
    let renderedProducts = []
    if(products){
        renderedProducts = products.map((product,key) => {
            return <Product   product={product} key={product.productId}/>
        })
    }
    return <div className='cards-wrapper'>{renderedProducts}</div>
}