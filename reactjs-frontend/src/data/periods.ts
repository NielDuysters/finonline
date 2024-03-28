import Cookies from "js-cookie"
import axios from "axios"
import * as appConstants from "../appConstants"

export async function getPeriods() {
    const authToken = Cookies.get("auth-token")

    try {
       const response = await axios.get(`${appConstants.URL}/charts/periods`,
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
