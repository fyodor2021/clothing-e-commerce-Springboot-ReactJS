import { useState, useEffect, useRef } from 'react'
import Input from '../components/Input'
import arzPic from '../statics/arz-fine-foods-mis.png'
import Footer from '../components/Footer';
import { useNavigate } from 'react-router-dom';
import useAuthContext from '../hooks/useAuthContext';

export default function RegistrationPage() {
    const [email, setEmail] = useState('');
    const [fname, setFname] = useState('');
    const [lname, setLname] = useState('');
    const [password, setPassword] = useState('');
    const [passwordRetype, setPasswordRetype] = useState('');
    const [valMessage, setValMessage] = useState('');
    const navigate = useNavigate()
    const {register} = useAuthContext();
    const autoCompleteRef = useRef();
    const addressRef = useRef();
    const addressEl = document.getElementById('address')
    const options = {
        componentRestrictions: { country: 'CA' },
        fields: ["formatted_address"],
        types: ["address"]
    };
    useEffect(() => {
        autoCompleteRef.current = new window.google.maps.places.Autocomplete(
            addressRef.current,
            options
        );
    }, []);
    const handleClick = () => {
        // setReg(false);
    };
    const handleLoginNavigate = () => {
        navigate('/login')
    }
    const onFormSubmit = async (event) => {
        event.preventDefault();
        const el = document.getElementById('address')
        const address = el.value
        function validateEmail(email) {
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            return emailRegex.test(email);
        }
        function validateAddress(address) {
            return address.trim() !== '';
        }
        function validateFirstName(firstName) {
            return /^[a-zA-Z]+$/.test(firstName);
        }
        function validateLastName(lastName) {
            return /^[a-zA-Z]+$/.test(lastName);
        }
        function validatePassword(password) {
            return password.length >= 8;
        }
        function validatePasswordRetype(password, passwordRetype) {
            return password === passwordRetype;
        }
        if (!fname || !lname || !email || !address || !password || !passwordRetype) {
            setValMessage('None of the fields can be empty');
        } else {
           
            if (!validateEmail(email)) {
                setValMessage("invalid email")
            }
            if (!validateAddress(address)) {
                setValMessage("invalid addess")
            }
            if (!validateFirstName(fname)) {
                setValMessage("invalid firstname")
            }
            if (!validateLastName(lname)) {
                setValMessage("invalid lastname")
            }
            if (!validatePassword(password)) {
                setValMessage("invalid password")
            }
            if (!validatePasswordRetype(password,passwordRetype)) {
                setValMessage("password doesn't match")
            }
        };
        if(validateAddress(address) && validateEmail(email) && validateFirstName(fname) && validateLastName(lname) && validatePassword(password) && validatePasswordRetype(password, passwordRetype))
        {
            const user = {
                email,address,
                firstname:fname,
                lastname: lname,
                password
            }
            const res = register(user)
            res.then(res => {
                navigate('/login')    
                setValMessage(res)
            })
        }


    }
    const handleKeyDown = (event) => {
        if(event.key === 'Enter'){
            event.preventDefault()
        }
    }
        return (
            <div>
                <div className='reg-body-container'>
                    <div className='reg-input-container'>
                        <form onSubmit={onFormSubmit}>
                            <div className='reg-input-wrapper '>
                                <div>
                                    <div className='flex-row'>
                                        <div className='reg-input'>
                                            <Input
                                                state={[fname, setFname]}
                                                htmlFor='fname'
                                                fieldClassName='input-field'
                                                labelClassName='input-label'
                                                labelContent='First Name: '
                                                required={true}
                                                type='text'
                                            />
                                        </div>
                                        <div className='reg-input'>
                                            <Input
                                                state={[lname, setLname]}
                                                htmlFor='lname'
                                                fieldClassName='input-field'
                                                labelClassName='input-label'
                                                labelContent='Last Name: '
                                                required={true}
                                                type='text'
                                            />
                                        </div>
                                    </div>
                                    <div className='reg-input'>
                                        <Input
                                            state={[email, setEmail]}
                                            htmlFor='email'
                                            fieldClassName='input-field'
                                            labelClassName='input-label'
                                            labelContent='Email: '
                                            required={true}
                                            type='text'
                                        />
                                    </div>
                                    <div className='reg-input'>
                                        <div className="input-component-container">
                                            <label className="input-component-label input-label" htmlFor="address">Adress: <span style={{ color: 'red' }}>*</span></label>
                                            <input id="address" 
                                            onKeyDown={handleKeyDown} 
                                            className="input-component-field" type='text'
                                                ref={addressRef}
                                                htmlFor="address"
                                                />
                                        </div>
                                    </div>
                                    <div className='reg-input'>
                                        <Input
                                            state={[password, setPassword]}
                                            htmlFor={'password'}
                                            labelContent={'Password: '}
                                            fieldClassName='input-field'
                                            labelClassName='input-label'
                                            required={true}
                                            type='password'
                                        />
                                    </div>
                                    <div className='reg-input'>
                                        <Input
                                            state={[passwordRetype, setPasswordRetype]}
                                            htmlFor={'passwordretype'}
                                            labelContent={'Retype Password: '}
                                            fieldClassName='input-field'
                                            labelClassName='input-label'
                                            required={true}
                                            type='password'
                                        />
                                    </div>
                                </div>

                            </div>
                            <span style={{ color: 'red' }}>{valMessage}</span>
                            <div className='login-button-container'>
                                <button className='button' type="submit">Register</button>
                            </div>
                        </form>
                        <div style={{ margin: '10px' }}>
                            <span>Or </span>
                            <span style={{ color: 'red' }} onClick={handleLoginNavigate}>
                                Login
                            </span>
                        </div>
                    </div>
                    <div className='login-vertical-line'>
                    </div>
                    <div className='arz-pic-login'>
                        <img src={arzPic} width='800px' />
                    </div>
                </div>
                <div>
                    <Footer />
                </div>
            </div>)
    }