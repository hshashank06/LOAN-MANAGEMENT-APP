import { useEffect, useState } from "react"
import { useLocation, useNavigate } from "react-router-dom"
import ItemForm from "./itemRegistrationForm"
const EditItem=()=>{
    const location=useLocation()
    const id=new URLSearchParams(location.search).get('id')
    const navigate=useNavigate()
    
    return(
        <>
        <ItemForm onRegister={()=>{navigate('/item-management')}} itemId={id}/>
        </>
    )
}

export default EditItem