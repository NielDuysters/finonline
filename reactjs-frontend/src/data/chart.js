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
exports.userCapitalEvolution = exports.transactionsPerCategory = exports.revenueAndExpensesMonthly = void 0;
const js_cookie_1 = __importDefault(require("js-cookie"));
const axios_1 = __importDefault(require("axios"));
const appConstants = __importStar(require("../appConstants"));
function revenueAndExpensesMonthly() {
    return __awaiter(this, void 0, void 0, function* () {
        const authToken = js_cookie_1.default.get("auth-token");
        try {
            const response = yield axios_1.default.get(`${appConstants.URL}/charts/revenue-expenses-monthly`, {
                insecureHTTPParser: true,
                headers: {
                    Authorization: `Bearer ${authToken}`,
                }
            });
            if (response.status === 200) {
                let data = response.data;
                let transactionDateLabels = [];
                console.log(data);
                let col1 = data.filter((d) => d.type === "REVENUE").map((d) => ({
                    label: d.dateYear,
                    y: d.amount
                }));
                let col2 = data.filter((d) => d.type === "EXPENSE").map((d) => ({
                    label: d.dateYear,
                    y: d.amount
                }));
                col1.concat(col2).forEach((d) => {
                    if (!transactionDateLabels.includes(d.label)) {
                        transactionDateLabels.push(d.label);
                    }
                });
                transactionDateLabels.forEach((label) => {
                    let check = col1.some((x) => x.label === label);
                    if (!check) {
                        col1.push({
                            label: label,
                            y: 0.00
                        });
                    }
                    check = col2.some((x) => x.label === label);
                    if (!check) {
                        col2.push({
                            label: label,
                            y: 0.00
                        });
                    }
                });
                data = [
                    {
                        type: "column",
                        color: "#088A08",
                        dataPoints: col1,
                    },
                    {
                        type: "column",
                        color: "#FF0000",
                        dataPoints: col2,
                    }
                ];
                return data;
            }
            else {
                return [];
            }
        }
        catch (err) {
            return [];
        }
    });
}
exports.revenueAndExpensesMonthly = revenueAndExpensesMonthly;
function transactionsPerCategory(type, dateYear) {
    return __awaiter(this, void 0, void 0, function* () {
        const authToken = js_cookie_1.default.get("auth-token");
        let url = `${appConstants.URL}/charts/transactions-per-category/${type}`;
        if (dateYear) {
            url = url + `/${dateYear}`;
        }
        try {
            const response = yield axios_1.default.get(url, {
                insecureHTTPParser: true,
                headers: {
                    Authorization: `Bearer ${authToken}`,
                }
            });
            if (response.status === 200) {
                let data = response.data;
                data = data.map((d) => ({
                    label: d.cashflowCategory.label,
                    y: d.amount,
                    color: d.cashflowCategory.color,
                }));
                data = [
                    {
                        type: "column",
                        dataPoints: data,
                    }
                ];
                return data;
            }
            else {
                return [];
            }
        }
        catch (err) {
            return [];
        }
    });
}
exports.transactionsPerCategory = transactionsPerCategory;
function userCapitalEvolution() {
    return __awaiter(this, void 0, void 0, function* () {
        const authToken = js_cookie_1.default.get("auth-token");
        let url = `${appConstants.URL}/charts/user-capital-evolution`;
        try {
            const response = yield axios_1.default.get(url, {
                insecureHTTPParser: true,
                headers: {
                    Authorization: `Bearer ${authToken}`,
                }
            });
            if (response.status === 200) {
                let data = response.data;
                data = Object.keys(data).map(key => ({
                    x: parseCustomDate(key),
                    y: data[key]
                }));
                data.sort((a, b) => a.x.getTime() - b.x.getTime());
                data = [
                    {
                        type: "line",
                        color: "#088A08",
                        dataPoints: data,
                    }
                ];
                return data;
            }
            else {
                return [];
            }
        }
        catch (err) {
            return [];
        }
    });
}
exports.userCapitalEvolution = userCapitalEvolution;
function parseCustomDate(dateString) {
    const months = {
        January: 0, February: 1, March: 2, April: 3, May: 4, June: 5,
        July: 6, August: 7, September: 8, October: 9, November: 10, December: 11
    };
    const match = dateString.match(/(\w+)\s+(\d{4})/);
    if (!match) {
        console.error("Invalid date format");
        return null;
    }
    const monthName = match[1];
    const year = parseInt(match[2]);
    if (!(monthName in months)) {
        console.error("Invalid month name");
        return null;
    }
    const month = months[monthName];
    return new Date(year, month);
}
