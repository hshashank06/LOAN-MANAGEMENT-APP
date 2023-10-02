import { useEffect, useState } from "react"
import { useLocation, useNavigate } from "react-router-dom"
import ItemForm from "./itemRegistrationForm"
import  Button  from 'react-bootstrap/Button'


const EditItem=()=>{
    const location=useLocation()
    const id=new URLSearchParams(location.search).get('id')
    const navigate=useNavigate()

    const handleClick = () => {
        navigate(-1);
    }
    
    return(
        <>
        <Button variant="secondary" id="register-button" onClick={handleClick}>Show All Items</Button>
        <ItemForm onRegister={()=>{navigate('/item-management')}} itemId={id}/>
        </>
    )
}

export default EditItem