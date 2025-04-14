import {useEffect} from "react";
import Input from "../components/Input";
import josedorCard from "../statics/josedor-card.png";
import {useNavigate} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {
    setEmail,
    setLoggedAddress,
    setLoggedEmail,
    setLoggedFname,
    setLoggedLname,
    setPassword,
    setResMessage,
    setToken, useLazyFetchCartProductsQuery,
    useLoginMutation,
} from "../store";
import useCartContext from "../hooks/useCartContext";

export default function Login() {
    const [login, loginResult] = useLoginMutation();
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const {
        email,
        emailVal,
        emailValMes,
        password,
        passwordVal,
        passwordValMes,
    } = useSelector((state) => {
        return state.input;
    });
    const {handleAddToCart} = useCartContext();

    const {resMessage} = useSelector((state) => {
        return state.validation;
    });
    const {value: token} = useSelector((state) => {
        return state.token;
    });
    const [fetchCartProducts, fetchCartProductsResult] =
        useLazyFetchCartProductsQuery();
    const handleRegisterNavigate = () => {
        navigate("/register");
    };

    useEffect(() => {
      if (fetchCartProductsResult.isSuccess) {
        for (let cartProduct of fetchCartProductsResult.data){
            handleAddToCart(
             cartProduct.productId, cartProduct.size,
             cartProduct.quantity,
            )
        }
      }
    }, [fetchCartProductsResult]);



    useEffect(() => {
        if (loginResult.isSuccess) {
            if (loginResult.data.statusCodeValue == 200) {
                if (loginResult && loginResult.data) {
                    console.log(loginResult.data);
                    dispatch(setToken(loginResult.data.body.token));
                    dispatch(setLoggedFname(loginResult.data.body.fname));
                    dispatch(setLoggedLname(loginResult.data.body.lname));
                    dispatch(setLoggedEmail(loginResult.data.body.email));
                    dispatch(setLoggedAddress(loginResult.data.body.address));
                    const cartProductReq = {
                        userEmail: loginResult.data.body.email,
                    };
                    fetchCartProducts(cartProductReq);
                    // window.localStorage.removeItem("cart");
                    // window.location.reload();
                }
            } else if (loginResult.data.statusCodeValue == 401) {
                dispatch(setResMessage("user was not found"));
            }
        }
    }, [loginResult]);

    const onFormSubmit = async (event) => {
        event.preventDefault();
        if (emailVal || passwordVal) {
            const user = {
                email: email.toLowerCase(),
                password,
            };

            login(user);

        } else {
            dispatch(setResMessage("Please enter the correct values"));
        }
    };
    const inputFieldStyle = "input-field h-[35px] text-[.95rem] w-full rounded-[3px]";
    const inputLabelStyle = "input-label text-[.85rem] m-0 p-1";

    return (
        <div className="flex flex-col-reverse w-full pt-48 xl:flex-row xl:pt-0">
            <div className="w-full flex justify-center items-center flex-col">
                <form
                    onSubmit={onFormSubmit}
                    className="bg-[#f5f4f4] w-full md:w-[50%] p-3 rounded-xl"
                >
                    <div className="p-3 h-36 flex items-start flex-col justify-center">
                        <div className="text-2xl text-bold">Welcome Back!</div>
                        <div className="text-xl">Let's get you logged in!</div>
                    </div>
                    <Input
                        setState={setEmail}
                        state={email}
                        val={emailVal}
                        valMes={emailValMes}
                        htmlFor="email"
                        fieldClassName={inputFieldStyle}
                        labelClassName={inputLabelStyle}
                        labelContent="Email: "
                        required={true}
                        type="text"
                    />

                    <Input
                        setState={setPassword}
                        state={password}
                        val={passwordVal}
                        valMes={passwordValMes}
                        htmlFor={"password"}
                        labelContent={"Password: "}
                        fieldClassName={inputFieldStyle}
                        labelClassName={inputLabelStyle}
                        required={true}
                        type="password"
                    />
                    <span style={{color: "red", padding: "3px 10px"}}>
            {resMessage}
          </span>
                    <div className="login-button-container">
                        <button className="button" type="submit">
                            Login
                        </button>
                        <div style={{margin: "10px"}}>
                            <span>Or </span>
                            <span
                                style={{color: "red", cursor: "pointer"}}
                                onClick={handleRegisterNavigate}
                            >
                Register
              </span>
                        </div>
                    </div>
                </form>
            </div>
            <div className="w-full flex justify-center items-center xl:h-screen xl:bg-black xl:w-[80%] mb-2 xl:mb-0 ">
                <img src={josedorCard} className=" md:w-[50%] xl:w-full rounded-xl "/>
            </div>
        </div>
    );
}
