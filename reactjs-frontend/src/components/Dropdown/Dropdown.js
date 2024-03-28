"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const jsx_runtime_1 = require("react/jsx-runtime");
require("./Dropdown.sass");
const react_1 = require("react");
function Dropdown({ items, onItemSelect }) {
    const [isOpen, setIsOpen] = (0, react_1.useState)(false);
    const [selected, setSelected] = (0, react_1.useState)({ label: "", value: "" });
    (0, react_1.useEffect)(() => {
        if (items.length > 0) {
            setSelected({
                label: items[0].label,
                value: items[0].value,
            });
        }
    }, [items]);
    if (items.length === 0) {
        return null;
    }
    const handleItemSelect = (label, value) => {
        setSelected(() => ({
            label: label,
            value: value,
        }));
        onItemSelect(label, value);
    };
    return ((0, jsx_runtime_1.jsxs)("div", Object.assign({ className: "dropdown", onClick: (event) => setIsOpen(!isOpen) }, { children: [(0, jsx_runtime_1.jsxs)("div", Object.assign({ className: "dropdown-selected" }, { children: [selected.label, " ", (0, jsx_runtime_1.jsx)("img", { alt: "", className: isOpen ? "open" : "", src: "/assets/images/panel/dropdown-arrow.png" })] })), (0, jsx_runtime_1.jsx)(DropdownList, { items: items, isOpen: isOpen, setIsOpen: setIsOpen, handleItemSelect: handleItemSelect, selected: selected })] })));
}
function DropdownList({ items, isOpen, setIsOpen, handleItemSelect, selected }) {
    const renderItems = () => {
        return items
            .map(c => ((0, jsx_runtime_1.jsx)("div", Object.assign({ className: "dropdown-item", onClick: () => handleItemSelect(c.label, c.value) }, { children: c.label }), c.value)));
    };
    return (isOpen ? ((0, jsx_runtime_1.jsx)("div", Object.assign({ className: "dropdown-list" }, { children: renderItems() }))) : null);
}
exports.default = Dropdown;
