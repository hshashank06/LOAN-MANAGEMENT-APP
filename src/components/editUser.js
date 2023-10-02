import RegistrationForm from "./registrationForm"
import { useEffect, useState } from "react"
import { useLocation, useNavigate } from "react-router-dom"
import  Button  from 'react-bootstrap/Button'

const EditUser=()=>{
    const location=useLocation()
    const id=new URLSearchParams(location.search).get('id')
    const navigate=useNavigate()

    const handleClick = () => {
        navigate(-1);
    }

    
    
    return(
        <>
        <Button variant="secondary" id="register-button" onClick={handleClick}>Show All Users</Button>
        <RegistrationForm onRegister={()=>{navigate('/customer')}} userId={id}/>
        </>
    )
}

export default EditUser