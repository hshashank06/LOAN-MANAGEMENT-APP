// import logo from './logo.svg';
import './App.css';
import RegistrationForm from './components/registrationForm';
import AdminDashboard from './components/adminDashboard';
import CustomerManagement from './components/customerManagement';
import LoanManagement from './components/loanManagement';
import Login from './components/login';
import {BrowserRouter as Router, Route, Routes, Link} from 'react-router-dom'
import Button from 'react-bootstrap/Button'
import 'bootstrap/dist/css/bootstrap.min.css'
import Userdashboard from './components/User_dashboard';
import Viewloan from './components/View_loan';
import LoanApplyForm from './components/LoanApplyForm';
import LoanNavBar from './components/navBar';

function App() {

  const registerUser=(user)=>{
    console.log(user)
  }

  return (
    <Router>
      <div className="App">
      <LoanNavBar/>
      
      <Routes>
        <Route path='/' element={
          <>
          <Login onLogin={registerUser}/>
          </>
        } />
        <Route path='/register' element={<RegistrationForm onRegister={registerUser}/>} />
        <Route path='/user-dashboard' element={<Userdashboard/>} />
        <Route path='/admin-dashboard' element={<AdminDashboard/>} />
        <Route path='/customer' element={<CustomerManagement/>}/>
       <Route path='/view-loan' element={<Viewloan/>}/>
      < Route path='/apply-loan' element={<LoanApplyForm/>}/>
        <Route path='/loan-management' element={<LoanManagement/>}/>
      </Routes> 
      
    </div>
    </Router>
    
  );
}

export default App;