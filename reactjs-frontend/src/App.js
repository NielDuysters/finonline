"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const jsx_runtime_1 = require("react/jsx-runtime");
const Routes_1 = __importDefault(require("./routes/Routes"));
require("./styles/App.sass");
const useAuth_1 = require("./hooks/useAuth");
const AuthContext_1 = require("./context/AuthContext");
const Header_1 = __importDefault(require("./components/Header/Header"));
function App() {
    const { user, isLoading } = (0, useAuth_1.useAuth)();
    return ((0, jsx_runtime_1.jsx)(AuthContext_1.AuthContext.Provider, Object.assign({ value: { user, isLoading } }, { children: (0, jsx_runtime_1.jsxs)("div", Object.assign({ className: "App" }, { children: [(0, jsx_runtime_1.jsx)(Header_1.default, {}), (0, jsx_runtime_1.jsx)("main", Object.assign({ className: "App-content" }, { children: (0, jsx_runtime_1.jsx)(Routes_1.default, {}) }))] })) })));
}
exports.default = App;
