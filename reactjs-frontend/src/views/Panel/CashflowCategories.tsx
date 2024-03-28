import React, { useState, useEffect } from "react"
import { addCategory, deleteCategory, getCashflowCategoryItems } from "../../data/cashflowCategories"
import * as Types from "../../types"

export interface CashflowCategoryItemProps {
    label: string
    color?: string
    persistent: boolean
    id: number
    type: string
    categoriesLength: number
    setCategoriesLength: (x: number) => void
}

export interface AddCategoryItemFormProps {
    addCategoryPopupState: Types.AddCategoryPopupState
    setAddCategoryPopupState: (x: React.SetStateAction<Types.AddCategoryPopupState>) => void
    categoriesLength: number
    setCategoriesLength: (x: number) => void
}

export function CashflowCategories({categoriesLength, setCategoriesLength} : {categoriesLength: number, setCategoriesLength: (x: number) => void}) {
    const [categories, setCategories] = useState<Types.Category[]>([])
    const [addCategoryPopupState, setAddCategoryPopupState] = useState<Types.AddCategoryPopupState>({isOpen: false, type: ""})

    useEffect(() => {
        const fetchCategories = async () => {
            const items = await getCashflowCategoryItems()
            setCategories(items)
            setCategoriesLength(items.length)
        }

        fetchCategories()
    }, [categoriesLength])

    const renderCategoryItems = (type: string) => {
        return categories
        .filter(c => c.type === type)
        .map(c => (
            <CashflowCategoryItem key={c.id} id={c.id} type={c.type} persistent={c.persistent} label={c.label} color={c.color} categoriesLength={categoriesLength} setCategoriesLength={setCategoriesLength} />
        ))
    }

    return (
        <div id="cashflow-categories-container">
            <div className="cashflow-category">
                <span className="title">Revenue categories</span>

                <div className="cashflow-category-items">
                    { renderCategoryItems("REVENUE") }
                    <div key="0" className="cashflow-category-item add-category-item" onClick={() => setAddCategoryPopupState(prevState => ({
                        isOpen: true,
                        type: "revenue"
                    }))}>+ Add</div>
                </div>
            </div>
            <div className="cashflow-category">
                <span className="title">Expense categories</span>

                <div className="cashflow-category-items">
                    { renderCategoryItems("EXPENSE") }

                    <div key="0" className="cashflow-category-item add-category-item" onClick={() => setAddCategoryPopupState(prevState => ({
                        isOpen: true,
                        type: "expense"
                    }))}>+ Add</div>
                </div>
            </div>
            
            <AddCategoryItemForm addCategoryPopupState={addCategoryPopupState} setAddCategoryPopupState={setAddCategoryPopupState} categoriesLength={categoriesLength} setCategoriesLength={setCategoriesLength} />
        </div>
    )
}

function CashflowCategoryItem(props: CashflowCategoryItemProps) {
    const handleDelete = async (event: React.MouseEvent<HTMLDivElement, MouseEvent>, id: number) => {
        try {
            await deleteCategory(id)
            props.setCategoriesLength(props.categoriesLength - 1)
        } catch (err: any) {
            if (err instanceof Error) {
                console.log(err)
            }
        }
    }
    
    return (
        <div className="cashflow-category-item" key={props.id.toString()} data-persistent={props.persistent.toString()}>
            <div className="delete-cashflow-category-item" onClick={(event) => handleDelete(event, props.id)}>
                <img alt="Delete" src="/assets/images/panel/delete.png" />
            </div>

            <div className="bullet" style={{ backgroundColor: props.color == null ? "#000000" : props.color }}></div>
            <span className="label">{ props.label }</span>
        </div>
    )
}

function AddCategoryItemForm({addCategoryPopupState, setAddCategoryPopupState, categoriesLength, setCategoriesLength}: AddCategoryItemFormProps) {
    const [formData, setFormData] = useState({
        label: ""
    })
    const [formMessage, setFormMessage] = useState("")
    
    const handleSubmit = async (event: React.FormEvent<HTMLFormElement> | React.MouseEvent<HTMLInputElement, MouseEvent>, type: string) => {
        event.preventDefault()
     
        try {
            await addCategory({
                label: formData.label,
                type: type.toUpperCase(),
            })

            handleClosePopup()
            setCategoriesLength(categoriesLength + 1)
        } catch (err: any) {
            if (err instanceof Error) {
                setFormMessage(err.message)
            }
        }
    }

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFormData({ label: event.target.value  });
    }
    
    const handleClosePopup = () => {
        setAddCategoryPopupState((prevState: Types.AddCategoryPopupState) => ({
            ...prevState,
            isOpen: false,
        }))
        
        setFormData({ label: "" })
    }

    return (
        addCategoryPopupState.isOpen ? (
            <div>
                <div id="popup-background" />
                <div className="popup">
                    <div className="close-popup" onClick={handleClosePopup}>&times;</div>
                    <span className="popup-title">Add { addCategoryPopupState.type } category</span>
                    <form onSubmit={(event) => handleSubmit(event, addCategoryPopupState.type)}>
                        <input type="text" placeholder="Category label" value={formData.label} onChange={handleChange} />
                        <input type="submit" value="Add" />
                    </form>
                    <span style={{color: "#FF0000"}} className="popup-response-message">{formMessage}</span>
                </div>
            </div>
        ) : null
    )
}

