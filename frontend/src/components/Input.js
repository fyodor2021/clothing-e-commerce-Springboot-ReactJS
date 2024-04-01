export default function Input({
    htmlFor,
    labelContent,
    state,
    fieldClassName,
    labelClassName,
    required,
    type
}) {
    const [inputState, setInputState] = state;
    const handleInputChange = (event) => {
        setInputState(event.target.value)

    }
    return(    
<div className="input-component-container">
        <label className={"input-component-label " + labelClassName} htmlFor={htmlFor}>{labelContent}{required ? <span style={{ color: 'red' }}>*</span> :''}</label>
        <input className={"input-component-field " + fieldClassName} type={type ? type : 'text'}
            htmlFor={htmlFor}
            value={inputState}
            onChange={handleInputChange} />
    </div>
    )
}