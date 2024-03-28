import React, { useState } from "react"
import { setUserStartCapital } from "../../data/users"
import * as Types from "../../types"

interface SetStartCapitalFormProps {
    user: Types.User | null
    setUser: React.Dispatch<React.SetStateAction<Types.User | null>>
}

export function SetStartCapitalForm({user, setUser} : SetStartCapitalFormProps) {
    const [formData, setFormData] = useState({
        newStartCapital: 0.00,
    })
    const [formMessage, setFormMessage] = useState({
        type: "",
        message: "",
    })
    
    const handleSubmit = async (event: React.FormEvent<HTMLFormElement> | React.MouseEvent<HTMLInputElement, MouseEvent>) => {
        event.preventDefault()
     
        try {
            const response = await setUserStartCapital(formData)
            setUser(response)
        } catch (err: any) {
            if (err instanceof Error) {
                setFormMessage({
                    type: "error",
                    message: `Error: ${err.message}`
                })
            }
        }
    }
    
    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFormData(prevFormData => ({
            ...prevFormData,
            [event.target.name]: event.target.value                    
        }))    
    }

    return (
        <div>
            <div id="popup-background" />
            <div className="popup">
                <div className="close-popup">&times;</div>
                <span className="popup-title">Set start capital</span>
                <p>Input the current capital of the account you want to track your finances of.</p>
                <form id="set-startcapital-form" onSubmit={(event) => handleSubmit(event)}>
                    <div className="input-wrapper">
                        <span className="label">Amount</span>
                        <span className="input-icon">&euro;</span>
                        <input type="text" name="newStartCapital" className="input-padding-left" placeholder="0,00" onChange={handleChange}  />
                    </div>
                    <input type="submit" value="Set" />
                </form>
                <span style={{color: "#FF0000"}} className="popup-response-message">{ formMessage.message }</span>
            </div>
        </div>
    )
}
