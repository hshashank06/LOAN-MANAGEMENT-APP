import {Link} from 'react-router-dom'
import  Button  from 'react-bootstrap/Button'
import Table from 'react-bootstrap/Table'
const CustomerManagement=()=>{
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
            <Link to='/register'><Button variant="secondary" id="register-button">Register</Button></Link>
            <Table striped bordered hover>
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
                    dummyData && dummyData.map((user)=>(
                        <tr key={user.userId}>
                            <td>{dummyData.indexOf(user)+1}</td>
                            <td>{user.userId}</td>
                            <td>{user.firstName}</td>
                            <td>{user.lastName}</td>
                            <td>{user.age}</td>
                            <td>{user.emailId}</td>
                            <td><Button variant="link">Edit</Button></td>
                            <td><Button variant="link">Delete</Button></td>
                        </tr>
                    ))
                }
            </tbody>
            </Table>
        </div>
    )
}

export default CustomerManagement