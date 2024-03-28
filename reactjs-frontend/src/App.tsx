import AppRouter from "./routes/Routes"
import "./styles/App.sass"
import { useAuth } from "./hooks/useAuth"
import { AuthContext } from "./context/AuthContext"
import Header from "./components/Header/Header"

function App() {
    const { user, isLoading } = useAuth()
    
    return (
        <AuthContext.Provider value={{ user,  isLoading }}>
            <div className="App">
                <Header />
                
                <main className="App-content">
                    <AppRouter />
                </main>
            </div>
        </AuthContext.Provider>
    )
}

export default App
