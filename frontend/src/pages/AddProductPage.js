import axios from "axios";
import { useState } from "react";
import Input from "../components/Input";
import Product from "../components/Product";
import { v4 as uuidv4 } from 'uuid';
import Compressor from 'compressorjs';
export default function AddProductPage() {

        const [files, setFile] = useState([]);
        const [uploadedFile, setUploadedFile] = useState([]);
        const [renederedImages, setRenderedImages] = useState([]);
        const [productName, setProductName] = useState('grapes');
        const [brand, setBrand] = useState('grapes');
        const [description, setDescription] = useState('grapes');
        const [category, setCategory] = useState('grapes');
        const [size, setSize] = useState('50~100');
        const [tags, setTags] = useState('fruits');
        const [unit, setUnit] = useState('grams');
        const [cost, setCost] = useState(0.99);
        const [currentPrice, setCurrentPrice] = useState(2.99);
        const [isTaxed, setIsTaxed] = useState(false);
        const [skuCode, setSkuCode] = useState('sku-444234');
        const [upcCode, setUpcCode] = useState('234523452345');
        const [vendor, setVendor] = useState('arz-fine-foods');


        const handleFileUploadChange = (event) => {
                setFile(prev => [...prev, event.target.files[0]])
                const reader = new FileReader()
                reader.onload = (e) => {
                        const { result } = e.target;
                        setRenderedImages(prev => [...prev,
                        <div className="uploaded-image-item-container">
                                <img className="uploaded-image-item"
                                        key={event.target.files[0].name + uuidv4()}
                                        src={result} />
                        </div>])
                }
                if (event.target.files[0]) {
                        new Compressor(event.target.files[0], {
                                quality: .5,
                                success: result => {

                                        reader.readAsDataURL(event.target.files[0])
                                }
                        })
                }
        }

        const handleFileSubmit = (event) => {
                event.preventDefault();
                const productRequest = {
                        productName,
                        brand,
                        description,
                        category,
                        size,
                        tags,
                        unit,
                        cost,
                        currentPrice,
                        isTaxed,
                        skuCode,
                        upcCode,
                        vendor,
                }
                const formData = new FormData();
                for (const file of files) {

                        formData.append('files', file)
                }
                formData.append('productRequest', JSON.stringify(productRequest))
                // formData.append('productRequest', JSON.stringify({"productName":"double chocolate"}))

                axios.post("http://localhost:5000/api/product", formData)
        }

        return <div>
                <form onSubmit={handleFileSubmit}>
                        <div className="uploaded-image-wrapper">
                                <div className="uploaded-star-image">{renederedImages[renederedImages.length - 1]}</div>
                                <div className="uploaded-image-container">{renederedImages}</div>
                        </div>
                        <div>
                                <div style={{ display: 'flex', justifyContent: 'center' }}>
                                        <button className="button">
                                                <input id="fileupload" type="file" name="images" multiple accept="image/png, image/jpeg, image/jpg" onChange={handleFileUploadChange} />
                                                file upload
                                        </button>
                                </div>
                                <div className="input-component-wrapper">
                                        <Input htmlFor={'productName'}
                                                labelContent={'Product Name: '}
                                                state={[productName, setProductName]}
                                        />


                                        <Input htmlFor={'brand'}
                                                labelContent={'Brand: '}
                                                state={[brand, setBrand]}
                                        />
                                </div>
                                <div className="input-component-wrapper">
                                        <Input htmlFor={'description'}
                                                labelContent={'Description: '}
                                                state={[description, setDescription]}
                                        />

                                        <Input htmlFor={'category'}
                                                labelContent={'Category: '}
                                                state={[category, setCategory]}
                                        />
                                </div>
                                <div className="input-component-wrapper">

                                        <Input htmlFor={'size'}
                                                labelContent={'Size: '}
                                                state={[size, setSize]}
                                        />

                                        <Input htmlFor={'tags'}
                                                labelContent={'Tags: '}
                                                state={[tags, setTags]}
                                        />
                                </div>
                                <div className="input-component-wrapper">

                                        <Input htmlFor={'unit'}
                                                labelContent={'Unit: '}
                                                state={[unit, setUnit]}
                                        />

                                        <Input htmlFor={'cost'}
                                                labelContent={'Cost: '}
                                                state={[cost, setCost]}
                                        />
                                </div>
                                <div className="input-component-wrapper">

                                        <Input htmlFor={'currentPrice'}
                                                labelContent={'Current Price: '}
                                                state={[currentPrice, setCurrentPrice]}
                                        />

                                        <Input htmlFor={'isTaxed'}
                                                labelContent={'Taxed?: '}
                                                state={[isTaxed, setIsTaxed]}
                                        />
                                </div>
                                <div className="input-component-wrapper">

                                        <Input htmlFor={'skucode'}
                                                labelContent={'SKU code: '}
                                                state={[skuCode, setSkuCode]}
                                        />

                                        <Input htmlFor={'upcCode'}
                                                labelContent={'UPC code: '}
                                                state={[upcCode, setUpcCode]}
                                        />
                                </div>
                                <div className="input-component-wrapper">

                                        <Input htmlFor={'vendor'}
                                                labelContent={'Vendor: '}
                                                state={[vendor, setVendor]}
                                        />
                                </div>
                                <button className="button" type="submit">submit</button>
                        </div>
                </form >
        </div >
}
