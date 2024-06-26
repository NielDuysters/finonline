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
exports.addCashflow = exports.getCashflow = void 0;
const js_cookie_1 = __importDefault(require("js-cookie"));
const axios_1 = __importDefault(require("axios"));
const appConstants = __importStar(require("../appConstants"));
function getCashflow() {
    return __awaiter(this, void 0, void 0, function* () {
        const authToken = js_cookie_1.default.get("auth-token");
        try {
            const response = yield axios_1.default.get(`${appConstants.URL}/cashflow`, {
                insecureHTTPParser: true,
                headers: {
                    Authorization: `Bearer ${authToken}`,
                }
            });
            if (response.status === 200) {
                return response.data;
            }
        }
        catch (err) {
            throw err;
        }
    });
}
exports.getCashflow = getCashflow;
function addCashflow(data) {
    return __awaiter(this, void 0, void 0, function* () {
        const authToken = js_cookie_1.default.get("auth-token");
        try {
            const response = yield axios_1.default.post(`http://127.0.0.1:8080/cashflow`, data["formData"], {
                insecureHTTPParser: true,
                headers: {
                    Authorization: `Bearer ${authToken}`,
                    "Content-Type": "application/json",
                }
            });
            if (response.status === 201) {
                return response;
            }
        }
        catch (err) {
            if (err.response.status === 409) {
                if (err.response.data === "") {
                    err.response.data = "Invalid input.";
                }
                throw new Error(`${err.response.data}`);
            }
            throw err;
        }
    });
}
exports.addCashflow = addCashflow;
