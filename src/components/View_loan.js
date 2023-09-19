import {Link, useLocation} from 'react-router-dom'
import  Button  from 'react-bootstrap/Button'
import Table from 'react-bootstrap/Table'
import { useContext, useEffect, useState } from 'react'
import { UserContext, useUser } from './userContext'
const Viewloan=()=>{
    const [response,setResponse] = useState([]);
    const location = useLocation();
    const {userId,setUserId} = useContext(UserContext);
    console.log(userId);

    const getDataFromBackend = async () => {
        const url = `http://localhost:8082/loanapp/display/loan/userId/${userId}`
        let options = {
            method : 'GET',
            mode : 'cors',
            headers:{
                'Content-Type':'application/json'
            },
        }
        const response = await fetch(url,options)
        const data = await response.json();
        console.log(data)
        setResponse(data)

    }

    useEffect(() => {
        getDataFromBackend();
    },[])

    const dummyData=[
        {
            "loantype":"Educational loan",
            "accountnumber":"23452435266624",
            "principleamount":"250000",
            "interestrate":10,
            "Repaymentperiod":21
        },
       
        {
            "loantype":"commercial loan",
            "accountnumber":"23452435266624",
            "principleamount":"250000",
            "interestrate":10,
            "Repaymentperiod":21
        }
    ]
    return(
        <div>
            <Link to='/view-loan'> </Link>
            <Table striped bordered hover>
            <thead>
                <tr>
                <th>#</th>
                <th>Loan Id</th>
                <th>Loan Type</th>
                <th>Loan Duration</th>
                <th>Loan Status</th>
                
                </tr>
            </thead>
            <tbody>
                {
                    response && response.map((loan)=>(
                        <tr key={loan.loanId}>
                            <td>{response.indexOf(loan)+1}</td>
                            <td>{loan.loanId}</td>
                            <td>{loan.loanType}</td>
                            <td>{loan.loanDuration}</td>
                            <td>{loan.status}</td>
                            
                        </tr>
                    ))
                }
            </tbody>
            </Table>
            <Link to='/user-dashboard'><Button variant="secondary">Go Back</Button></Link>
        </div>
    )
}

export default Viewloan