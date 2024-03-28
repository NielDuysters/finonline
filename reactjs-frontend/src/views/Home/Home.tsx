import React, { Component } from "react"
import { AuthContext } from "../../context/AuthContext"
import axios from "axios"
import Cookies from "js-cookie"
import "../../styles/Home.sass"
import * as appConstants from "../../appConstants"

interface HomeProps {}

interface HomeState {
    message: Record<string, string>
    inputs: Record<string, string>
}

class Home extends Component<HomeProps, HomeState> {

    static contextType = AuthContext
    context!: React.ContextType<typeof AuthContext>
        
    constructor(props: HomeProps, context: React.ContextType<typeof AuthContext>) {
        super(props, context)
        
        this.state = {
            message: {},
            inputs: {},
        }

        this.handleChange = this.handleChange.bind(this)
        this.Form = this.Form.bind(this)
        this.UserGreeting = this.UserGreeting.bind(this)
    }


    handleChange : React.ChangeEventHandler<HTMLInputElement> = (event) => {
        let inputs = this.state.inputs
        inputs[event.target.name] = event.target.value
        this.setState({inputs})
    }

    render() {
        return (
            <div id="container">
                <div id="text">
                    <div>
                        <h1>Welcome to Finonline</h1>
                        <span id="slogan">Administrate your personal expenses online.</span>
                        <p>
                            This is a demo-project for my job-application showcasing my skills in full-stack webdevelopment, Java, Spring Boot and React.js.
                        </p>
                    </div>
                </div>
                <div id="form-container">
                    <this.UserGreeting />
                </div>
            </div>
        )
    }

    UserGreeting() {
        const { user } = this.context

        if (user != null) {
            return (
                <div id="welcome">
                    <span className="title">Welcome {user.name}</span>
                    <a href="/panel" className="button">Go to panel.</a>
                </div>
            )
        } else {
            return <this.Form />
        }
    }

    Form() {
        const handleSubmit = (event: React.FormEvent<HTMLFormElement> | React.MouseEvent<HTMLInputElement, MouseEvent>, action: string) => {
            event.preventDefault()
            
            switch (action) {
                case "register":
                    this.register(this.state.inputs)
                    break
                default:
                    this.login(this.state.inputs)
            }
        }

        return (
            <form onSubmit={(event) => handleSubmit(event, 'login')} >
                <span className="title">Login</span>
                <div className="input-wrapper">
                    <img src="/assets/images/profile.png" alt="" />
                    <input type="text" name="name" placeholder="Username" minLength={3} required={true} onChange={this.handleChange} />
                </div>
                <div className="input-wrapper">
                    <img src="/assets/images/password.png" alt="" />
                    <input type="password" name="pass" placeholder="Password" minLength={5} required={true} onChange={this.handleChange} />
                </div>
                <div id="form-buttons">
                    <input type="submit" name="login" value="Login" onClick={(event) => handleSubmit(event, 'login')} />
                    <input type="submit" name="register" value="Or register" id="registerbtn" onClick={(event) => handleSubmit(event, 'register')} />
                </div>
                <span className={`message ${this.state.message["type"]}`}>
                    { this.state.message["text"] }
                </span>
            </form>
        )
    }

    async register(data: Record<string, string>) {
        let message = this.state.message

        try {
            const response = await axios.post(`${appConstants.URL}/users`,
                    data
                ,
                {
                    insecureHTTPParser: true,
                    headers: {
                        "Content-Type": "application/json",
                    }
                })

            if (response.status === 201) {
                message["type"] = "ok"
                message["text"] = "Successfully registered."
                
                this.setState({ message })
            } else {
                message["type"] = "error"
                message["text"] = "Error: " + response.data
                
                this.setState({ message })
            }
        } catch (err: any) {
            message["type"] = "error"
            message["text"] = `Error: ${err.message}`
            
            this.setState({ message })

        }
    }

    async login(data: Record<string, string>) {
       let message = this.state.message

        try {
           const response = await axios.post(`${appConstants.URL}/auth`,
                    data
                ,
                {
                    insecureHTTPParser: true,
                    headers: {
                        "Content-Type": "application/json",
                    }
                })
            if (response.status === 200) {
                message["type"] = "ok"
                message["text"] = "Login successful."
                
                this.setState({ message  })

                const token = response.data.token
                Cookies.set("auth-token", token, { expires: 1 })

                window.location.replace("/panel")
            } else {
                message["type"] = "error"
                message["text"] = "Error: Login failed."
                
                this.setState({ message  })
            }
        } catch (err) {
            message["type"] = "error"
            message["text"] = "Error: Login failed."

            this.setState({ message  })
        }
    }
}

export default Home
