import React from "react";
import Button from 'react-bootstrap/Button'
import { useLocation, useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import { useUser } from "./userContext";

function Userdashboard()
{
    const navigate= useNavigate();
    const location = useLocation();
    const {userId} = useUser();
    console.log(userId,"Here")



    const viewLoansPage = () => {

        navigate("/view-loan",{state:{userId:userId}})
    }
    return(
        <>
        <div style={{
           
           
            alignItems:"center",
            justifyContent:"center",
            width: "50w",
            height: "50h"
        }}>
            <h1>User Dashboard</h1>
           <div style={{margin : "50px"}} >  </div><Button variant="primary" size="lg" onClick={viewLoansPage}>View Loan</Button>
           <Link to='/apply-loan'> <Button variant="primary" size="lg" > Apply For Loan</Button> </Link>
            <Link to= '/'> <Button variant="primary" size="lg">
                View Items Purchased
            </Button> </Link>
        </div>
        </>
    );
}
export default Userdashboard;