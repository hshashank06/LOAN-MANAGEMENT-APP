import React from "react";
import Button from 'react-bootstrap/Button'
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";

function Userdashboard()
{
    const navigate= useNavigate();
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
           <Link to='/view-loan'> <Button variant="primary" size="lg">View Loan</Button> </Link>
           <Link to='/apply-loan'> <Button variant="primary" size="lg" > Apply For Loan</Button> </Link>
            <Link to= '/'> <Button variant="primary" size="lg">
                View Items Purchased
            </Button> </Link>
        </div>
        </>
    );
}
export default Userdashboard;