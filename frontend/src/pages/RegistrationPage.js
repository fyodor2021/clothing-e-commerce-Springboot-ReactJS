import { useState } from 'react'
import Input from '../components/Input'
import arzPic from '../statics/arz-fine-foods-mis.png'
import Footer from '../components/Footer';
import { useNavigate } from 'react-router-dom';
export default function RegistrationPage() {
    const [Email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [address, setAddress] = useState('');
    const [province, setProvince] = useState('');
    const [dob, setDob] = useState('');
    const [password, setPassword] = useState('');
    const [passwordRetype, setPasswordRetype] = useState('');
    const [valMessage, setValMessage] = useState('');
    const [login, setLogin] = useState(false)
    const navigate = useNavigate()
    const handleClick = () => {
        // setReg(false);
    };
    const handleLoginNavigate = () => {
        navigate('/login')
    }
    const onFormSubmit = async (event) => {
        event.preventDefault();
        // setUsername('');
        // setPassword('');
        // if (!username || !password) {
        //     setValMessage('None of the fields can be empty');
        // } else {
        //     if (username.length < 3) {
        //         setValMessage('Not a valid username');
        //     } else if (password.length < 3) {
        //         setValMessage('Not a valid password');
        //     } else {
        //         const user = await axios.post('http://localhost:8079/login', {
        //             username,
        //             password,
        //         }).catch(err => setValMessage(err))
        //         if(user.data.length > 0){
        //             localStorage.setItem('login',true)
        //             setLogin(true)
        //         }
        //         setValMessage('');
        //     }
        // }
    };

    const handleUserChange = (event) => {
        setUsername(event.target.value);
    };

    const handlePassChange = (event) => {
        setPassword(event.target.value.trim());
    };
    return (
        <div>
            <div className='reg-body-container'>
                <div className='reg-input-container'>
                    <form onSubmit={onFormSubmit}>
                        <div className='reg-input-wrapper '>
                            <div >
                                <div  className='reg-input'>
                                    <Input
                                        state={[Email, setEmail]}
                                        htmlFor='email'
                                        fieldClassName='input-field'
                                        labelClassName='input-label'
                                        labelContent='Email: '
                                        required={true}
                                        type='text'
                                    />
                                </div>
                                <div className='reg-input'>
                                    <Input
                                        state={[username, setUsername]}
                                        htmlFor='username'
                                        fieldClassName='input-field'
                                        labelClassName='input-label'
                                        labelContent='Username: '
                                        required={true}
                                        type='text'
                                    />
                                </div>

                                <div className='reg-input'>
                                    <Input
                                        state={[address, setAddress]}
                                        htmlFor={'address'}
                                        labelContent={'Address: '}
                                        fieldClassName='input-field'
                                        labelClassName='input-label'
                                        required={true}
                                        type='text'
                                    />
                                </div>
                            </div>
                            <div>
                                <div className='reg-input'>
                                    <Input
                                        state={[province, setProvince]}
                                        htmlFor={'province'}
                                        labelContent={'Province: '}
                                        fieldClassName='input-field'
                                        labelClassName='input-label'
                                        required={true}
                                        type='text'
                                    />
                                </div>
                                <div className='reg-input'>
                                    <Input
                                        state={[dob, setDob]}
                                        htmlFor={'date'}
                                        labelContent={'Date of Birth: '}
                                        fieldClassName='input-field'
                                        labelClassName='input-label'
                                        required={true}
                                        type='date'
                                    />
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
                            <button className='button'>Register</button>
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