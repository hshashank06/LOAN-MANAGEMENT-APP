// import logo from './logo.svg';
import './App.css';
import RegistrationForm from './components/registrationForm';
import AdminDashboard from './components/adminDashboard';
import CustomerManagement from './components/customerManagement';
import Login from './components/login';
import {BrowserRouter as Router, Route, Routes, Link} from 'react-router-dom'
import Button from 'react-bootstrap/Button'
import 'bootstrap/dist/css/bootstrap.min.css'
import Userdashboard from './components/User_dashboard';

function App() {

  const registerUser=(user)=>{
    console.log(user)
  }

  return (
    <Router>
      <div className="App">
      <h1>LOAN APP</h1>
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
      </Routes> 
      
    </div>
    </Router>
    
  );
}

export default App;