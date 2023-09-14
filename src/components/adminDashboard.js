import Button from 'react-bootstrap/Button'
import {Link} from 'react-router-dom'
const AdminDashboard=()=>{
    return(
        <div className='admin-dashboard'>
            <h2>Admin Dashboard</h2>
            <div>
                <Link to="/customer"><Button className='dashboard-btn'>Customer Data Management</Button></Link>
                <Link to="/loan-management"><Button className='dashboard-btn'>Loan Card Management</Button></Link>
                <Link to="/"><Button className='dashboard-btn'>Items Master Data</Button></Link>
            </div>
            
        </div>
    )
}

export default AdminDashboard