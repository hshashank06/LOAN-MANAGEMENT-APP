import {useState, useEffect} from 'react'
import Button from 'react-bootstrap/Button'
import Form from 'react-bootstrap/Form'
import {Link} from 'react-router-dom'
import { Col } from 'react-bootstrap'

const LoanApplyForm = () =>{
    const [firstName,setFirstName]=useState('')
    const [dob,setdob] = useState('');
    const [lastName,setLastName]=useState('')
    const [email,setEmail]=useState('')
    const [age,setAge]=useState('')
    const [password,setPassword]=useState('')
    const [errorValues,setErrorValues]=useState({})
    const [submit,setSubmit]=useState(false)
    const [registrationError,setRegistrationError]=useState({})
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
                <Form.Control type="text" placeholder="Add Loan Type" />
                {/* </Col> */}
            </Form.Group>
            <Form.Group>
                {/* <Col xs={3}> */}
                <Form.Label>Loan Amount</Form.Label>
                <Form.Control type="text" placeholder="Add Loan Amount" />
                {/* </Col> */}
            </Form.Group>
            <Form.Group>
                {/* <Col xs={3}> */}
                <Form.Label>First Name</Form.Label>
                <Form.Control type="text" placeholder="Add First Name" />
                {/* </Col> */}
            </Form.Group>
            <Form.Group>
                {/* <Col xs={3}> */}
                <Form.Label>Last Name</Form.Label>
                <Form.Control type="text" placeholder="Add Last Name" />
                {/* </Col> */}
            </Form.Group>
            <Button type="submit" >Apply</Button>
            <Link to='/user-dashboard'><Button variant="secondary" >Go Back</Button></Link>

        </Form>
        </div>
            )
            }
export default LoanApplyForm