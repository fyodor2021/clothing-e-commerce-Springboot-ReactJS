import { useState } from 'react'
import Input from '../components/Input'
import arzPic from '../statics/arz-fine-foods-mis.png'
import { useNavigate } from 'react-router-dom';
import Footer from '../components/Footer'
export default function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [valMessage, setValMessage] = useState('');
    const [login, setLogin] = useState(false)
    const navigate = useNavigate();
    const handleClick = () => {
        // setReg(false);
    };
    const handleRegisterNavigate = () => {
        navigate('/register')
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
    return (
        <div>
            <div className='login-body-container'>
                <div className='login-input-container'>
                    <form onSubmit={onFormSubmit}>
                        <div className='login-input'>
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

                        <div className='login-input'>
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
                        <span style={{ color: 'red' }}>{valMessage}</span>
                        <div className='login-button-container'>
                            <button className='button'>Login</button>
                        </div>
                    </form>
                    <div style={{ margin: '10px' }}>
                        <span>Or </span>
                        <span style={{ color: 'red' }} onClick={handleRegisterNavigate}>
                            Register
                        </span>
                    </div>

                </div>
                <div className='login-vertical-line'>
                    </div>
                <div className='arz-pic-login'>
                    <img src={arzPic} width='800px'/>
                </div>
            </div>
            <div>
                <Footer/>
            </div>
        </div>
    )

}