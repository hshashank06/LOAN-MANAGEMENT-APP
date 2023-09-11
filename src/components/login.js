import React from "react";
import {useState, useEffect} from 'react'
import Button from 'react-bootstrap/Button'
import Form from 'react-bootstrap/Form'
function Login(){


    const [userId,setUserId] = useState();
    const [userPassword,setUserPassword] = useState();
    
    const loginUser= async ()=>{

        const user = 
        {userId:userId,
        userPassword:userPassword};

        console.log(user)
        try{
            const res= await fetch('http://localhost:8082/loanapp/validate',{
                method:'POST',
                body:JSON.stringify(user),
                headers:{
                    'Content-Type':'application/json'
                }
            })
            console.log(user)
            
            const data = await res.text();
            console.log(data)
            if(data === "VERIFIED"){
                alert("LOGIN IS COMPLETE")
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
               <Form.Group> <Form.Label htmlFor="email" >Email </Form.Label><Form.Control type ="email" value = {userId} placeholder="Enter Email"  onChange={(e)=>setUserId(e.target.value)}/>
               </Form.Group>
               <Form.Group><Form.Label htmlFor="password">Password</Form.Label><Form.Control type ="password" value = {userPassword} placeholder="Enter Password"  onChange={(e)=>setUserPassword(e.target.value)}/></Form.Group> 
                <Button type ="submit" onClick = {loginUser}>Submit</Button>

            </Form>
            
        </div>
    );
}
export default Login;