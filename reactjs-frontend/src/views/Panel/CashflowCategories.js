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
exports.CashflowCategories = void 0;
const jsx_runtime_1 = require("react/jsx-runtime");
const react_1 = require("react");
const cashflowCategories_1 = require("../../data/cashflowCategories");
function CashflowCategories({ categoriesLength, setCategoriesLength }) {
    const [categories, setCategories] = (0, react_1.useState)([]);
    const [addCategoryPopupState, setAddCategoryPopupState] = (0, react_1.useState)({ isOpen: false, type: "" });
    (0, react_1.useEffect)(() => {
        const fetchCategories = () => __awaiter(this, void 0, void 0, function* () {
            const items = yield (0, cashflowCategories_1.getCashflowCategoryItems)();
            setCategories(items);
            setCategoriesLength(items.length);
        });
        fetchCategories();
    }, [categoriesLength]);
    const renderCategoryItems = (type) => {
        return categories
            .filter(c => c.type === type)
            .map(c => ((0, jsx_runtime_1.jsx)(CashflowCategoryItem, { id: c.id, type: c.type, persistent: c.persistent, label: c.label, color: c.color, categoriesLength: categoriesLength, setCategoriesLength: setCategoriesLength }, c.id)));
    };
    return ((0, jsx_runtime_1.jsxs)("div", Object.assign({ id: "cashflow-categories-container" }, { children: [(0, jsx_runtime_1.jsxs)("div", Object.assign({ className: "cashflow-category" }, { children: [(0, jsx_runtime_1.jsx)("span", Object.assign({ className: "title" }, { children: "Revenue categories" })), (0, jsx_runtime_1.jsxs)("div", Object.assign({ className: "cashflow-category-items" }, { children: [renderCategoryItems("REVENUE"), (0, jsx_runtime_1.jsx)("div", Object.assign({ className: "cashflow-category-item add-category-item", onClick: () => setAddCategoryPopupState(prevState => ({
                                    isOpen: true,
                                    type: "revenue"
                                })) }, { children: "+ Add" }), "0")] }))] })), (0, jsx_runtime_1.jsxs)("div", Object.assign({ className: "cashflow-category" }, { children: [(0, jsx_runtime_1.jsx)("span", Object.assign({ className: "title" }, { children: "Expense categories" })), (0, jsx_runtime_1.jsxs)("div", Object.assign({ className: "cashflow-category-items" }, { children: [renderCategoryItems("EXPENSE"), (0, jsx_runtime_1.jsx)("div", Object.assign({ className: "cashflow-category-item add-category-item", onClick: () => setAddCategoryPopupState(prevState => ({
                                    isOpen: true,
                                    type: "expense"
                                })) }, { children: "+ Add" }), "0")] }))] })), (0, jsx_runtime_1.jsx)(AddCategoryItemForm, { addCategoryPopupState: addCategoryPopupState, setAddCategoryPopupState: setAddCategoryPopupState, categoriesLength: categoriesLength, setCategoriesLength: setCategoriesLength })] })));
}
exports.CashflowCategories = CashflowCategories;
function CashflowCategoryItem(props) {
    const handleDelete = (event, id) => __awaiter(this, void 0, void 0, function* () {
        try {
            yield (0, cashflowCategories_1.deleteCategory)(id);
            props.setCategoriesLength(props.categoriesLength - 1);
        }
        catch (err) {
            if (err instanceof Error) {
                console.log(err);
            }
        }
    });
    return ((0, jsx_runtime_1.jsxs)("div", Object.assign({ className: "cashflow-category-item", "data-persistent": props.persistent.toString() }, { children: [(0, jsx_runtime_1.jsx)("div", Object.assign({ className: "delete-cashflow-category-item", onClick: (event) => handleDelete(event, props.id) }, { children: (0, jsx_runtime_1.jsx)("img", { alt: "Delete", src: "/assets/images/panel/delete.png" }) })), (0, jsx_runtime_1.jsx)("div", { className: "bullet", style: { backgroundColor: props.color == null ? "#000000" : props.color } }), (0, jsx_runtime_1.jsx)("span", Object.assign({ className: "label" }, { children: props.label }))] }), props.id.toString()));
}
function AddCategoryItemForm({ addCategoryPopupState, setAddCategoryPopupState, categoriesLength, setCategoriesLength }) {
    const [formData, setFormData] = (0, react_1.useState)({
        label: ""
    });
    const [formMessage, setFormMessage] = (0, react_1.useState)("");
    const handleSubmit = (event, type) => __awaiter(this, void 0, void 0, function* () {
        event.preventDefault();
        try {
            yield (0, cashflowCategories_1.addCategory)({
                label: formData.label,
                type: type.toUpperCase(),
            });
            handleClosePopup();
            setCategoriesLength(categoriesLength + 1);
        }
        catch (err) {
            if (err instanceof Error) {
                setFormMessage(err.message);
            }
        }
    });
    const handleChange = (event) => {
        setFormData({ label: event.target.value });
    };
    const handleClosePopup = () => {
        setAddCategoryPopupState((prevState) => (Object.assign(Object.assign({}, prevState), { isOpen: false })));
        setFormData({ label: "" });
    };
    return (addCategoryPopupState.isOpen ? ((0, jsx_runtime_1.jsxs)("div", { children: [(0, jsx_runtime_1.jsx)("div", { id: "popup-background" }), (0, jsx_runtime_1.jsxs)("div", Object.assign({ className: "popup" }, { children: [(0, jsx_runtime_1.jsx)("div", Object.assign({ className: "close-popup", onClick: handleClosePopup }, { children: "\u00D7" })), (0, jsx_runtime_1.jsxs)("span", Object.assign({ className: "popup-title" }, { children: ["Add ", addCategoryPopupState.type, " category"] })), (0, jsx_runtime_1.jsxs)("form", Object.assign({ onSubmit: (event) => handleSubmit(event, addCategoryPopupState.type) }, { children: [(0, jsx_runtime_1.jsx)("input", { type: "text", placeholder: "Category label", value: formData.label, onChange: handleChange }), (0, jsx_runtime_1.jsx)("input", { type: "submit", value: "Add" })] })), (0, jsx_runtime_1.jsx)("span", Object.assign({ style: { color: "#FF0000" }, className: "popup-response-message" }, { children: formMessage }))] }))] })) : null);
}
