import React from "react";
import Button from 'react-bootstrap/Button'
import Form from 'react-bootstrap/Form'
function Login(){
   
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
               <Form.Group> <Form.Label htmlFor="email" >Email </Form.Label><Form.Control type ="email"placeholder="Enter Email"/>
               </Form.Group>
               <Form.Group><Form.Label htmlFor="password">Password</Form.Label><Form.Control type ="password" placeholder="Enter Password"/></Form.Group> 
                <Button type ="submit">Submit</Button>

            </Form>
            
        </div>
    );
}
export default Login;