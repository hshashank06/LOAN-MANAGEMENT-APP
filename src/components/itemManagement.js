import {Link, useNavigate} from 'react-router-dom'
import  Button  from 'react-bootstrap/Button'
import Table from 'react-bootstrap/Table'
import {useState, useEffect} from 'react'
import RegistrationForm from './registrationForm'
import ItemForm from './itemRegistrationForm'
const LoanManagement=()=>{
    const [addLoan,setLoan]=useState(false)
    const [response,setResponse]=useState([])
    const handleClick=()=>{
        setLoan(!addLoan)
        console.log(addLoan)
    }
    const navigate=useNavigate()
    
    const displayData=async()=>{
        try{
            const url="http://localhost:8082/loanapp/display/items"
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
            const res= await fetch('http://localhost:8082/loanapp/loan/issue',{
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

    const onEdit =async(e)=>{
        const itemId=e.target.parentNode.id
        navigate('/edit-item?id='+itemId) 
    }
    const onDelete=async(e)=>{
        const itemId=e.target.parentNode.id
        try{
            console.log("delete")
            const res= await fetch('http://localhost:8082/loanapp/delete/item',{
                method:'DELETE',
                mode:'cors',
                body:JSON.stringify({
                    itemId
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
            <h2 className='sub-heading admin-heading'>Item Management</h2>

            <Button variant="secondary" id="register-button" onClick={handleClick}>{addLoan?'Show All Items': 'Register Items'}</Button>
            <>
            {addLoan && <ItemForm onRegister={displayData}/>}
            </>
            {!addLoan && <Table striped bordered hover>
            <thead>
                <tr>
                <th>#</th>
                <th>Item Id</th>
                <th>Description</th>
                <th>Item Type</th>
                <th>Item Make</th>
                <th>Value</th>
                <th>Issue Status</th>

                {/* <th>User</th>
                <th>Items</th> */}
                </tr>
            </thead>
            <tbody>
                {
                    response && response.map((item)=>(
                        <tr key={item.itemId}>
                            <td>{response.indexOf(item)+1}</td>
                            <td>{item.itemId}</td>
                            <td>{item.description}</td>
                            <td>{item.itemType}</td>
                            <td>{item.itemMake}</td>
                            <td>{item.itemValue}</td>
                            <td  className={item.issueStatus==="YES"?'pass':'fail'}>{item.issueStatus}</td>
                            {/* <td>{loan.user}</td>
                            <td>{loan.item.length}</td> */}
                            {/* <td id={loan.loanId}><Button id="YES" variant="link" disabled={loan.status==="YES"?true:false} onClick={onApprove}>{loan.status==="YES"?'Approved':'Approve'}</Button></td>
                            <td id={loan.loanId}><Button id="NO" variant="link" disabled={loan.status==="NO"?true:false} onClick={onApprove}>{loan.status==="NO"?'Rejected':'Reject'}</Button></td> */}
                            <td id={item.itemId}><Button variant="link" onClick={onEdit}>Edit</Button></td>
                            <td id={item.itemId}><Button variant="link" onClick={onDelete}>Delete</Button></td>

                        </tr>
                    ))
                }
            </tbody>
            </Table>}
        </div>
    )
}

export default LoanManagement