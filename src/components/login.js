import React, { useContext } from "react";
import {useState, useEffect} from 'react'
import Button from 'react-bootstrap/Button'
import Form from 'react-bootstrap/Form'
import {useNavigate} from 'react-router-dom'
import { UserContext, useUser } from "./userContext";
function Login(){


    const {userId,setUserId}= useContext(UserContext);
    const [userPassword,setUserPassword] = useState();
    const [isAdmin,setAdmin]=useState(false)
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
                navigate('/user-dashboard')
                }
                else
                navigate('/admin-dashboard')
            }
            else{
                alert("LOGIN COULD NOT BE DONE")
            }
        }
        catch(e){
            // console.log(e)
            console.log(e)
        }
    }

   
    return(
        <div style={{
            display:"flex",
            flexDirection: "column",
            alignItems:"center",
            justifyContent:"center",
            width: "100vw",
            height: "50vh"
        }}>
            <div className="title"> <h2>User Login</h2>
           </div> 
            <Form>
               <Form.Group> <Form.Label >User ID </Form.Label><Form.Control type ="text" value = {userId} placeholder="Enter User Id"  onChange={(e)=>setUserId(e.target.value)}/>
               </Form.Group>
               <Form.Group><Form.Label htmlFor="password">Password</Form.Label><Form.Control type ="password" value = {userPassword} placeholder="Enter Password"  onChange={(e)=>setUserPassword(e.target.value)}/></Form.Group>
               <Form.Group><Form.Label >Are you an Admin</Form.Label><input type ="checkbox" checked ={isAdmin}   onChange={()=>setAdmin(!isAdmin)}/></Form.Group>  
                <Button type ="submit" onClick = {loginUser}>Submit</Button>

            </Form>
            
        </div>
    );
}
export default Login;