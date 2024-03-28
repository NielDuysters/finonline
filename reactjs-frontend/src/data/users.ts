import Cookies from "js-cookie"
import axios from "axios"
import * as appConstants from "../appConstants"

export async function getUser() {
    const authToken = Cookies.get("auth-token")

    try {
       const response = await axios.get(`${appConstants.URL}/users`,
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

export async function setUserStartCapital(data: Record<string, number>) {
    const authToken = Cookies.get("auth-token")

    try {
       const response = await axios.patch(`${appConstants.URL}/users`,
            data
       ,
        {
            insecureHTTPParser: true,
            headers: {
                Authorization: `Bearer ${authToken}`,
                "Content-Type": "application/json",
            }
        })
  
        if (response.status === 200) {
            return response.data
        }
    } catch (err: any) {
        throw err
    }
}
