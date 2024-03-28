import React, { useState, useEffect } from "react"
import { CashflowCategoryItemProps } from "./CashflowCategories"
import Dropdown from "../../components/Dropdown/Dropdown"
import { getCashflowCategoryItems } from "../../data/cashflowCategories"
import { addCashflow } from "../../data/cashflow"
import { typeDropdownItems } from "../../data/constants/typeDropdownItems"

interface AddCashflowFormProps {
    addCashflowDropdownState: boolean
    setAddCashflowDropdownState: (x: boolean) => void
    tableItemsLength: number
    setTableItemsLength: (x: number) => void
    categoriesLength: number,
}


export function AddCashflowForm({addCashflowDropdownState, setAddCashflowDropdownState, tableItemsLength, setTableItemsLength, categoriesLength}: AddCashflowFormProps) {
    
    const date = new Date()

    const [formData, setFormData] = useState({
        type: "REVENUE",
        categoryId: 0,
        amount: 0.00,
        description: "",
        transactionDate: date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear(),
    })
    
    const [formMessage, setFormMessage] = useState({
        type: "",
        message: "",
    })

    const [categoryDropdownItems, setCategoryDropdownItems] = useState([])

    const handleTypeChange = async (value: string) => {
        setFormData((prevState: typeof formData) => ({
            ...prevState,
            type: value,
        }))
    }
    
    const handleCategoryChange = async (value: string) => {
        setFormData((prevState: typeof formData) => ({
            ...prevState,
            categoryId: parseInt(value),
        }))
    }
    
    const handleAmountChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFormData((prevState: typeof formData) => ({
            ...prevState,
            amount: parseFloat(event.target.value.replace(",", ".")),
        }))
    }
    
    const handleDescriptionChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFormData((prevState: typeof formData) => ({
            ...prevState,
            description: event.target.value,
        }))
    }
    
    const handleTransactionDateChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFormData((prevState: typeof formData) => ({
            ...prevState,
            transactionDate: event.target.value,
        }))
    }
    
    const handleSubmit = async (event: React.FormEvent<HTMLFormElement> | React.MouseEvent<HTMLInputElement, MouseEvent>) => {
        event.preventDefault()
     
        try {
            const response = await addCashflow({ formData })
            if (response !== undefined && response.status === 201) {
                setTableItemsLength(tableItemsLength + 1)
                setFormMessage({
                    type: "ok",
                    message: "Successfully added.",
                })
            }
        } catch (err: any) {
            if (err instanceof Error) {
                setFormMessage({
                    type: "error",
                    message: `Error: ${err.message}`,
                })
            }
        }
    }

    useEffect(() => {
        const fetchData = async () => {
            const categoryItems = await getCashflowCategoryItems()
            const filteredItems = categoryItems
                .filter((c: CashflowCategoryItemProps) => c.type === formData.type)
                .map((c: CashflowCategoryItemProps) => ({
                    label: c.label,
                    value: c.id,
                }))
            
            setCategoryDropdownItems(filteredItems)

            const defaultCategory = categoryItems
                .filter((c: CashflowCategoryItemProps) => c.type === formData.type && c.persistent === true)
                .map((c: CashflowCategoryItemProps) => c.id)
          
            setFormData((prevState: typeof formData) => ({
                ...prevState,
                categoryId: parseInt(defaultCategory),
            }))
        }

        fetchData()
    }, [formData.type, categoriesLength])


    return (
        addCashflowDropdownState ? (
            <form id="add-cashflow-form" onSubmit={(event) => handleSubmit(event)}>
                <div className="input-wrapper">
                    <span className="label">Type</span>
                    <Dropdown items={typeDropdownItems} onItemSelect={(label, value) => handleTypeChange(value)} />
                </div>
                <div className="input-wrapper">
                    <span className="label">Category</span>
                    <Dropdown items={categoryDropdownItems} onItemSelect={(label, value) => handleCategoryChange(value)} />
                </div>
                <div className="input-wrapper">
                    <span className="label">Amount</span>
                    <span className="input-icon">&euro;</span>
                    <input type="text" className="input-padding-left" placeholder="0,00" onChange={(event) => handleAmountChange(event)} />
                </div>
                <div className="input-wrapper">
                    <span className="label">Description</span>
                    <input type="text" placeholder="Food" onChange={(event) => handleDescriptionChange(event)} />
                </div>
                <div className="input-wrapper">
                    <span className="label">Transaction date</span>
                    <input type="text" placeholder="dd/mm/yyyy" value={formData.transactionDate} onChange={(event) => handleTransactionDateChange(event)} />
                </div>

                <input type="submit" value="Add" />
                
                <span style={{color: formMessage.type === "ok" ? "#088A08" : "#FF0000"}} className="message">{formMessage.message}</span>
            </form>
        ) : null
    )
}

