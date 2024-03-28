"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.AuthContext = void 0;
const react_1 = require("react");
exports.AuthContext = (0, react_1.createContext)({
    user: null,
    isLoading: true,
});
