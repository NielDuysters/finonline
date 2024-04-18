import { useEffect, useRef } from "react"
import axios from "axios"
import Cookies from "js-cookie"
import "./Table.sass"

interface TableProps {
    items: any[],
    tableItemsLength: number,
    setTableItemsLength: (x: number) => void,
}

function Table({items, tableItemsLength, setTableItemsLength}: TableProps) {
    
    const tableRef = useRef<HTMLDivElement>(null)
    useEffect(() => {
        if (tableRef.current) {
            const columnCells = tableRef.current.querySelector(".table-head .row")?.children as HTMLCollectionOf<HTMLElement>
            if (columnCells) {
                const columnCellWidths = Array.from(columnCells).map((cell: HTMLElement) => cell.offsetWidth) as number[]
                const tableBodyRows = tableRef.current.querySelectorAll(".table-body .row")
                if (tableBodyRows) {
                    const tableBodyRowsHTML = Array.from(tableBodyRows) as HTMLElement[]
                    tableBodyRowsHTML.forEach(row => {
                        const cells = row.children as HTMLCollectionOf<HTMLElement>
                        Array.from(cells).forEach((cell: HTMLElement, i: number) => {
                            cell.style.width = `${columnCellWidths[i]}px`
                        })
                    })
                }
            }
        }
    })

    if (items.length === 0) {
        return (
            <>
                <span style={{ display: "block", color: "#FFFFFF", marginTop: "25px" }}>No data available.</span>
            </>
        )
    }

    const columns: string[] = Object.keys(items[0])
    .concat("delete")
    .filter(c => !["type", "id", "user"].includes(c))

    const columnMapping : Record<string, string> = {
        "cashflowCategory": "category",
        "transactionDate": "date",
    }
    const renderTableHeadItems = () => {
        return columns
        .map(c => (
            <div className="cell" key={columnMapping[c] || c}>{columnMapping[c] || c}</div>
        ))
    }

    const renderTableRowCells = (item: any, columns: string[]) => {
        const cells = []

        for (let i = 0; i < columns.length; i++) {
            const c = columns[i]

            if (c === "amount") {
                const type = item.type
                const amount = "€ " + (type === "REVENUE" ? "" : "-") + parseFloat(item[c]).toFixed(2).replace(".", ",")

                cells.push(
                    <div className="cell" style={{color: (type === "REVENUE" ? "#088A08" : "#FF0000")}} key={i}>
                        { amount }
                    </div>
                )
            } else if (c === "cashflowCategory") {

                let category = item[c]
                if (category === null) {
                    category = {
                        color: "#FF0000",
                        label: "other",
                    }
                }

                cells.push(
                    <div className="cell" key={i}>
                        <div className="cashflow-category-item">
                            <div className="bullet" style={{backgroundColor: category.color}}></div>
                            <span className="label">{ category.label }</span>
                        </div>
                    </div>
                )
            } else if (c === "description") {
                cells.push(
                    <div className="cell" key={i}>
                        {item[c] === null || item[c] === "" ? "N.V.T" : item[c]}
                    </div>
                )
            }
            else if (c === "transactionDate") {
                const date = new Date(item[c])
                cells.push(
                    <div className="cell" key={i}>
                        {date.getFullYear() + "/" + date.getMonth() + "/" + date.getDate()}
                    </div>
                )
            } else if (c === "delete") {
                
                const handleDelete = async (event: React.MouseEvent<HTMLDivElement, MouseEvent>, id: number) => {
                    try {
                        await deleteCashflow(id)
                        setTableItemsLength(tableItemsLength - 1)
                    } catch (err: any) {
                        if (err instanceof Error) {
                            console.log(err)
                        }
                    }
                }
                    
                cells.push(
                    <div className="cell" key={i}>
                        <img onClick={(event) => handleDelete(event, item["id"])} className="table-button" alt="Delete" src="/assets/images/panel/delete.png" />
                    </div>
                )
            } else {
                cells.push(
                    <div className="cell" key={i}>
                        {typeof item[c] === 'object' ? JSON.stringify(item[c]) : item[c]}
                    </div>
                )
            }
        }

        return cells
    }


    return (
        <div className="table" ref={tableRef}>
            <div className="table-head">
                <div className="row">
                    { renderTableHeadItems() }
                </div>
            </div>
            <div className="table-body">
                {items.map((item, index) => (
                    <div className="row" key={index}>{ renderTableRowCells(item, columns) }</div>
                ))}
            </div>
        </div>
    )   
}

async function deleteCashflow(id: number) {
    const authToken = Cookies.get("auth-token")

    try {
       const response = await axios.delete(`http://127.0.0.1:8080/cashflow/${id}`,
        {
            insecureHTTPParser: true,
            headers: {
                Authorization: `Bearer ${authToken}`,
            }
        })
   
        if (response.status === 200) {
            return response
        }
    } catch (err: any) {
        if (err.response.status === 409) {
            throw new Error("Error deleting")
        }
        
        throw err
    }
}

export default Table
