import Product from '../components/Product'
export default function ProductList({ products, className, suggestion }) {
    let renderedProducts = []
    if (products) {
        renderedProducts = products.map((product, key) => {
            return <Product suggestion={suggestion} product={product} key={product.productId} />
        })
    }
    return <div className={'cards-wrapper overflow-y-auto h-[calc(100vh-192px)] ' + className}>{renderedProducts}</div>
}

