import React from "react";
import Button from 'react-bootstrap/Button'
import  Card  from "react-bootstrap/Card";
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

    const viewItemsPurchased = () => {
        navigate("/items-purchased")
    }
    return(
        <>
        <div style={{
           
           
            alignItems:"center",
            justifyContent:"center",
            width: "50w",
            height: "50h"
        }}>
            <h1 className="sub-heading">User Dashboard</h1>
            <div style={{margin : "50px"}} >  </div>

            <div className="user-dashboard">

            <Card style={{ width: '18rem' }} className="user-card">
            <Card.Body>
                <Card.Title>View Loans</Card.Title>
                <Card.Text>
                View all the loans applied by you and their approval status.
                </Card.Text>
                <Button variant="primary" size="lg" onClick={viewLoansPage}>View Loan</Button>
            </Card.Body>
            </Card>

            <Card style={{ width: '18rem' }} className="user-card">
            <Card.Body>
                <Card.Title>Apply Loans</Card.Title>
                <Card.Text>
                Quickly apply for all your loan requirements
                </Card.Text>
                <Link to='/apply-loan'> <Button variant="primary" size="lg" > Apply For Loan</Button> </Link>
            </Card.Body>
            </Card>

            <Card style={{ width: '18rem' }} className="user-card">
            <Card.Body>
                <Card.Title>View Items</Card.Title>
                <Card.Text>
                View all the items applied/purcahsed by you and their approval status.
                </Card.Text>
                <Button variant="primary" size="lg" onClick = {viewItemsPurchased}>
                View Items 
                </Button> 
            </Card.Body>
            </Card>
            </div>

           
           
             
            
        </div>
        </>
    );
}
export default Userdashboard;