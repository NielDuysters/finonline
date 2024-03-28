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
exports.AddCashflowForm = void 0;
const jsx_runtime_1 = require("react/jsx-runtime");
const react_1 = require("react");
const Dropdown_1 = __importDefault(require("../../components/Dropdown/Dropdown"));
const cashflowCategories_1 = require("../../data/cashflowCategories");
const cashflow_1 = require("../../data/cashflow");
const typeDropdownItems_1 = require("../../data/constants/typeDropdownItems");
function AddCashflowForm({ addCashflowDropdownState, setAddCashflowDropdownState, tableItemsLength, setTableItemsLength, categoriesLength }) {
    const date = new Date();
    const [formData, setFormData] = (0, react_1.useState)({
        type: "REVENUE",
        categoryId: 0,
        amount: 0.00,
        description: "",
        transactionDate: date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear(),
    });
    const [formMessage, setFormMessage] = (0, react_1.useState)({
        type: "",
        message: "",
    });
    const [categoryDropdownItems, setCategoryDropdownItems] = (0, react_1.useState)([]);
    const handleTypeChange = (value) => __awaiter(this, void 0, void 0, function* () {
        setFormData((prevState) => (Object.assign(Object.assign({}, prevState), { type: value })));
    });
    const handleCategoryChange = (value) => __awaiter(this, void 0, void 0, function* () {
        setFormData((prevState) => (Object.assign(Object.assign({}, prevState), { categoryId: parseInt(value) })));
    });
    const handleAmountChange = (event) => {
        setFormData((prevState) => (Object.assign(Object.assign({}, prevState), { amount: parseFloat(event.target.value.replace(",", ".")) })));
    };
    const handleDescriptionChange = (event) => {
        setFormData((prevState) => (Object.assign(Object.assign({}, prevState), { description: event.target.value })));
    };
    const handleTransactionDateChange = (event) => {
        setFormData((prevState) => (Object.assign(Object.assign({}, prevState), { transactionDate: event.target.value })));
    };
    const handleSubmit = (event) => __awaiter(this, void 0, void 0, function* () {
        event.preventDefault();
        try {
            const response = yield (0, cashflow_1.addCashflow)({ formData });
            if (response !== undefined && response.status === 201) {
                setTableItemsLength(tableItemsLength + 1);
                setFormMessage({
                    type: "ok",
                    message: "Successfully added.",
                });
            }
        }
        catch (err) {
            if (err instanceof Error) {
                setFormMessage({
                    type: "error",
                    message: `Error: ${err.message}`,
                });
            }
        }
    });
    (0, react_1.useEffect)(() => {
        const fetchData = () => __awaiter(this, void 0, void 0, function* () {
            const categoryItems = yield (0, cashflowCategories_1.getCashflowCategoryItems)();
            const filteredItems = categoryItems
                .filter((c) => c.type === formData.type)
                .map((c) => ({
                label: c.label,
                value: c.id,
            }));
            setCategoryDropdownItems(filteredItems);
            const defaultCategory = categoryItems
                .filter((c) => c.type === formData.type && c.persistent === true)
                .map((c) => c.id);
            setFormData((prevState) => (Object.assign(Object.assign({}, prevState), { categoryId: parseInt(defaultCategory) })));
        });
        fetchData();
    }, [formData.type, categoriesLength]);
    return (addCashflowDropdownState ? ((0, jsx_runtime_1.jsxs)("form", Object.assign({ id: "add-cashflow-form", onSubmit: (event) => handleSubmit(event) }, { children: [(0, jsx_runtime_1.jsxs)("div", Object.assign({ className: "input-wrapper" }, { children: [(0, jsx_runtime_1.jsx)("span", Object.assign({ className: "label" }, { children: "Type" })), (0, jsx_runtime_1.jsx)(Dropdown_1.default, { items: typeDropdownItems_1.typeDropdownItems, onItemSelect: (label, value) => handleTypeChange(value) })] })), (0, jsx_runtime_1.jsxs)("div", Object.assign({ className: "input-wrapper" }, { children: [(0, jsx_runtime_1.jsx)("span", Object.assign({ className: "label" }, { children: "Category" })), (0, jsx_runtime_1.jsx)(Dropdown_1.default, { items: categoryDropdownItems, onItemSelect: (label, value) => handleCategoryChange(value) })] })), (0, jsx_runtime_1.jsxs)("div", Object.assign({ className: "input-wrapper" }, { children: [(0, jsx_runtime_1.jsx)("span", Object.assign({ className: "label" }, { children: "Amount" })), (0, jsx_runtime_1.jsx)("span", Object.assign({ className: "input-icon" }, { children: "\u20AC" })), (0, jsx_runtime_1.jsx)("input", { type: "text", className: "input-padding-left", placeholder: "0,00", onChange: (event) => handleAmountChange(event) })] })), (0, jsx_runtime_1.jsxs)("div", Object.assign({ className: "input-wrapper" }, { children: [(0, jsx_runtime_1.jsx)("span", Object.assign({ className: "label" }, { children: "Description" })), (0, jsx_runtime_1.jsx)("input", { type: "text", placeholder: "Food", onChange: (event) => handleDescriptionChange(event) })] })), (0, jsx_runtime_1.jsxs)("div", Object.assign({ className: "input-wrapper" }, { children: [(0, jsx_runtime_1.jsx)("span", Object.assign({ className: "label" }, { children: "Transaction date" })), (0, jsx_runtime_1.jsx)("input", { type: "text", placeholder: "dd/mm/yyyy", value: formData.transactionDate, onChange: (event) => handleTransactionDateChange(event) })] })), (0, jsx_runtime_1.jsx)("input", { type: "submit", value: "Add" }), (0, jsx_runtime_1.jsx)("span", Object.assign({ style: { color: formMessage.type === "ok" ? "#088A08" : "#FF0000" }, className: "message" }, { children: formMessage.message }))] }))) : null);
}
exports.AddCashflowForm = AddCashflowForm;
