import RegistrationForm from "./registrationForm"
import { useEffect, useState } from "react"
import { useLocation, useNavigate } from "react-router-dom"

const EditUser=()=>{
    const location=useLocation()
    const id=new URLSearchParams(location.search).get('id')
    const navigate=useNavigate()
    
    return(
        <>
        <RegistrationForm onRegister={()=>{navigate('/customer')}} userId={id}/>
        </>
    )
}

export default EditUser