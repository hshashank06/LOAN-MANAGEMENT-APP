import {Link} from 'react-router-dom'
import  Button  from 'react-bootstrap/Button'
import Table from 'react-bootstrap/Table'
import {useState, useEffect} from 'react'
import RegistrationForm from './registrationForm'
const CustomerManagement=()=>{
    const [register,setRegister]=useState(false)
    const [response,setResponse] = useState([])
    const handleClick=()=>{
        setRegister(!register)
        console.log(register)
    }


    useEffect(()=>{
        
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
    displayData()
       
        
    },[])
    const dummyData=[
        {
            "userId":1,
            "firstName":"Ahb",
            "lastName":"Bbsghwf",
            "age":21,
            "emailId":"1@gamil.com"
        },
        {
            "userId":2,
            "firstName":"HVhb",
            "lastName":"Hkkssghwf",
            "age":23,
            "emailId":"2@gamil.com"
        },
        {
            "userId":3,
            "firstName":"HJkzdAhb",
            "lastName":"BIbsghwf",
            "age":24,
            "emailId":"3@gamil.com"
        }
    ]
    return(
        <div>
            <Button variant="secondary" id="register-button" onClick={handleClick}>{register?'Show All Users': 'Register User'}</Button>
            <>
            {register && <RegistrationForm onRegister={()=>{}}/>}
            </>
            {!register && <Table striped bordered hover>
            <thead>
                <tr>
                <th>#</th>
                <th>Customer Id</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Age</th>
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
                            <td>{user.userEmail}</td>
                            <td><Button variant="link">Edit</Button></td>
                            <td><Button variant="link">Delete</Button></td>
                        </tr>
                    ))
                }
            </tbody>
            </Table>}
        </div>
    )
}

export default CustomerManagement