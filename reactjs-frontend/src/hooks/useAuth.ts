import { useEffect, useState } from "react"
import AuthenticatedUser from "../utils/auth"

export const useAuth = () => {
    const [isLoading, setIsLoading] = useState(true) 
    const [user, setUser] = useState(null)

    useEffect(() => {
        const checkAuth = async() => {

            const authenticatedUser = await AuthenticatedUser()

            if (authenticatedUser == null) {
                setUser(null)
            } else {
                setUser(authenticatedUser)
            }

            setIsLoading(false)
        }

        checkAuth()
    }, [])

    return { user, setUser, isLoading }
}
