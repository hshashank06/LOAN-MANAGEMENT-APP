import {useState, useEffect, useContext} from 'react'
import Button from 'react-bootstrap/Button'
import Form from 'react-bootstrap/Form'
import {Link, useNavigate} from 'react-router-dom'
import { Col } from 'react-bootstrap'
import { UserContext } from './userContext'
import Modal from 'react-bootstrap/Modal'
import PopupModal from './popupModal'

const LoanApplyForm = (loanId) =>{

    const [loanType,setLoanType] = useState("");
    const options = ["CAR","FURNITURE","EDUCATION","HOME","MEDICAL","ACCIDENT","PROPERTY"]
    const [loanduration,setLoanDuration] = useState("");
    const [status,setStatus]=useState("")
    const [show, setShow] = useState(false);
    const [popupHeading,setHeading]=useState('')
    const [popupBody,setBody]=useState('')
    

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    // const {userId} = useContext(UserContext);
    const userId=localStorage.getItem('userId')
    const navigate=useNavigate()
    const registerLoan = async () => {

        const dataToSend = {
            loanType : loanType,
            loanDuration:loanduration,
        }
        const url = `http://localhost:8082/loanapp/register/${userId}/loan`
        const options = {
            method : 'POST',
            mode: 'cors',
            headers:{
                'Content-Type':'application/json'
            },
            body:JSON.stringify(dataToSend)
        }
        try{
            const response = await fetch(url,options);
            const reply = await response.text();
            console.log(reply)
            if(reply ==="NEW Loan REGISTERED"){
                setHeading('Success !!')
                setBody('Loan has been submitted for admin approval')
                setShow(true)
            }
            else{
                setHeading('Loan Application Failed!!')
                setBody('Please enter all the details correctly!')
                setShow(true)
            }
        }
        catch(e){
            setHeading('Loan Application Failed!!')
            setBody('Please enter correct details!')
            setShow(true)
        }
        
    }

    const handleGoBack = () => {
        navigate(-1);
    }


    const updateLoan= async(loan)=>{
        
        console.log(loan)
        try{
            const res= await fetch('http://localhost:8082/loanapp/loan/'+loanId.loanId+'/update',{
                method:'PUT',
                body:JSON.stringify(loan),
                headers:{
                    'Content-Type':'application/json'
                }
            })
           
            console.log(res)
            // const data = await res.text();
            if(res.ok ===  true){
                setHeading('Success !!')
                setBody('Loan has been updated')
                setShow(true)
                navigate('/loan-management')
            }
            else{
                alert("loan could not be updated")
            }

        }
        catch(e){
            console.log(e)
            // setRegistrationError(e)
            // console.log(registrationError)
            
        }
    }

    const onButtonClick = (e) => {
        e.preventDefault()
        if(loanId.loanId)
        updateLoan({loanType,'loanDuration':parseInt(loanduration),status})
        else
        registerLoan();
        setLoanType('');
        setLoanDuration('');
    }
    useEffect(()=>{
        const displayData=async()=>{
            try{
                const url="http://localhost:8082/loanapp/display/loan/all"
                let options={
                    method:'GET'
                }
                const res=await fetch(url,options)
                const data=await res.json()
                console.log(loanId)
                const desiredLoan=data.find((el)=>el.loan.loanId==loanId.loanId)
                console.log(desiredLoan)
                // setUserValues(desiredUser)
                console.log(data)
                setLoanType(desiredLoan.loan.loanType)
                setLoanDuration(desiredLoan.loan.loanDuration)
                setStatus(desiredLoan.loan.status)
            }
            catch(e){
                console.log(e)
            }
            
        }
        if(loanId.loanId){
            console.log("lol")
            displayData()
            // console.log(userValues)
        }
    },[]) 
    
    return(
       <div style={{
        margin:"auto",
        width:"30%",
        flexDirection: "column",
        alignItems:"center",
        justifyContent:"center",
        
    }}> <Link to='/apply-loan' ></Link> 
        <h2 className='sub-heading'>Loan Apply Form</h2>
        <PopupModal show={show} heading={popupHeading} body={popupBody} handleClose={handleClose}/>
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
                <Form.Label>Loan Duration (in yrs)</Form.Label>
                <Form.Control type="text" placeholder="Add a Loan Duration" value={loanduration} onChange = {(event) => {setLoanDuration(event.target.value)}} />
                {/* </Col> */}
            </Form.Group>
            
            <Button className="mt-2 ml-2 mr-2" type="submit" onClick={onButtonClick}>{loanId.loanId?'Update':'Apply'}</Button>
            <Button className="mt-2 ml-2 mr-2" variant="secondary" onClick = {handleGoBack} >Go Back</Button>

        </Form>
        </div>
            )
            }
export default LoanApplyForm