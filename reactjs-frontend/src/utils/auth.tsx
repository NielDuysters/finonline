import Cookies from "js-cookie"
import axios from "axios"
import * as appConstants from "../appConstants"

const AuthenticatedUser = async () => {
    const authToken = Cookies.get("auth-token")

    if (authToken == null) {
        return null
    }

    try {
        const response = await axios.get(`${appConstants.URL}/auth`,
            {
                insecureHTTPParser: true,
                headers: {
                    Authorization: `Bearer ${authToken}`
                }
            })

        if (response.status === 200) {
            return response.data
        } else {
            return null
        }
    } catch (err) {
        return null
    }
}

export default AuthenticatedUser
