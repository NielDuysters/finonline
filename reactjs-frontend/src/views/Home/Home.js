"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const jsx_runtime_1 = require("react/jsx-runtime");
const react_1 = require("react");
const AuthContext_1 = require("../../context/AuthContext");
const axios_1 = __importDefault(require("axios"));
const js_cookie_1 = __importDefault(require("js-cookie"));
require("../../styles/Home.sass");
const appConstants = __importStar(require("../../appConstants"));
class Home extends react_1.Component {
    constructor(props, context) {
        super(props, context);
        this.handleChange = (event) => {
            let inputs = this.state.inputs;
            inputs[event.target.name] = event.target.value;
            this.setState({ inputs });
        };
        this.state = {
            message: {},
            inputs: {},
        };
        this.handleChange = this.handleChange.bind(this);
        this.Form = this.Form.bind(this);
        this.UserGreeting = this.UserGreeting.bind(this);
    }
    render() {
        return ((0, jsx_runtime_1.jsxs)("div", Object.assign({ id: "container" }, { children: [(0, jsx_runtime_1.jsx)("div", Object.assign({ id: "text" }, { children: (0, jsx_runtime_1.jsxs)("div", { children: [(0, jsx_runtime_1.jsx)("h1", { children: "Welcome to Finonline" }), (0, jsx_runtime_1.jsx)("span", Object.assign({ id: "slogan" }, { children: "Administrate your personal expenses online." })), (0, jsx_runtime_1.jsx)("p", { children: "This is a demo-project for my job-application showcasing my skills in full-stack webdevelopment, Java, Spring Boot and React.js." })] }) })), (0, jsx_runtime_1.jsx)("div", Object.assign({ id: "form-container" }, { children: (0, jsx_runtime_1.jsx)(this.UserGreeting, {}) }))] })));
    }
    UserGreeting() {
        const { user } = this.context;
        if (user != null) {
            return ((0, jsx_runtime_1.jsxs)("div", Object.assign({ id: "welcome" }, { children: [(0, jsx_runtime_1.jsxs)("span", Object.assign({ className: "title" }, { children: ["Welcome ", user.name] })), (0, jsx_runtime_1.jsx)("a", Object.assign({ href: "/panel", className: "button" }, { children: "Go to panel." }))] })));
        }
        else {
            return (0, jsx_runtime_1.jsx)(this.Form, {});
        }
    }
    Form() {
        const handleSubmit = (event, action) => {
            event.preventDefault();
            switch (action) {
                case "register":
                    this.register(this.state.inputs);
                    break;
                default:
                    this.login(this.state.inputs);
            }
        };
        return ((0, jsx_runtime_1.jsxs)("form", Object.assign({ onSubmit: (event) => handleSubmit(event, 'login') }, { children: [(0, jsx_runtime_1.jsx)("span", Object.assign({ className: "title" }, { children: "Login" })), (0, jsx_runtime_1.jsxs)("div", Object.assign({ className: "input-wrapper" }, { children: [(0, jsx_runtime_1.jsx)("img", { src: "/assets/images/profile.png", alt: "" }), (0, jsx_runtime_1.jsx)("input", { type: "text", name: "name", placeholder: "Username", minLength: 3, required: true, onChange: this.handleChange })] })), (0, jsx_runtime_1.jsxs)("div", Object.assign({ className: "input-wrapper" }, { children: [(0, jsx_runtime_1.jsx)("img", { src: "/assets/images/password.png", alt: "" }), (0, jsx_runtime_1.jsx)("input", { type: "password", name: "pass", placeholder: "Password", minLength: 5, required: true, onChange: this.handleChange })] })), (0, jsx_runtime_1.jsxs)("div", Object.assign({ id: "form-buttons" }, { children: [(0, jsx_runtime_1.jsx)("input", { type: "submit", name: "login", value: "Login", onClick: (event) => handleSubmit(event, 'login') }), (0, jsx_runtime_1.jsx)("input", { type: "submit", name: "register", value: "Or register", id: "registerbtn", onClick: (event) => handleSubmit(event, 'register') })] })), (0, jsx_runtime_1.jsx)("span", Object.assign({ className: `message ${this.state.message["type"]}` }, { children: this.state.message["text"] }))] })));
    }
    register(data) {
        return __awaiter(this, void 0, void 0, function* () {
            let message = this.state.message;
            try {
                const response = yield axios_1.default.post(`${appConstants.URL}/users`, data, {
                    insecureHTTPParser: true,
                    headers: {
                        "Content-Type": "application/json",
                    }
                });
                if (response.status === 201) {
                    message["type"] = "ok";
                    message["text"] = "Successfully registered.";
                    this.setState({ message });
                }
                else {
                    message["type"] = "error";
                    message["text"] = "Error: " + response.data;
                    this.setState({ message });
                }
            }
            catch (err) {
                message["type"] = "error";
                message["text"] = `Error: ${err.message}`;
                this.setState({ message });
            }
        });
    }
    login(data) {
        return __awaiter(this, void 0, void 0, function* () {
            let message = this.state.message;
            try {
                const response = yield axios_1.default.post(`${appConstants.URL}/auth`, data, {
                    insecureHTTPParser: true,
                    headers: {
                        "Content-Type": "application/json",
                    }
                });
                if (response.status === 200) {
                    message["type"] = "ok";
                    message["text"] = "Login successful.";
                    this.setState({ message });
                    const token = response.data.token;
                    js_cookie_1.default.set("auth-token", token, { expires: 1 });
                    window.location.replace("/panel");
                }
                else {
                    message["type"] = "error";
                    message["text"] = "Error: Login failed.";
                    this.setState({ message });
                }
            }
            catch (err) {
                message["type"] = "error";
                message["text"] = "Error: Login failed.";
                this.setState({ message });
            }
        });
    }
}
Home.contextType = AuthContext_1.AuthContext;
exports.default = Home;
