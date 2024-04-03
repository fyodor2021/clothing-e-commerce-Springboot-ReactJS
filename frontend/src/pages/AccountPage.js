import { useLocation, useNavigate } from "react-router-dom";
import Footer from "../components/Footer";
import { useEffect, useState, useRef } from 'react'
import useAuthContext from "../hooks/useAuthContext";
import { hasSelectionSupport } from "@testing-library/user-event/dist/utils";
import arzFineFoodsStore from '../statics/arz-fine-foods-store.jpg'

import Wallet from "../components/Wallet";
import PersonalInfo from "../components/PersonalInfo";
export default function AccountPage() {
    const [selected, setSelected] = useState('My Account');
    const { signout, loggedUser } = useAuthContext();
    const navigate = useNavigate()
    const {state } = useLocation();
    useEffect(() => {
        if(state == 'add payment'){
            setSelected('My Wallet')
        }
    },[])
    const handleMenuSelect = (value) => {
        setSelected(value)
    }
    console.log(selected);
    const handleSignOut = () => {
        signout()
    }
    let firstname
    let lastname
    let email
    let address
    let content;
    if (loggedUser) {
        firstname = loggedUser.firstname;
        lastname = loggedUser.lastname;
        email = loggedUser.email;
        address = loggedUser.address;
        const user = {
            firstname,
            lastname,
            email,
            address
        }
        switch (selected) {
            case 'My Account':
                content = <PersonalInfo />
                break;
            case 'My Wallet':
                content = state === 'add payment' ? <Wallet checkoutAddPayment={true}/> : <Wallet/>
                break;
            case 'Active Orders':
                content = <div>Active Orders</div>
                break;
            case 'Order Again':
                content = <div>Order Again</div>
                break;
            case 'Contact Us':
                content =
                    <div>
                        <h1 className="account-page-headers">Tell us how we did!</h1>
                        <img src={arzFineFoodsStore} width="800" />
                        <div className="account-info-item">
                            <div className="account-info-label">Contact Us at</div>
                            <div>444-444-3309</div>
                        </div>
                        <div className="account-info-item">
                            <div className="account-info-label">Email</div>
                            <div>arzfinefoods@arz-support.com</div>
                        </div>


                    </div>

                break;
        }
    }
    return (
        <div className="account-page">
            <div className="account-page-container">
                <div className="account-page-wrapper">
                    <div className="account-page-item">

                        <h1 className="account-page-headers">
                            {firstname} {lastname}
                        </h1>
                        <h1 className="account-page-headers">
                            Manage Account
                        </h1>
                        <div>
                            <h1 onClick={() => handleMenuSelect('My Account')}>
                                My Account
                            </h1>
                            <h1 onClick={() => handleMenuSelect('My Wallet')}>
                                My Wallet
                            </h1>
                        </div>
                        <h1 className="account-page-headers">
                            My Items
                        </h1>
                        <div>
                            <h1 onClick={() => handleMenuSelect('Active Orders')}>
                                Active Orders
                            </h1>
                            <h1 onClick={() => handleMenuSelect('Order Again')}>
                                Order Again
                            </h1>
                        </div>
                        <h1 className="account-page-headers">
                            Customer Service
                        </h1>
                        <div>
                            <h1 onClick={() => handleMenuSelect('Contact Us')}>
                                Contact Us
                            </h1>
                        </div>
                        <h1 className="account-page-headers" onClick={handleSignOut}>
                            Sign Out
                        </h1>
                    </div>
                    <div className="account-page-item">
                        {
                            content
                        }

                    </div>

                </div>

            </div>
            <div>
                <Footer />
            </div>
        </div>
    )
}