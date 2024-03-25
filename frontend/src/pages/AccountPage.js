import { useNavigate } from "react-router-dom";
import Footer from "../components/Footer";
import { useEffect, useState, useRef } from 'react'
import useAuthContext from "../hooks/useAuthContext";
import { hasSelectionSupport } from "@testing-library/user-event/dist/utils";
export default function AccountPage() {
    const [selected, setSelected] = useState('My Account');
    const { signout, loggedUser } = useAuthContext();
    const navigate = useNavigate()
    const handleMenuSelect = (value) => {
        setSelected(value)
    }

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
        switch (selected) {
            case 'My Account':
                content =
                    <div>
                        <div className="account-info-item">
                            <div className="account-info-label">Full name</div>
                            <div>{firstname} {lastname}</div>
                        </div>
                        <div className="account-info-item">
                            <div className="account-info-label">Email</div>
                            <div>{email}</div>
                        </div>
                        <div className="account-info-item">
                            <div className="account-info-label">Address</div>
                            <div>{address}</div>
                        </div>
                        <div className="account-info-item">
                            <div className="account-info-label">Password</div>
                            <div>*******************</div>
                        </div>

                    </div>

                break;
            case 'My Wallet':
                content = <div>My Wallet</div>
                break;
            case 'My Points':
                content = <div>My Points</div>
                break;
            case 'Re-order':
                content = <div>Order Again</div>
                break;
            case 'Purchase History':
                content = <div>Purchase History</div>
                break;
            case 'Contact Us':
                content = <div>Contact Us</div>
                break;
        }
    }
    return (
        <div>
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
                            <h1 onClick={() => handleMenuSelect('My Points')}>
                                My Points
                            </h1>

                        </div>
                        <h1 className="account-page-headers">
                            My Items
                        </h1>
                        <div>
                            <h1 onClick={() => handleMenuSelect('Re-order')}>
                                Re-order
                            </h1>
                            <h1 onClick={() => handleMenuSelect('Purchase History')}>
                                Purchase History
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
                        <h1 className="account-page-headers">Your Personal Information</h1>
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