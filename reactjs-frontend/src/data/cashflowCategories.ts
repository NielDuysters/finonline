import Cookies from "js-cookie"
import axios from "axios"
import * as appConstants from "../appConstants"

export async function addCategory(data: Record<string, string>) {
    const authToken = Cookies.get("auth-token")
    
    try {
       const response = await axios.post(`${appConstants.URL}/cashflowcategories`,
            data
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
            throw new Error("Label " + data.label + " already exists.")
        }
        
        throw err
    }
}

export async function deleteCategory(id: number) {
    const authToken = Cookies.get("auth-token")

    try {
       const response = await axios.delete(`${appConstants.URL}/cashflowcategories/${id}`,
        {
            insecureHTTPParser: true,
            headers: {
                Authorization: `Bearer ${authToken}`,
            }
        })
   
        if (response.status === 200) {
            return response
        }
    } catch (err: any) {
        if (err.response.status === 409) {
            throw new Error("Error deleting")
        }
        
        throw err
    }
}

export async function getCashflowCategoryItems() {
    const authToken = Cookies.get("auth-token")

    try {
        const response = await axios.get(`${appConstants.URL}/cashflowcategories`,
            {
                insecureHTTPParser: true,
                headers: {
                    Authorization: `Bearer ${authToken}`
                }
            })

        if (response.status === 200) {
            return response.data
        } else {
            return []
        }
    } catch (err) {
        return []
    }
}
