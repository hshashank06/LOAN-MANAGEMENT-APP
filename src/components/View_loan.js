import {Link} from 'react-router-dom'
import  Button  from 'react-bootstrap/Button'
import Table from 'react-bootstrap/Table'
const Viewloan=()=>{
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
                <th>Loan Type </th>
                <th>Loan Account Number</th>
                <th>Principle Amount</th>
                <th>Interest rate</th>
                <th> Repayment Period</th>
                </tr>
            </thead>
            <tbody>
                {
                    dummyData && dummyData.map((loan)=>(
                        <tr key={loan.loantype}>
                            <td>{dummyData.indexOf(loan)+1}</td>
                            <td>{loan.loantype}</td>
                            <td>{loan.accountnumber}</td>
                            <td>{loan.principleamount}</td>
                            <td>{loan.interestrate}</td>
                            <td>{loan.Repaymentperiod}</td>
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