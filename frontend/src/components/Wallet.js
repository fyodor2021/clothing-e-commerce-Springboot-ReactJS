import arzBrand from '../statics/arz-brand.png'
import { FiPlusCircle } from "react-icons/fi";
import { useEffect, useState } from 'react'
import Input from './Input';
import useAuthContext from '../hooks/useAuthContext';
import usePaymentContext from '../hooks/usePaymentContext'
import Accordion from './Accordion';
export default function Wallet({checkoutAddPayment}) {
    const { loggedUser } = useAuthContext();
    const { addPaymentMethod, userCardsInfo } = usePaymentContext();
    const [addPayment, setAddPayment] = useState(false)
    const [firstname, setFirstname] = useState()
    const [lastname, setLastname] = useState()
    const [securityCode, setSecurityCode] = useState()
    const [cardNumber, setCardNumber] = useState()
    const [expiration, setExpiration] = useState()
    useEffect(() => {
        if(checkoutAddPayment){
            setAddPayment(true)
        }
    },[])
    const handleAddPaymentMethod = () => {
        setAddPayment(true)
    }
    const cancelAddAction = () => {
        setAddPayment(false)
    }
    const handleAddPaymentSubmit = (event) => {
        event.preventDefault();
        const addPaymentRequest = {
            cardHolderFirstName: firstname,
            cardHolderLastName: lastname,
            cardNumber,
            cvv: securityCode,
            expiryDate: expiration,
            userEmail: loggedUser.email
        }
        addPaymentMethod(addPaymentRequest)
        setAddPayment(false)
    }
    console.log(userCardsInfo)
    return !addPayment ?
        <div className="account-wallet-container">
            <div className="flex-row ">
                <h1 className="account-page-headers">Your Payment Methods</h1>
                <div onClick={handleAddPaymentMethod}><FiPlusCircle /></div>
            </div>
            <div className="account-info-item">
                <div className="account-info-label">Default</div>
                <div className="arz-card">
                    <div>
                        <div>
                            Points
                        </div>
                        <div>
                            Balance: {loggedUser.points}
                        </div>
                    </div>
                    <div className="arz-card-number">4242 4242 4242 4242</div>
                    <div>
                        <div className="arz-card-holder-name">{loggedUser.firstname} {loggedUser.lastname}</div>
                        <img src={arzBrand} width="80" />
                    </div>
                </div>
            </div>
            <Accordion items={userCardsInfo} />
        </div>
        :
        <div className="account-wallet-container">
            <div className="flex-row ">
                <h1 className="account-page-headers">Add Payment Method</h1>
            </div>
            <div>
                <form onSubmit={handleAddPaymentSubmit}>
                    <div className='flex-row'>
                        <Input
                            state={[firstname, setFirstname]}
                            htmlFor={'firstname'}
                            labelContent={'First Name: '}
                            fieldClassName='input-field'
                            labelClassName='account-info-label'
                            required={true}
                            type='text'
                        />
                        <Input
                            state={[lastname, setLastname]}
                            htmlFor={'lastname'}
                            labelContent={'Last Name: '}
                            fieldClassName='input-field'
                            labelClassName='account-info-label'
                            required={true}
                            type='text'
                        />
                    </div>
                    <div className='flex-row'>
                        <Input
                            state={[cardNumber, setCardNumber]}
                            htmlFor={'card-number'}
                            labelContent={'Card Number: '}
                            fieldClassName='input-field'
                            labelClassName='account-info-label'
                            required={true}
                            type='text'
                        />
                        <Input
                            state={[securityCode, setSecurityCode]}
                            htmlFor={'security-code'}
                            labelContent={'Security Code: '}
                            fieldClassName='input-field'
                            labelClassName='account-info-label'
                            required={true}
                            type='text'
                        />
                    </div>
                    <div>
                        <Input
                            state={[expiration, setExpiration]}
                            htmlFor={'expiration'}
                            labelContent={'Expiration Date: '}
                            fieldClassName='input-field'
                            labelClassName='account-info-label'
                            required={true}
                            type='text'
                        />
                    </div>
                    <div>
                        <button className="button info-edit-form-buttons">Submit</button>
                        <button className="button cancel-button info-edit-form-buttons" onClick={cancelAddAction}>Cancel</button>
                    </div>
                </form>
            </div>
        </div >
    // <div>
    //     <h1>Billing Address</h1>
    //     <div>
    //         <Input
    //             state={[streetAddress, setStreetAddress] }
    //             htmlFor={'street-address'}
    //             labelContent={'Street Address: '}
    //             fieldClassName='input-field'
    //             labelClassName='account-info-label'
    //             required={true}
    //             type='text'
    //         />
    //     </div>
    //     <div>
    //         <Input
    //             state={[streetAddress2, setStreetAddress2] }
    //             htmlFor={'street-address2'}
    //             labelContent={'Street Address 2: '}
    //             fieldClassName='input-field'
    //             labelClassName='account-info-label'
    //             required={true}
    //             type='text'
    //         />
    //     </div>
    //     <div className='flex-row'>
    //         <Input
    //             state={[city, setCity]}
    //             htmlFor={'city'}
    //             labelContent={'City: '}
    //             fieldClassName='input-field'
    //             labelClassName='account-info-label'
    //             required={true}
    //             type='text'
    //         />
    //         <Input
    //             state={[province, setProvince]}
    //             htmlFor={'province'}
    //             labelContent={'Province: '}
    //             fieldClassName='input-field'
    //             labelClassName='account-info-label'
    //             required={true}
    //             type='text'
    //         />
    //     </div>
    //     <div>
    //         <Input
    //             state={[zipCode, setZipCode]}
    //             htmlFor={'zip-code'}
    //             labelContent={'Zip Code: '}
    //             fieldClassName='input-field'
    //             labelClassName='account-info-label'
    //             required={true}
    //             type='text'
    //         />
    //     </div>
    // </div>
}