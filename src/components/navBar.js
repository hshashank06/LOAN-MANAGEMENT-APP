import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import { useNavigate } from 'react-router-dom';

function LoanNavBar() {
  const navigate=useNavigate()
  return (
    <Navbar expand="lg" className="nav-bar navbar-dark">
      <Container>
        <Navbar.Brand href="/" className='nav-bar-brand'>Loan Application</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ms-auto">
            <Nav.Link href="/">Home</Nav.Link>
            <Nav.Link href="#" onClick={()=>{
              if(localStorage.getItem('adminId'))
              navigate('/admin-dashboard')
              else
              navigate('/user-dashboard')
            }}>DashBoard</Nav.Link>
            <Nav.Link href="#"  onClick={()=>{
              localStorage.setItem('userId','')
              localStorage.setItem('adminId','')
              navigate('/')}}>Logout</Nav.Link>
            {/* <NavDropdown title="Dropdown" id="basic-nav-dropdown">
              <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
              <NavDropdown.Item href="#action/3.2">
                Another action
              </NavDropdown.Item>
              <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="#action/3.4">
                Separated link
              </NavDropdown.Item>
            </NavDropdown> */}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default LoanNavBar;