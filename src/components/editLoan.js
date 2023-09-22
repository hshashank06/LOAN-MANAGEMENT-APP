import { useEffect, useState } from "react"
import { useLocation, useNavigate } from "react-router-dom"
import ItemForm from "./itemRegistrationForm"
import LoanApplyForm from "./LoanApplyForm"
const EditLoan=()=>{
    const location=useLocation()
    const id=new URLSearchParams(location.search).get('id')
    const navigate=useNavigate()
    
    return(
        <>
        <LoanApplyForm  loanId={id}/>
        </>
    )
}

export default EditLoan