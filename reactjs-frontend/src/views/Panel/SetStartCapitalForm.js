"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.SetStartCapitalForm = void 0;
const jsx_runtime_1 = require("react/jsx-runtime");
const react_1 = require("react");
const users_1 = require("../../data/users");
function SetStartCapitalForm({ user, setUser }) {
    const [formData, setFormData] = (0, react_1.useState)({
        newStartCapital: 0.00,
    });
    const [formMessage, setFormMessage] = (0, react_1.useState)({
        type: "",
        message: "",
    });
    const handleSubmit = (event) => __awaiter(this, void 0, void 0, function* () {
        event.preventDefault();
        try {
            const response = yield (0, users_1.setUserStartCapital)(formData);
            setUser(response);
        }
        catch (err) {
            if (err instanceof Error) {
                setFormMessage({
                    type: "error",
                    message: `Error: ${err.message}`
                });
            }
        }
    });
    const handleChange = (event) => {
        setFormData(prevFormData => (Object.assign(Object.assign({}, prevFormData), { [event.target.name]: event.target.value })));
    };
    return ((0, jsx_runtime_1.jsxs)("div", { children: [(0, jsx_runtime_1.jsx)("div", { id: "popup-background" }), (0, jsx_runtime_1.jsxs)("div", Object.assign({ className: "popup" }, { children: [(0, jsx_runtime_1.jsx)("div", Object.assign({ className: "close-popup" }, { children: "\u00D7" })), (0, jsx_runtime_1.jsx)("span", Object.assign({ className: "popup-title" }, { children: "Set start capital" })), (0, jsx_runtime_1.jsx)("p", { children: "Input the current capital of the account you want to track your finances of." }), (0, jsx_runtime_1.jsxs)("form", Object.assign({ id: "set-startcapital-form", onSubmit: (event) => handleSubmit(event) }, { children: [(0, jsx_runtime_1.jsxs)("div", Object.assign({ className: "input-wrapper" }, { children: [(0, jsx_runtime_1.jsx)("span", Object.assign({ className: "label" }, { children: "Amount" })), (0, jsx_runtime_1.jsx)("span", Object.assign({ className: "input-icon" }, { children: "\u20AC" })), (0, jsx_runtime_1.jsx)("input", { type: "text", name: "newStartCapital", className: "input-padding-left", placeholder: "0,00", onChange: handleChange })] })), (0, jsx_runtime_1.jsx)("input", { type: "submit", value: "Set" })] })), (0, jsx_runtime_1.jsx)("span", Object.assign({ style: { color: "#FF0000" }, className: "popup-response-message" }, { children: formMessage.message }))] }))] }));
}
exports.SetStartCapitalForm = SetStartCapitalForm;
