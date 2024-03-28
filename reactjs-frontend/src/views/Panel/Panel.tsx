import React, { useState, useEffect } from "react"
// @ts-ignore
import CanvasJSReact from "@canvasjs/react-charts"
import "../../styles/Panel.sass"
import "../../styles/Popup.sass"
import { CashflowCategories } from "./CashflowCategories"
import { SetStartCapitalForm } from "./SetStartCapitalForm"
import { FinanceOverviewTable } from "./FinanceOverviewTable"
import Dropdown from "../../components/Dropdown/Dropdown"
import { revenueAndExpensesMonthly, transactionsPerCategory, userCapitalEvolution } from "../../data/chart"
import { chartOptionsDropdownItems } from "../../data/constants/chartOptionsDropdownItems"
import { typeDropdownItems } from "../../data/constants/typeDropdownItems"
import { getUser } from "../../data/users"
import { getPeriods } from "../../data/periods"
import * as Types from "../../types"

var CanvasJSChart = CanvasJSReact.CanvasJSChart

interface chartDataOptionsInterface {
    chartType: string,
    cashflowType: string,
    chartPeriod: string,
    chartTitle: string,
}

function Panel() {
    const [user, setUser] = useState<Types.User | null>(null)
    const [tableItemsLength, setTableItemsLength] = useState(0)
    const [chartData, setChartData] = useState<any[]>([])
    const [chartDataOptions, setChartDataOptions] = useState<chartDataOptionsInterface>({
        chartType: chartOptionsDropdownItems[0].value,
        cashflowType: "REVENUE",
        chartPeriod: "",
        chartTitle: "Revenue / Expenses per month",
    })
    const [chartPeriods, setChartPeriods] = useState<{label: string, value: string}[]>([])
    const [categoriesLength, setCategoriesLength] = useState(0)
    
    useEffect(() => {
        const updateTableItemsLength = () => {
            setTableItemsLength(document.querySelectorAll(".row").length)
        }

        updateTableItemsLength()

        const observer = new MutationObserver(updateTableItemsLength)
        observer.observe(document.body, { subtree: true, childList: true })
        return () => observer.disconnect()
    }, [])

    useEffect(() => {
        const fetchUser = async () => {
            const user = await getUser()
            setUser(user)
        }

        const fetchChartData = async () => {
            let data = null;
            switch (chartDataOptions.chartType) {
                case "MONTHLY":
                    data = await revenueAndExpensesMonthly()
                    setChartDataOptions(prevState => ({
                        ...prevState,
                        chartTitle: "Revenue / Expenses per month",
                    }))
                    break
                case "CATEGORY":
                    data = await transactionsPerCategory(chartDataOptions.cashflowType, chartDataOptions.chartPeriod)
                    setChartDataOptions(prevState => ({
                        ...prevState,
                        chartTitle: chartDataOptions.cashflowType + " per category in " + chartDataOptions.chartPeriod,
                    }))
                    break
                default:
                    setChartDataOptions(prevState => ({
                        ...prevState,
                        chartTitle: "Total capital evolution",
                    }))
                    data = await userCapitalEvolution()
            }

            try {
                if (data !== null) {
                    setChartData(data)
                }
            } catch (e) {
                console.log(e)
            }
        }

        fetchUser()
        fetchChartData()
    }, [chartDataOptions.chartType, chartDataOptions.cashflowType, chartDataOptions.chartPeriod, tableItemsLength, categoriesLength])
    
    useEffect(() => {
        const fetchPeriods = async () => {
            const periods = (await getPeriods()).map((c: string) => ({
                label: c,
                value: c,
            }))

            setChartPeriods(periods)

            if (periods.length > 0 && chartDataOptions.chartPeriod === "") {
                setChartDataOptions(prevState => ({
                    ...prevState,
                    chartPeriod: periods[0].value,
                }))
            }
        }

        fetchPeriods()
    }, [chartDataOptions.cashflowType, tableItemsLength])

    const options = {
        title: {
            text: chartDataOptions.chartTitle,
            fontColor: "#FFFFFF",
        },
        toolTip: {
            contentFormatter: function (e: any) {
               return `${e.entries[0].dataPoint.label}: € ${e.entries[0].dataPoint.y.toFixed(2).replace(".", ",")}`
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
    }


    if (user === null || user["startCapital"] === null) {
        return <SetStartCapitalForm user={user} setUser={setUser} />
    }

    return (
        <div id="panel">
            <CashflowCategories categoriesLength={categoriesLength} setCategoriesLength={setCategoriesLength} />
            { tableItemsLength > 0 ? (
                <div id="chart-container">
                    <div id="chart-dropdown-container">
                    <Dropdown items={chartOptionsDropdownItems} onItemSelect={(label, value) => setChartDataOptions(prevState => ({...prevState, chartType: value}))} />
                        {chartDataOptions.chartType === "CATEGORY" ? (
                            <>
                                <Dropdown items={typeDropdownItems} onItemSelect={(label, value) => setChartDataOptions(prevState => ({...prevState, cashflowType: value}))} />
                                <Dropdown items={chartPeriods} onItemSelect={(label, value) => setChartDataOptions(prevState => ({...prevState, chartPeriod: value}))} />
                            </>
                        ) : null}
                    </div>
                    <CanvasJSChart options = {options} />
                </div>
            ) : null }
            <FinanceOverviewTable user={user} tableItemsLength={tableItemsLength} setTableItemsLength={setTableItemsLength} categoriesLength={categoriesLength} />
        </div>
    )
}


export default Panel
