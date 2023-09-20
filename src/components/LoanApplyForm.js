import {useState, useEffect, useContext} from 'react'
import Button from 'react-bootstrap/Button'
import Form from 'react-bootstrap/Form'
import {Link} from 'react-router-dom'
import { Col } from 'react-bootstrap'
import { UserContext } from './userContext'

const LoanApplyForm = () =>{

    const [loanType,setLoanType] = useState("");
    const options = ["CAR","FURNITURE","EDUCATION","HOME","MEDICAL","ACCIDENT","PROPERTY"]
    const [loanduration,setLoanDuration] = useState("");
    const {userId} = useContext(UserContext);

    const registerLoan = async () => {

        const dataToSend = {
            loanType : loanType,
            loanDuration:loanduration,
        }
        const url = `http://localhost:8082/loanapp/register/loan/${userId}`
        const options = {
            method : 'POST',
            mode: 'cors',
            headers:{
                'Content-Type':'application/json'
            },
            body:JSON.stringify(dataToSend)
        }

        const response = await fetch(url,options);
        const reply = await response.text();
    }

    const onButtonClick = () => {
        registerLoan();
        setLoanType('');
        setLoanDuration(null);
    }
    
    return(
       <div style={{
        margin:"auto",
        width:"30%",
        flexDirection: "column",
        alignItems:"center",
        justifyContent:"center",
        
    }}> <Link to='/apply-loan' ></Link> 
        <Form className="loan-form" >
            <Form.Group>
                
                {/* <Col xs={3}> */}
                <Form.Label>Loan Type</Form.Label>
                <Form.Control as="select" value={loanType} onChange={(event) => {setLoanType(event.target.value)}} >
                <option value = "">Choose A Loan Type</option>
                <option value = "CAR">CAR</option>
                <option value = "FURNITURE">FURNITURE</option>
                <option value = "EDUCATION">EDUCATION</option>
                <option value = "HOME">HOME</option>
                <option value = "MEDICAL">MEDICAL</option>
                <option value = "ACCIDENT">ACCIDENT</option>
                <option value = "PROPERTY">PROPERTY</option>
                
                
                {/* </Col> */}
                </Form.Control>
            </Form.Group>
            <Form.Group>
                {/* <Col xs={3}> */}
                <Form.Label>Loan Duration</Form.Label>
                <Form.Control type="text" placeholder="Add a Loan Duration" onChange = {(event) => {setLoanDuration(event.target.value)}} />
                {/* </Col> */}
            </Form.Group>
            
            <Button className="mt-2 ml-2 mr-2" type="button" onClick={onButtonClick}>Apply</Button>
            <Link  to='/user-dashboard'><Button className="mt-2 ml-2 mr-2" variant="secondary" >Go Back</Button></Link>

        </Form>
        </div>
            )
            }
export default LoanApplyForm