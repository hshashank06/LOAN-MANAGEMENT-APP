// import logo from './logo.svg';
import './App.css';
import RegistrationForm from './components/registrationForm';
import Login from './components/login';
import {BrowserRouter as Router, Route, Routes, Link} from 'react-router-dom'
import Button from 'react-bootstrap/Button'
import 'bootstrap/dist/css/bootstrap.min.css'

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
          <Link to='/register'><Button variant="secondary">Register</Button></Link>
          </>
        } />
        <Route path='/register' element={<RegistrationForm onRegister={registerUser}/>} />
      </Routes> 
      
    </div>
    </Router>
    
  );
}

export default App;