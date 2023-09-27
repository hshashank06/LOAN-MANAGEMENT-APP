import React, { useContext } from "react";
import {useState, useEffect} from 'react'
import Button from 'react-bootstrap/Button'
import Form from 'react-bootstrap/Form'
import  Modal  from "react-bootstrap/Modal";
import {useNavigate} from 'react-router-dom'
import { UserContext, useUser } from "./userContext";
import PopupModal from "./popupModal";

function Login(){


    const {userId,setUserId}= useContext(UserContext);
    const [userPassword,setUserPassword] = useState();
    const [isAdmin,setAdmin]=useState(false)
    const [show, setShow] = useState(false);
    const [popupHeading,setHeading]=useState('')
    const [popupBody,setBody]=useState('')
    

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const navigate=useNavigate()

    const loginUser= async (e)=>{
        e.preventDefault()
        const user = 
        {userId:userId,
        userPassword:userPassword};
        const admin={
            adminId:userId,
            adminPassword:userPassword
        }
        console.log(user)
        try{
            let adminLogin=false
            let payload=user
            if(isAdmin){
                adminLogin=true
                payload=admin
            }
            let url='http://localhost:8082/loanapp/validate/user'
            if(adminLogin){
                url='http://localhost:8082/loanapp/validate/admin'
            }
            const res= await fetch(url,{
                method:'POST',
                mode:'cors',
                body:JSON.stringify(payload),
                headers:{
                    'Content-Type':'application/json'
                }
            })
            console.log(user)
            
            const data = await res.text();
            console.log(data)
            if(data === "TRUE"){
                // alert("LOGIN IS COMPLETE")
                if(!adminLogin){
                    console.log(userId)
                    localStorage.setItem('userId',userId)
                    localStorage.setItem('adminId','')

                navigate('/user-dashboard')
                }
                else{
                    localStorage.setItem('userId','')
                    localStorage.setItem('adminId',userId)
                    navigate('/admin-dashboard')
                }
               
            }
            else{
                setHeading('Login Failed!!')
                setBody('Incorrect login Id or password!')
                setShow(true)
            }
        }
        catch(e){
            // console.log(e)
            setHeading('Error!!')
            setBody('Login could not be completed!')
            setShow(true)
            console.log(e)
        }
    }

   
    return(
        <div className="bg-page">
        <div style={{
            display:"flex",
            flexDirection: "column",
            alignItems:"center",
            justifyContent:"center",
            width: "100vw",
            height: "50vh"
        }}>
            <div className="title"> <h2 className=" sub-heading bg-heading">User Login</h2>
           </div> 
           <PopupModal show={show} heading={popupHeading} body={popupBody} handleClose={handleClose}/>
            <Form style={{
                margin:"auto",
                width:"30vw"
            }}>
               <Form.Group> <Form.Label >User ID </Form.Label><Form.Control type ="text" value = {userId} placeholder="Enter User Id"  onChange={(e)=>setUserId(e.target.value)}/>
               </Form.Group>
               <Form.Group><Form.Label htmlFor="password">Password</Form.Label><Form.Control type ="password" value = {userPassword} placeholder="Enter Password"  onChange={(e)=>setUserPassword(e.target.value)}/></Form.Group>
               <Form.Group><Form.Label >Are you an Admin</Form.Label><input type ="checkbox" checked ={isAdmin}   onChange={()=>setAdmin(!isAdmin)}/></Form.Group>  
                <Button type ="submit" onClick = {loginUser}>Submit</Button>

            </Form>
            
        </div>
        </div>
    );
}
export default Login;