import Cookies from "js-cookie"
import axios from "axios"
import * as appConstants from "../appConstants"

export async function getCashflow() {
    const authToken = Cookies.get("auth-token")

    try {
        const response = await axios.get(`${appConstants.URL}/cashflow`,
        {
            insecureHTTPParser: true,
            headers: {
                Authorization: `Bearer ${authToken}`,
            }
        })

        if (response.status === 200) {
            return response.data
        }
    } catch (err: any) {
        throw err
    }
}

export async function addCashflow(data: Record<string, any>) {
    const authToken = Cookies.get("auth-token")

    try {
       const response = await axios.post(`http://127.0.0.1:8080/cashflow`,
            data["formData"]
        ,
        {
            insecureHTTPParser: true,
            headers: {
                Authorization: `Bearer ${authToken}`,
                "Content-Type": "application/json",
            }
        })
   
        if (response.status === 201) {
            return response
        }
    } catch (err: any) {
        if (err.response.status === 409) {
            if (err.response.data === "") {
                err.response.data = "Invalid input."
            }

            throw new Error(`${err.response.data}`)
        }
        
        throw err
    }
}

