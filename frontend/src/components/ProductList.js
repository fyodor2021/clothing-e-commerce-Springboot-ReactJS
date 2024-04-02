import { useEffect } from 'react';
import Product from '../components/Product'
import useFilterContext from '../hooks/useFilterContext'
export default function ProductList({ products }) {
    const { filter } = useFilterContext();
    let renderedProducts = []
    if (filter) {
        switch (filter) {
            case 'hightolow':
                products.sort((a, b) => {
                    const aprice = a.price;
                    const bprice = b.price;
                    if (aprice > bprice) {
                        return -1;
                    }
                    if (aprice < bprice) {
                        return 1;
                    }
                    return 0;
                })
                break;
            case 'lowtohigh':
                products.sort((a, b) => {
                    const aprice = a.price;
                    const bprice = b.price;
                    if (aprice > bprice) {
                        return 1;
                    }
                    if (aprice < bprice) {
                        return -1;
                    }
                    return 0;
                })
                break;
                default:
                    products.sort((a, b) => {
                        const aRelevance = (
                            (a.tags.includes(filter) ? 1 : 0) +
                            (a.productName.includes(filter) ? 1 : 0)
                        );
                        const bRelevance = (
                            (b.tags.includes(filter) ? 1 : 0) +
                            (b.productName.includes(filter) ? 1 : 0)
                        );
                
                        return bRelevance - aRelevance;
                    });
                    break;

        }
    }
    if (products) {
        renderedProducts = products.map((product, key) => {

            return <Product product={product} key={product.productId} />
        })
    }
    return <div className='cards-wrapper'>{renderedProducts}</div>
}

// case 'non-dairy':
//     products.sort((a, b) => {
//         const aNonDairy = a.tags.includes('non-dairy');
//         const bNonDairy = b.tags.includes('non-dairy');
//         if (aNonDairy && !bNonDairy) {
//             return -1;
//         } else if (!aNonDairy && bNonDairy) {
//             return 1;
//         } else {
//             return 0;
//         }
//     })
//     break;
// case 'vegan':
//     products.sort((a, b) => {
//         const avigan = a.tags.includes('vegan');
//         const bvigan = b.tags.includes('vegan');
//         if (avigan && !bvigan) {
//             return -1;
//         } else if (!avigan && bvigan) {
//             return 1;
//         } else {
//             return 0;
//         }
//     })
//     break;
// case 'nuts-free':
//     products.sort((a, b) => {
//         const aNutFree = a.tags.includes('nuts-free');
//         const bNutFree = b.tags.includes('nuts-free');
//         if (aNutFree && !bNutFree) {
//             return -1;
//         } else if (!aNutFree && bNutFree) {
//             return 1;
//         } else {
//             return 0;
//         }
//     })
//     break;
// case 'gluten-free':
//     products.sort((a, b) => {
//         const aGlutenFree = a.tags.includes('gluten-free');
//         const bGlutenFree = b.tags.includes('gluten-free');
//         if (aGlutenFree && !bGlutenFree) {
//             return -1;
//         } else if (!aGlutenFree && bGlutenFree) {
//             return 1;
//         } else {
//             return 0;
//         }
//     })
//     break;
// case 'organic':
//     products.sort((a, b) => {
//         const aorganic = a.tags.includes('organic');
//         const borganic = b.tags.includes('organic');
//         if (aorganic && !borganic) {
//             return -1;
//         } else if (!aorganic && borganic) {
//             return 1;
//         } else {
//             return 0;
//         }
//     })
//     break;
// case 'shellfish':
//     products.sort((a, b) => {
//         const aShellfish = a.tags.includes('shellfish');
//         const bShellfish = b.tags.includes('shellfish');
//         if (aShellfish && !bShellfish) {
//             return -1;
//         } else if (!aShellfish && bShellfish) {
//             return 1;
//         } else {
//             return 0;
//         }
//     })
//     break;