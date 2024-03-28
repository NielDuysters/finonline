export type AddCategoryPopupState = {
    isOpen: boolean,
    type: string,
}

export type Category = {
    id: number,
    label: string,
    color: string,
    type: string,
    persistent: boolean,
}

export type User = {
    id: number,
    name: string,
    startCapital: number,
    currentCapital: number,
}
