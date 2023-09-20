import {Link} from 'react-router-dom'
import  Button  from 'react-bootstrap/Button'
import Table from 'react-bootstrap/Table'
import {useState, useEffect} from 'react'
import RegistrationForm from './registrationForm'
const LoanManagement=()=>{
    const [addLoan,setLoan]=useState(false)
    const [response,setResponse]=useState([])
    const handleClick=()=>{
        setLoan(!addLoan)
        console.log(addLoan)
    }
    
    const displayData=async()=>{
        try{
            const url="http://localhost:8082/loanapp/display/loan/all"
            let options={
                method:'GET'
            }
            const res=await fetch(url,options)
            const data=await res.json()
            setResponse(data)
            console.log(data)
        }
        catch(e){
            console.log(e)
        }
        
    }
    useEffect(()=>{
        displayData()  
    },[])

    const onApprove=async(e)=>{
        const loanId=e.target.parentNode.id
        try{
            let status="YES"
            if(e.target.id=="NO")
            status="NO"
            const res= await fetch(`http://localhost:8082/loanapp/loan/${loanId}/issue`,{
                method:'POST',
                mode:'cors',
                body:JSON.stringify({
                    loanId,
                    status
                }),
                headers:{
                    'Content-Type':'application/json'
                }
            })
           

            const data = await res.json();
            if(res.status===200){
                await displayData()
            }
        }
        catch(e){

        }

    }


    const onDelete=async(e)=>{
        const loanId=e.target.parentNode.id
        try{
            console.log("delete")
            const res= await fetch('http://localhost:8082/loanapp/delete/loan/'+loanId,{
                method:'POST',
                mode:'cors',
                body:JSON.stringify({
                    loanId
                }),
                headers:{
                    'Content-Type':'application/json'
                }
            })
           

            const data = await res.json();
           
            
        }
        catch(e){

        }
        await displayData()
    }
    
    return(
        <div>
            <Button variant="secondary" id="register-button" onClick={handleClick}>{addLoan?'Show All Loans': 'Apply Loans'}</Button>
            <>
            {addLoan && <RegistrationForm onRegister={()=>{}}/>}
            </>
            {!addLoan && <Table striped bordered hover>
            <thead>
                <tr>
                <th>#</th>
                <th>Loan Id</th>
                <th>Loan Type</th>
                <th>Loan Duration</th>
                <th>User</th>
                {/* <th>Items</th> */}
                </tr>
            </thead>
            <tbody>
                {
                    response && response.map((loanData)=>(
                        <tr key={loanData.loan.loanId}>
                            <td>{response.indexOf(loanData)+1}</td>
                            <td>{loanData.loan.loanId}</td>
                            <td>{loanData.loan.loanType}</td>
                            <td>{loanData.loan.loanDuration}</td>
                            <td>{loanData.user.firstName+' '+loanData.user.lastName}</td>
                            {/* <td>{loan.item.length}</td> */}
                            <td id={loanData.loan.loanId}><Button id="YES" variant="link" disabled={loanData.loan.status==="YES"?true:false} onClick={onApprove}>{loanData.loan.status==="YES"?'Approved':'Approve'}</Button></td>
                            <td id={loanData.loan.loanId}><Button id="NO" variant="link" disabled={loanData.loan.status==="NO"?true:false} onClick={onApprove}>{loanData.loan.status==="NO"?'Rejected':'Reject'}</Button></td>
                            <td id={loanData.loan.loanId}><Button variant="link" onClick={onDelete}>Delete</Button></td>

                        </tr>
                    ))
                }
            </tbody>
            </Table>}
        </div>
    )
}

export default LoanManagement