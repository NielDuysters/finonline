import "./Dropdown.sass"
import { useState, useEffect } from "react"

interface DropdownProps {
    items: { label: string; value: string }[]
    onItemSelect: (label: string, value: string) => void
}

function Dropdown({items, onItemSelect}: DropdownProps) {
    const [isOpen, setIsOpen] = useState(false)
    const [selected, setSelected] = useState({label: "", value: ""})

    useEffect(() => {
        if (items.length > 0) {
            setSelected({
                label: items[0].label,
                value: items[0].value,
            })
        }      
    }, [items])

    if (items.length === 0) {
        return null
    }

    const handleItemSelect = (label: string, value: string) => {
        setSelected(() => ({
            label: label,
            value: value,
        }))

        onItemSelect(label, value)
    }

    return (
        <div className="dropdown" onClick={(event) => setIsOpen(!isOpen)}>
            <div className="dropdown-selected">{ selected.label } <img alt="" className={isOpen ? "open" : ""} src="/assets/images/panel/dropdown-arrow.png" /></div>
            <DropdownList items={items} isOpen={isOpen} setIsOpen={setIsOpen} handleItemSelect={handleItemSelect} selected={selected} />
        </div>
    )
}

function DropdownList({items, isOpen, setIsOpen, handleItemSelect, selected}: {items: {label: string; value: string;}[], isOpen: boolean, setIsOpen: (x: boolean) => void, handleItemSelect: (label: string, value: string) => void, selected: {label: string; value: string;}}) {

    const renderItems = () => {
        return items
        .map(c => (
            <div className="dropdown-item" key={c.value} onClick={() => handleItemSelect(c.label, c.value)}>{c.label}</div>
        ))
    }

    return (
        isOpen ? (
            <div className="dropdown-list">
                { renderItems() }
            </div>
        ) : null
    )
}

export default Dropdown
