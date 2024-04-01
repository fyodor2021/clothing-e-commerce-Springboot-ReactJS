import { useEffect, useState } from 'react'
import Input from '../components/Input'
import arzPic from '../statics/arz-fine-foods-mis.png'
import { useNavigate } from 'react-router-dom';
import Footer from '../components/Footer'
import useAuthContext from '../hooks/useAuthContext';
import useValidationContext from '../hooks/useValidationContext';
export default function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    useEffect(() => {
        if(window.localStorage.getItem(process.env.REACT_APP_AUTH_TOKEN_LOCAL)){
            navigate("/")
        }
    },[])
    const {login,token} = useAuthContext();
        const {valMessage,setValMessage} = useValidationContext();
    const navigate = useNavigate();
    const handleRegisterNavigate = () => {
        navigate('/register')
    }

    const onFormSubmit = async (event) => {
        event.preventDefault();
        if (!username || !password) {
            setValMessage('None of the fields can be empty');
        } else {
            if (username.length < 3) {
                setValMessage('Not a valid username');
            } else if (password.length < 3) {
                setValMessage('Not a valid password');
            } else {
                const user = {
                    email:username.toLowerCase(),
                    password,
                }
                const res = login(user)
                // .then(res => {
                //     if(res.response){
                //         setValMessage("User Not Found.")
                //     }else{
                //         navigate('/')
                //     }
                // }).catch(err => {
                //     console.log(err)
                // })
            }
        }
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
                            <button className='button' type='submit'>Login</button>
                        </div>
                    </form>
                    <div style={{ margin: '10px' }}>
                        <span>Or </span>
                        <span style={{ color: 'red',cursor:'pointer' }} onClick={handleRegisterNavigate}>
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