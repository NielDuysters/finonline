import { createContext } from "react"
import { User } from "../hooks/useUser"

interface AuthContext {
    user: User | null
    isLoading: boolean
}

export const AuthContext = createContext<AuthContext>({
    user: null,
    isLoading: true,
})
