import React from "react";
import Button from 'react-bootstrap/Button'
function Userdashboard()
{
    return(
        <>
        <div style={{
            display:"flex",
            flexDirection: "column",
            alignItems:"center",
            justifyContent:"center",
            width: "100vw",
            height: "50vh"
        }}>
            <h1>User Dashboard</h1>
           <p>  <Button variant="primary" size="lg">View Loan</Button> </p>
           <p> <Button variant="primary" size="lg">
                Apply For Loan
            </Button> </p>
           <p> <Button variant="primary" size="lg">
                View Items Purchased
            </Button> </p>
        </div>
        </>
    );
}
export default Userdashboard;