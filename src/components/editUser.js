import RegistrationForm from "./registrationForm"
import { useEffect, useState } from "react"
import { useLocation } from "react-router-dom"

const EditUser=()=>{
    const location=useLocation()
    const id=new URLSearchParams(location.search).get('id')

    // const getUserData=async()=>{
    //     try{
    //         const url="http://localhost:8082/loanapp/get/users"
    //         let options={
    //             method:'GET'
    //         }
    //         const res=await fetch(url,options)
    //         const data=await res.json()
    //         setResponse(data)
    //         console.log(data)
    //     }
    //     catch(e){
    //         console.log(e)
    //     }
    // }
    useEffect(()=>{
        console.log(id)

    })
    return(
        <>
        <RegistrationForm/>
        </>
    )
}

export default EditUser