import Cookies from "js-cookie"
import axios from "axios"
import * as appConstants from "../appConstants"
import * as Types from "../types"

export async function revenueAndExpensesMonthly() { 
    const authToken = Cookies.get("auth-token")

    try {
       const response = await axios.get(`${appConstants.URL}/charts/revenue-expenses-monthly`,
        {
            insecureHTTPParser: true,
            headers: {
                Authorization: `Bearer ${authToken}`,
            }
        })
  
        if (response.status === 200) {
    
            let data = response.data
            let transactionDateLabels : string[] = []

            let col1 = data.filter((d: {amount: number, dateYear: string, type: string}) => d.type === "REVENUE").map((d: {amount: number, dateYear: string, type: string}) => ({
                label: d.dateYear,
                y: d.amount
            }))
            let col2 = data.filter((d: {amount: number, dateYear: string, type: string}) => d.type === "EXPENSE").map((d: {amount: number, dateYear: string, type: string}) => ({
                label: d.dateYear,
                y: d.amount
            }))

            col1.concat(col2).forEach((d: {label: string, y: number}) => {
                if (!transactionDateLabels.includes(d.label)) {
                    transactionDateLabels.push(d.label)
                }
            })

            transactionDateLabels.forEach((label: string) => {
                let check = col1.some((x: {label: string, y: number}) => x.label === label)
                if (!check) {
                    col1.push({
                        label: label,
                        y: 0.00
                    })
                }
                
                check = col2.some((x: {label: string, y: number}) => x.label === label)
                if (!check) {
                    col2.push({
                        label: label,
                        y: 0.00
                    })
                }
            })

            data = [
                {
                    type: "column",
                    color: "#088A08",
                    dataPoints: col1,
                },
                {
                    type: "column",
                    color: "#FF0000",
                    dataPoints: col2,
                }
            ]

            return data
        } else {
            return []
        }
    } catch (err: any) {
        return []
    }
}

export async function transactionsPerCategory(type: string, dateYear: string | null) { 
    const authToken = Cookies.get("auth-token")

    let url = `${appConstants.URL}/charts/transactions-per-category/${type}`
    if (dateYear) {
        url = url + `/${dateYear}`
    }

    try {
       const response = await axios.get(url,
        {
            insecureHTTPParser: true,
            headers: {
                Authorization: `Bearer ${authToken}`,
            }
        })
  
        if (response.status === 200) {
            let data = response.data
            data = data.map((d: {amount: number, cashflowCategory: Types.Category, type: string}) => ({
                label: d.cashflowCategory.label,
                y: d.amount,
                color: d.cashflowCategory.color,
            }))

            data = [
                {
                    type: "column",
                    dataPoints: data,
                }
            ]

            return data
        } else {
            return []
        }
    } catch (err: any) {
        return []
    }
}

export async function userCapitalEvolution() { 
    const authToken = Cookies.get("auth-token")

    let url = `${appConstants.URL}/charts/user-capital-evolution`

    try {
       const response = await axios.get(url,
        {
            insecureHTTPParser: true,
            headers: {
                Authorization: `Bearer ${authToken}`,
            }
        })
  
        if (response.status === 200) {

            let data = response.data
            data = Object.keys(data).map(key => ({
                x: parseCustomDate(key),
                y: data[key]
            }))
            data.sort((a: {x: Date, y: number}, b: {x: Date, y: number}) => a.x.getTime() - b.x.getTime())

            data = [
                {
                    type: "line",
                    color: "#088A08",
                    dataPoints: data,
                }
            ]
            return data
        } else {
            return []
        }
    } catch (err: any) {
        return []
    }
}

function parseCustomDate(dateString: string): Date | null {
    const months: { [key: string]: number } = {
        January: 0, February: 1, March: 2, April: 3, May: 4, June: 5,
        July: 6, August: 7, September: 8, October: 9, November: 10, December: 11
    };

    const match = dateString.match(/(\w+)\s+(\d{4})/);
    if (!match) {
        console.error("Invalid date format");
        return null;
    }

    const monthName = match[1];
    const year = parseInt(match[2]);

    if (!(monthName in months)) {
        console.error("Invalid month name");
        return null;
    }

    const month = months[monthName];
    return new Date(year, month);
}

