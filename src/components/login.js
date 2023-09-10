import React from "react";
function Login(){
   
    return(
        <div style={{
            display:"flex",
            flexDirection: "column",
            alignItems:"center",
            justifyContent:"center",
            width: "100vw",
            height: "100vh"
        }}>
            <p className="title"> <h1>User Login</h1>
           </p> 
            <form>
               <div> <label htmlFor="email" >Email </label><input type ="email"placeholder="Enter Email"/>
               </div>
               <div><label For="password">Password</label><input type ="password" placeholder="Enter Password"/></div> 
                <input type ="submit"/>

            </form>
            
        </div>
    );
}
export default Login;