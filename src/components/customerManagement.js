import {Link , useNavigate} from 'react-router-dom'
import  Button  from 'react-bootstrap/Button'
import Table from 'react-bootstrap/Table'
import {useState, useEffect} from 'react'
import RegistrationForm from './registrationForm'
const CustomerManagement=()=>{
    const [register,setRegister]=useState(false)
    const [response,setResponse] = useState([])
    const navigate=useNavigate()

    const handleClick=()=>{
        setRegister(!register)
        console.log(register)
    }

    const displayData=async()=>{
        try{
            const url="http://localhost:8082/loanapp/get/users"
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
    const onEdit =async(e)=>{
        const userId=e.target.parentNode.id
        navigate('/edit-user?id='+userId) 
    }
    const onDelete =async(e)=>{
        const userId=e.target.parentNode.id
        try{
            const res= await fetch('http://localhost:8082/loanapp/users/delete',{
                method:'POST',
                mode:'cors',
                body:JSON.stringify({
                    userId
                }),
                headers:{
                    'Content-Type':'application/json'
                }
            })
           

            const data = await res.text();
            console.log(data)
        }
        catch(e){

        }

       await displayData()
           

    }

    return(
        <div>
            <h2 className='sub-heading admin-heading'>Customer Management</h2>
            <Button variant="secondary" id="register-button" onClick={handleClick}>{register?'Show All Users': 'Register User'}</Button>
            <>
            {register && <RegistrationForm onRegister={displayData}/>}
            </>
            {!register && <Table striped bordered hover>
            <thead>
                <tr>
                <th>#</th>
                <th>Customer Id</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Age</th>
                <th>Date of Birth</th>
                <th>Email Id</th>
                </tr>
            </thead>
            <tbody>
                {
                    response && response.map((user)=>(
                        <tr key={user.userId}>
                            <td>{response.indexOf(user)+1}</td>
                            <td>{user.userId}</td>
                            <td>{user.firstName}</td>
                            <td>{user.lastName}</td>
                            <td>{user.userAge}</td>
                            <td>{user.userdob}</td>
                            <td>{user.userEmail}</td>
                            <td id={user.userId}><Button variant="link" onClick={onEdit}>Edit</Button></td>
                            <td id={user.userId}><Button variant="link" onClick={onDelete}>Delete</Button></td>
                        </tr>
                    ))
                }
            </tbody>
            </Table>}
        </div>
    )
}

export default CustomerManagement