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
// @ts-ignore
const react_charts_1 = __importDefault(require("@canvasjs/react-charts"));
require("../../styles/Panel.sass");
require("../../styles/Popup.sass");
const CashflowCategories_1 = require("./CashflowCategories");
const SetStartCapitalForm_1 = require("./SetStartCapitalForm");
const FinanceOverviewTable_1 = require("./FinanceOverviewTable");
const Dropdown_1 = __importDefault(require("../../components/Dropdown/Dropdown"));
const chart_1 = require("../../data/chart");
const chartOptionsDropdownItems_1 = require("../../data/constants/chartOptionsDropdownItems");
const typeDropdownItems_1 = require("../../data/constants/typeDropdownItems");
const users_1 = require("../../data/users");
const periods_1 = require("../../data/periods");
var CanvasJSChart = react_charts_1.default.CanvasJSChart;
function Panel() {
    const [user, setUser] = (0, react_1.useState)(null);
    const [tableItemsLength, setTableItemsLength] = (0, react_1.useState)(0);
    const [chartData, setChartData] = (0, react_1.useState)([]);
    const [chartDataOptions, setChartDataOptions] = (0, react_1.useState)({
        chartType: chartOptionsDropdownItems_1.chartOptionsDropdownItems[0].value,
        cashflowType: "REVENUE",
        chartPeriod: "",
        chartTitle: "Revenue / Expenses per month",
    });
    const [chartPeriods, setChartPeriods] = (0, react_1.useState)([]);
    const [categoriesLength, setCategoriesLength] = (0, react_1.useState)(0);
    (0, react_1.useEffect)(() => {
        const updateTableItemsLength = () => {
            setTableItemsLength(document.querySelectorAll(".row").length);
        };
        updateTableItemsLength();
        const observer = new MutationObserver(updateTableItemsLength);
        observer.observe(document.body, { subtree: true, childList: true });
        return () => observer.disconnect();
    }, []);
    (0, react_1.useEffect)(() => {
        const fetchUser = () => __awaiter(this, void 0, void 0, function* () {
            const user = yield (0, users_1.getUser)();
            setUser(user);
        });
        const fetchChartData = () => __awaiter(this, void 0, void 0, function* () {
            let data = null;
            switch (chartDataOptions.chartType) {
                case "MONTHLY":
                    data = yield (0, chart_1.revenueAndExpensesMonthly)();
                    setChartDataOptions(prevState => (Object.assign(Object.assign({}, prevState), { chartTitle: "Revenue / Expenses per month" })));
                    break;
                case "CATEGORY":
                    data = yield (0, chart_1.transactionsPerCategory)(chartDataOptions.cashflowType, chartDataOptions.chartPeriod);
                    setChartDataOptions(prevState => (Object.assign(Object.assign({}, prevState), { chartTitle: chartDataOptions.cashflowType + " per category in " + chartDataOptions.chartPeriod })));
                    break;
                default:
                    setChartDataOptions(prevState => (Object.assign(Object.assign({}, prevState), { chartTitle: "Total capital evolution" })));
                    data = yield (0, chart_1.userCapitalEvolution)();
            }
            try {
                if (data !== null) {
                    setChartData(data);
                }
            }
            catch (e) {
                console.log(e);
            }
        });
        fetchUser();
        fetchChartData();
    }, [chartDataOptions.chartType, chartDataOptions.cashflowType, chartDataOptions.chartPeriod, tableItemsLength, categoriesLength]);
    (0, react_1.useEffect)(() => {
        const fetchPeriods = () => __awaiter(this, void 0, void 0, function* () {
            const periods = (yield (0, periods_1.getPeriods)()).map((c) => ({
                label: c,
                value: c,
            }));
            setChartPeriods(periods);
            if (periods.length > 0 && chartDataOptions.chartPeriod === "") {
                setChartDataOptions(prevState => (Object.assign(Object.assign({}, prevState), { chartPeriod: periods[0].value })));
            }
        });
        fetchPeriods();
    }, [chartDataOptions.cashflowType, tableItemsLength]);
    const options = {
        title: {
            text: chartDataOptions.chartTitle,
            fontColor: "#FFFFFF",
        },
        toolTip: {
            contentFormatter: function (e) {
                return `${e.entries[0].dataPoint.label}: € ${e.entries[0].dataPoint.y.toFixed(2).replace(".", ",")}`;
            },
        },
        backgroundColor: "#181A20",
        axisX: {
            interval: 1,
            intervalType: "month",
            labelFontColor: "#FFFFFF",
        },
        axisY: {
            labelFontColor: "#FFFFFF",
            valueFormatString: "€ #,##0.00",
        },
        data: chartData,
    };
    if (user === null || user["startCapital"] === null) {
        return (0, jsx_runtime_1.jsx)(SetStartCapitalForm_1.SetStartCapitalForm, { user: user, setUser: setUser });
    }
    return ((0, jsx_runtime_1.jsxs)("div", Object.assign({ id: "panel" }, { children: [(0, jsx_runtime_1.jsx)(CashflowCategories_1.CashflowCategories, { categoriesLength: categoriesLength, setCategoriesLength: setCategoriesLength }), tableItemsLength > 0 ? ((0, jsx_runtime_1.jsxs)("div", Object.assign({ id: "chart-container" }, { children: [(0, jsx_runtime_1.jsxs)("div", Object.assign({ id: "chart-dropdown-container" }, { children: [(0, jsx_runtime_1.jsx)(Dropdown_1.default, { items: chartOptionsDropdownItems_1.chartOptionsDropdownItems, onItemSelect: (label, value) => setChartDataOptions(prevState => (Object.assign(Object.assign({}, prevState), { chartType: value }))) }), chartDataOptions.chartType === "CATEGORY" ? ((0, jsx_runtime_1.jsxs)(jsx_runtime_1.Fragment, { children: [(0, jsx_runtime_1.jsx)(Dropdown_1.default, { items: typeDropdownItems_1.typeDropdownItems, onItemSelect: (label, value) => setChartDataOptions(prevState => (Object.assign(Object.assign({}, prevState), { cashflowType: value }))) }), (0, jsx_runtime_1.jsx)(Dropdown_1.default, { items: chartPeriods, onItemSelect: (label, value) => setChartDataOptions(prevState => (Object.assign(Object.assign({}, prevState), { chartPeriod: value }))) })] })) : null] })), (0, jsx_runtime_1.jsx)(CanvasJSChart, { options: options })] }))) : null, (0, jsx_runtime_1.jsx)(FinanceOverviewTable_1.FinanceOverviewTable, { user: user, tableItemsLength: tableItemsLength, setTableItemsLength: setTableItemsLength, categoriesLength: categoriesLength })] })));
}
exports.default = Panel;
