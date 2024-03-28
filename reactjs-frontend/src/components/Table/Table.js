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
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const jsx_runtime_1 = require("react/jsx-runtime");
const react_1 = require("react");
const axios_1 = __importDefault(require("axios"));
const js_cookie_1 = __importDefault(require("js-cookie"));
require("./Table.sass");
function Table({ items, tableItemsLength, setTableItemsLength }) {
    const tableRef = (0, react_1.useRef)(null);
    (0, react_1.useEffect)(() => {
        var _a;
        if (tableRef.current) {
            const columnCells = (_a = tableRef.current.querySelector(".table-head .row")) === null || _a === void 0 ? void 0 : _a.children;
            if (columnCells) {
                const columnCellWidths = Array.from(columnCells).map((cell) => cell.offsetWidth);
                const tableBodyRows = tableRef.current.querySelectorAll(".table-body .row");
                if (tableBodyRows) {
                    const tableBodyRowsHTML = Array.from(tableBodyRows);
                    tableBodyRowsHTML.forEach(row => {
                        const cells = row.children;
                        Array.from(cells).forEach((cell, i) => {
                            cell.style.width = `${columnCellWidths[i]}px`;
                        });
                    });
                }
            }
        }
    });
    if (items.length === 0) {
        return ((0, jsx_runtime_1.jsx)(jsx_runtime_1.Fragment, { children: (0, jsx_runtime_1.jsx)("span", Object.assign({ style: { display: "block", color: "#FFFFFF", marginTop: "25px" } }, { children: "No data available." })) }));
    }
    const columns = Object.keys(items[0])
        .concat("delete")
        .filter(c => !["type", "id"].includes(c));
    const columnMapping = {
        "cashflowCategory": "category",
        "transactionDate": "date",
    };
    const renderTableHeadItems = () => {
        return columns
            .map(c => ((0, jsx_runtime_1.jsx)("div", Object.assign({ className: "cell" }, { children: columnMapping[c] || c }), columnMapping[c] || c)));
    };
    const renderTableRowCells = (item, columns) => {
        const cells = [];
        for (let i = 0; i < columns.length; i++) {
            const c = columns[i];
            if (c === "amount") {
                const type = item.type;
                const amount = "€ " + (type === "REVENUE" ? "" : "-") + parseFloat(item[c]).toFixed(2).replace(".", ",");
                cells.push((0, jsx_runtime_1.jsx)("div", Object.assign({ className: "cell", style: { color: (type === "REVENUE" ? "#088A08" : "#FF0000") } }, { children: amount }), i));
            }
            else if (c === "cashflowCategory") {
                let category = item[c];
                if (category === null) {
                    category = {
                        color: "#FF0000",
                        label: "other",
                    };
                }
                cells.push((0, jsx_runtime_1.jsx)("div", Object.assign({ className: "cell" }, { children: (0, jsx_runtime_1.jsxs)("div", Object.assign({ className: "cashflow-category-item" }, { children: [(0, jsx_runtime_1.jsx)("div", { className: "bullet", style: { backgroundColor: category.color } }), (0, jsx_runtime_1.jsx)("span", Object.assign({ className: "label" }, { children: category.label }))] })) }), i));
            }
            else if (c === "description") {
                cells.push((0, jsx_runtime_1.jsx)("div", Object.assign({ className: "cell" }, { children: item[c] === null || item[c] === "" ? "N.V.T" : item[c] }), i));
            }
            else if (c === "createdAt") {
                const date = new Date(item[c]);
                cells.push((0, jsx_runtime_1.jsx)("div", Object.assign({ className: "cell" }, { children: date.getFullYear() + "/" + date.getMonth() + "/" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() }), i));
            }
            else if (c === "delete") {
                const handleDelete = (event, id) => __awaiter(this, void 0, void 0, function* () {
                    try {
                        yield deleteCashflow(id);
                        setTableItemsLength(tableItemsLength - 1);
                    }
                    catch (err) {
                        if (err instanceof Error) {
                            console.log(err);
                        }
                    }
                });
                cells.push((0, jsx_runtime_1.jsx)("div", Object.assign({ className: "cell" }, { children: (0, jsx_runtime_1.jsx)("img", { onClick: (event) => handleDelete(event, item["id"]), className: "table-button", alt: "Delete", src: "/assets/images/panel/delete.png" }) }), i));
            }
            else {
                cells.push((0, jsx_runtime_1.jsx)("div", Object.assign({ className: "cell" }, { children: typeof item[c] === 'object' ? JSON.stringify(item[c]) : item[c] }), i));
            }
        }
        return cells;
    };
    return ((0, jsx_runtime_1.jsxs)("div", Object.assign({ className: "table", ref: tableRef }, { children: [(0, jsx_runtime_1.jsx)("div", Object.assign({ className: "table-head" }, { children: (0, jsx_runtime_1.jsx)("div", Object.assign({ className: "row" }, { children: renderTableHeadItems() })) })), (0, jsx_runtime_1.jsx)("div", Object.assign({ className: "table-body" }, { children: items.map((item, index) => ((0, jsx_runtime_1.jsx)("div", Object.assign({ className: "row" }, { children: renderTableRowCells(item, columns) }), index))) }))] })));
}
function deleteCashflow(id) {
    return __awaiter(this, void 0, void 0, function* () {
        const authToken = js_cookie_1.default.get("auth-token");
        try {
            const response = yield axios_1.default.delete(`http://127.0.0.1:8080/cashflow/${id}`, {
                insecureHTTPParser: true,
                headers: {
                    Authorization: `Bearer ${authToken}`,
                }
            });
            if (response.status === 200) {
                return response;
            }
        }
        catch (err) {
            if (err.response.status === 409) {
                throw new Error("Error deleting");
            }
            throw err;
        }
    });
}
exports.default = Table;
