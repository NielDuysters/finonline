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
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const jsx_runtime_1 = require("react/jsx-runtime");
const react_router_dom_1 = require("react-router-dom");
const component_1 = __importDefault(require("@loadable/component"));
const useAuth_1 = require("../hooks/useAuth");
const Home = (0, component_1.default)(() => Promise.resolve().then(() => __importStar(require("../views/Home/Home"))));
const Panel = (0, component_1.default)(() => Promise.resolve().then(() => __importStar(require("../views/Panel/Panel"))));
const ProtectedRoute = ({ Component }) => {
    const { user, isLoading } = (0, useAuth_1.useAuth)();
    if (isLoading) {
        console.log("Is loading");
        return null;
    }
    if (user == null) {
        return (0, jsx_runtime_1.jsx)(react_router_dom_1.Navigate, { to: "/", replace: true });
    }
    return (0, jsx_runtime_1.jsx)(Component, {});
};
const AppRouter = () => {
    return ((0, jsx_runtime_1.jsx)(react_router_dom_1.BrowserRouter, { children: (0, jsx_runtime_1.jsxs)(react_router_dom_1.Routes, { children: [(0, jsx_runtime_1.jsx)(react_router_dom_1.Route, { path: "/", element: (0, jsx_runtime_1.jsx)(Home, {}) }), (0, jsx_runtime_1.jsx)(react_router_dom_1.Route, { path: "/panel", element: (0, jsx_runtime_1.jsx)(ProtectedRoute, { Component: Panel }) })] }) }));
};
exports.default = AppRouter;
