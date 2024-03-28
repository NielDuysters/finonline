import React, { useState, useEffect } from "react"
import { AddCashflowForm } from "./AddCashflowForm"
import Table from "../../components/Table/Table"
import { getCashflow } from "../../data/cashflow"
import { getUser } from "../../data/users"
import * as Types from "../../types"

export function FinanceOverviewTable({user, tableItemsLength, setTableItemsLength, categoriesLength} : {user : Types.User, tableItemsLength: number, setTableItemsLength: (x: number) => void, categoriesLength: number}) {
    const [addCashflowDropdownState, setAddCashflowDropdownState] = useState(false)
    const [tableItems, setTableItems] = useState<any[]>([])
    const [userCurrentCapital, setUserCurrentCapital] = useState(user["currentCapital"])

    useEffect(() => {
       const fetchCashflow = async () => {
            const items = await getCashflow()
            setTableItems(items)
        }

        const fetchUserCurrentCapital = async () => {
            const user = await getUser()
            setUserCurrentCapital(user["currentCapital"])
        }

        fetchCashflow()
        fetchUserCurrentCapital()
    }, [tableItemsLength, categoriesLength])

    const userStartCapital = user["startCapital"] 
    
    const percentage = (((userCurrentCapital + 1) - (userStartCapital + 1)) / (userStartCapital + 1)) * 100
    const percentageString = " (" + (percentage >= 0 ? "+" : "") + percentage.toFixed(2) + "%)"

    let userCapitalString = userCurrentCapital.toFixed(2).replace(".", ",") + percentageString

    return (
        <div id="overview">
            <div id="overview-buttons">
                <div className="button" id="add-cashflow" onClick={(event) => setAddCashflowDropdownState(!addCashflowDropdownState)}>+ Add cashflow</div>
            </div>

            <AddCashflowForm addCashflowDropdownState={addCashflowDropdownState} setAddCashflowDropdownState={setAddCashflowDropdownState} tableItemsLength={tableItemsLength} setTableItemsLength={setTableItemsLength} categoriesLength={categoriesLength} />

            <br />
            <div id="total-capital">
                <span className="label">Totaal: </span>
                <span className="value" style={{color: userCurrentCapital >= userStartCapital ? "#088A08" : "#FF0000"}}> &euro; {userCapitalString} </span>
            </div>
            <Table items={tableItems} tableItemsLength={tableItemsLength} setTableItemsLength={setTableItemsLength} />
        </div>
    )
}


