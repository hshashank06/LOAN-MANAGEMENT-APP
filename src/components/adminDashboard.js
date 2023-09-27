import Button from 'react-bootstrap/Button'
import {Link} from 'react-router-dom'
import Card from 'react-bootstrap/Card'
const AdminDashboard=()=>{
    return(
        <div className='bg-page'>
        <div className='admin-dashboard'>
            <h1 className='sub-heading bg-heading'>Admin Dashboard</h1>
            <div className='admin-cards'>
            <Card style={{ width: '18rem' }} className="user-card">
            <Card.Body>
                <Card.Title>Manage Users</Card.Title>
                <Card.Text>
                View, register, edit and delete users.
                </Card.Text>
                <Link to="/customer"><Button className='dashboard-btn'>Customer Management</Button></Link>
            </Card.Body>
            </Card>

            <Card style={{ width: '18rem' }} className="user-card">
            <Card.Body>
                <Card.Title>Manage Loans</Card.Title>
                <Card.Text>
                View, approve, edit and delete all the loans applied by users.
                </Card.Text>
                <Link to="/loan-management"><Button className='dashboard-btn'>Loan Management</Button></Link>
            </Card.Body>
            </Card>

            <Card style={{ width: '18rem' }} className="user-card">
            <Card.Body>
                <Card.Title>Manage Items</Card.Title>
                <Card.Text>
                View, register, edit and delete all the items purchased.
                </Card.Text>
                <Link to="/item-management"><Button className='dashboard-btn'>Items Management</Button></Link>
            </Card.Body>
            </Card>
                
                
            </div>
            
        </div>
        </div>
    )
}

export default AdminDashboard