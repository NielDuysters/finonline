import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router-dom"
import loadable from "@loadable/component"
import { useAuth } from "../hooks/useAuth"

const Home = loadable(() => import("../views/Home/Home"))
const Panel = loadable(() => import("../views/Panel/Panel"))

const ProtectedRoute = ({Component} : { Component: React.ComponentType }) => {
    
    const { user, isLoading } = useAuth()

    if (isLoading) {
        console.log("Is loading")
        return null
    }

    if (user == null) {
        return <Navigate to="/" replace />
    }

    return <Component />
}

const AppRouter = () => {
    return (
            <Router>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/panel" element={<ProtectedRoute Component={Panel} />} />
                </Routes>
            </Router>
    )
}

export default AppRouter
