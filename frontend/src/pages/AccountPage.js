import { useNavigate } from "react-router-dom";
import Footer from "../components/Footer";
import {useState} from 'react'
export default function AccountPage() {
    const [selected, setSelected] = useState('My Account');
    const navigate = useNavigate()
    const handleMenuSelect = (value) => {
            setSelected(value)

    }
    const handleSignOut = () => {
        
    }
    return (
        <div>
            <div className="account-page-container">
                <div className="account-page-wrapper">
                    <div className="account-page-item">
                        <h1 className="text-4xl">
                            My Account
                        </h1>
                        <h1 className="text-4xl">
                                Hello, John Smith
                        </h1>
                        <h1 className="text-4xl">
                                Manage Account
                        </h1>
                        <div>
                            <h1 className="text-2xl" onClick={() => handleMenuSelect('Personal Info')}>
                                Personal Info
                            </h1>
                            <h1 className="text-2xl"  onClick={() => handleMenuSelect('My Wallet')}>
                                My Wallet
                            </h1>
                            <h1 className="text-2xl"  onClick={() => handleMenuSelect('My Points')}>
                                My Points
                            </h1>
                            <h1 className="text-2xl"  onClick={() => handleMenuSelect('My Account')}>
                                My Account
                            </h1>
                        </div>
                        <h1 className="text-4xl">
                                My Items
                        </h1>
                        <div>
                            <h1 className="text-2xl"  onClick={() => handleMenuSelect('Re-order')}>
                                Re-order
                            </h1>
                            <h1 className="text-2xl"  onClick={() => handleMenuSelect('Purchase History')}>
                                Purchase History
                            </h1>
                        </div>
                        <h1 className="text-4xl">
                                Customer Service
                        </h1>
                        <div>
                            <h1 className="text-2xl"  onClick={() => handleMenuSelect('Contact Us')}>
                                Contact Us
                            </h1>
                        </div>
                        <h1 className="text-4xl"  onClick={handleSignOut}>
                                Sign Out
                        </h1>
                    </div>
                    <div className="account-page-item">
                        {
                            selected
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