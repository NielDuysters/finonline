"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const jsx_runtime_1 = require("react/jsx-runtime");
require("./Header.sass");
function Header() {
    return ((0, jsx_runtime_1.jsx)("header", { children: (0, jsx_runtime_1.jsx)("img", { id: "logo", src: "/assets/images/logo.png", alt: "fin | online" }) }));
}
exports.default = Header;
