import { useDispatch, useSelector } from "react-redux"
import { useRef, useEffect } from "react";
import { setAddress } from "../store";
export default function Input({
    htmlFor,
    labelContent,
    setState,
    state,
    val,
    fieldClassName,
    labelClassName,
    required,
    type,
    valMes,
    address = false
}) {
    const addressRef = useRef(state);
    const autoCompleteRef = useRef();
    const dispatch = useDispatch();
    const options = {
        componentRestrictions: { country: 'CA' },
        fields: ["formatted_address"],
        types: ["address"]
    };

    useEffect(() => {
        if (addressRef.current) {
            autoCompleteRef.current = new window.google.maps.places.Autocomplete(
                addressRef.current,
                options
            );
            autoCompleteRef.current.addListener('place_changed', handlePlaceSelect);
        }
    }, [address]);
    const handleKeyDown = (event) => {
        if (event.key === 'Enter') {
            event.preventDefault()
            dispatch(setState(event.target.value))
        }
    }

    const handleInputChange = (event) => {
        dispatch(setState(event.target.value))
    }

    const handleMouseDown = (event) => {
        dispatch(setState(event.target.value))
    }
    const handlePlaceSelect = () => {
        dispatch(setAddress(addressRef.current.value));
    }
    return (
        !address ? (
            <div className="input-component-container">
                <label className={"input-component-label " + labelClassName} htmlFor={htmlFor}>
                    {labelContent}
                    {required ? <span style={{ color: 'red' }}>*</span> : ''}
                </label>
                <input
                    className={"input-component-field " + fieldClassName}
                    type={type ? type : 'text'}
                    style={{
                        outline: '.5px solid',
                        borderColor: val ? (state ? 'green' : 'red') : 'gray'
                    }}
                    htmlFor={htmlFor}
                    value={state}
                    onChange={handleInputChange}
                />
                <div className={"text-red-600 text-[.75rem] p-1"}>{valMes}</div>
            </div>
        ) : (
            <div className="input-component-container">
            <label className="input-label text-[.85rem] m-0 p-1" htmlFor="address">
                Address: <span style={{ color: 'red' }}>*</span>
            </label>
            <input
                id="address"
                onChange={handleInputChange}
                className={"input-component-field h-[35px] text-[.95rem] w-full rounded-[3px] " + 
                    (val  ? 'border border-gray-400' : 
                    (val === false ? 'border border-red-600' : 'border border-gray-400'))}
                onKeyDown={handleKeyDown}
                onMouseDown={handleMouseDown}
                type='text'
                ref={addressRef}
                htmlFor="address"
            />
            <div style={{ color: 'red' }}>{valMes}</div>
        </div>
        )
    );
}