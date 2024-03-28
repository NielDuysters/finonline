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
exports.FinanceOverviewTable = void 0;
const jsx_runtime_1 = require("react/jsx-runtime");
const react_1 = require("react");
const AddCashflowForm_1 = require("./AddCashflowForm");
const Table_1 = __importDefault(require("../../components/Table/Table"));
const cashflow_1 = require("../../data/cashflow");
const users_1 = require("../../data/users");
function FinanceOverviewTable({ user, tableItemsLength, setTableItemsLength, categoriesLength }) {
    const [addCashflowDropdownState, setAddCashflowDropdownState] = (0, react_1.useState)(false);
    const [tableItems, setTableItems] = (0, react_1.useState)([]);
    const [userCurrentCapital, setUserCurrentCapital] = (0, react_1.useState)(user["currentCapital"]);
    (0, react_1.useEffect)(() => {
        const fetchCashflow = () => __awaiter(this, void 0, void 0, function* () {
            const items = yield (0, cashflow_1.getCashflow)();
            setTableItems(items);
        });
        const fetchUserCurrentCapital = () => __awaiter(this, void 0, void 0, function* () {
            const user = yield (0, users_1.getUser)();
            setUserCurrentCapital(user["currentCapital"]);
        });
        fetchCashflow();
        fetchUserCurrentCapital();
    }, [tableItemsLength, categoriesLength]);
    const userStartCapital = user["startCapital"];
    const percentage = (((userCurrentCapital + 1) - (userStartCapital + 1)) / (userStartCapital + 1)) * 100;
    const percentageString = " (" + (percentage >= 0 ? "+" : "") + percentage.toFixed(2) + "%)";
    let userCapitalString = userCurrentCapital.toFixed(2).replace(".", ",") + percentageString;
    return ((0, jsx_runtime_1.jsxs)("div", Object.assign({ id: "overview" }, { children: [(0, jsx_runtime_1.jsx)("div", Object.assign({ id: "overview-buttons" }, { children: (0, jsx_runtime_1.jsx)("div", Object.assign({ className: "button", id: "add-cashflow", onClick: (event) => setAddCashflowDropdownState(!addCashflowDropdownState) }, { children: "+ Add cashflow" })) })), (0, jsx_runtime_1.jsx)(AddCashflowForm_1.AddCashflowForm, { addCashflowDropdownState: addCashflowDropdownState, setAddCashflowDropdownState: setAddCashflowDropdownState, tableItemsLength: tableItemsLength, setTableItemsLength: setTableItemsLength, categoriesLength: categoriesLength }), (0, jsx_runtime_1.jsx)("br", {}), (0, jsx_runtime_1.jsxs)("div", Object.assign({ id: "total-capital" }, { children: [(0, jsx_runtime_1.jsx)("span", Object.assign({ className: "label" }, { children: "Totaal: " })), (0, jsx_runtime_1.jsxs)("span", Object.assign({ className: "value", style: { color: userCurrentCapital >= userStartCapital ? "#088A08" : "#FF0000" } }, { children: [" \u20AC ", userCapitalString, " "] }))] })), (0, jsx_runtime_1.jsx)(Table_1.default, { items: tableItems, tableItemsLength: tableItemsLength, setTableItemsLength: setTableItemsLength })] })));
}
exports.FinanceOverviewTable = FinanceOverviewTable;
